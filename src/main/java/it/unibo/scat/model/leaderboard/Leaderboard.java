package it.unibo.scat.model.leaderboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import it.unibo.scat.common.GameRecord;
import it.unibo.scat.model.game.GameWorld;

/**
 * This class handles the leaderboard logic.
 */
public class Leaderboard {
    private final List<GameRecord> games;
    private final Path leaderboardPath;
    private static final String RESOURCE_PATH = "leaderboard/leaderboard.txt";

    /**
     * Leaderboard constructor.
     * 
     * @param filename the name of the file containing the leaderboard records
     * 
     */
    public Leaderboard(final String filename) {
        final String userHome = System.getProperty("user.home");

        this.leaderboardPath = Path.of(userHome, ".scat-game", filename);
        this.games = new ArrayList<>();

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

        try {
            final Path parent = leaderboardPath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(leaderboardPath)) {
                try (InputStream input = getClass().getResourceAsStream(RESOURCE_PATH)) {
                    if (input != null) {
                        Files.copy(input, leaderboardPath);
                    } else {

                        Files.createFile(leaderboardPath);
                    }
                }
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot create leaderboard file: " + leaderboardPath, e);
        }

        try (BufferedReader reader = Files.newBufferedReader(leaderboardPath, StandardCharsets.UTF_8)) {

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
                date = LocalDate.parse(field[idxDate].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                games.add(new GameRecord(name, score, level, date));

                line = reader.readLine();

            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load records from file: " + leaderboardPath + "Exception: ", e);
        }
        sortGames();

    }

    /**
     * Completely rewrites the leaderboard file with the current sorted records
     */
    public void updateFile() {
        sortGames();
        try (BufferedWriter writer = Files.newBufferedWriter(leaderboardPath)) {
            for (final GameRecord game : games) {
                writer.write(
                        game.getName() + ";" + game.getScore() + ";" + game.getLevel() + ";" + game.getDate() + "\n");
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot write leaderboard on file: " + leaderboardPath + "Exsception: ", e);
        }

    }

    /**
     * adds a new record to the leaderboard.
     * 
     * @param newRecord the record to add
     */
    public void addNewGameRecord(final GameRecord newRecord) {
        games.add(newRecord);
        updateFile();
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
                int r = Integer.compare(o2.getScore(), o1.getScore());
                if (r != 0) {
                    return r;
                }
                r = Integer.compare(o2.getLevel(), o1.getLevel());
                if (r != 0) {
                    return r;
                }
                r = o2.getDate().compareTo(o1.getDate());
                if (r != 0) {
                    return r;
                }
                return 0;
            }

        });

    }

    /**
     * Debug function, to remove.
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void printLeaderboard() {
        final Logger logger = Logger.getLogger(GameWorld.class.getName());
        for (final var x : games) {

            logger.info(x.getName() + " " + x.getScore() + " " + x.getLevel() + " " + x.getDate());
        }
    }

}
