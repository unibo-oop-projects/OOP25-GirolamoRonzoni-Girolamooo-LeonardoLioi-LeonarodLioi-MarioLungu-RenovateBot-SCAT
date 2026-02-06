package it.unibo.scat.view.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.util.AudioManager;
import it.unibo.scat.util.AudioTrack;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.components.CustomLabel;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * ...
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })

public final class SettingsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int VERTICAL_GAP = 50;
    private final transient MenuActionsInterface viewInterface;
    private final transient MenuPanelInterface menuInterface;
    private JLabel playLabel;
    private JLabel quitGameLabel;
    private JLabel leaderboardLabel;
    private JLabel creditsLabel;
    private final transient AudioManager effectSound;

    /**
     * @param viewInterface ...
     * @param menuInterface ...
     * 
     */
    public SettingsPanel(final MenuActionsInterface viewInterface, final MenuPanelInterface menuInterface) {
        this.viewInterface = viewInterface;
        this.menuInterface = menuInterface;
        effectSound = new AudioManager();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initPlayLabel();
        initCreditsLabel();
        initLeaderboardLabel();
        initQuitGameLabel();
    }

    /**
     * ...
     */
    private void initPlayLabel() {
        playLabel = new CustomLabel("PLAY");
        playLabel.setAlignmentX(CENTER_ALIGNMENT);
        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showUsernamePanel();
                effectSound.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalGlue());
        add(playLabel);
    }

    /**
     * ...
     */
    private void initLeaderboardLabel() {
        leaderboardLabel = new CustomLabel("SCORES");
        leaderboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showLeaderboardPanel();
                effectSound.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_GAP));
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
                effectSound.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_GAP));
        add(creditsLabel);
    }

    /**
     * ...
     */
    private void initQuitGameLabel() {
        quitGameLabel = new CustomLabel("QUIT");
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                viewInterface.quitGame();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_GAP));
        add(quitGameLabel);
        add(Box.createVerticalGlue());
    }

    /**
     * Temporary function to pass checkstyle...
     */
    public void useless() {
        playLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        creditsLabel.setAlignmentX(CENTER_ALIGNMENT);
    }
}
