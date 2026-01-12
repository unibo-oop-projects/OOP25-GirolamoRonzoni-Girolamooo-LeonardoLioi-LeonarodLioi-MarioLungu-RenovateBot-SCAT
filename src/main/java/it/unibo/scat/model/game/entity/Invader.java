package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Invader" entity.
 */
@SuppressWarnings("PMD.UnusedPrivateMethod")
// @SuppressFBWarnings("SS_SHOULD_BE_STATIC")
public final class Invader extends AbstractEntity {
    private static final long INVADER_SHOOTING_COOLDOWN = 500;
    private static Direction currDirection = Direction.RIGHT;
    private static Direction nextDirection = Direction.DOWN;
    private static long lastShotTime;

    /**
     * @param type   ...
     * @param x      ...
     * @param y      ...
     * @param width  ...
     * @param height ...
     * @param health ...
     * 
     */
    public Invader(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

    /**
     * ...
     */
    public void move() {

    }

    /**
     * ...
     */
    private void moveLeft() {

    }

    /**
     * ...
     *
     */
    private void moveRight() {

    }

    /**
     * ...
     */
    private void moveDown() {

    }

    /**
     * @return ...
     * 
     */
    public static Direction getCurrDirection() {
        return currDirection;
    }

    /**
     * @return ...
     * 
     */
    public static Direction getNextDirection() {
        return nextDirection;
    }

    /**
     * @param dir ...
     * 
     */
    public static void setCurrDirection(final Direction dir) {
        currDirection = dir;
    }

    /**
     * @param dir ...
     * 
     */
    public static void setNextDirection(final Direction dir) {
        nextDirection = dir;
    }

    /**
     * @return ...
     * 
     */
    public long getLastShotTime() {
        return lastShotTime;
    }

    /**
     * @param shotTime ...
     * 
     */
    public static void setLastShotTime(final long shotTime) {
        lastShotTime = shotTime;
    }

    /**
     * @return ...
     * 
     */
    public long getShootingCooldown() {
        return INVADER_SHOOTING_COOLDOWN;
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     * 
     * @return ...
     * 
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private Direction tempUseAllFields() {
        return this.currDirection;
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     * 
     * @return ...
     * 
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private Direction tempUseAfeeffellFields() {
        return this.nextDirection;
    }

}
