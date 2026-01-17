package it.unibo.scat.model.leaderboard;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import it.unibo.scat.common.GameRecord;

/**
 * This class handles the leaderboard logic.
 */
public class Leaderboard {
    private final List<GameRecord> games;
    private final String filename;

    /**
     * Leaderboard constructor.
     * 
     * @param filename the name of the file containing the leaderboard records
     * 
     */
    public Leaderboard(final String filename) {
        this.filename = filename;
        games = new ArrayList<>();
    }

    /**
     * Initializes the leaderboard.
     * 
     * @param file the file of the leaderboard.
     */
    public void initLeaderboard(final String file) {

    }

    /**
     * Writes ex-novo the leaderboard file.
     */
    public void updateFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename))) {
            for (final GameRecord game : games) {
                writer.write(
                        game.getName() + ";" + game.getScore() + ";" + game.getLevel() + ";" + game.getDate() + "\n");
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot write leaderboard on file: " + filename + "Exception: ", e);
        }

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
