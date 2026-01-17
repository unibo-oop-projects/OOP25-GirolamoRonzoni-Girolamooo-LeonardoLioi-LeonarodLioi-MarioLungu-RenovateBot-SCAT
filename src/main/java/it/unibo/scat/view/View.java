package it.unibo.scat.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
@SuppressWarnings("PMD.SingularField")
public final class View implements ViewInterface, MenuActionsInterface {
    // private final Dimension frameDim = new
    // Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 10 * 10,
    // Toolkit.getDefaultToolkit().getScreenSize().height / 10 * 10);
    private final Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds();
    private ControlInterface controlInterface;
    private ModelObservable modelObservable;
    private JFrame frame;
    private Dimension screenDim;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    @Override
    public void initEverything() {
        initFrame();
        initGamePanel();
        initMenuPanel();

        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
    }

    /**
     * ...
     */
    private void initFrame() {
        frame = new JFrame();
        // frame.setUndecorated(true); // ... da mettere in seguito maybe
        frame.setTitle("SCAT 🚀👾");
        frame.setBounds(bounds);
        // frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),
        // BoxLayout.Y_AXIS));
        frame.getContentPane().setLayout(new CardLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
    }

    /**
     * ...
     */
    private void initMenuPanel() {
        menuPanel = new MenuPanel();
        menuPanel.setBackground(Color.CYAN);
    }

    /**
     * ...
     */
    private void initGamePanel() {

    }

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

    @Override
    public void closeFrame() {

    }

    @Override
    public List<EntityView> fetchEntitiesFromModel() {
        return new ArrayList<>();
    }

    @Override
    public Leaderboard fetchLeaderboard() {
        return null;
    }

    @Override
    public int fetchScore() {
        return 0;
    }

    @Override
    public String fetchUsername() {
        return null;
    }

    @Override
    public JFrame getFrame() {
        return null;
    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void quitGame() {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void setUsername(final String username) {

    }

    @Override
    public void showGamePanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void showMenuPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

}
