package it.unibo.scat.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.model.api.ModelObservable;
import it.unibo.scat.model.leaderboard.Leaderboard;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.api.ViewInterface;
import it.unibo.scat.view.game.GamePanel;
import it.unibo.scat.view.menu.MenuPanel;

/**
 * The main class for the "View" section of the MVC pattern.
 */
@SuppressFBWarnings({ "UUF_UNUSED_FIELD", "URF_UNREAD_FIELD" })
public class View implements ViewInterface, MenuActionsInterface {
    private ControlInterface controlInterface;
    private ModelObservable modelObservable;
    private JFrame frame;
    private Dimension screenDim;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    /**
     * @param mObservable ...
     * 
     */
    public void setModelObservable(final ModelObservable mObservable) {
        this.modelObservable = mObservable;
    }

    /**
     * @param cInterface ...
     * 
     */
    public void setControlInterface(final ControlInterface cInterface) {
        this.controlInterface = cInterface;
    }

    /**
     * ...
     */
    @Override
    public void closeFrame() {

    }

    /**
     * ...
     */
    @Override
    public void initEverything() {

    }

    /**
     * ...
     */
    @Override
    public List<EntityView> fetchEntitiesFromModel() {
        return new ArrayList<>();
    }

    /**
     * @return ...
     * 
     */
    @Override
    public Leaderboard fetchLeaderboard() {
        return null;
    }

    /**
     * @return ...
     * 
     */
    @Override
    public int fetchScore() {
        return 0;
    }

    /**
     * @return ...
     * 
     */
    @Override
    public String fetchUsername() {
        return null;
    }

    /**
     * @return ...
     * 
     */
    @Override
    public JFrame getFrame() {
        return null;
    }

    /**
     * ...
     */
    @Override
    public void hideGamePanel() {

    }

    /**
     * ...
     */
    @Override
    public void hideMenuPanel() {

    }

    /**
     * ...
     */
    @Override
    public void pauseGame() {

    }

    /**
     * ...
     */
    @Override
    public void quitGame() {

    }

    /**
     * ...
     */
    @Override
    public void resetGame() {

    }

    /**
     * ...
     */
    @Override
    public void resumeGame() {

    }

    /**
     * @param username ...
     * 
     */
    @Override
    public void setUsername(final String username) {

    }

    /**
     * ...
     */
    @Override
    public void showGamePanel() {

    }

    /**
     * ...
     */
    @Override
    public void showMenuPanel() {

    }

}
