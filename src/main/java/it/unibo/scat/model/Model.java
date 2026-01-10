package it.unibo.scat.model;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameState;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.model.api.ModelObservable;
import it.unibo.scat.model.game.GameLogic;
import it.unibo.scat.model.game.GameWorld;
import it.unibo.scat.model.leaderboard.Leaderboard;

/**
 * The main class for the "Model" section of the MVC pattern.
 */
@SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will be used by upcoming game logic")
public class Model implements ModelInterface, ModelObservable {
    private int score;
    private int level;
    private String username;
    private GameState gameState;
    private Leaderboard leaderboard;
    private GameWorld gameWorld;
    private GameLogic gameLogic;

    /**
     * ...
     */
    public void increaseLevel() {

    }

    /**
     * @param points ...
     *
     */
    public void updateScore(final int points) {

    }

    /**
     * ...
     */
    @Override
    public void addPlayerShot() {

    }

    /**
     * ...
     */
    @Override
    public void endGame() {

    }

    /**
     * ...
     */
    @Override
    public void initEverything() {

    }

    /**
     * @param direction ...
     * @return ...
     * 
     */
    @Override
    public int movePlayer(final int direction) {
        return 0;
    }

    @Override
    public void pause() {

    }

    /**
     * ...
     */
    @Override
    public void resetGame() {

    }

    /**
     * ...
     */
    @Override
    public void resume() {

    }

    /**
     * ...
     */
    @Override
    public void update() {

    }

    /**
     * @return ...
     * 
     */
    @Override
    public List<EntityView> getEntities() {
        return new ArrayList<>();
    }

    /**
     * @return ...
     * 
     */
    @Override
    public List<Record> getLeaderboard() {
        return new ArrayList<>();
    }

    /**
     * @return ...
     * 
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     * @return ...
     * 
     */
    @Override
    public String getUsername() {
        return null;
    }

}
