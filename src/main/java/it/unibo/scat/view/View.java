package it.unibo.scat.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.view.game.GamePanel;
import it.unibo.scat.view.menu.MenuPanel;

/**
 * The main class for the "View" section of the MVC pattern.
 */
@SuppressFBWarnings("UUF_UNUSED_FIELD")
public class View {
    private ControlInterface controlInterface;
    private JFrame frame;
    private Dimension screenDim;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
}
