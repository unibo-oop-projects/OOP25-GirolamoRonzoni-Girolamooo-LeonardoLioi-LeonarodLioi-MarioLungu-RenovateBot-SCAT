package it.unibo.scat.view.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel that contains all the graphics element for the menu.
 */
public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage background;

    /**
     * ...
     */
    public MenuPanel() {
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResource("/images/menu_background.jpg")));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load menu background", e);
        }

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        int panelW = getWidth();
        int panelH = getHeight();

        int imgW = background.getWidth();
        int imgH = background.getHeight();

        double scale = Math.max(
                (double) panelW / imgW,
                (double) panelH / imgH);

        int drawW = (int) Math.ceil(imgW * scale);
        int drawH = (int) Math.ceil(imgH * scale);

        int x = (panelW - drawW) / 2;
        int y = (panelH - drawH) / 2;

        g.drawImage(background, x, y, drawW, drawH, null);
    }

}
