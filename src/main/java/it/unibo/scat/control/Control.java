package it.unibo.scat.control;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.view.api.ViewInterface;

/**
 * The main class for the "Control" section of the MVC pattern.
 */
@SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will be used by upcoming game logic")
public class Control implements ControlInterface {

    private ViewInterface viewInterface;
    private ModelInterface modelInterface;

    /**
     * ...
     */
    @Override
    public void notifyPauseGame() {

    }

    /**
     * ...
     */
    @Override
    public void notifyPlayerMovement() {

    }

    /**
     * ...
     */
    @Override
    public void notifyPlayerShot() {

    }

    /**
     * ...
     */
    @Override
    public void notifyQuitGame() {

    }

    /**
     * ...
     */
    @Override
    public void notifyResetGame() {

    }

    /**
     * ...
     */
    @Override
    public void notifyResumeGame() {

    }

    /**
     * @param username ...
     * 
     */
    @Override
    public void notifySetUsername(final String username) {

    }

}
