package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Invader" entity.
 */
public class Invader extends AbstractEntity {

    /**
     * @param type   ...
     * @param x      ...
     * @param y      ...
     * @param width  ...
     * @param height ...
     * @param health ...
     * 
     */
    public Invader(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

    /**
     * ...
     */
    public void move() {

    }

    /**
     * ...
     */
    public void moveLeft() {

    }

    /**
     * ...
     *
     */
    public void moveRight() {

    }

    /**
     * ...
     */
    public void moveDown() {

    }

}
