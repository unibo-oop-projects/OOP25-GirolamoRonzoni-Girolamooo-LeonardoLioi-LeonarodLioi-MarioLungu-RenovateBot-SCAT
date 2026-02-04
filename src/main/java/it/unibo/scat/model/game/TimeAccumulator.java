package it.unibo.scat.model.game;

import java.util.concurrent.atomic.AtomicInteger;

import it.unibo.scat.common.Constants;

/**
 * ...
 */
public final class TimeAccumulator {
    private final DifficultyManager difficultyManager;
    private final AtomicInteger invadersAccMs = new AtomicInteger(0);
    private final AtomicInteger bonusInvaderAccMs = new AtomicInteger(0);
    private final AtomicInteger shotsAccMs = new AtomicInteger(0);

    /**
     * @param difficultyManager ...
     * 
     */
    public TimeAccumulator(final DifficultyManager difficultyManager) {
        this.difficultyManager = difficultyManager;
    }

    /**
     * ...
     */
    private void consumeAccumulators() {
        if (invadersAccMs.get() >= difficultyManager.getInvadersStepMs()) {
            invadersAccMs.set(invadersAccMs.get() - difficultyManager.getInvadersStepMs());
        }

        if (bonusInvaderAccMs.get() >= Constants.BONUSINVADER_STEP_MS) {
            bonusInvaderAccMs.set(bonusInvaderAccMs.get() - Constants.BONUSINVADER_STEP_MS);
        }

        if (shotsAccMs.get() >= Constants.SHOT_STEP_MS) {
            shotsAccMs.set(shotsAccMs.get() - Constants.SHOT_STEP_MS);
        }
    }

    /**
     * ...
     */
    public void incrementTimeAccumulators() {
        consumeAccumulators();

        invadersAccMs.set(invadersAccMs.get() + Constants.GAME_STEP_MS);
        bonusInvaderAccMs.set(bonusInvaderAccMs.get() + Constants.GAME_STEP_MS);
        shotsAccMs.set(shotsAccMs.get() + Constants.GAME_STEP_MS);
    }

    /**
     * @return ...
     * 
     */
    public int getInvadersAccMs() {
        return invadersAccMs.get();
    }

    /**
     * @return ...
     * 
     */
    public int getBonusInvaderAccMs() {
        return bonusInvaderAccMs.get();
    }

    /**
     * @return ...
     * 
     */
    public int getShotsAccMs() {
        return shotsAccMs.get();
    }

}
