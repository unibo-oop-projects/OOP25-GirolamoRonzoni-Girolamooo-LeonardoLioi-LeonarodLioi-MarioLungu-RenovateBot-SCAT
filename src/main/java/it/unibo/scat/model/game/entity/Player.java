package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Player" entity.
 */
// @SuppressFBWarnings("SS_SHOULD_BE_STATIC")
public class Player extends AbstractEntity {
    private static final long PLAYER_SHOOTING_COOLDOWN = 500;
    private long lastPlayerShotTime;

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

    }

    /**
     * ...
     */
    public void moveRight() {

    }

    /**
     * @return ...
     * 
     */
    public long getLastPlayerShotTime() {
        return lastPlayerShotTime;
    }

    /**
     * @param lastPlayerShotTime ...
     * 
     */
    public void setLastPlayerShotTime(final long lastPlayerShotTime) {
        this.lastPlayerShotTime = lastPlayerShotTime;
    }

    /**
     * @return ...
     * 
     */
    public long getPlayerShootingCooldown() {
        return PLAYER_SHOOTING_COOLDOWN;
    }

}
