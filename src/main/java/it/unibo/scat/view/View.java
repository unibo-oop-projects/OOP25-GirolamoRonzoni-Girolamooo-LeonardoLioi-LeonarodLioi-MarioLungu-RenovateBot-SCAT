package it.unibo.scat.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.model.api.ModelObservable;
import it.unibo.scat.util.AudioManager;
import it.unibo.scat.util.AudioTrack;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.api.ViewInterface;
import it.unibo.scat.view.game.GameKL;
import it.unibo.scat.view.game.GamePanel;
import it.unibo.scat.view.menu.MenuPanel;

/**
 * The main class for the "View" section of the MVC pattern.
 */
// @SuppressFBWarnings({ "UUF_UNUSED_FIELD", "URF_UNREAD_FIELD" })
@SuppressFBWarnings("UUF_UNUSED_FIELD")
// @SuppressWarnings("PMD.SingularField")
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
    private AudioManager backgroundSound;

    @Override
    public void initEverything() {
        backgroundSound = new AudioManager();

        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new GameKL(controlInterface));

        initFrame();
        showMenuPanel();
    }

    /**
     * ...
     */
    private void initFrame() {
        frame = new JFrame();
        frame.setTitle("SCAT🚀👾");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.getContentPane().setLayout(new CardLayout());
        frame.getContentPane().add(gamePanel, "GAME");

        frame.pack();
        final java.awt.Insets ins = frame.getInsets();

        final Dimension best = gamePanel.computeBestFrameSize(bounds, ins);
        frame.setSize(best);

        frame.setLocation(
                bounds.x + (bounds.width - best.width) / 2,
                bounds.y + (bounds.height - best.height) / 2);

        frame.setVisible(true);
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
        this.frame.dispose();
    }

    @Override
    public List<EntityView> fetchEntitiesFromModel() {
        return modelObservable.getEntities();
    }

    @Override
    public List<GameRecord> fetchLeaderboard() {
        return modelObservable.getLeaderboard();
    }

    @Override
    public int fetchScore() {
        return modelObservable.getScore();
    }

    @Override
    public String fetchUsername() {
        return modelObservable.getUsername();
    }

    @SuppressFBWarnings("EI_EXPOSE_REP")
    @Override
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public void pauseGame() {
        controlInterface.notifyPauseGame();
    }

    @Override
    public void quitGame() {
        controlInterface.notifyQuitGame();
    }

    @Override
    public void resetGame() {
        controlInterface.notifyResetGame();
    }

    @Override
    public void resumeGame() {
        controlInterface.notifyResumeGame();
    }

    @Override
    public void setUsername(final String username) {
        controlInterface.notifySetUsername(username);
    }

    @Override
    public int fetchPlayerHealth() {
        return modelObservable.getPlayerHealth();
    }

    @Override
    public void showGamePanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);

        backgroundSound.stop();
    }

    @Override
    public void showMenuPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        backgroundSound.play(AudioTrack.SOUND_TRACK, true);
    }

    @Override
    public void update() {
        gamePanel.update();
    }

    @Override
    public void startGame() {
        controlInterface.notifyStartGame();
    }

    @Override
    public ControlInterface getControlInterface() {
        return controlInterface;
    }
}
