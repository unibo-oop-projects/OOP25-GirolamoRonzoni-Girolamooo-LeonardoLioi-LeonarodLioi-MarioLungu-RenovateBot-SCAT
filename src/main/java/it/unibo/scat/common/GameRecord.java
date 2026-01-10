package it.unibo.scat.common;

import java.time.LocalDate;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class represents a single record of the Leaderboard.
 */
// @SuppressFBWarnings({ "UUF_UNUSED_FIELD", "URF_UNREAD_FIELD" })
@SuppressFBWarnings("URF_UNREAD_FIELD")
public class GameRecord {
    private final String name;
    private final int score;
    private final int level;
    private final LocalDate date;

    /**
     * @param name  ...
     * @param score ...
     * @param level ...
     * @param date  ...
     * 
     */
    public GameRecord(final String name, final int score, final int level, final LocalDate date) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.date = date;
    }
}
