package it.unibo.scat.view.game.api;

import it.unibo.scat.common.GameState;

/**
 * ...
 */
public interface GamePanelInterface {
    /**
     * ...
     */
    void pause();

    /**
     * ...
     */
    void resume();

    /**
     * ...
     */
    void abortGame();

    /**
     * ...
     */
    void quit();

    /**
     * @return ...
     * 
     */
    int getScore();

    /**
     * @return ...
     * 
     */
    int getPlayerHealth();

    /**
     * @return ...
     * 
     */
    int getLevel();

    /**
     * @return ...
     * 
     */
    String getUsername();

    /**
     * ...
     */
    void restart();

    /**
     * ...
     */
    void showGameOver();

    /**
     * @return ...
     */
    GameState getGameState();
}
