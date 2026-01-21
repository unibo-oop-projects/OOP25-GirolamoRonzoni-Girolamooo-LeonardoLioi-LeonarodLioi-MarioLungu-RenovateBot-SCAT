package it.unibo.scat.view.game;

import it.unibo.scat.common.Direction;
import it.unibo.scat.control.api.ControlInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Custom KeyListener for the GamePanel.
 */
public class GameKL implements KeyListener {
    private final ControlInterface controlInterface;

    /**
     * need it to pass the tests.
     * 
     * @param controlInterface ..
     */
    public GameKL(final ControlInterface controlInterface) {
        this.controlInterface = controlInterface;
    }

    /**
     * @param e ...
     * 
     */
    @Override
    public void keyTyped(final KeyEvent e) {

    }

    /**
     * @param e ...
     * 
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int eventCode = e.getKeyCode();

        switch (eventCode) {
            case KeyEvent.VK_SPACE:
                controlInterface.notifyPlayerShot();
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                controlInterface.notifyPlayerMovement(Direction.LEFT);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                controlInterface.notifyPlayerMovement(Direction.RIGHT);
                break;

            case KeyEvent.VK_ESCAPE:
                controlInterface.notifyPauseGame();
                break;

            default:
                break;
        }
    }

    /**
     * @param e ...
     * 
     */
    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
