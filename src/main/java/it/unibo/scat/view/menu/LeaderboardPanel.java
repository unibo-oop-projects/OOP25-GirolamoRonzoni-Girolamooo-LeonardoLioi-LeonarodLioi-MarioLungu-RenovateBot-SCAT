package it.unibo.scat.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * This class handles the leaderboard panel.
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")

public final class LeaderboardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient MenuPanelInterface menuInterface;

    /**
     * @param mInterface ...
     * 
     */
    public LeaderboardPanel(final MenuPanelInterface mInterface) {
        this.menuInterface = mInterface;
        // final Color background = new Color(0, 0, 0, 150);
        // setBackground(background);

        final JLabel label = new JLabel("LEADERBOARD PANEL!!");
        add(label);

        final JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                menuInterface.showSettingsPanel();
            }

        });
        add(backButton);
    }

}
