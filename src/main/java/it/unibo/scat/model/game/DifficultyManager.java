package it.unibo.scat.model.game;

/**
 * ...
 */
public final class DifficultyManager {

    /**
     * @param level ...
     * @return ...
     * 
     */
    public int getInvadersStepMs(final int level) {
        return 300;
    }

    /**
     * @param level ...
     * @return ...
     * 
     */
    public int getInvadersShootingCooldown(final int level) {
        return 300;
    }

    /**
     * @param level           ...
     * @param invadersCounter ...
     * @return ...
     * 
     */
    public int getMaxInvadersShots(final int level, final int invadersCounter) {
        return 3;
    }
}
