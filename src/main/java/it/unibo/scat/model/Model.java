package it.unibo.scat.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameState;
import it.unibo.scat.model.game.GameLogic;
import it.unibo.scat.model.game.GameWorld;
import it.unibo.scat.model.leaderboard.Leaderboard;

/**
 * The main class for the "Model" section of the MVC pattern.
 */
@SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will be used by upcoming game logic")
public class Model {
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
    void increaseLevel() {

    }

}
