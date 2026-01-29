package it.unibo.scat.model.game;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ...
 */
public final class DifficultyManager {
    private final AtomicInteger level = new AtomicInteger(1);

    /**
     * @return ...
     * 
     */
    public int getInvadersStepMs() {
        return 300;
    }

    /**
     * @return ...
     * 
     */
    public int getInvadersShootingCooldown() {
        return 300;
    }

    /**
     * @param level ...
     * @return ...
     * 
     */
    public int getMaxInvadersShots(final int invadersCounter) {
        return 3;
    }

    /**
     * @return ...
     * 
     */
    public int getLevel() {
        return level.get();
    }

    /**
     * ...
     */
    public void incrementLevel() {
        level.incrementAndGet();
    }
}
