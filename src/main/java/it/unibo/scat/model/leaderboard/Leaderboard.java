package it.unibo.scat.model.leaderboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import it.unibo.scat.common.GameRecord;

/**
 * This class handles the leaderboard logic.
 */
public class Leaderboard {
    private final List<GameRecord> games;
    private final String leaderboardFile;

    /**
     * Empty default Leaderboard constructor.
     * 
     * @param leaderboardFile the file of the leaderboard.
     */
    public Leaderboard(final String leaderboardFile) {
        this.games = new ArrayList<>();
        this.leaderboardFile = leaderboardFile;
    }

    /**
     * Initializes the leaderboard.
     * 
     */
    public void initLeaderboard() {
        final int idxName = 0;
        final int idxScore = 1;
        final int idxLevel = 2;
        final int idxDate = 3;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                getClass().getClassLoader().getResourceAsStream(leaderboardFile)),
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
            throw new IllegalStateException("Cannot load records from file: " + leaderboardFile + "Exception: ", e);
        }

    }

    /**
     * Updates the leaderboard file.
     */
    public void updateFile() {

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(leaderboardFile), StandardCharsets.UTF_8))) {

            for (final GameRecord g : games) {
                writer.write(g.getName() + ";");
                writer.write(g.getScore() + ";");
                writer.write(g.getLevel() + ";");
                writer.write(g.getDate() + ";");
                writer.newLine();
            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot write records into file: " + leaderboardFile + "Exception: ", e);
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

    /**
     * Sorts the game records by score, then by level, then by date.
     */
    public void sortGames() {
        games.sort(new Comparator<>() {

            @Override
            public int compare(final GameRecord o1, final GameRecord o2) {
                int r = Integer.compare(o1.getScore(), o2.getScore());
                if (r != 0) {
                    return r;
                }
                r = Integer.compare(o1.getLevel(), o2.getLevel());
                if (r != 0) {
                    return r;
                }
                r = o1.getDate().compareTo(o2.getDate());
                if (r != 0) {
                    return r;
                }
                return 0;
            }

        });

    }

    // /**
    // * ...
    // */
    // private void printLeaderboard() {
    // final Logger logger = Logger.getLogger(GameWorld.class.getName());
    // for (final var x : games) {

    // logger.info(x.getName() + x.getScore() + x.getLevel() + x.getDate());
    // }
    // }

}
