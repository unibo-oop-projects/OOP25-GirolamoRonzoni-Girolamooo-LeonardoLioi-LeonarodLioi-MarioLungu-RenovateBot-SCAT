package it.unibo.scat.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.game.api.GamePanelInterface;
import it.unibo.scat.view.game.canvas.Canvas;
import it.unibo.scat.view.game.statusbar.StatusBar;

/**
 * Panel that contains all the graphics element for the game.
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
public final class GamePanel extends JPanel implements GamePanelInterface {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface viewInterface;
    private transient List<BufferedImage> backgrounds;

    private Canvas canvas;
    private StatusBar statusBar;
    private PausePanel pausePanel;
    private GameOverPanel gameOverPanel;
    private int currentBackgroundIndex;
    private boolean repaint = true;

    /**
     * @param viewInterface ...
     * 
     */
    public GamePanel(final MenuActionsInterface viewInterface) {
        this.viewInterface = viewInterface;

        setLayout(new BorderLayout());

        initBackgrounds();
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
     * ...
     */
    private void initBackgrounds() {
        backgrounds = new ArrayList<>();

        try {
            for (int i = 0; i < UIConstants.GAME_BACKGROUNDS_PATHS.size(); i++) {
                final BufferedImage tmp = ImageIO.read(
                        Objects.requireNonNull(getClass().getResource(UIConstants.GAME_BACKGROUNDS_PATHS.get(i))));

                backgrounds.add(tmp);
            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load game background", e);
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final BufferedImage currentBg = backgrounds.get(currentBackgroundIndex);
        final int panelW = getWidth();
        final int panelH = getHeight();
        final int imgW = currentBg.getWidth();
        final int imgH = currentBg.getHeight();

        final double scale = Math.max((double) panelW / imgW, (double) panelH / imgH);

        final int drawW = (int) Math.ceil(imgW * scale);
        final int drawH = (int) Math.ceil(imgH * scale);
        final int x = (panelW - drawW) / 2;
        final int y = (panelH - drawH) / 2;

        g.drawImage(currentBg, x, y, drawW, drawH, null);
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
        repaint = false;

        if (shouldChangeBackground()) {
            updateBackground();
            repaint = true;
        }

        canvas.update();
        statusBar.repaint();
        canvas.repaint();
    }

    /**
     * ...
     */
    public void updateBackground() {
        currentBackgroundIndex = viewInterface.getLevel() - 1;
        if (currentBackgroundIndex >= backgrounds.size()) {
            currentBackgroundIndex %= backgrounds.size();
        }
    }

    /**
     * @return ...
     * 
     */
    private boolean shouldChangeBackground() {
        int bgIndex = viewInterface.getLevel() - 1;
        if (bgIndex >= backgrounds.size()) {
            bgIndex %= backgrounds.size();
        }

        return bgIndex != currentBackgroundIndex;
    }
}
