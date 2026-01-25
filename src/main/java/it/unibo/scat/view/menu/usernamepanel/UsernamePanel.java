package it.unibo.scat.view.menu.usernamepanel;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.scat.view.api.MenuActionsInterface;

/**
 * This class handles the username panel.
 */
public final class UsernamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final MenuActionsInterface menuActionsInterface;
    private JTextField usernameField;
    private final Font baseFont = new Font("Calibri", Font.BOLD, 80);
    private String username = "user";

    /**
     * @param menuActionsInterface ...
     * 
     */
    public UsernamePanel(final MenuActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initUsernameField();
    }

    /**
     * ...
     */
    private void initUsernameField() {

        usernameField = new JTextField("user");

        add(Box.createVerticalGlue());
        add(usernameField);
    }
}
