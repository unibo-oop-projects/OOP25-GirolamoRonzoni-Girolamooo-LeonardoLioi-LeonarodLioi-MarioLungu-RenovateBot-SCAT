package it.unibo.scat.control.api;

import it.unibo.scat.common.Direction;

/**
 * Interface for the Control class.
 */
public interface ControlInterface {

    /**
     * @param username ...
     *
     */
    void notifySetUsername(String username);

    /**
     * ...
     */
    void notifyPlayerShot();

    /**
     * ...
     */
    void notifyQuitGame();

    /**
     * @param direction ...
     */
    void notifyPlayerMovement(Direction direction);

    /**
     * ...
     */
    void notifyPauseGame();

    /**
     * ...
     */
    void notifyResumeGame();

    /**
     * ...
     */
    void notifyResetGame();
}
