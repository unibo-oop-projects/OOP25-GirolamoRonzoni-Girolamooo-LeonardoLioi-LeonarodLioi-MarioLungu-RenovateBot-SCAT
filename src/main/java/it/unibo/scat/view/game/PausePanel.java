package it.unibo.scat.view.game;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.scat.util.AudioManager;
import it.unibo.scat.view.UIConstants;

/**
 * Panel shown in the GamePanel when the game is paused.
 */
@SuppressWarnings("unused")
public final class PausePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final int TITLE_SPACING = 30;

    private final transient AudioManager soundEffect;

    private final JButton resumeButton;
    private final JButton menuButton;
    private final JButton quitButton;

    /**
     * ...
     */
    public PausePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(UIConstants.PANELS_BG_COLOR);

        this.soundEffect = new AudioManager();

        // il titolo
        addTitle();

        // iniz bottoni
        resumeButton = new JButton();
        menuButton = new JButton();
        quitButton = new JButton();

    }

    /**
     * ...
     */
    private void addTitle() {
        this.add(Box.createVerticalGlue());

        final JLabel title = new JLabel("PAUSE");

        title.setFont(UIConstants.TITLE_FONT);
        title.setForeground(UIConstants.ARCADE_GREEN);
        title.setAlignmentX(CENTER_ALIGNMENT);

        add(title);
        add(Box.createVerticalStrut(TITLE_SPACING));
    }

    /**
     * ...
     * 
     * @param text ...
     * @return ...
     */
    private JButton createButton(final String text) {
        final JButton button = new JButton(text);

        button.setFont(UIConstants.MEDIUM_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setAlignmentX(CENTER_ALIGNMENT);

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(button);
        return button;
    }
}
