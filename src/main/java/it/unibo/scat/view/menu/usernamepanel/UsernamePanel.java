package it.unibo.scat.view.menu.usernamepanel;

import javax.swing.JPanel;

import it.unibo.scat.view.api.MenuActionsInterface;

/**
 * This class handles the username panel.
 */
public final class UsernamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final MenuActionsInterface menuActionsInterface;
    private String username = "user";

    public UsernamePanel(final MenuActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;
    }
}
