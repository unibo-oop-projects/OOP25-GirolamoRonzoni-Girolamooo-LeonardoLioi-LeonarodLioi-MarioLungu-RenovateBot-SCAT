package it.unibo.scat.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.game.api.GamePanelInterface;

/**
 * ...
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")
public final class StatusBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient GamePanelInterface gamePanelInterface;
    private JLabel pauseButton;
    private JPanel livesPanel;

    /**
     * @param gamePanelInterface ...
     * 
     */
    public StatusBar(final GamePanelInterface gamePanelInterface) {
        this.gamePanelInterface = gamePanelInterface;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        initPauseButton();
        initLivesPanel();
    }

    /**
     * ...
     */
    private void initPauseButton() {
        final int h = 70;

        final Font baseFont = new Font("Calibri", Font.BOLD, 40);
        final Font hoverFont = new Font("Calibri", Font.BOLD, 50);

        pauseButton = new JLabel("||");
        pauseButton.setPreferredSize(new Dimension(h, h));
        pauseButton.setMinimumSize(new Dimension(h, h));
        pauseButton.setMaximumSize(new Dimension(h, h));
        // pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusable(false);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setFont(baseFont);
        pauseButton.setHorizontalAlignment(SwingConstants.CENTER);
        pauseButton.setVerticalAlignment(SwingConstants.CENTER);
        pauseButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        pauseButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                gamePanelInterface.pause();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                pauseButton.setFont(hoverFont);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                pauseButton.setFont(baseFont);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }

        });

        add(pauseButton, BorderLayout.WEST);
        // add(Box.createHorizontalStrut(10));

    }

    /**
     * ...
     */
    private void initLivesPanel() {

    }
}
