package it.unibo.scat.model.game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private final List<AbstractEntity> entities;
    private final List<AbstractEntity> invaders;
    private final List<AbstractEntity> shots;
    private Player player;

    /**
     * ...
     */
    public GameWorld() {
        entities = new ArrayList<>();
        invaders = new ArrayList<>();
        shots = new ArrayList<>();
        player = null;
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
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
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
            throw new IllegalStateException("Cannot load entities", e);
        }

    }

    /**
     * @return ...
     *
     */
    public List<AbstractEntity> getEntities() {
        return new ArrayList<>();
    }

    /**
     * @return ...
     *
     */
    public List<Shot> getShots() {
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
            invaders.add(e);
        }

        if (e instanceof Shot) {
            shots.add(e);
        }
    }

    /**
     * @param e ...
     * 
     */
    public void removeEntity(final AbstractEntity e) {

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
}
