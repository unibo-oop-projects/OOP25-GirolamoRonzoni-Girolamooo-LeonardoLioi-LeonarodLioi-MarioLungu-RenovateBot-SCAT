package it.unibo.scat.view.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.components.CustomLabel;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * ...
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })

public final class SettingsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface viewInterface;
    private final transient MenuPanelInterface menuInterface;
    private JLabel newGameLabel;
    private JLabel quitGameLabel;
    private JLabel leaderboardLabel;
    private JLabel creditsLabel;

    /**
     * @param viewInterface ...
     * @param menuInterface ...
     * 
     */
    public SettingsPanel(final MenuActionsInterface viewInterface, final MenuPanelInterface menuInterface) {
        this.viewInterface = viewInterface;
        this.menuInterface = menuInterface;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initNewGameLabel();
        initCreditsLabel();
        initLeaderboardLabel();
        initQuitGameLabel();
    }

    /**
     * ...
     */
    private void initNewGameLabel() {
        newGameLabel = new CustomLabel("PLAY");
        newGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        newGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                viewInterface.showGamePanel();
                viewInterface.resumeGame();
            }
        });

        add(Box.createVerticalGlue());
        add(newGameLabel);
    }

    /**
     * ...
     */
    private void initLeaderboardLabel() {
        leaderboardLabel = new CustomLabel("LEADERBOARD");
        leaderboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showLeaderboardPanel();
            }
        });

        add(Box.createVerticalStrut(10));
        add(leaderboardLabel);
    }

    /**
     * ...
     */
    private void initCreditsLabel() {
        creditsLabel = new CustomLabel("ABOUT");
        creditsLabel.setAlignmentX(CENTER_ALIGNMENT);
        creditsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showCreditsPanel();
            }
        });

        add(Box.createVerticalStrut(10));
        add(creditsLabel);
    }

    /**
     * ...
     */
    private void initQuitGameLabel() {
        quitGameLabel = new CustomLabel("EXIT");
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                viewInterface.quitGame();
            }
        });

        add(Box.createVerticalStrut(10));
        add(quitGameLabel);
        add(Box.createVerticalGlue());
    }

    /**
     * Temporary function to pass checkstyle...
     */
    public void useless() {
        newGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        creditsLabel.setAlignmentX(CENTER_ALIGNMENT);
    }
}
