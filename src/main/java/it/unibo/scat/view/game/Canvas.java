package it.unibo.scat.view.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Costants;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.view.api.MenuActionsInterface;

/**
 * ...
 */
// @SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")
// @SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
@SuppressFBWarnings("EI_EXPOSE_REP2")

public final class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient MenuActionsInterface viewInterface;
    private transient List<EntityView> entities;
    private transient Image voidImage;
    private transient Image player;
    private final transient Image[] invader1;
    private final transient Image[] invader2;
    private final transient Image[] invader3;
    private final transient Image[] invader4;
    private final transient Image[] bunker;
    private final transient Image[] shot;
    private int animationFrame;

    /**
     * ...
     * 
     * @param viewInterface ...
     */
    public Canvas(final MenuActionsInterface viewInterface) {
        this.viewInterface = viewInterface;

        invader1 = new Image[2];
        invader2 = new Image[2];
        invader3 = new Image[2];
        invader4 = new Image[2];
        bunker = new Image[3];
        shot = new Image[2];
        entities = null; // to do for the checkstyle

        initImages();
        updateEntities();
    }

    /**
     * ...
     */
    private void updateEntities() {
        entities = viewInterface.fetchEntitiesFromModel();
    }

    /**
     * ...
     */
    public void initImages() {
        // VOID
        voidImage = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/null.png"))).getImage();

        // PLAYER
        player = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/player.png"))).getImage();

        // SHOTS
        shot[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/shots/player_shot.png"))).getImage();
        shot[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/shots/invader_shot.png"))).getImage();

        // BUNKERS
        bunker[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/bunkers/bunker1.png"))).getImage();
        bunker[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/bunkers/bunker2.png"))).getImage();
        bunker[2] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/bunkers/bunker3.png"))).getImage();

        // INVADERS
        invader1[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_1_1.png"))).getImage();
        invader1[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_1_2.png"))).getImage();

        invader2[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_2_1.png"))).getImage();
        invader2[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_2_2.png"))).getImage();

        invader3[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_3_1.png"))).getImage();
        invader3[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_3_2.png"))).getImage();

        invader4[0] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_4_1.png"))).getImage();
        invader4[1] = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/entities/invaders/invader_4_2.png"))).getImage();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int scaleX = getWidth() / Costants.WORLD_WIDTH;
        final int scaleY = getHeight() / Costants.WORLD_HEIGHT;

        for (final EntityView entity : entities) {
            if (!entity.isAlive()) {
                continue;
            }

            final int x = entity.getPosition().getX() * scaleX;
            final int y = entity.getPosition().getY() * scaleY;
            final int width = entity.getWidth() * scaleX;
            final int height = entity.getHeight() * scaleY;

            final Image imm = fetchImage(entity);
            g.drawImage(imm, x, y, width, height, null);
        }

        animationFrame = animationFrame == 1 ? 0 : 1;
    }

    /**
     * @param entity ...
     * @return ...
     * 
     */
    private Image fetchImage(final EntityView entity) {

        switch (entity.getType()) {
            case INVADER_1 -> {
                return invader1[animationFrame];
            }
            case INVADER_2 -> {
                return invader2[animationFrame];
            }
            case INVADER_3 -> {
                return invader3[animationFrame];
            }
            case INVADER_4 -> {
                return invader4[animationFrame];
            }
            case PLAYER -> {
                return player;
            }
            case PLAYER_SHOT -> {
                return shot[0];
            }
            case INVADER_SHOT -> {
                return shot[1];
            }
            case BUNKER -> {
                final int bunker2Life = 20;
                if (entity.getHealth() > bunker2Life) {
                    return bunker[2];
                }

                final int bunker1Life = 10;
                if (entity.getHealth() > bunker1Life) {
                    return bunker[1];
                }

                return bunker[0];
            }
        }

        return voidImage;
    }

    /**
     * useless, temporary, to be deleted...
     */
    public void useless() {
        player.notifyAll();
        viewInterface.notifyAll();
        entities = new ArrayList<>();
        entities.clear();
    }
}
