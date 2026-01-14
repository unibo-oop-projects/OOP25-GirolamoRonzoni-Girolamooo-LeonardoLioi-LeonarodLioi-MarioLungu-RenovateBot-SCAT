package it.unibo.scat.model;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
     * Model constructor.
     */
    public Model() {
        this.gameWorld = new GameWorld(WORLD_WIDTH, WORLD_HEIGHT); // to remove when unecessary
        this.leaderboard = new Leaderboard(); // to remove when unecessary
        this.gameLogic = new GameLogic(gameWorld);
    }

    /**
     * increses the level.
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

    /**
     * Adds player's shot.
     */
    @Override
    public void addPlayerShot() {
        gameLogic.addPlayerShot();
    }

    /**
     * Ends the game.
     */
    @Override
    public void endGame() {
        gameState = GameState.GAMEOVER;
    }

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

    /**
     * Moves the player in the given direction.
     * Gets the player from the gameWorld and updates its position.
     * 
     * @param direction the movement direction
     */
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

    /**
     * Pauses the game.
     * Sets the game state to PAUSE.
     */
    @Override
    public void pauseGame() {
        gameState = GameState.PAUSE;
    }

    /**
     * Resets all entities throught the gameLogic and restores score and difficulty.
     */
    @Override
    public void resetGame() {
        gameLogic.resetEntities();
        score = 0;
        level = 0;
    }

    /**
     * Resumes the game.
     * Sets the game state to RUNNING.
     */
    @Override
    public void resumeGame() {
        gameState = GameState.RUNNING;
    }

    /**
     * Updates whether the invaders should change direction, while calling multiple
     * other methods.
     */
    @Override
    public void update() {
        final CollisionReport collisionReport;
        final int newPoints;

        gameLogic.moveEntities();

        collisionReport = gameLogic.checkCollisions();
        newPoints = gameLogic.handleCollisionReport(collisionReport);

        gameLogic.removeDeadShots();
        updateScore(newPoints);

        if (gameWorld.shouldInvadersChangeDirection()) {
            gameWorld.changeInvadersDirection();
        }

        if (gameLogic.checkGameEnd() != GameResult.STILL_PLAYING) {
            endGame();
        }
    }

    /**
     * Entities getter.
     * 
     * @return the entity list.
     */
    @Override
    public List<EntityView> getEntities() {
        return new ArrayList<>(this.gameWorld.getEntities());
    }

    /**
     * Leaderboard getter.
     * 
     * @return the leaderboard.
     */
    @Override
    public List<GameRecord> getLeaderboard() {
        return leaderboard.getAllRecords();
    }

    /**
     * Score getter.
     * 
     * @return the score.
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Username getter.
     * 
     * @return the username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Username setter.
     * 
     * @param username the username that the player chose.
     */
    @Override
    public void setUsername(final String username) {
        this.username = username;
    }
}
