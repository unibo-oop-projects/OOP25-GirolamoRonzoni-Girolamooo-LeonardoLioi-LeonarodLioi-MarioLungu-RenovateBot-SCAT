package it.unibo.scat.common;

import java.time.LocalDate;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class represents a single record of the Leaderboard.
 */
@SuppressFBWarnings("UUF_UNUSED_FIELD")
public class Record {
    private String name;
    private int score;
    private int level;
    private LocalDate date;
}
