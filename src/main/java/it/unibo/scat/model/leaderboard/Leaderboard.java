package it.unibo.scat.model.leaderboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.unibo.scat.common.GameRecord;

/**
 * Class to manage the leaderboard: loading, saving and sorting scores.
 */
public class Leaderboard {
    private static final String RESOURCE_PATH = "/data/leaderboard.txt";
    private final List<GameRecord> games;
    private final Path leaderboardPath;

    /**
     * Leaderboard constructor, sets the save folder in the user home directory.
     * 
     * @param filename the name of the file to create
     */
    public Leaderboard(final String filename) {
        final String userHome = System.getProperty("user.home");
        this.leaderboardPath = Path.of(userHome, ".scat", filename);
        this.games = new ArrayList<>();

    }

    /**
     * Prepares the leaderboard. If the file does not exist,
     * it copies the default data from the resources.
     */
    public void initLeaderboard() {
        try {
            final Path parent = leaderboardPath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(leaderboardPath)) {
                try (InputStream input = Leaderboard.class.getResourceAsStream(RESOURCE_PATH)) {
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
            final int idxName = 0;
            final int idxScore = 1;
            final int idxLevel = 2;
            final int idxDate = 3;

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
     * Saves the list of games to the file.
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
     * Adds a new record and saves it to the disk.
     * 
     * @param newRecord the game result to add
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

}
