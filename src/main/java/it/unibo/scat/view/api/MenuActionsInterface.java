package it.unibo.scat.view.api;

import java.util.List;

import javax.swing.JFrame;

import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;

/**
 * Interface for the View class, used by MenuPanel, GamePanel.
 */
public interface MenuActionsInterface {

    /**
     * @return ...
     *
     */
    JFrame getFrame();

    /**
     * ...
     */
    void pauseGame();

    /**
     * ...
     */
    void resumeGame();

    /**
     * ...
     */
    void resetGame();

    /**
     * ...
     */
    void quitGame();

    /**
     * @param username ...
     *
     */
    void setUsername(String username);

    /**
     * @return ...
     *
     */
    List<EntityView> fetchEntitiesFromModel();

    /**
     * @return ...
     *
     */
    List<GameRecord> fetchLeaderboard();

    /**
     * @return ...
     *
     */
    String fetchUsername();

    /**
     * ...
     */
    void showGamePanel();

    /**
     * ...
     */
    void showMenuPanel();

    /**
     * @return ...
     *
     */
    int fetchScore();

    /**
     * @return ...
     * 
     */
    int fetchPlayerHealth();
}
