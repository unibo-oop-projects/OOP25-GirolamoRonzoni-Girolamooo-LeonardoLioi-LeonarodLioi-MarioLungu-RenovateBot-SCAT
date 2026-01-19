package it.unibo.scat.view.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * ...
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")

public final class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private transient BufferedImage background;

    /**
     * ...
     */
    public Canvas() {

        initBackground();
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
}
