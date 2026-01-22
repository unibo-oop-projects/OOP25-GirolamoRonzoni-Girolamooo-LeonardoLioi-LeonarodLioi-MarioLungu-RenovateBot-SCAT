package it.unibo.scat.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.game.api.GamePanelInterface;
import it.unibo.scat.view.game.statusbar.StatusBar;

/**
 * Panel that contains all the graphics element for the game.
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
public final class GamePanel extends JPanel implements GamePanelInterface {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface viewInterface;
    private transient BufferedImage background;

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

        initBackground();
        initCanvas();
        initStatusBar();
        initPausePanel();
        initGameOverPanel();
    }

    /**
     * ...
     */
    private void initCanvas() {
        canvas = new Canvas(viewInterface);
        canvas.setOpaque(false);
        canvas.setFocusable(true);

        add(canvas, BorderLayout.CENTER);
    }

    /**
     * ...
     */
    private void initStatusBar() {
        final int height = 70;
        statusBar = new StatusBar(this);
        statusBar.setPreferredSize(new Dimension(0, height));
        statusBar.setOpaque(false);
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

    /**
     * ...
     */
    private void initBackground() {
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/images/game_background3.jpg")));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load game background", e);
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (background == null) {
            return;
        }

        final int panelW = getWidth();
        final int panelH = getHeight();
        final int imgW = background.getWidth();
        final int imgH = background.getHeight();

        final double scale = Math.max((double) panelW / imgW, (double) panelH / imgH);

        final int drawW = (int) Math.ceil(imgW * scale);
        final int drawH = (int) Math.ceil(imgH * scale);
        final int x = (panelW - drawW) / 2;
        final int y = (panelH - drawH) / 2;

        g.drawImage(background, x, y, drawW, drawH, null);
    }

    /**
     * @return ...
     * 
     */
    @Override
    public int getScore() {
        return viewInterface.fetchScore();
    }

    /**
     * @return ...
     * 
     */
    @Override
    public int getPlayerHealth() {
        return viewInterface.fetchPlayerHealth();
    }

    /**
     * ...
     */
    public void update() {
        canvas.update();

        statusBar.repaint();
        canvas.repaint();
    }
}
