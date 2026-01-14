package it.unibo.scat.model.api;

import java.util.List;

import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;

/**
 * Read-only interface for the Model class, seen from the View.
 */
public interface ModelObservable {

    /**
     * Entity getter.
     *
     * @return list of entities.
     */
    List<EntityView> getEntities();

    /**
     * Username getter.
     *
     * @return the username.
     */
    String getUsername();

    /**
     * @return ...
     *
     */
    int getScore();

    /**
     * @return ...
     *
     */
    List<GameRecord> getLeaderboard();
}
