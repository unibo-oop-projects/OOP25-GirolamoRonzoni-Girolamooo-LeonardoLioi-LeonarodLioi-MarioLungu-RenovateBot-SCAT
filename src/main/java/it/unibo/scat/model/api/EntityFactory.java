package it.unibo.scat.model.api;

import it.unibo.scat.common.EntityType;
import it.unibo.scat.model.game.entity.AbstractEntity;

/**
 * ...
 */
@FunctionalInterface
public interface EntityFactory {

    /**
     * @param type ...
     * @param x    ...
     * @param y    ...
     * @return ...
     * 
     */
    AbstractEntity createEntity(EntityType type, int x, int y);
}
