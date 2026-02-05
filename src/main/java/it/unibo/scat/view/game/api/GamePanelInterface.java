package it.unibo.scat.view.game.api;

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
}
