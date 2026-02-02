package it.unibo.scat.view.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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
    private transient volatile List<EntityView> entities;
    private transient Image voidImage;
    private final transient Image[] player;
    private final transient Image[] invader1;
    private final transient Image[] invader2;
    private final transient Image[] invader3;
    private final transient Image[] invader4;
    private final transient Image[] bunker;
    private final transient Image[] shot;
    private final AtomicInteger invadersAnimationFrame = new AtomicInteger(0);
    private final AtomicInteger bonusInvaderAnimationFrame = new AtomicInteger(0);
    private int lastInvadersHash;
    private int lastBonusHash;

    /**
     * ...
     * 
     * @param menuActionsInterface ...
     */
    public Canvas(final MenuActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;

        invader1 = new Image[UIConstants.INVADER1_PATHS.size()];
        invader2 = new Image[UIConstants.INVADER2_PATHS.size()];
        invader3 = new Image[UIConstants.INVADER3_PATHS.size()];
        invader4 = new Image[UIConstants.BONUS_INVADER_PATHS.size()];
        bunker = new Image[UIConstants.BUNKER_PATHS.size()];
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

        final int invHash = hashPositions(entities, EntityType.INVADER_1, EntityType.INVADER_2, EntityType.INVADER_3);
        if (invHash != lastInvadersHash) {
            invadersAnimationFrame.set(invadersAnimationFrame.get() == 1 ? 0 : 1);
            lastInvadersHash = invHash;
        }

        final int bonusInvHash = hashPositions(entities, EntityType.BONUS_INVADER);
        if (bonusInvHash != lastBonusHash) {
            bonusInvaderAnimationFrame.set(bonusInvaderAnimationFrame.get() == 1 ? 0 : 1);
            lastBonusHash = bonusInvHash;
        }
    }

    /**
     * Calculates a hash value based on the position of all the entieis of one (or
     * more) EntityType.
     * The hash changes only when at least one Entity of the group changes
     * position.
     *
     * @param entityList the entities list
     * @param types      the entity types to include
     * @return a hash of the group position, or 0 if there are no entities of the
     *         given type
     */

    private static int hashPositions(final List<EntityView> entityList, final EntityType... types) {
        final int hashingValue = 31;
        int minX = Constants.BORDER_RIGHT;
        int maxX = Constants.BORDER_LEFT;
        int minY = Constants.BORDER_BOTTOM;
        int maxY = Constants.BORDER_UP;

        for (final EntityView entity : entityList) {
            for (final EntityType type : types) {
                if (entity.getType() == type) {
                    minX = Math.min(minX, entity.getPosition().getX());
                    maxX = Math.max(maxX, entity.getPosition().getX());
                    minY = Math.min(minY, entity.getPosition().getY());
                    maxY = Math.max(maxY, entity.getPosition().getY());
                    break;
                }
            }
        }

        int hash = 1;
        hash = hashingValue * hash + minX;
        hash = hashingValue * hash + minY;
        hash = hashingValue * hash + maxX;
        hash = hashingValue * hash + maxY;
        return hash;
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
        if (entities == null) {
            throw new IllegalStateException("Null entities in Canvas!");
        }

        final List<EntityView> list = entities;

        final int scaleX = getWidth() / Constants.BORDER_RIGHT;
        final int scaleY = getHeight() / Constants.BORDER_BOTTOM;

        for (final EntityView entity : list) {

            // ENTITIES
            final int x = entity.getPosition().getX() * scaleX;
            final int y = entity.getPosition().getY() * scaleY;
            final int width = entity.getWidth() * scaleX;
            final int height = entity.getHeight() * scaleY;

            final Image imm = fetchImage(entity);
            g.drawImage(imm, x, y, width, height, null);

            // BUNKERS LIFE COUNTERS
            if (entity.getType() == EntityType.BUNKER) {
                final int newX = x + (entity.getWidth() / 2 * scaleX);
                final int newY = y + ((entity.getHeight() + 1) * scaleY);
                g.drawString(String.valueOf(entity.getHealth()), newX, newY);
            }
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
                return invader1[invadersAnimationFrame.get()];
            }
            case INVADER_2 -> {
                return invader2[invadersAnimationFrame.get()];
            }
            case INVADER_3 -> {
                return invader3[invadersAnimationFrame.get()];
            }
            case BONUS_INVADER -> {
                return invader4[bonusInvaderAnimationFrame.get()];
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
}
