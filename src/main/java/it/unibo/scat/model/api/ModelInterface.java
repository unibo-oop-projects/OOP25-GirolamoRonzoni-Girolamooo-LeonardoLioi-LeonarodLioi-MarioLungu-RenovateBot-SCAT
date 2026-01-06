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
    void generateInvaderShot();

    /**
     * @param points ...
     * 
     */
    void updateScore(int points);

    /**
     * ...
     */
    void resetGame();

    /**
     * ...
     */
    void deleteShotsAndLoots();

    /**
     * @param direction ...
     * @return ...
     * 
     */
    int movePlayer(int direction);

    /**
     * @return ...
     * 
     */
    int checkGameEnd();

    /**
     * ...
     */
    void increaseLevel();

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
