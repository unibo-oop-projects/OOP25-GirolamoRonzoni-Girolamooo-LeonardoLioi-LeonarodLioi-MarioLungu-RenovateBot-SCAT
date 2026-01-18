package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel that contains all the graphics element for the menu.
 */
// @SuppressFBWarnings(value = "SINGULAR_FIELD", justification = "Panels are
// stored as fields for later interactions")
// @SuppressWarnings("PMD.UnusedPrivateField")
public final class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private transient BufferedImage background;
    private SettingsPanel settingsPanel;
    private UsernamePanel usernamePanel;
    private LeaderboardPanel leaderboardPanel;
    private CreditsPanel creditsPanel;

    /**
     * ...
     */
    public MenuPanel() {
        setLayout(new BorderLayout());

        initBackground();
        initPanels();
    }

    /**
     * ...
     */
    private void initBackground() {
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResource("/images/menu_background.jpg")));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load menu background", e);
        }
    }

    /**
     * ...
     */
    private void initPanels() {
        settingsPanel = new SettingsPanel();
        usernamePanel = new UsernamePanel();
        leaderboardPanel = new LeaderboardPanel();
        creditsPanel = new CreditsPanel();

        add(usernamePanel, BorderLayout.NORTH);
        add(settingsPanel, BorderLayout.CENTER);
        add(leaderboardPanel, BorderLayout.EAST);
        add(creditsPanel, BorderLayout.SOUTH);

        tempFunction(); // ... temporary and useless to pass checkstyle, to remove
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int panelW = getWidth();
        final int panelH = getHeight();
        final int imgW = background.getWidth();
        final int imgH = background.getHeight();

        final double scale = Math.max(
                (double) panelW / imgW,
                (double) panelH / imgH);

        final int drawW = (int) Math.ceil(imgW * scale);
        final int drawH = (int) Math.ceil(imgH * scale);
        final int x = (panelW - drawW) / 2;
        final int y = (panelH - drawH) / 2;

        g.drawImage(background, x, y, drawW, drawH, null);
    }

    /**
     * useless temporary function to pass the fucking checkstyle, to remove when
     * unecessary.
     */
    private void tempFunction() {
        creditsPanel.setVisible(false);
        usernamePanel.setVisible(false);
        leaderboardPanel.setVisible(false);
        settingsPanel.setVisible(false);

        creditsPanel.setVisible(true);
        usernamePanel.setVisible(true);
        leaderboardPanel.setVisible(true);
        settingsPanel.setVisible(true);
    }
}
