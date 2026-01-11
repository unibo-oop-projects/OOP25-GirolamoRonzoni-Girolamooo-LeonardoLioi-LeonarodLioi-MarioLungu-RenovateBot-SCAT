package it.unibo.scat.model.game;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.game.entity.AbstractEntity;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Shot;

/**
 * ...
 */
@SuppressFBWarnings({ "UUF_UNUSED_FIELD", "EI2" })
public class GameLogic {
    private final GameWorld gameWorld;
    private int lastInvaderShotTime;
    private int invaderShootCooldown;

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
     *
     */
    public void handleCollisionReport(final CollisionReport cr) {

    }

    /**
     * ...
     */
    public void addPlayerShot() {

    }

    /**
     * ...
     */
    public void resetEntities() {

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
     * ...
     */
    public void generateInvaderShot() {

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
}
