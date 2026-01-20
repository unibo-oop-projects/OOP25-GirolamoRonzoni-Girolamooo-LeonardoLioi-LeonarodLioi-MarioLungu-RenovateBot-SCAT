package it.unibo.scat.view.game.statusbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // setBackground(Color.BLACK);

        initPauseButton();
        initLivesPanel();
    }

    /**
     * ...
     */
    private void initPauseButton() {
        final int h = 70;

        final Font baseFont = new Font("ARIAL", Font.BOLD, 40);
        final Font hoverFont = new Font("ARIAL", Font.BOLD, 50);

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
        final int h = 80;
        final int fontHeight = 50;

        livesPanel = new JPanel();
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.X_AXIS));
        livesPanel.setOpaque(false);
        livesPanel.setPreferredSize(new Dimension(0, h));

        final JLabel text = new JLabel("LIVES:");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Calibri", Font.BOLD, fontHeight));
        text.setPreferredSize(new Dimension(0, h));

        final JPanel imageP = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final Image img = new ImageIcon(
                        Objects.requireNonNull(getClass().getResource("/images/life/life_2.png"))).getImage();

                final int imgW = img.getWidth(this);
                final int imgH = img.getHeight(this);

                if (imgW <= 0 || imgH <= 0) {
                    return;
                }

                final int targetH = 80;
                final double scale = (double) targetH / imgH;
                final int drawH = targetH;
                final int drawW = (int) Math.round(imgW * scale);

                final int x = (getWidth() - drawW) / 2;
                final int y = (getHeight() - drawH) / 2;

                g.drawImage(img, x, y, drawW, drawH, this);

            }

        };
        imageP.setPreferredSize(new Dimension(0, h));
        imageP.setOpaque(false);

        livesPanel.add(text);
        livesPanel.add(imageP);
        add(livesPanel, BorderLayout.EAST);
    }

    /**
     * usless function temporary for checkstyle ....
     */
    public void useless() {
        livesPanel.setBackground(Color.RED);
        pauseButton.setBackground(Color.RED);
    }
}
