package it.unibo.scat.model.game;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ...
 */
public final class DifficultyManager {
    private static final int TEMPPPPPPPPPPPPPPP = 200;

    private static final int MIN_INVADERS_STEP_MS = 500;
    private static final int STEP_SPEED_INCREMENT = 20;
    // i use it for knowing that the invaders don't get too much speed at a certain
    // point of time.
    private static final int MIN_STEP_LIMIT = 100;
    private final AtomicInteger level = new AtomicInteger(1);

    /**
     * Returns the coefficient used for level incrementation (for steps AND for
     * shots).
     * 
     * @param factor ...
     * @return ...
     */
    private int calculateIncrementLevel(final int factor) {
        return (level.get() - 1) * factor;
    }

    /**
     * Calculates and returns the new speed of invaders steps.
     * 
     * @return invaders speed.
     * 
     */
    public int getInvadersStepMs() {
        final int reduction = calculateIncrementLevel(STEP_SPEED_INCREMENT);
        final int currentStep = MIN_INVADERS_STEP_MS - reduction;

        return Math.max(MIN_STEP_LIMIT, currentStep);
    }

    /**
     * @return ...
     */
    public int getInvadersShootingCooldown() {
        return TEMPPPPPPPPPPPPPPP * 4;
    }

    /**
     * @param invadersCounter ...
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

    /**
     * ...
     */
    public void resetLevel() {
        level.set(1);
    }
}
