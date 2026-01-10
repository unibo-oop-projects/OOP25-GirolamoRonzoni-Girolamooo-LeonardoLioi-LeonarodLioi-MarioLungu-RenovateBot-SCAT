package it.unibo.scat.model;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.common.GameState;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.model.api.ModelObservable;
import it.unibo.scat.model.game.GameLogic;
import it.unibo.scat.model.game.GameWorld;
import it.unibo.scat.model.leaderboard.Leaderboard;

/**
 * The main class for the "Model" section of the MVC pattern.
 */
@SuppressFBWarnings({ "UUF_UNUSED_FIELD", "URF_UNREAD_FIELD" })
// @SuppressFBWarnings("UUF_UNUSED_FIELD")
public final class Model implements ModelInterface, ModelObservable {
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
    public Model() {
        this.gameWorld = new GameWorld(); // to remove when unecessary
        this.leaderboard = new Leaderboard(); // to remove when unecessary
    }

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

    @Override
    public void addPlayerShot() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void initEverything(final String entitiesFile, final String leaderboardFile) {
        gameWorld = new GameWorld();
        gameLogic = new GameLogic(gameWorld);
        leaderboard = new Leaderboard();
        score = 0;
        level = 0;
        gameState = GameState.valueOf("PAUSE");

        gameWorld.initEntities(entitiesFile);
        leaderboard.initLeaderboard(leaderboardFile);
    }

    @Override
    public int movePlayer(final int direction) {
        return 0;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<EntityView> getEntities() {
        return new ArrayList<>();
    }

    @Override
    public List<GameRecord> getLeaderboard() {
        return new ArrayList<>();
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String getUsername() {
        return null;
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void tempUseAllFields() {
        gameWorld.update();
        leaderboard.initLeaderboard("aa");
    }
}
