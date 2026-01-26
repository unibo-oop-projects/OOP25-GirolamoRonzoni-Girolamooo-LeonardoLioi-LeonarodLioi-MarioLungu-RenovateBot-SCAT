package it.unibo.scat.view.menu.usernamepanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.UIConstants;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.components.CustomTextField;

/**
 * This class handles the username panel.
 */
public final class UsernamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String USERNAME = "USERNAME";
    private static final int VERTICAL_SPACE = 40;
    private final transient MenuActionsInterface menuActionsInterface;
    private CustomTextField usernameField;

    /**
     * @param menuActionsInterface ...
     * 
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public UsernamePanel(final MenuActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UIConstants.PANELS_BG_COLOR);
        setBorder(new LineBorder(Color.BLACK, 10));

        initUsernameText();
        initUsernameField();
        initShipText();
        initButtonsWrapper();
        initPlayButton();
    }

    /**
     * ...
     */
    private void initUsernameText() {
        final JLabel label = new JLabel("ENTER USERNAME");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(UIConstants.MEDIUM_FONT);
        label.setFocusable(false);
        label.setForeground(Color.GREEN);

        add(Box.createVerticalGlue());
        add(label);
    }

    /**
     * ...
     */
    private void initUsernameField() {
        usernameField = new CustomTextField();
        usernameField.setAlignmentX(CENTER_ALIGNMENT);
        usernameField.setText(USERNAME);
        usernameField.setForeground(Color.GRAY);
        usernameField.setHorizontalAlignment(JTextField.CENTER);

        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                if (USERNAME.equals(usernameField.getText())) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(final FocusEvent e) {
                if (usernameField.getText().isBlank()) {
                    usernameField.setText(USERNAME);
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });

        add(Box.createVerticalStrut(VERTICAL_SPACE));
        add(usernameField);
    }

    /**
     * ...
     */
    private void initShipText() {
        final JLabel label = new JLabel("CHOOSE SHIP");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(UIConstants.MEDIUM_FONT);
        label.setFocusable(false);
        label.setForeground(Color.GREEN);

        add(Box.createVerticalGlue());
        add(label);
    }

    /**
     * ...
     */
    private void initButtonsWrapper() {
        final ButtonsWrapper buttonsWrapper = new ButtonsWrapper(menuActionsInterface);
        buttonsWrapper.setOpaque(false);

        final int width = 400;
        final int height = width * 2 / 3;
        final Dimension d = new Dimension(width, height);
        buttonsWrapper.setPreferredSize(d);
        buttonsWrapper.setMinimumSize(d);
        buttonsWrapper.setMaximumSize(d);

        add(Box.createVerticalStrut(VERTICAL_SPACE));
        add(buttonsWrapper);
    }

    /**
     * ...
     */
    private void initPlayButton() {

        final JButton playButton = new JButton("PLAY");
        playButton.setFocusable(false);
        playButton.setFont(UIConstants.TITLE_FONT);
        playButton.setBackground(Color.GREEN);
        playButton.setForeground(Color.BLACK);
        playButton.setAlignmentX(CENTER_ALIGNMENT);

        final FontMetrics fm = getFontMetrics(UIConstants.MEDIUM_FONT);
        final int maxWidth = fm.charWidth('W') * 15 + getInsets().left
                + getInsets().right;
        final int maxHeight = fm.getHeight() * 2 + getInsets().top
                + getInsets().bottom;

        final Dimension d = new Dimension(maxWidth, maxHeight);
        playButton.setPreferredSize(d);
        playButton.setMinimumSize(d);
        playButton.setMaximumSize(d);

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                playButton.setBackground(Color.WHITE);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                playButton.setBackground(Color.GREEN);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (usernameField.getText().isBlank() || USERNAME.equals(usernameField.getText())
                        || menuActionsInterface.getChosenShipIndex() < 0) {
                    return;
                }

                menuActionsInterface.setUsername(usernameField.getText());
                menuActionsInterface.showGamePanel();
                menuActionsInterface.startGame();
            }

        });

        add(Box.createVerticalStrut(VERTICAL_SPACE * 3));
        add(playButton);
        add(Box.createVerticalGlue());
    }
}
