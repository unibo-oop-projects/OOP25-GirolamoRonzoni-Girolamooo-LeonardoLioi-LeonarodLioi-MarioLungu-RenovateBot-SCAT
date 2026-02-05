package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.util.AudioManager;
import it.unibo.scat.util.AudioTrack;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * This class handles the credits panel.
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")

public final class CreditsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int TITLE_SPACING = 50;
    private static final int BOTTOM_SPACING = 20;
    private static final int NAV_H = 40;
    private static final int NAV_V = 10;
    private static final String CREDITS = "CREDITS";
    private static final String TUTORIAL = "TUTORIAL";
    private static final String POINTS = "POINTS";

    private static final Font FONT_TITLE = UIConstants.FONT_L;
    private static final Font FONT_INFO = UIConstants.FONT_XS;
    private static final Font FONT_BUTTON = UIConstants.FONT_XS;
    private static final Font FONT_STORY = UIConstants.FONT_XS;
    private static final Font FONT_POINTS = UIConstants.FONT_XS;

    private static final Color TITLE_COLOR = Color.GREEN;
    private static final Color TEXT_COLOR = Color.WHITE;

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
        setBackground(Color.BLACK);
        setBorder(UIConstants.PANELS_BORDER);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        cardsPanel.setOpaque(false);

        cardsPanel.add(createCredits(), CREDITS);
        cardsPanel.add(createTutorial(), TUTORIAL);

        add(cardsPanel, BorderLayout.CENTER);

        prev = createButton("< PREVIOUS", () -> showPage(CREDITS));
        next = createButton("NEXT >", () -> showPage(TUTORIAL));

        menuButton = createButton("< MENU", () -> {
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

        // addCustomLabel(p, "S.C.A.T.", FONT_TITLE, TITLE_COLOR);
        // p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p, "SPACE COMBAT: ALIEN TAKEOVER", FONT_TITLE, TITLE_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p,
                "Tribute to the original 'Space Invaders', the legendary game",
                FONT_STORY, TEXT_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p,
                "designed by Tomohiro Nishikado and released by Taito in 1978.",
                FONT_STORY, TEXT_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p,
                "A global phenomenon of the late 70s, it sold over 360,000 arcade",
                FONT_STORY, TEXT_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p,
                "cabinets and grossed billions worldwide.  ",
                FONT_STORY, TEXT_COLOR);
        p.add(Box.createVerticalStrut(TITLE_SPACING));

        addCustomLabel(p,
                "This remake was developed as a project",
                FONT_STORY, TEXT_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p,
                "for the OOP course at the University of Bologna",
                FONT_STORY, TEXT_COLOR);
        p.add(Box.createVerticalGlue());

        addCustomLabel(p, "CREDITS TO:", FONT_TITLE, TITLE_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p, "Girolamo Ronzoni", UIConstants.FONT_S, TEXT_COLOR);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p, "Leonardo Lioi", UIConstants.FONT_S, TEXT_COLOR);

        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        addCustomLabel(p, "Mario Lungu", UIConstants.FONT_S, TEXT_COLOR);
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

        addCustomLabel(p, "INVADERS", FONT_TITLE, TITLE_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));

        final JPanel invadersRow = new JPanel();
        invadersRow.setLayout(new BoxLayout(invadersRow, BoxLayout.Y_AXIS));
        invadersRow.setOpaque(false);

        final String equals = " =    ";
        invadersRow
                .add(createInvaderPanel("/entities/gifs/gif1.gif", equals
                        + Constants.POINTS_INVADER1 + " " + POINTS));
        invadersRow
                .add(createInvaderPanel("/entities/gifs/gif2.gif", equals
                        + Constants.POINTS_INVADER2 + " " + POINTS));
        invadersRow
                .add(createInvaderPanel("/entities/gifs/gif3.gif", equals
                        + Constants.POINTS_INVADER3 + " " + POINTS));
        invadersRow.add(
                createInvaderPanel("/entities/gifs/gif4.gif",
                        "=   " + Constants.POINTS_BONUS_INVADER + " " + POINTS));
        p.add(invadersRow);
        p.add(Box.createVerticalGlue());

        addCustomLabel(p, "HOW TO PLAY?", FONT_TITLE, TITLE_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p, "MOVE LEFT: LEFT ARROW", FONT_INFO, TEXT_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p, "MOVE RIGHT: RIGHT ARROW", FONT_INFO, TEXT_COLOR);
        p.add(Box.createVerticalStrut(BOTTOM_SPACING));
        addCustomLabel(p, "SHOOT: SPACE", FONT_INFO, TEXT_COLOR);

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
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setOpaque(false);

        final ImageIcon gifIcon = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(path)));

        final JLabel imageLabel = new JLabel(gifIcon);

        imageLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel score = new JLabel("    " + points);
        score.setFont(FONT_POINTS);
        score.setForeground(Color.WHITE);

        p.add(imageLabel);
        // p.add());
        p.add(score);

        return p;
    }

    /**
     * ...
     * 
     * @param defaultText ...
     * @param action      ...
     * @return ...
     */
    private JLabel createButton(final String defaultText, final Runnable action) {
        final JLabel button = new JLabel(defaultText);
        button.setFont(FONT_BUTTON);
        button.setForeground(Color.RED);
        button.setAlignmentX(CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                action.run();
                soundEffect.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                soundEffect.play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                button.setForeground(Color.RED);
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
            menuButton.setVisible(true);
        } else {
            prev.setVisible(true);
            next.setVisible(false);
            menuButton.setVisible(false);
        }
    }

    /**
     * ...
     */
    private void initNavigationPanel() {
        final JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, NAV_H, NAV_V));
        navPanel.setOpaque(false);
        navPanel.setBorder(BorderFactory.createEmptyBorder(BOTTOM_SPACING * 2, 0, BOTTOM_SPACING * 2, 0));

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
     * @param color  ...
     */
    private void addCustomLabel(final JPanel target, final String text, final Font font, final Color color) {
        final JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setAlignmentY(CENTER_ALIGNMENT);
        target.add(label);
    }
}
