package it.unibo.scat.model.api;

import java.util.List;

import it.unibo.scat.common.EntityView;
import it.unibo.scat.common.GameRecord;

/**
 * Read-only interface for the Model class, seen from the View.
 */
public interface ModelState {

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
      * Returns the current game score.
      *
      * @return the score
      */
     int getScore();

     /**
      * Returns the list of game records stored in the leaderboard.
      *
      * @return the leaderboard records
      */
     List<GameRecord> getLeaderboard();

     /**
      * Returns the health of the entity "player".
      * 
      * @return the health of the players (0,1,2 or 3)
      */
     int getPlayerHealth();

     /**
      * @return ...
      * 
      */
     int getInvadersStepMs();

     /**
      * @return ...
      * 
      */
     int getInvadersAccMs();

     /**
      * @return ...
      * 
      */
     int getBonusInvaderAccMs();

     /**
      * @return ...
      * 
      */
     int getLevel();
}
