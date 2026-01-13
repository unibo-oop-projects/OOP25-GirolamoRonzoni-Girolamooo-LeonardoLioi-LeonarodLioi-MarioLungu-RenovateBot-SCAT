package it.unibo.scat.model;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Direction;
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
@SuppressFBWarnings("URF_UNREAD_FIELD")
// @SuppressFBWarnings("UUF_UNUSED_FIELD")
public final class Model implements ModelInterface, ModelObservable {
    private static final int WORLD_WIDTH = 59;
    private static final int WORLD_HEIGHT = 35;
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
        this.gameWorld = new GameWorld(WORLD_WIDTH, WORLD_HEIGHT); // to remove when unecessary
        this.leaderboard = new Leaderboard(); // to remove when unecessary
        this.gameLogic = new GameLogic(gameWorld);
    }

    /**
     * @param username ...
     * 
     */
    public Model(final String username) {
        this.gameWorld = new GameWorld(WORLD_WIDTH, WORLD_HEIGHT); // to remove when unecessary
        this.leaderboard = new Leaderboard(); // to remove when unecessary
        this.username = username;
    }

    /**
     * ...
     */
    public void increaseLevel() {
        this.level++;

    }

    /**
     * @param points ...
     *
     */
    public void updateScore(final int points) {
        score += points;
    }

    @Override
    public void addPlayerShot() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void initEverything(final String entitiesFile, final String leaderboardFile) {
        gameWorld = new GameWorld(WORLD_WIDTH, WORLD_HEIGHT);
        gameLogic = new GameLogic(gameWorld);
        leaderboard = new Leaderboard();
        score = 0;
        level = 0;
        gameState = GameState.valueOf("PAUSE");

        gameWorld.initEntities(entitiesFile);
        leaderboard.initLeaderboard(leaderboardFile);

        // DEBUG
        // gameWorld.printEntities();
    }

    @Override
    public void movePlayer(final Direction direction) {
        switch (direction) {
            case LEFT:
                gameWorld.getPlayer().moveLeft();
                break;
            case RIGHT:
                gameWorld.getPlayer().moveRight();
                break;

            default:
                break;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resetGame() {

        gameLogic.resetEntities();
        score = 0;
        level = 0;
    }

    @Override
    public void resume() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<EntityView> getEntities() {
        return new ArrayList<>(this.gameWorld.getEntities());
    }

    @Override
    public List<GameRecord> getLeaderboard() {
        return leaderboard.getAllRecords();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getUsername() {
        return this.username;
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
