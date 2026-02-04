package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.util.AudioManager;
import it.unibo.scat.util.AudioTrack;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * This class handles the credits panel.
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")

public final class CreditsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int TITLE_SPACING = 10;
    private static final int BOTTOM_SPACING = 10;
    private static final int SPACING_NAMES = 5;
    private static final int NAV_H = 40;
    private static final int NAV_V = 10;
    private static final String CALIBRI = "Calibri";
    private static final String CREDITS = "CREDITS";
    private static final String TUTORIAL = "TUTORIAL";
    private static final String POINTS = "POINTS";

    private static final int INVADER_WIDTH = 60;
    private static final int INVADER_HEIGHT = 40;
    private static final int INVAVER_H = 40;
    private static final int INVADER_V = 5;
    private static final int UNDER_INVADER = 5;
    private static final Font FONT_TITLE = new Font(CALIBRI, Font.BOLD, 40);
    private static final Font FONT_INFO = new Font(CALIBRI, Font.BOLD, 20);
    private static final Font FONT_BUTTON = new Font(CALIBRI, Font.BOLD, 30);
    private static final Font FONT_STORY = new Font(CALIBRI, Font.ITALIC, 18);
    private static final Font FONT_POINTS = new Font(CALIBRI, Font.BOLD, 12);

    private final transient MenuPanelInterface menuInterface;
    private final transient AudioManager soundEffect;

    private final JPanel cardsPanel;
    private final CardLayout cardLayout;
    private final JLabel prev;
    private final JLabel next;
    private final JLabel menuButton;

    /**
     * @param mInterface ...
     * 
     */
    public CreditsPanel(final MenuPanelInterface mInterface) {
        this.menuInterface = mInterface;
        this.soundEffect = new AudioManager();

        setLayout(new BorderLayout());
        setOpaque(false);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        cardsPanel.setOpaque(false);

        cardsPanel.add(createCredits(), CREDITS);
        cardsPanel.add(createTutorial(), TUTORIAL);

        add(cardsPanel, BorderLayout.CENTER);

        prev = createButton("PREVIOUS", "< PREVIOUS", () -> showPage(CREDITS));

        next = createButton("NEXT", "NEXT >", () -> showPage(TUTORIAL));

        menuButton = createButton("MENU", "< MENU >", () -> {
            showPage(CREDITS);
            menuInterface.showSettingsPanel();
        });

        prev.setVisible(false);
        initNavigationPanel();

    }

    /**
     * Creates and returns the credits panel.
     * 
     * @return the credits panel.
     */
    private JPanel createCredits() {
        final JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createVerticalGlue());
        p.setOpaque(false);

        addCustomLabel(p, "CREDITS PANEL", FONT_TITLE);

        p.add(Box.createVerticalStrut(TITLE_SPACING));

        addCustomLabel(p, "Tribute to the original 'Space Invaders', the legendary game", FONT_STORY);
        addCustomLabel(p, "designed by Tomohiro Nishikado and released by Taito in 1978.", FONT_STORY);
        addCustomLabel(p, "A global phenomenon of the late 70s, it sold over 360,000 arcade cabinets", FONT_STORY);
        addCustomLabel(p, "and grossed billions worldwide.", FONT_STORY);

        addCustomLabel(p,
                "This remake was developed as a project for the OOP course at the University of Bologna.",
                FONT_STORY);

        addCustomLabel(p, "Credits to:", FONT_STORY);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p, "GIROLAMO RONZONI - girolamo.ronzoni@studio.unibo.it", FONT_INFO);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p, "LEONARDO LIOI - leonardo.lioi@studio.unibo.it", FONT_INFO);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p, "MARIO LUNGU - mario.lungu@studio.unibo.it", FONT_INFO);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        p.add(Box.createVerticalGlue());

        return p;
    }

    /**
     * ...
     * 
     * @return ...
     */
    private JPanel createTutorial() {
        final JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        p.add(Box.createVerticalGlue());

        addCustomLabel(p, "INVADERS", FONT_TITLE);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        final JPanel invadersRow = new JPanel(new FlowLayout(FlowLayout.CENTER, INVAVER_H, INVADER_V));
        invadersRow.setOpaque(false);

        invadersRow
                .add(createInvaderPanel("entities/invaders/invader_1_1.png", Constants.POINTS_INVADER1 + " " + POINTS));
        invadersRow
                .add(createInvaderPanel("entities/invaders/invader_2_1.png", Constants.POINTS_INVADER2 + " " + POINTS));
        invadersRow
                .add(createInvaderPanel("entities/invaders/invader_3_1.png", Constants.POINTS_INVADER3 + " " + POINTS));
        invadersRow.add(
                createInvaderPanel("entities/invaders/invader_4_1.png", Constants.POINTS_BONUS_INVADER + " " + POINTS));

        p.add(invadersRow);

        addCustomLabel(p, "HOW TO PLAY?", FONT_TITLE);

        p.add(Box.createVerticalStrut(TITLE_SPACING));

        addCustomLabel(p, "MOVE LEFT: LEFT ARROW", FONT_INFO);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p, "MOVE RIGHT: RIGHT ARROW", FONT_INFO);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p, "SHOOT: SPACE", FONT_INFO);

        p.add(Box.createVerticalGlue());
        return p;
    }

    /**
     * ...
     * 
     * @param path   ...
     * @param points ...
     * @return ...
     */
    private JPanel createInvaderPanel(final String path, final String points) {
        final JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        final URL url = ClassLoader.getSystemResource(path);

        final JLabel image;

        final ImageIcon originalIcon = new ImageIcon(url);
        final java.awt.Image scaledImg = originalIcon.getImage()
                .getScaledInstance(INVADER_WIDTH, INVADER_HEIGHT, java.awt.Image.SCALE_SMOOTH);

        image = new JLabel(new ImageIcon(scaledImg));

        image.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel score = new JLabel(points);
        score.setFont(FONT_POINTS);
        score.setForeground(Color.WHITE);
        score.setAlignmentX(CENTER_ALIGNMENT);

        p.add(image);
        p.add(Box.createVerticalStrut(UNDER_INVADER));
        p.add(score);

        return p;
    }

    /**
     * ...
     * 
     * @param defaultText ...
     * @param hoverText   ...
     * @param action      ...
     * @return ...
     */
    private JLabel createButton(final String defaultText, final String hoverText, final Runnable action) {
        final JLabel button = new JLabel(defaultText);
        button.setFont(FONT_BUTTON);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                action.run();
                soundEffect.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                button.setText(hoverText);
                button.setForeground(Color.RED);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                soundEffect.play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                button.setText(defaultText);
                button.setForeground(Color.WHITE);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    /**
     * ...
     * 
     * @param pageName ...
     */
    private void showPage(final String pageName) {
        cardLayout.show(cardsPanel, pageName);

        if (CREDITS.equals(pageName)) {
            prev.setVisible(false);
            next.setVisible(true);
        } else {
            prev.setVisible(true);
            next.setVisible(false);
        }
    }

    /**
     * ...
     */
    private void initNavigationPanel() {
        final JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, NAV_H, NAV_V));
        navPanel.setOpaque(false);

        navPanel.add(prev);
        navPanel.add(menuButton);
        navPanel.add(next);

        add(navPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates labels similar to CustomLabel.
     *
     * @param target ...
     * @param text   ...
     * @param font   ...
     */
    private void addCustomLabel(final JPanel target, final String text, final Font font) {
        final JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                label.setForeground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                label.setForeground(Color.WHITE);
            }
        });
        target.add(label);
        target.add(Box.createVerticalStrut(SPACING_NAMES));
    }
}
