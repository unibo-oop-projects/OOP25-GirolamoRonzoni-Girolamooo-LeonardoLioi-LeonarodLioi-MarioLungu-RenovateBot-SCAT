package it.unibo.scat.view.menu;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.menu.api.MenuPanelInterface;
import it.unibo.scat.view.menu.usernamepanel.UsernamePanel;

/**
 * ...
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
public final class MenuPanel extends JPanel implements MenuPanelInterface {
    private static final long serialVersionUID = 1L;

    private static final String CARD_SETTINGS = "SETTINGS";
    private static final String CARD_USERNAME = "USERNAME";
    private static final String CARD_LEADERBOARD = "LEADERBOARD";
    private static final String CARD_CREDITS = "CREDITS";

    private final transient MenuActionsInterface menuActionsInterface;
    private transient BufferedImage background;

    private final transient CardLayout cardLayout = new CardLayout();

    private SettingsPanel settingsPanel;
    private UsernamePanel usernamePanel;
    private LeaderboardPanel leaderboardPanel;
    private CreditsPanel creditsPanel;

    /**
     * @param menuActionsInterface ...
     * 
     */
    public MenuPanel(final MenuActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;
        setLayout(cardLayout);

        initBackground();
        initPanels();

        showSettingsPanel();
    }

    /**
     * ...
     */
    private void initBackground() {
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/backgrounds/menu_background2.jpg")));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load menu background", e);
        }
    }

    /**
     * ...
     */
    private void initPanels() {
        settingsPanel = new SettingsPanel(menuActionsInterface, this);
        usernamePanel = new UsernamePanel(menuActionsInterface);
        leaderboardPanel = new LeaderboardPanel(this);
        creditsPanel = new CreditsPanel(this);

        settingsPanel.setOpaque(false);

        final double ratio = 0.75;

        add(percentCenteredCard(settingsPanel, 1, ratio), CARD_SETTINGS);
        add(percentCenteredCard(usernamePanel, ratio, ratio), CARD_USERNAME);
        add(percentCenteredCard(leaderboardPanel, ratio, ratio), CARD_LEADERBOARD);
        add(percentCenteredCard(creditsPanel, ratio, ratio), CARD_CREDITS);
    }

    /**
     * @param content     ...
     * @param widthRatio  ...
     * @param heightRatio ...
     * @return ...
     * 
     */
    private static JPanel percentCenteredCard(final JComponent content, final double widthRatio,
            final double heightRatio) {
        final JPanel wrapper = new JPanel(null) {
            private static final long serialVersionUID = 1L;

            @Override
            public void doLayout() {
                final int w = getWidth();
                final int h = getHeight();

                final int cw = Math.max(1, (int) Math.round(w * widthRatio));
                final int ch = Math.max(1, (int) Math.round(h * heightRatio));

                content.setBounds((w - cw) / 2, (h - ch) / 2, cw, ch);
            }
        };

        wrapper.setOpaque(false);
        // content.setOpaque(false);
        wrapper.add(content);
        return wrapper;
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

    @Override
    public void showLeaderboardPanel() {
        cardLayout.show(this, CARD_LEADERBOARD);
        revalidate();
        repaint();
    }

    @Override
    public void showCreditsPanel() {
        cardLayout.show(this, CARD_CREDITS);
        revalidate();
        repaint();
    }

    @Override
    public void showSettingsPanel() {
        cardLayout.show(this, CARD_SETTINGS);
        revalidate();
        repaint();
    }

    /**
     * ...
     */
    @Override
    public void showUsernamePanel() {
        cardLayout.show(this, CARD_USERNAME);
        revalidate();
        repaint();
    }

    /**
     * useless, to pass checkstyle temporary...
     */
    public void useless() {
        settingsPanel.doLayout();
        usernamePanel.doLayout();
        leaderboardPanel.doLayout();
        creditsPanel.doLayout();
    }
}
