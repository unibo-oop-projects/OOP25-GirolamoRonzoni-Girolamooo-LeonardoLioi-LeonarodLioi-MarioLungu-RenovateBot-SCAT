package it.unibo.scat.model.leaderboard;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameRecord;

/**
 * This class handles the leaderboard logic.
 */
@SuppressFBWarnings("UUF_UNUSED_FIELD")
public class Leaderboard {
    private List<GameRecord> games;

    /**
     * Costruttore vuoto di default.
     */
    public Leaderboard() {
        // Default constructor
    }

    /**
     * @param filename ...
     * 
     */
    public void initLeaderboard(final String filename) {

    }

    /**
     * ...
     */
    public void updateFile() {

    }

    /**
     * @param newRecord ...
     * 
     */
    public void addNewRecord(final GameRecord newRecord) {

    }

    /**
     * @return ...
     * 
     */
    public List<GameRecord> getAllRecords() {
        return new ArrayList<>();
    }
}
