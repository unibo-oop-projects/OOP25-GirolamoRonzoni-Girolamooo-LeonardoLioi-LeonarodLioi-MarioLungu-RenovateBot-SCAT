package it.unibo.scat.view.menu.usernamepanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.components.CustomButton;

/**
 * ...
 */
public final class ButtonsWrapper extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * @param menuActionsInterface ...
     */
    public ButtonsWrapper(final MenuActionsInterface menuActionsInterface) {

        setLayout(new GridLayout(2, 3, 10, 10));

        final int buttonsCounter = 6;
        final CustomButton[] buttons = new CustomButton[buttonsCounter];

        for (int i = 0; i < buttonsCounter; i++) {
            final int index = i;
            buttons[i] = new CustomButton(UIConstants.PLAYER_PATHS.get(i));

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    menuActionsInterface.setChosenShipIndex(index);

                    for (int a = 0; a < buttonsCounter; a++) {
                        buttons[a].setSelection(false);
                        buttons[a].repaint();
                    }

                    buttons[index].setSelection(true);
                }
            });

            add(buttons[i]);
        }

    }
}
