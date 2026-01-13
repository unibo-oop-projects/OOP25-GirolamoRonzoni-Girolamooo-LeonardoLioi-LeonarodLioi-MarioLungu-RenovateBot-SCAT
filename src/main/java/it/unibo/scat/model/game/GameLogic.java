package it.unibo.scat.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.game.entity.AbstractEntity;
import it.unibo.scat.model.game.entity.Bunker;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Player;
import it.unibo.scat.model.game.entity.Shot;

/**
 * ...
 */
@SuppressFBWarnings({ "EI2", "DMI_RANDOM_USED_ONLY_ONCE" })
public class GameLogic {
    private final GameWorld gameWorld;

    /**
     * @param gWorld ...
     * 
     */
    public GameLogic(final GameWorld gWorld) {
        this.gameWorld = gWorld;
    }

    /**
     * @return ...
     *
     */
    public CollisionReport checkCollisions() {
        final List<AbstractEntity> entitiesThatGotShot = new ArrayList<>();
        final List<Shot> shotList = gameWorld.getShots();

        for (final Shot shot : shotList) {
            for (final AbstractEntity gotShot : gameWorld.getEntities()) {

                if (!shot.equals(gotShot)) {

                    boolean ok = false;
                    if (isCollision(shot, gotShot)) {

                        if (shot.getDirection() == Direction.UP && gotShot instanceof Invader) {
                            ok = true;
                        }

                        if (gotShot instanceof Bunker) {
                            ok = true;
                        }

                        if (shot.getDirection() == Direction.DOWN && gotShot instanceof Player) {
                            ok = true;
                        }

                        if (gotShot instanceof Shot && ((Shot) gotShot).getDirection() != shot.getDirection()) {
                            ok = true;
                        }

                        if (ok) {
                            entitiesThatGotShot.add(shot);
                            entitiesThatGotShot.add(gotShot);
                            break;
                        }
                    }
                }
            }
        }
        return new CollisionReport(entitiesThatGotShot);
    }

    private boolean isCollision(final AbstractEntity e1, final AbstractEntity e2) {
        return checkX(e1, e2) && checkY(e1, e2);
    }

    private boolean checkX(final AbstractEntity e1, final AbstractEntity e2) {
        return e1.getPosition().getX() < e2.getPosition().getX() + e2.getWidth()
                && e2.getPosition().getX() < e1.getPosition().getX() + e1.getWidth();
    }

    private boolean checkY(final AbstractEntity e1, final AbstractEntity e2) {
        return e1.getPosition().getY() < e2.getHeight() + e2.getPosition().getY()
                && e2.getPosition().getY() < e1.getHeight() + e1.getPosition().getY();
    }

    /**
     * @param cr ...
     * @return ...
     * 
     */
    public int handleCollisionReport(final CollisionReport cr) {
        int points = 0;

        for (final AbstractEntity entity : cr.getEntities()) {
            points += entity.onHit();
        }
        return points;
    }

    /**
     * ...
     */
    public void addPlayerShot() {
        if (!canPlayerShoot()) {
            return;
        }

        final Player player = gameWorld.getPlayer();

        final int shotWidth = 1;
        final int shotHeight = 2;
        final int shotHealth = 1;
        final int shotX = player.getPosition().getX() + (player.getWidth() / 2);
        final int shotY = player.getPosition().getY() - shotHeight;

        final Shot newShot = new Shot(EntityType.SHOT, shotX, shotY, shotWidth, shotHeight, shotHealth, Direction.UP);

        gameWorld.addEntity(newShot);

    }

    /**
     * ...
     */
    public void resetEntities() {

        removeAllShots();
        gameWorld.getEntities().forEach(x -> {
            x.reset();
        });
    }

    /**
     * @return ...
     *
     */
    public GameResult checkGameEnd() {
        return null;
    }

    /**
     * ...
     */
    public void removeAllShots() {

        gameWorld.getEntities().forEach(x -> {

            if (x instanceof Shot) {
                gameWorld.getEntities().remove(x);
            }

        });
        gameWorld.getShots().clear();

    }

    /**
     * ...
     */
    public void moveEntities() {
        for (final Invader invader : gameWorld.getInvaders()) {
            invader.move();
        }

        for (final Shot shot : gameWorld.getShots()) {
            shot.move();
        }
    }

    /**
     * @return ...
     * 
     */
    public boolean canInvadersShoot() {
        final long currTime = System.currentTimeMillis();
        return (currTime - Invader.getLastShotTime()) >= Invader.getShootingCooldown();
    }

    /**
     * ...
     */
    public void generateInvaderShot() {
        final Invader invader = getRandomInvader();

        final Shot newShot = new Shot(EntityType.SHOT, invader.getPosition().getX(), invader.getPosition().getY() + 2,
                1, 2, 1, Direction.DOWN);

        gameWorld.addEntity(newShot);
    }

    /**
     * @return ...
     * 
     */
    private Invader getRandomInvader() {
        final List<Invader> aliveInvaders = gameWorld.getInvaders().stream()
                .filter(Invader::isAlive)
                .toList();

        if (aliveInvaders.isEmpty()) {
            return null;
        }

        return aliveInvaders.get(new Random().nextInt(aliveInvaders.size()));
    }

    /**
     * ...
     */
    public void updateLastInvadersShotTime() {
        Invader.setLastShotTime(System.currentTimeMillis());
    }

    /**
     * ...
     */
    public void removeDeadShots() {

    }

    /**
     * @return ...
     * 
     */
    public boolean canPlayerShoot() {
        final long actualTime = System.currentTimeMillis();

        return actualTime - Player.getLastShotTime() >= Player.getShootingCooldown();
    }
}
