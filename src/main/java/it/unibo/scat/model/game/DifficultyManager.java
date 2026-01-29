package it.unibo.scat.model.game;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ...
 */
public final class DifficultyManager {
    private static final int TEMPPPPPPPPPPPPPPP = 200;
    private final AtomicInteger level = new AtomicInteger(1);

    /**
     * @return ...
     * 
     */
    public int getInvadersStepMs() {
        return TEMPPPPPPPPPPPPPPP * 2;
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
