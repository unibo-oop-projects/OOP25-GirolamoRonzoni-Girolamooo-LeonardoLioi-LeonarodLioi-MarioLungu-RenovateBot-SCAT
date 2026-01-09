package it.unibo.scat.model.game;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.model.game.entity.AbstractEntity;

/**
 * ...
 */
@SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will be used by upcoming game logic")
public class CollisionReport {
    private List<AbstractEntity> entities;
    private boolean collisions;

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
    public boolean hasCollisions() {
        return false;
    }

}
