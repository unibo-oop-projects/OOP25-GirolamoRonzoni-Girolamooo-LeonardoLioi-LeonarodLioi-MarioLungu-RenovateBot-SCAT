package it.unibo.scat.model.leaderboard;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class handles the leaderboard logic.
 */
@SuppressFBWarnings("UUF_UNUSED_FIELD")
public class Leaderboard {
    private List<Record> games;

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
    public void addNewRecord(final Record newRecord) {

    }

    /**
     * @return ...
     * 
     */
    public List<Record> getAllRecords() {
        return new ArrayList<>();
    }
}
