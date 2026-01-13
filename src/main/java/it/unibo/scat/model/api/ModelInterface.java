package it.unibo.scat.model.api;

import it.unibo.scat.common.Direction;

/**
 * Interface for the Model class, seen from the Controller.
 */
public interface ModelInterface {

    /**
     * ...
     */
    void addPlayerShot();

    /**
     * @param entitiesFile    ...
     * @param leaderboardFile ...
     * 
     */
    void initEverything(String entitiesFile, String leaderboardFile);

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
     * 
     */
    void movePlayer(Direction direction);

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
