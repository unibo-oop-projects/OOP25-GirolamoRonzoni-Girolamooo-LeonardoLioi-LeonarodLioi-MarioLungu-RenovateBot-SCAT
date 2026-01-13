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
     * Costruttore vuoto di default.
     */
    public Leaderboard() {
        this.games = new ArrayList<>();
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
    public void addNewGameRecord(final GameRecord newRecord) {
        games.add(newRecord);
    }

    /**
     * @return ...
     * 
     */
    public List<GameRecord> getAllRecords() {
        return List.copyOf(games);
    }
}
