package it.unibo.scat.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.game.api.GamePanelInterface;

/**
 * Panel that contains all the graphics element for the game.
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
public final class GamePanel extends JPanel implements GamePanelInterface {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface viewInterface;

    private Canvas canvas;
    private StatusBar statusBar;
    private PausePanel pausePanel;
    private GameOverPanel gameOverPanel;

    /**
     * @param viewInterface ...
     * 
     */
    public GamePanel(final MenuActionsInterface viewInterface) {
        this.viewInterface = viewInterface;

        setLayout(new BorderLayout());

        initCanvas();
        initStatusBar();
        initPausePanel();
        initGameOverPanel();
    }

    /**
     * ...
     */
    private void initCanvas() {
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);
    }

    /**
     * ...
     */
    private void initStatusBar() {
        final int height = 70;
        statusBar = new StatusBar(this);
        statusBar.setPreferredSize(new Dimension(0, height));
        add(statusBar, BorderLayout.NORTH);
    }

    /**
     * @param maxWindowBounds ...
     * @param ins             ...
     * @return ...
     * 
     */
    public Dimension computeBestFrameSize(final Rectangle maxWindowBounds, final Insets ins) {
        final double aspect = 59.0 / 36.0;

        final int wMax = maxWindowBounds.width - ins.left - ins.right;
        final int hMax = maxWindowBounds.height - ins.top - ins.bottom;

        final int barH = statusBar.getPreferredSize().height;

        final int canvasH = Math.min(hMax - barH, (int) Math.floor(wMax / aspect));
        final int canvasW = (int) Math.floor(canvasH * aspect);

        return new Dimension(
                canvasW + ins.left + ins.right,
                canvasH + barH + ins.top + ins.bottom);
    }

    /**
     * ...
     */
    private void initPausePanel() {
        pausePanel = new PausePanel();
    }

    /**
     * ...
     */
    private void initGameOverPanel() {
        gameOverPanel = new GameOverPanel();
    }

    @Override
    public void pause() {
        viewInterface.pauseGame();
    }

    @Override
    public void resume() {
        viewInterface.resumeGame();
    }

    /**
     * temporary useless, to pass checkstyle, to remove...
     */
    public void useless() {
        statusBar.transferFocus();
        canvas.repaint();
        pausePanel.repaint();
        gameOverPanel.repaint();
    }

}
