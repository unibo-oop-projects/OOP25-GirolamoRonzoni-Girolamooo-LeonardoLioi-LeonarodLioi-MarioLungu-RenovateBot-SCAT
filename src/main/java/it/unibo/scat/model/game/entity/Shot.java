package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Shot" entity.
 */
public class Shot extends AbstractEntity {
    private final Direction direction;

    /**
     * @param type      ...
     * @param x         ...
     * @param y         ...
     * @param width     ...
     * @param height    ...
     * @param health    ...
     * @param direction ...
     * 
     */
    public Shot(final EntityType type, final int x, final int y, final int width, final int height,
            final int health, final Direction direction) {
        super(type, x, y, width, height, health);
        this.direction = direction;
    }

    /**
     * ...
     */
    public void move() {

        switch (direction) {

            case UP:
                setPosition(getPosition().getX(), getPosition().getY() - 1);
                break;

            case DOWN:
                setPosition(getPosition().getX(), getPosition().getY() + 1);
                break;
            default:
                break;
        }

    }

    /**
     * @return ...
     * 
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     * 
     * @return ...
     * 
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private Direction tempUseAllFields() {
        return this.direction;
    }
}
