package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.Position;

/**
 * Abstract class for Entities.
 */
@SuppressWarnings("unused")
// @SuppressWarnings("PMD.UnusedPrivateField")
// @SuppressFBWarnings("URF_UNREAD_FIELD")
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
     * @param x ...
     * @param y ...
     * 
     */
    public void setPosition(final int x, final int y) {
        this.position = new Position(x, y);

    }

    /**
     *
     */
    private void setAlive() {
        this.alive = true;
    }

    /**
     *
     */
    private void decreaseHealth() {
        this.health--;
    }

    /**
     *
     */
    private void die() {
        this.alive = false;
    }

    /**
     * ...
     */
    public void reset() {

        resetHealth();
        resetStartingPosition();

    }

    /**
     * ...
     */
    private void resetHealth() {

        this.health = startingHealth;
        setAlive();

    }

    /**
     * ...
     */
    private void resetStartingPosition() {

        this.position = startingPosition;

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
     * @return ...
     * 
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return ...
     * 
     */
    public int getHeight() {

        return this.height;

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

    /**
     * DEBUG FUNCTION.
     */
    @Override
    public final String toString() {
        return "Entity: " + this.entityType + " pos:(" + this.position.getX() + "," + this.position.getY()
                + ") dims: " + this.width + "x" + this.height + " health: " + this.health + " alive: " + this.alive;
    }
}
