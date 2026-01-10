package it.unibo.scat.control;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.control.api.ControlInterface;
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

    /**
     * @param vInterface ...
     * @param mInterface ...
     * 
     */
    public Control(final ViewInterface vInterface, final ModelInterface mInterface) {
        this.viewInterface = vInterface;
        this.modelInterface = mInterface;
    }

    /**
     * ...
     */
    public void start() {
        modelInterface.initEverything("data/entities.txt", "data/leaderboard.txt");
        viewInterface.initEverything();
    }

    @Override
    public void notifyPauseGame() {

    }

    @Override
    public void notifyPlayerMovement() {

    }

    @Override
    public void notifyPlayerShot() {

    }

    @Override
    public void notifyQuitGame() {

    }

    @Override
    public void notifyResetGame() {

    }

    @Override
    public void notifyResumeGame() {

    }

    @Override
    public void notifySetUsername(final String username) {

    }

}
