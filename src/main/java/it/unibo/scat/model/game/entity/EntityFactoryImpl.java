package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.model.api.EntityFactory;

/**
 * ...
 */
public class EntityFactoryImpl implements EntityFactory {

    @Override
    public AbstractEntity createEntity(final EntityType type, final int x, final int y) {
        AbstractEntity newEntity;

        switch (type) {
            case BUNKER -> {
                newEntity = new Bunker(type, x, y, Constants.BUNKER_WIDTH, Constants.BUNKER_HEIGHT,
                        Constants.BUNKER_HEALTH);
            }
            case PLAYER -> {
                newEntity = new Player(type, x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT,
                        Constants.PLAYER_HEALTH);
            }
            case PLAYER_SHOT, INVADER_SHOT -> {
                newEntity = new Player(type, x, y, Constants.SHOT_WIDTH, Constants.SHOT_HEIGHT,
                        Constants.SHOT_HEALTH);
            }
            default -> {
                newEntity = new Invader(type, x, y, Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                        Constants.INVADERS_HEALTH);
            }
        }

        return newEntity;
    }
}
