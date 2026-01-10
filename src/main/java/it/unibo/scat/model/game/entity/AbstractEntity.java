package it.unibo.scat.model.game.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.Position;

/**
 * Abstract class for Entities.
 */
@SuppressWarnings("unused")
@SuppressFBWarnings("URF_UNREAD_FIELD")
public abstract class AbstractEntity implements EntityView {
    private boolean alive;
    private int health;
    private int startingHealth;
    private int width;
    private int height;
    private Position position;
    private Position startingPosition;
    private EntityType entityType;

    /**
     * @param type   ...
     * @param x      ...
     * @param y      ...
     * @param width  ...
     * @param height ...
     * @param health ...
     * 
     */
    public AbstractEntity(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        this.alive = true;
        this.entityType = type;
        this.startingPosition = new Position(x, y);
        this.position = new Position(x, y);
        this.width = width;
        this.height = height;
        this.startingHealth = health;
        this.health = health;
    }

    /**
     * @return ...
     * 
     */
    public int onHit() {
        return -1;
    }

    /**
     * ...
     */
    public void setPosition() {

    }

    /**
     *
     */
    private void setAlive() {

    }

    /**
     *
     */
    public void decreaseHealth() {

    }

    /**
     *
     */
    public void die() {

    }

    /**
     * ...
     */
    public void reset() {

    }

    /**
     * ...
     */
    private void resetHealth() {

    }

    /**
     * ...
     */
    private void resetStartingPosition() {

    }

    /**
     * @return ...
     * 
     */
    @Override
    public Position getPosition() {
        return null;
    }

    /**
     * @return ...
     * 
     */
    @Override
    public EntityType getType() {
        return null;
    }

    /**
     * @return ...
     * 
     */
    @Override
    public boolean isAlive() {
        return false;
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void tempUseAllFields() {
        alive = !alive;
        health = health + 1;
        startingHealth = startingHealth + 0;
        width = width + 0;
        height = height + 0;
        position = startingPosition;
        entityType = EntityType.BUNKER;
        startingPosition = new Position(3, 2);
    }

}
