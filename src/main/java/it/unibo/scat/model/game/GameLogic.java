package it.unibo.scat.model.game;

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
        return null;
    }

    /**
     * @param cr ...
     * @return ...
     * 
     */
    public int handleCollisionReport(final CollisionReport cr) {
        return 0;
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

        deleteShots();
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
    public void deleteShots() {

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
     * @param e ...
     * 
     */
    public void addEntity(final AbstractEntity e) {

    }

    /**
     * @param e ...
     * 
     */
    public void removeEntity(final AbstractEntity e) {

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
