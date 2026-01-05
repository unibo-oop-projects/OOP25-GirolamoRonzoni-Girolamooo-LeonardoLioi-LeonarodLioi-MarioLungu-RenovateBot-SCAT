package it.unibo.scat.model.api;

/**
 * Interface for the Model class, seen from the Controller.
 */
public interface ModelInterface {

    /**
     * @return ...
     * 
     */
    int checkCollisions();

    /**
     * ...
     */
    void addPlayerShot();

    /**
     * ...
     */
    void initEverything();

    /**
     * ...
     */
    void update();

    /**
     * ...
     */
    void generateInvaderShot();

    /**
     * @param points ...
     * 
     */
    void updateScore(int points);
}
