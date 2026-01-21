package it.unibo.scat.control.gameloop;

import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameState;
import it.unibo.scat.model.Model;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.view.api.ViewInterface;

/**
 * Main game loop: updates the model at a fixed tick rate and schedules the view
 * update.
 * The loop runs only when the game status is {@link GameState#RUNNING}.
 */
public final class GameLoop implements Runnable {

    private final ModelInterface model;
    private final ViewInterface view;
    private final long tickMillis;

    private final Object pauseLock = new Object();

    private volatile boolean running;

    /**
     * Creates a new game loop.
     *
     * @param model      the game model
     * @param view       the game view
     * @param tickMillis the tick duration in milliseconds
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public GameLoop(final ModelInterface model, final ViewInterface view, final long tickMillis) {
        this.model = model;
        this.view = view;
        this.tickMillis = tickMillis;
    }

    /**
     * Starts the loop (idempotent).
     */
    public void start() {
        this.running = true;
    }

    /**
     * Stops the loop permanently.
     */
    public void stop() {
        this.running = false;
        resumeGame();
    }

    /**
     * Resumes the loop if it was paused.
     */
    public void resumeGame() {
        synchronized (pauseLock) {
            Model.setGameState(GameState.PAUSE);
            pauseLock.notifyAll();
        }
    }

    @Override
    public void run() {
        while (running) {
            waitIfNotPlaying();

            if (!running) {
                break;
            }

            final long start = System.currentTimeMillis();

            model.update();

            SwingUtilities.invokeLater(view::update);

            final long elapsed = System.currentTimeMillis() - start;
            sleepUninterruptibly(Math.max(0L, tickMillis - elapsed));
        }
    }

    /**
     * ...
     */
    private void waitIfNotPlaying() {
        if (Model.getGameState() == GameState.RUNNING) {
            return;
        }

        synchronized (pauseLock) {
            while (running && Model.getGameState() != GameState.RUNNING) {

                try {
                    pauseLock.wait();
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

        }
    }

    /**
     * @param millis ...
     * 
     */
    private static void sleepUninterruptibly(final long millis) {
        if (millis <= 0L) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
