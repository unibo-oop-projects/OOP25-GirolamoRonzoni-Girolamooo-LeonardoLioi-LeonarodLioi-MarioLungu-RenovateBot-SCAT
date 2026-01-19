package it.unibo.scat.view.game;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.scat.view.game.api.GamePanelInterface;

/**
 * ...
 */
public final class StatusBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient GamePanelInterface gamePanelInterface;

    /**
     * @param gamePanelInterface ...
     * 
     */
    public StatusBar(final GamePanelInterface gamePanelInterface) {
        this.gamePanelInterface = gamePanelInterface;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.YELLOW);

        JLabel label = new JLabel("PROVAAAAA STATUSBAR");
        add(label);
    }
}
