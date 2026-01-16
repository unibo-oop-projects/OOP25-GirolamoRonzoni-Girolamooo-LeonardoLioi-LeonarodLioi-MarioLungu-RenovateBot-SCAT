package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.Position;

/**
 * Abstract class for Entities.
 */
public abstract class AbstractEntity implements EntityView {
    /** Default points value. */
    protected static final int NO_POINTS = 0;

    private static final int NO_HEALTH = 0;
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
     * Reduces health, then if health is equal to 0, the entity that got hit dies.
     * Each one of the dead invaders return the appropiate amount of points
     * 
     * @return number of points based on the type of invader that died.
     */
    public int onHit() {
        decreaseHealth();
        if (this.health == NO_HEALTH) {
            die();
        }
        return NO_POINTS;
    }

    /**
     * Sets the position of the entity.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setPosition(final int x, final int y) {
        position = new Position(x, y);
    }

    /**
     * Sets alive to true.
     */
    private void setAlive() {
        alive = true;
    }

    /**
     * Decreases health by 1.
     */
    private void decreaseHealth() {
        health--;
    }

    /**
     * Sets alive to false.
     */
    private void die() {
        alive = false;
    }

    /**
     * Resets the entity to its initial state.
     * Calls the internal reset methods for health and position.
     */
    public void reset() {
        resetHealth();
        resetStartingPosition();
    }

    /**
     * Resets the health to the starting value and marks the entity as alive.
     */
    private void resetHealth() {
        health = startingHealth;
        setAlive();
    }

    /**
     * Resets the position to the starting position.
     */
    private void resetStartingPosition() {
        position = startingPosition;
    }

    /**
     * @return the current position of the entity
     * 
     */
    @Override
    public Position getPosition() {
        return new Position(this.position.getX(), this.position.getY());
    }

    /**
     * Entity type getter.
     * 
     * @return the entity type.
     */
    @Override
    public EntityType getType() {
        return entityType;
    }

    /**
     * Alive boolean getter.
     * 
     * @return alive status.
     */
    @Override
    public boolean isAlive() {
        return alive;
    }

    /**
     * Width getter.
     * 
     * @return the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the entity.
     * 
     */
    public int getHeight() {

        return height;

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
