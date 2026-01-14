package it.unibo.scat.model.leaderboard;

import java.util.ArrayList;
import java.util.List;

import it.unibo.scat.common.GameRecord;

/**
 * This class handles the leaderboard logic.
 */
public class Leaderboard {
    private final List<GameRecord> games;

    /**
     * Empty default Leaderboard constructor.
     */
    public Leaderboard() {
        this.games = new ArrayList<>();
    }

    /**
     * Initializes the leaderboard.
     * 
     * @param filename the file of the leaderboard.
     */
    public void initLeaderboard(final String filename) {

    }

    /**
     * Updates the leaderboard file.
     */
    public void updateFile() {

    }

    /**
     * adds a new record to the leaderboard.
     * 
     * @param newRecord the record to add
     */
    public void addNewGameRecord(final GameRecord newRecord) {
        games.add(newRecord);
    }

    /**
     * @return all the records of the leaderboard.
     * 
     */
    public List<GameRecord> getAllRecords() {
        return List.copyOf(games);
    }
}
