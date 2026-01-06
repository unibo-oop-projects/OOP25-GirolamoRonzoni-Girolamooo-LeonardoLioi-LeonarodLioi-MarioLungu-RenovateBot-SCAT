package it.unibo.scat.control.api;

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
     * ...
     */
    void notifyPlayerMovement();

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
