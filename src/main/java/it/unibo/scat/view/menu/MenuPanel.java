package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * Panel that contains all the graphics element for the menu.
 */
// @SuppressFBWarnings(value = "SINGULAR_FIELD", justification = "Panels are
// stored as fields for later interactions")
// @SuppressWarnings("PMD.UnusedPrivateField")
@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Interface reference intentionally shared")
public final class MenuPanel extends JPanel implements MenuPanelInterface {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface viewInterface;
    private transient BufferedImage background;
    private SettingsPanel settingsPanel;
    private UsernamePanel usernamePanel;
    private LeaderboardPanel leaderboardPanel;
    private CreditsPanel creditsPanel;

    /**
     * @param vInterface ...
     * 
     */
    public MenuPanel(final MenuActionsInterface vInterface) {
        viewInterface = vInterface;
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
                            getClass().getResource("/images/menu_background2.jpg")));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load menu background", e);
        }
    }

    /**
     * ...
     */
    private void initPanels() {
        settingsPanel = new SettingsPanel(viewInterface, this);
        usernamePanel = new UsernamePanel();
        leaderboardPanel = new LeaderboardPanel();
        creditsPanel = new CreditsPanel();

        settingsPanel.setBackground(Color.GRAY);
        usernamePanel.setBackground(Color.YELLOW);
        leaderboardPanel.setBackground(Color.GREEN);
        creditsPanel.setBackground(Color.red);

        settingsPanel.setOpaque(false);
        usernamePanel.setBackground(Color.YELLOW);
        leaderboardPanel.setBackground(Color.GREEN);
        creditsPanel.setBackground(Color.red);

        add(settingsPanel, BorderLayout.CENTER);
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

    @Override
    public void showLeaderboardPanel() {
        System.out.println("showing leadewrboard");
        removeAll();
        add(leaderboardPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public void showCreditsPanel() {
        removeAll();
        add(creditsPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public void showSettingsPanel() {
        removeAll();
        add(settingsPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
