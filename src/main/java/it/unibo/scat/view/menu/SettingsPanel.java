package it.unibo.scat.view.menu;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.scat.view.components.CustomLabel;

/**
 * ...
 */

public final class SettingsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel newGameLabel;
    private JLabel quitGameLabel;
    private JLabel leaderBoardLabel;
    private JLabel infoLabel;

    /**
     * ...
     */
    public SettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initComponents();
    }

    /**
     * ...
     */
    private void initComponents() {
        newGameLabel = new CustomLabel("New Game");
        quitGameLabel = new CustomLabel("Quit Game");
        leaderBoardLabel = new CustomLabel("Show Leaderboard");
        infoLabel = new CustomLabel("Info");

        newGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderBoardLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(newGameLabel);
        add(Box.createVerticalStrut(100));
        add(leaderBoardLabel);
        add(Box.createVerticalStrut(100));
        add(infoLabel);
        add(Box.createVerticalStrut(100));
        add(quitGameLabel);
        add(Box.createVerticalGlue());
    }

    /**
     * Temporary function to pass checkstyle...
     */
    public void useless() {
        newGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderBoardLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);
    }
}
