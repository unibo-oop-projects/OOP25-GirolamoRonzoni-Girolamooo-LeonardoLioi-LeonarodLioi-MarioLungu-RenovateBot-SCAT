package it.unibo.scat.control;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.GameState;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.control.gameloop.GameLoop;
import it.unibo.scat.model.Model;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.view.api.ViewInterface;

/**
 * The main class for the "Control" section of the MVC pattern.
 */
// @SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will
// be used by upcoming game logic")
// @SuppressFBWarnings({ "EI2", "URF_UNREAD_FIELD" })
@SuppressFBWarnings("EI2")
public class Control implements ControlInterface {
    private final ViewInterface viewInterface;
    private final ModelInterface modelInterface;
    private final GameLoop gameLoop;
    private final Thread gameThread;

    /**
     * @param vInterface ...
     * @param mInterface ...
     * 
     */
    public Control(final ViewInterface vInterface, final ModelInterface mInterface) {
        this.viewInterface = vInterface;
        this.modelInterface = mInterface;

        final int tickMillis = 400;
        gameLoop = new GameLoop(modelInterface, viewInterface, tickMillis);
        gameThread = new Thread(gameLoop, "game-loop");
    }

    /**
     * ...
     */
    public void init() {
        modelInterface.initEverything("data/entities.txt", "data/leaderboard.txt");
        viewInterface.initEverything();
    }

    /**
     * Game thread starting.
     */
    @Override
    public void notifyStartGame() {
        Model.setGameState(GameState.RUNNING);
        gameLoop.start();
        gameThread.start();
    }

    /**
     * ...
     */
    @Override
    public void notifyPauseGame() {
        Model.setGameState(GameState.PAUSE);
    }

    /**
     * @param direction ...
     * 
     */
    @Override
    public void notifyPlayerMovement(final Direction direction) {
        modelInterface.movePlayer(direction);
    }

    /**
     * ...
     */
    @Override
    public void notifyPlayerShot() {
        modelInterface.addPlayerShot();
    }

    @SuppressFBWarnings(value = "DM_EXIT", justification = "Application termination is intended")
    @Override
    public final void notifyQuitGame() {
        viewInterface.closeFrame();
        System.exit(0);
    }

    /**
     * ...
     */
    @Override
    public void notifyResetGame() {
        modelInterface.resetGame();
    }

    /**
     * ...
     */
    @Override
    public void notifyResumeGame() {
        Model.setGameState(GameState.RUNNING);
        gameLoop.resumeGame();
    }

    /**
     * @param username ...
     * 
     */
    @Override
    public void notifySetUsername(final String username) {
        modelInterface.setUsername(username);
    }

}
