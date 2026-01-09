package it.unibo.scat.model.api;

/**
 * Interface for the Model class, seen from the Controller.
 */
public interface ModelInterface {

    /**
     * @return ...
     *
     */
    int checkCollisions();

    /**
     * ...
     */
    void addPlayerShot();

    /**
     * ...
     */
    void initEverything();

    /**
     * ...
     */
    void update();

    /**
     * ...
     */
    void resetGame();

    /**
     * @param direction ...
     * @return ...
     *
     */
    int movePlayer(int direction);

    /**
     * ...
     */
    void endGame();

    /**
     * ...
     */
    void pause();

    /**
     * ...
     */
    void resume();
}
