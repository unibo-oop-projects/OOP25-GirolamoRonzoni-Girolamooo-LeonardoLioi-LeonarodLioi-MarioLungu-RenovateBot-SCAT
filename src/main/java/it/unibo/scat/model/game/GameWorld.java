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
 * Class that represents the game world and holds the game's state.
 */
@SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
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
    private Invader bonusInvader;
    private final int worldWidth;
    private final int worldHeight;

    /**
     * GameWorld constructor.
     * 
     * @param wWidth  the world's width.
     * @param wHeight the world's height.
     */
    public GameWorld(final int wWidth, final int wHeight) {
        entities = new ArrayList<>();
        invaders = new ArrayList<>();
        shots = new ArrayList<>();
        player = null;
        bonusInvader = null;

        worldWidth = wWidth;
        worldHeight = wHeight;
    }

    /**
     * Initializes the game entities by loading them from a file.
     * 
     * @param filename the file containing the entities configuration
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
                        this.player = (Player) newEntity;
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
     * Entities getter.
     * 
     * @return the list of entities.
     */
    @SuppressFBWarnings(EI_EXPOSE_REP)
    public List<AbstractEntity> getEntities() {
        return entities;
    }

    /**
     * Shots getter.
     * 
     * @return the list of shots.
     */
    @SuppressFBWarnings(EI_EXPOSE_REP)
    public List<Shot> getShots() {
        return shots;
    }

    /**
     * Invaders getter.
     * 
     * @return the list of invaders.
     */
    @SuppressFBWarnings(EI_EXPOSE_REP)
    public List<Invader> getInvaders() {
        return invaders;
    }

    /**
     * @return the player entity
     *
     */
    @SuppressFBWarnings(EI_EXPOSE_REP)
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Adds an entity to the game world and the appropriate internal list.
     * 
     * @param e the entity to add
     */
    @SuppressFBWarnings(EI_EXPOSE_REP)

    public void addEntity(final AbstractEntity e) {
        entities.add(e);

        if (e instanceof Invader) {
            if (e.getType() == EntityType.INVADER_4) {
                bonusInvader = (Invader) e;
                return;
            } else {
                invaders.add((Invader) e);
                return;
            }
        }

        if (e instanceof Shot) {
            shots.add((Shot) e);
        }
    }

    /**
     * Removes an entity from the game world and from the appropriate internal list.
     * 
     * @param e the entity to remove
     */
    public void removeEntity(final AbstractEntity e) {
        entities.remove(e);
        if (e instanceof Invader) {
            if (e.getType() == EntityType.INVADER_4) {
                bonusInvader = null;
            } else {
                invaders.remove((Invader) e);
            }
        }

        if (e instanceof Shot) {
            shots.remove((Shot) e);
        }
    }

    /**
     * Changes the movement direction of the invaders.
     * 
     */
    public void changeInvadersDirection() {

        for (final Invader x : invaders) {
            if (x.getCurrDirection() == Direction.DOWN) {
                x.setCurrDirection(x.getNextDirection());
                x.setNextDirection(Direction.DOWN);
            } else {
                x.setNextDirection(x.getCurrDirection() == Direction.LEFT ? Direction.RIGHT : Direction.LEFT);
                x.setCurrDirection(Direction.DOWN);
            }
        }
    }

    /**
     * Checks if the invaders need to change direction.
     * 
     * @return true if the direction should change, false otherwise
     */
    public boolean shouldInvadersChangeDirection() {

        for (final Invader x : invaders) {
            if (x.getCurrDirection() == Direction.DOWN) {
                return true;
            }

        }
        final boolean hitRight = didInvadersHitRight();
        final boolean hitLeft = didInvadersHitLeft();

        return hitLeft || hitRight;
    }

    /**
     * Checks if any invader has reached the right border.
     * 
     * @return true if an invader hit the right border
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
     * Checks if any invader has reached the left border.
     * 
     * @return true if an invader hit the left border
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
     * @return the width of the game world
     * 
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * @return the height of the game world
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
     * Returns the vertical limit that invaders must not cross.
     * 
     * @return the invader bottom limit
     */
    public static int getInvaderBottomLimit() {
        return INVADER_BOTTOM_LIMIT;
    }

    /**
     * Returns the left border of the game world.
     * 
     * @return the left border value
     */
    public static int getBorderLeft() {
        return BORDER_LEFT;
    }

    /**
     * Returns the right border of the game world.
     * 
     * @return the right border value
     */
    public static int getBorderRight() {
        return BORDER_RIGHT;
    }

    /**
     * Returns the bottom border of the game world.
     * 
     * @return the bottom border value
     */
    public static int getBorderBottom() {
        return BORDER_BOTTOM;
    }

    /**
     * Returns the upper border of the game world.
     * 
     * @return the upper border value
     */
    public static int getBorderUp() {
        return BORDER_UP;
    }

    /**
     * @return returns the bonusInvader, it could also be null!
     * 
     */
    @SuppressFBWarnings(EI_EXPOSE_REP)
    public Invader getBonusInvader() {
        return bonusInvader;
    }

    /**
     * @return returns true if the bonusInvader is alive (not null).
     *         Because when the bonusInvader is not alive it is setted to null.
     * 
     */
    public boolean isBonusInvaderAlive() {
        return bonusInvader != null;
    }

    /**
     * .Creates and adds a bonus invader at a random side of the game world.
     */
    public void spawnBonusInvader() {
        final int leftPos = -1;
        final int rightPos = 61;
        final boolean left = new java.util.Random().nextBoolean();
        final Direction direction = left ? Direction.RIGHT : Direction.LEFT;
        final int x = left ? leftPos : rightPos;
        final int y = 2;

        final Invader invader = new Invader(EntityType.INVADER_4, x, y, 3, 2, 1);

        for (final Invader i : invaders) {
            i.setCurrDirection(direction);
            i.setNextDirection(direction);
        }
        addEntity(invader);
    }
}
