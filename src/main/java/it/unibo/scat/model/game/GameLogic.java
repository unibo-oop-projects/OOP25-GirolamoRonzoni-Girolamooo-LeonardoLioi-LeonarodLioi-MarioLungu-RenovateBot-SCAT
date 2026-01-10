package it.unibo.scat.model.game;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.game.entity.AbstractEntity;

/**
 * ...
 */
@SuppressFBWarnings({ "UUF_UNUSED_FIELD", "URF_UNREAD_FIELD", "EI2" })
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
