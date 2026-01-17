package it.unibo.scat.model.leaderboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
// import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

// import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.GameRecord;
// import it.unibo.scat.model.game.entity.AbstractEntity;
// import it.unibo.scat.model.game.entity.Bunker;
// import it.unibo.scat.model.game.entity.Invader;
// import it.unibo.scat.model.game.entity.Player;

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
        final int idxName = 0;
        final int idxScore = 1;
        final int idxLevel = 2;
        final int idxDate = 3;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                getClass().getClassLoader().getResourceAsStream(filename)),
                        StandardCharsets.UTF_8))) {

            String line;
            String name;
            int score;
            int level;
            LocalDate date;

            line = reader.readLine();
            while (line != null) {
                final String[] field = line.trim().split(";");

                name = String.valueOf(field[idxName]);
                score = Integer.parseInt(field[idxScore]);
                level = Integer.parseInt(field[idxLevel]);
                date = LocalDate.parse(field[idxDate].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                games.add(new GameRecord(name, score, level, date));

                line = reader.readLine();

            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load records from file: " + filename + "Exception: ", e);
        }

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

    /**
     * ...
     */
    private void printLeaderboard() {
        for (final var x : games) {
            System.out.println(x.getName() + x.getScore() + x.getLevel() + x.getDate());
        }
    }

}
