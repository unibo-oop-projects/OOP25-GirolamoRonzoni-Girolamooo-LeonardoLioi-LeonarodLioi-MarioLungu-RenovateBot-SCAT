package it.unibo.scat.model;

import java.util.List;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.common.GameState;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.model.api.ModelObservable;
import it.unibo.scat.model.game.CollisionReport;
import it.unibo.scat.model.game.GameLogic;
import it.unibo.scat.model.game.GameWorld;
import it.unibo.scat.model.leaderboard.Leaderboard;

/**
 * The main class for the "Model" section of the MVC pattern.
 */
// @SuppressFBWarnings("URF_UNREAD_FIELD")
public final class Model implements ModelInterface, ModelObservable {
    private static GameState gameState;
    private int score;
    private int level;

    private String username;
    private Leaderboard leaderboard;
    private GameWorld gameWorld;
    private GameLogic gameLogic;

    /**
     * Creates and initializes the list of entities and the entity object, by
     * reading them from file.
     * Creates and initializes the leaderboard.
     * 
     * @param entitiesFile    the file of entities.
     * @param leaderboardFile the leaderboard file.
     */
    @Override
    public void initEverything(final String entitiesFile, final String leaderboardFile) {
        gameWorld = new GameWorld();
        gameLogic = new GameLogic(gameWorld);
        leaderboard = new Leaderboard(leaderboardFile);
        score = 0;
        level = 0;
        setGameState(GameState.PAUSE);

        gameWorld.initEntities(entitiesFile);
        leaderboard.initLeaderboard();

        // DEBUG
        // gameWorld.printEntities();
    }

    /**
     * increses the level by one.
     */
    public void increaseLevel() {
        this.level++;
    }

    /**
     * @param points the points to add to the current score.
     * 
     */
    public void updateScore(final int points) {
        score += points;
    }

    @Override
    public void addPlayerShot() {
        gameLogic.addPlayerShot();
    }

    @Override
    public void movePlayer(final Direction direction) {
        if (gameLogic.canPlayerMove(direction)) {
            gameWorld.getPlayer().move(direction);
        }
    }

    @Override
    public void resetGame() {
        gameLogic.resetEntities();
        score = 0;
        level = 0;
    }

    /**
     * @param state ...
     * 
     */
    public static void setGameState(final GameState state) {
        gameState = state;
    }

    /**
     * @return ...
     * 
     */
    public static GameState getGameState() {
        return gameState;
    }

    @Override
    public void update() {
        final CollisionReport collisionReport;
        final int newPoints;

        gameLogic.handleInvadersMovement();
        gameLogic.handleShotsMovement();
        gameLogic.handleBonusInvader();

        if (!gameLogic.areInvadersAlive(gameWorld.getInvaders())) {
            increaseLevel();
            gameLogic.resetEntities();
        }

        collisionReport = gameLogic.checkCollisions();
        newPoints = gameLogic.handleCollisionReport(collisionReport);
        updateScore(newPoints);

        gameLogic.removeDeadShots();

        if (gameLogic.checkGameEnd() != GameResult.PLAYING) {
            setGameState(GameState.GAMEOVER);
        }
    }

    @Override
    public List<EntityView> getEntities() {
        return List.copyOf(gameWorld.getEntities());
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
        return username;
    }

    @Override
    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public int getPlayerHealth() {
        if (gameWorld.getPlayer() == null) {
            return 0;
        }

        return gameWorld.getPlayer().getHealth();
    }

    /**
     * @return ...
     * 
     */
    public int getLevel() {
        return level;
    }
}
