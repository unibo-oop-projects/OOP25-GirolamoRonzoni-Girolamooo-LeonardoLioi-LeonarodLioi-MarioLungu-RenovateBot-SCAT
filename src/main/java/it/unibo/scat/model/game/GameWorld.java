package it.unibo.scat.model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.model.game.entity.AbstractEntity;
import it.unibo.scat.model.game.entity.Bunker;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Player;
import it.unibo.scat.model.game.entity.Shot;

/**
 * ...
 */
// @SuppressFBWarnings("UUF_UNUSED_FIELD")
public class GameWorld {
    private static final String EI_EXPOSE_REP = "EI_EXPOSE_REP";
    private static final int INVADER_BOTTOM_LIMIT = 26;
    private static final int BORDER_LEFT = 1;
    private static final int BORDER_RIGHT = 59;
    private static final int BORDER_BOTTOM = 35;
    private static final int BORDER_UP = 1;
    private final List<AbstractEntity> entities;
    private final List<Invader> invaders;
    private final List<Shot> shots;
    private Player player;
    private final int worldWidth;
    private final int worldHeight;

    /**
     * @param wWidth  ...
     * @param wHeight ...
     * 
     */
    public GameWorld(final int wWidth, final int wHeight) {
        entities = new ArrayList<>();
        invaders = new ArrayList<>();
        shots = new ArrayList<>();
        player = null;

        worldWidth = wWidth;
        worldHeight = wHeight;
    }

    /**
     * @param filename ...
     * 
     */
    public void initEntities(final String filename) {
        final int idxType = 0;
        final int idxX = 1;
        final int idxY = 2;
        final int idxWidth = 3;
        final int idxHeight = 4;
        final int idxHealth = 5;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                getClass().getClassLoader().getResourceAsStream(filename)),
                        StandardCharsets.UTF_8))) {

            String line;
            int x;
            int y;
            int width;
            int height;
            int health;
            EntityType type;
            AbstractEntity newEntity;

            line = reader.readLine();
            while (line != null) {
                final String[] field = line.trim().split(";");

                type = EntityType.valueOf(field[idxType]);
                x = Integer.parseInt(field[idxX]);
                y = Integer.parseInt(field[idxY]);
                width = Integer.parseInt(field[idxWidth]);
                height = Integer.parseInt(field[idxHeight]);
                health = Integer.parseInt(field[idxHealth]);

                switch (type) {
                    case BUNKER -> {
                        newEntity = new Bunker(type, x, y, width, height, health);
                    }
                    case PLAYER -> {
                        newEntity = new Player(type, x, y, width, height, health);
                    }
                    default -> {
                        newEntity = new Invader(type, x, y, width, height, health);
                    }
                }

                addEntity(newEntity);
                line = reader.readLine();
            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load entities from file: " + filename + "Exception: ", e);
        }

    }

    /**
     * @return ...
     *
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = "Entities are a part of the game state, intentionally exposed")
    public List<AbstractEntity> getEntities() {
        return this.entities;
    }

    /**
     * @return ...
     *
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = "Shots are a part of the game state, intentionally exposed")
    public List<Shot> getShots() {
        return this.shots;
    }

    /**
     * @return ...
     *
     */
    public List<Invader> getInvaders() {
        return new ArrayList<>();
    }

    /**
     * @return ...
     *
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Player is part of the game state and intentionally exposed")
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @param e ...
     * 
     */
    public void addEntity(final AbstractEntity e) {
        entities.add(e);

        if (e instanceof Invader) {
            invaders.add((Invader) e);
        }

        if (e instanceof Shot) {
            shots.add((Shot) e);
        }
    }

    /**
     * @param e ...
     * 
     */
    public void removeEntity(final AbstractEntity e) {
        entities.remove(e);

        if (e instanceof Invader) {
            invaders.remove(e);
        }

        if (e instanceof Shot) {
            shots.remove(e);
        }
    }

    /**
     * ...
     */
    public void changeInvadersDirection() {
        if (Invader.getCurrDirection() == Direction.DOWN) {
            Invader.setNextDirection((Invader.getCurrDirection() == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT);
            Invader.setCurrDirection(Direction.DOWN);
        } else {
            Invader.setCurrDirection(Invader.getNextDirection());
            Invader.setNextDirection(Direction.DOWN);
        }
    }

    /**
     * @return ...
     * 
     */
    public boolean shouldInvadersChangeDirection() {
        if (Invader.getCurrDirection() == Direction.DOWN) {
            return true;
        }
        final boolean hitRight = didInvadersHitRight();
        final boolean hitLeft = didInvadersHitLeft();

        return hitLeft || hitRight;
    }

    /**
     * @return ...
     * 
     */
    private boolean didInvadersHitRight() {
        for (final Invader invader : invaders) {
            if ((invader.getPosition().getX() + invader.getWidth()) >= worldWidth) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return ...
     * 
     */
    private boolean didInvadersHitLeft() {
        for (final Invader invader : invaders) {
            if (invader.getPosition().getX() <= 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return ...
     * 
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * @return ...
     * 
     */
    public int getWorldHeight() {
        return worldHeight;
    }

    /**
     * Debug method.
     */
    public void printEntities() {
        final Logger logger = Logger.getLogger(GameWorld.class.getName());

        int i = 0;
        logger.info("PRINTING entities, size: " + entities.size());
        for (final AbstractEntity e : entities) {
            logger.info(i + ":" + e);
            i++;
        }

        i = 0;
        logger.info("\nPRINTING just invaders, size: " + invaders.size());
        for (final AbstractEntity e : invaders) {
            logger.info(i + ":" + e);
            i++;
        }

        i = 0;
        logger.info("\nPRINTING just shots, size: " + shots.size());
        for (final AbstractEntity e : shots) {
            logger.info(i + ":" + e);
            i++;
        }
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     * 
     * @return ...
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private Player tempUseAllFields() {
        player = new Player(EntityType.BUNKER, 10, 2, 1, 3, 4);
        return player;
    }

    /**
     * TEMPORARY METHOD TO PASS THE CHECKSTYLE.
     */
    public void update() {

    }

    /**
     * @return ...
     * 
     */
    public static int getInvaderBottomLimit() {
        return INVADER_BOTTOM_LIMIT;
    }

    /**
     * @return ...
     * 
     */
    public static int getBorderLeft() {
        return BORDER_LEFT;
    }

    /**
     * @return ...
     * 
     */
    public static int getBorderRight() {
        return BORDER_RIGHT;
    }

    /**
     * @return ...
     * 
     */
    public static int getBorderBottom() {
        return BORDER_BOTTOM;
    }

    /**
     * @return ...
     * 
     */
    public static int getBorderUp() {
        return BORDER_UP;
    }
}
