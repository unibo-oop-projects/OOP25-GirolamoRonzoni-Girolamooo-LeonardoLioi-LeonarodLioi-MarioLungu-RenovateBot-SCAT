package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Player" entity.
 */
// @SuppressFBWarnings("SS_SHOULD_BE_STATIC")
public class Player extends AbstractEntity {
    private static final long PLAYER_SHOOTING_COOLDOWN = 500;
    private static long lastShotTime;
    private static final int DISTANCE_OF_MOVEMENT = 1;

    /**
     * @param type   ...
     * @param x      ...
     * @param y      ...
     * @param width  ...
     * @param height ...
     * @param health ...
     * 
     */
    public Player(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

    /**
     * ...
     */
    public void moveLeft() {
        setPosition(getPosition().getX() - DISTANCE_OF_MOVEMENT, getPosition().getY());
    }

    /**
     * ...
     */
    public void moveRight() {
        setPosition(getPosition().getX() + DISTANCE_OF_MOVEMENT, getPosition().getY());
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
        return PLAYER_SHOOTING_COOLDOWN;
    }

}
