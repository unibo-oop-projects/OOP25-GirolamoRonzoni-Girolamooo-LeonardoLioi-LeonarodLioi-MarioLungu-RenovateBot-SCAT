package it.unibo.scat.model.game.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.Position;

/**
 * Abstract class for Entities.
 */
@SuppressWarnings("unused")
@SuppressFBWarnings("UUF_UNUSED_FIELD")
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
        return true;
    }
}
