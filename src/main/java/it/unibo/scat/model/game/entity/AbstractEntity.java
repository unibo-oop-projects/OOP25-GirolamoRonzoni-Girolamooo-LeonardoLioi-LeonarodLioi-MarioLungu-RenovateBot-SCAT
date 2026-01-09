package it.unibo.scat.model.game.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.Position;

/**
 * Abstract class for Entities.
 */
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@SuppressFBWarnings("UUF_UNUSED_FIELD")
public abstract class AbstractEntity {
    private boolean alive;
    private int health;
    private int startingHealth;
    private int width;
    private int height;
    private Position position;
    private Position startingPosition;
    private EntityType entityType;

    /*
     *
     *
     * @Override
     * public int onHit() {
     * return -1;
     * }
     */

    /**
     *
     */
    protected void setAlive() {

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
}
