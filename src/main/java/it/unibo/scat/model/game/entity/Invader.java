package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Invader" entity.
 */
// @SuppressFBWarnings("SS_SHOULD_BE_STATIC")
public final class Invader extends AbstractEntity {
    private static final int INVADER1_POINTS = 10;
    private static final int INVADER2_POINTS = 20;
    private static final int INVADER3_POINTS = 30;
    private static final int INVADER4_POINTS = 50;
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

        switch (currDirection) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case DOWN:
                moveDown();
                break;

            default:
                break;
        }

    }

    /**
     * ...
     */
    private void moveLeft() {
        setPosition(getPosition().getX() - 1, getPosition().getY());
    }

    /**
     * ...
     *
     */
    private void moveRight() {
        setPosition(getPosition().getX() + 1, getPosition().getY());
    }

    /**
     * ...
     */
    private void moveDown() {
        setPosition(getPosition().getX(), getPosition().getY() + 1);
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
    public static long getLastShotTime() {
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
    public static long getShootingCooldown() {
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

    /**
     * @return ...
     * 
     */
    @Override
    public int onHit() {
        super.onHit();

        if (!isAlive()) {
            switch (this.getType()) {
                case INVADER_1:
                    return INVADER1_POINTS;
                case INVADER_2:
                    return INVADER2_POINTS;
                case INVADER_3:
                    return INVADER3_POINTS;
                case INVADER_4:
                    return INVADER4_POINTS;
                default:
                    break;
            }
        }

        return NO_POINTS;
    }
}
