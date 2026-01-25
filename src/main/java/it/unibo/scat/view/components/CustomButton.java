package it.unibo.scat.view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class for customizable button, used both from MenuPanel and GamePanel.
 */
public final class CustomButton extends JButton {
    private static final long serialVersionUID = 1L;
    private final Image image;

    /**
     * @param imagePath ...
     * @param d         ...
     * 
     */
    public CustomButton(final String imagePath, final Dimension d) {
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        image = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(imagePath))).getImage();

        setContentAreaFilled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g2.dispose();
    }
}
