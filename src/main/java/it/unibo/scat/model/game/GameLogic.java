package it.unibo.scat.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.game.entity.AbstractEntity;
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
            for (final AbstractEntity entity : gameWorld.getEntities()) {
                final boolean isSameEntity = entity.equals(shot);
                final boolean isCollision = areColliding(shot, entity);
                final boolean isUselessCollision = isPlayerShot(shot) && entity instanceof Player
                        || isInvaderShot(shot) && entity instanceof Invader;

                if (isSameEntity || !isCollision || isUselessCollision) {
                    continue;
                }
                entitiesThatGotShot.add(shot);
                entitiesThatGotShot.add(entity);
            }
        }
        return new CollisionReport(entitiesThatGotShot);
    }

    private boolean isPlayerShot(final Shot shot) {
        return shot.getDirection() == Direction.UP;
    }

    private boolean isInvaderShot(final Shot shot) {
        return shot.getDirection() == Direction.DOWN;
    }

    private boolean areColliding(final AbstractEntity shot, final AbstractEntity e) {
        return checkX(shot, e) && checkY(shot, e);
    }

    private boolean checkX(final AbstractEntity shot, final AbstractEntity e) {
        return shot.getPosition().getX() < e.getPosition().getX() + e.getWidth()
                && e.getPosition().getX() < shot.getPosition().getX() + shot.getWidth();
    }

    private boolean checkY(final AbstractEntity shot, final AbstractEntity e) {
        return shot.getPosition().getY() < e.getHeight() + e.getPosition().getY()
                && e.getPosition().getY() < shot.getHeight() + shot.getPosition().getY();
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
    public void updateLastPlayerShotTime() {
        Player.setLastShotTime(System.currentTimeMillis());
    }

    /**
     * ...
     */
    public void removeDeadShots() {
        final List<Shot> toRemove = new ArrayList<>();

        for (final Shot shot : gameWorld.getShots()) {
            if (!shot.isAlive()) {
                toRemove.add(shot);
            }
        }

        for (final Shot shot : toRemove) {
            gameWorld.removeEntity(shot);
        }
    }

    /**
     * @return ...
     * 
     */
    public boolean canPlayerShoot() {
        final long actualTime = System.currentTimeMillis();

        return actualTime - Player.getLastShotTime() >= Player.getShootingCooldown();
    }

    /**
     * @param shot ...
     * @return ...
     * 
     */
    public boolean didShotHitBorder(final Shot shot) {
        final double y = shot.getPosition().getY();
        final double height = shot.getHeight();

        return y <= 0 || y + height >= gameWorld.getWorldHeight();
    }
}
