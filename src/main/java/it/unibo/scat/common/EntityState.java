package it.unibo.scat.common;

/**
 * Interface for the Entity class.
 */
public interface EntityState {

    /**
     * EntityType getter.
     * 
     * @return the type of entity.
     */
    EntityType getType();

    /**
     * Alive boolean getter.
     * 
     * @return true if the entity is alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Position getter.
     * 
     * @return the position.
     */
    Position getPosition();

    /**
     * ...
     * 
     * @return ...
     */
    int getWidth();

    /**
     * ...
     * 
     * @return ...
     */
    int getHeight();

    /**
     * ...
     * 
     * @return ...
     */
    int getHealth();
}
