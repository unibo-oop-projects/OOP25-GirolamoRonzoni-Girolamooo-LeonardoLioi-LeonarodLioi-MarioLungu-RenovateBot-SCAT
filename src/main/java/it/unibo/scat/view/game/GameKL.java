package it.unibo.scat.view.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.scat.common.Direction;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.view.game.api.GamePanelInterface;

/**
 * Custom KeyListener for the GamePanel.
 */
public class GameKL implements KeyListener {
    private final ControlInterface controlInterface;
    private final GamePanelInterface gamePanelInterface;

    /**
     * need it to pass the tests.
     * 
     * @param controlInterface   ...
     * @param gamePanelInterface ...
     */
    public GameKL(final ControlInterface controlInterface, final GamePanelInterface gamePanelInterface) {
        this.controlInterface = controlInterface;
        this.gamePanelInterface = gamePanelInterface;
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
                gamePanelInterface.pause();
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
