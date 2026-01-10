package it.unibo.scat.model.api;

import java.util.List;

import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;

/**
 * Read-only interface for the Model class, seen from the View.
 */
public interface ModelObservable {

    /**
     * @return ...
     *
     */
    List<EntityView> getEntities();

    /**
     * @return ...
     *
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
