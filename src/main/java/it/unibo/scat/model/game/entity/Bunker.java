package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Bunker" entity.
 */
public class Bunker extends AbstractEntity {

    /**
     * @param type   ...
     * @param x      ...
     * @param y      ...
     * @param width  ...
     * @param height ...
     * @param health ...
     * 
     */
    public Bunker(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

    /**
     * @return ...
     *
     */
    @Override
    public int onHit() {
        return -1;
    }

    /**
     * ...
     */
    @Override
    public void reset() {

    }

}
