package it.unibo.scat.model.api;

/**
 * Interface for the Model class, seen from the Controller.
 */
public interface ModelInterface {

    /**
     * ...
     */
    void addPlayerShot();

    /**
     * @param filename ...
     * 
     */
    void initEverything(String filename);

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
