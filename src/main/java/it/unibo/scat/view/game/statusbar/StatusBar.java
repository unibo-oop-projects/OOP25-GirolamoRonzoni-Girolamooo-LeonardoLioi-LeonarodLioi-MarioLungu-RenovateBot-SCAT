package it.unibo.scat.view.game.statusbar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.Box;
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
    private boolean isPausePanelHover = false;
    private JPanel pausePanel;
    private JPanel livesPanel;
    private JLabel scoreLabel;

    /**
     * @param gamePanelInterface ...
     * 
     */
    public StatusBar(final GamePanelInterface gamePanelInterface) {
        this.gamePanelInterface = gamePanelInterface;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // setBackground(Color.BLACK);

        initPausePanel();
        initScoreLabel();
        initLivesPanel();
    }

    /**
     * ...
     */
    private void initPausePanel() {
        final int targetH = 80;

        pausePanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            private final Image baseImage = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/images/pause/pause1.png"))).getImage();
            private final Image hoverImage = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/images/pause/pause2.png"))).getImage();

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);

                final Image img = isPausePanelHover ? hoverImage : baseImage;

                final int imgW = img.getWidth(this);
                final int imgH = img.getHeight(this);
                if (imgW <= 0 || imgH <= 0) {
                    return;
                }

                final double scale = (double) (targetH - 10) / imgH;
                final int drawH = (targetH - 10);
                final int drawW = (int) Math.round(imgW * scale);

                final int x = (getWidth() - drawW) / 2;
                final int y = (getHeight() - drawH) / 2;

                g.drawImage(img, x, y, drawW, drawH, this);
            }

        };
        pausePanel.setPreferredSize(new Dimension(targetH, targetH));
        pausePanel.setMinimumSize(new Dimension(targetH, targetH));
        pausePanel.setMaximumSize(new Dimension(targetH, targetH));
        pausePanel.setFocusable(false);
        pausePanel.setForeground(Color.WHITE);
        pausePanel.setOpaque(false);

        pausePanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                gamePanelInterface.pause();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                isPausePanelHover = true;
                pausePanel.repaint();
                pausePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                isPausePanelHover = false;
                pausePanel.repaint();
                pausePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

        });

        add(pausePanel);
    }

    /**
     * ...
     */
    private void initScoreLabel() {
        scoreLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setText("SCORE: " + gamePanelInterface.getScore());
            }

        };

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Calibri", Font.BOLD, 40));
        scoreLabel.setText("SCORE: " + gamePanelInterface.getScore());
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreLabel.setFocusable(false);
        scoreLabel.setOpaque(false);

        add(Box.createHorizontalGlue());
        add(scoreLabel);
    }

    /**
     * ...
     */
    private void initLivesPanel() {
        final int targetH = 80;
        final int targetW = 210;
        livesPanel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final Image currImg;

                switch (gamePanelInterface.getPlayerHealth()) {
                    case 1 -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/images/life/life_1.png"))).getImage();
                    }
                    case 2 -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/images/life/life_2.png"))).getImage();
                    }
                    case 3 -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/images/life/life_3.png"))).getImage();
                    }
                    default -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/images/life/life_0.png"))).getImage();
                    }
                }

                final int imgW = currImg.getWidth(this);
                final int imgH = currImg.getHeight(this);

                if (imgW <= 0 || imgH <= 0) {
                    return;
                }

                final double scale = (double) (targetH - 20) / imgH;
                final int drawH = targetH - 20;
                final int drawW = (int) Math.round(imgW * scale);

                final int x = (getWidth() - drawW) / 2;
                final int y = (getHeight() - drawH) / 2;

                g.drawImage(currImg, x, y, drawW, drawH, this);

            }

        };
        livesPanel.setOpaque(false);
        livesPanel.setFocusable(false);
        livesPanel.setPreferredSize(new Dimension(targetW, targetH));
        livesPanel.setMinimumSize(new Dimension(targetW, targetH));
        livesPanel.setMaximumSize(new Dimension(targetW, targetH));

        add(Box.createHorizontalGlue());
        add(livesPanel);
    }

    /**
     * usless function temporary for checkstyle ....
     */
    public void useless() {
        livesPanel.setBackground(Color.RED);
        pausePanel.setBackground(Color.RED);
        scoreLabel.setBackground(Color.RED);
    }
}
