package it.unibo.scat.view.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.MenuActionsInterface;

/**
 * ...
 */
// @SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")
// @SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
@SuppressFBWarnings("EI_EXPOSE_REP2")

public final class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface menuActionsInterface;
    private transient List<EntityView> entities;
    private transient Image voidImage;
    private final transient Image[] player;
    private final transient Image[] invader1;
    private final transient Image[] invader2;
    private final transient Image[] invader3;
    private final transient Image[] invader4;
    private final transient Image[] bunker;
    private final transient Image[] shot;
    private int invadersAnimationFrame;
    private int bonusInvaderAnimationFrame;
    private boolean changeInvadersSprite;
    private boolean changeBonusInvaderSprite;

    /**
     * ...
     * 
     * @param menuActionsInterface ...
     */
    public Canvas(final MenuActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;

        invader1 = new Image[2];
        invader2 = new Image[2];
        invader3 = new Image[2];
        invader4 = new Image[2];
        bunker = new Image[3];
        shot = new Image[2];
        player = new Image[UIConstants.PLAYER_PATHS.size()];
        entities = null; // to do for the checkstyle

        setForeground(UIConstants.WHITE_50_OPACITY);
        setFont(UIConstants.SMALL_FONT);

        initImages();
        update();
    }

    /**
     * ...
     */
    public void update() {
        entities = menuActionsInterface.fetchEntitiesFromModel();

        changeInvadersSprite = menuActionsInterface.getInvadersAccMs() >= menuActionsInterface.getInvadersStepMs();
        changeBonusInvaderSprite = menuActionsInterface.getBonusInvaderAccMs() >= Constants.BONUSINVADER_STEP_MS;
    }

    /**
     * ...
     */
    private void initImages() {
        // VOID
        voidImage = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.NULL_PATH))).getImage();

        // PLAYER
        player[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.PLAYER_PATHS.get(0)))).getImage();
        player[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.PLAYER_PATHS.get(1)))).getImage();
        player[2] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.PLAYER_PATHS.get(2)))).getImage();
        player[3] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.PLAYER_PATHS.get(3)))).getImage();
        player[4] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.PLAYER_PATHS.get(4)))).getImage();
        player[UIConstants.PLAYER_PATHS.size() - 1] = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource(UIConstants.PLAYER_PATHS.get(UIConstants.PLAYER_PATHS.size() - 1))))
                .getImage();

        // SHOTS
        shot[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.PLAYER_SHOT_PATH))).getImage();
        shot[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER_SHOT_PATH))).getImage();

        // BUNKERS
        bunker[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.BUNKER_PATHS.get(0)))).getImage();
        bunker[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.BUNKER_PATHS.get(1)))).getImage();
        bunker[2] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.BUNKER_PATHS.get(2)))).getImage();

        // INVADERS
        invader1[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER1_PATHS.get(0)))).getImage();
        invader1[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER1_PATHS.get(1)))).getImage();

        invader2[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER2_PATHS.get(0)))).getImage();
        invader2[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER2_PATHS.get(1)))).getImage();

        invader3[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER3_PATHS.get(0)))).getImage();
        invader3[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.INVADER3_PATHS.get(1)))).getImage();

        invader4[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.BONUS_INVADER_PATHS.get(0)))).getImage();
        invader4[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource(UIConstants.BONUS_INVADER_PATHS.get(1)))).getImage();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int scaleX = getWidth() / Constants.BORDER_RIGHT;
        final int scaleY = getHeight() / Constants.BORDER_BOTTOM;

        for (final EntityView entity : entities) {
            if (!entity.isAlive()) {
                continue;
            }

            // ENTITY
            final int x = entity.getPosition().getX() * scaleX;
            final int y = entity.getPosition().getY() * scaleY;
            final int width = entity.getWidth() * scaleX;
            final int height = entity.getHeight() * scaleY;

            final Image imm = fetchImage(entity);
            g.drawImage(imm, x, y, width, height, null);

            // BUNKERS LIFE COUNTER
            if (entity.getType() == EntityType.BUNKER) {
                final int newX = x + (entity.getWidth() / 2 * scaleX);
                final int newY = y + ((entity.getHeight() + 1) * scaleY);
                g.drawString(String.valueOf(entity.getHealth()), newX, newY);
            }
        }

        if (changeInvadersSprite) {
            invadersAnimationFrame = invadersAnimationFrame == 1 ? 0 : 1;
        }

        if (changeBonusInvaderSprite) {
            bonusInvaderAnimationFrame = bonusInvaderAnimationFrame == 1 ? 0 : 1;
        }

    }

    /**
     * @param entity ...
     * @return ...
     * 
     */
    private Image fetchImage(final EntityView entity) {

        switch (entity.getType()) {
            case INVADER_1 -> {
                return invader1[invadersAnimationFrame];
            }
            case INVADER_2 -> {
                return invader2[invadersAnimationFrame];
            }
            case INVADER_3 -> {
                return invader3[invadersAnimationFrame];
            }
            case BONUS_INVADER -> {
                return invader4[bonusInvaderAnimationFrame];
            }
            case PLAYER -> {
                return player[menuActionsInterface.getChosenShipIndex()];
            }
            case PLAYER_SHOT -> {
                return shot[0];
            }
            case INVADER_SHOT -> {
                return shot[1];
            }
            case BUNKER -> {
                if (entity.getHealth() > Constants.BUNKER_HEALTH / 3 * 2) {
                    return bunker[0];
                } else if (entity.getHealth() > Constants.BUNKER_HEALTH / 3) {
                    return bunker[1];
                }
                return bunker[2];
            }
        }

        return voidImage;
    }

    /**
     * useless, temporary, to be deleted...
     */
    public void useless() {
        player.notifyAll();
        menuActionsInterface.notifyAll();
        entities = new ArrayList<>();
        entities.clear();
    }

}
