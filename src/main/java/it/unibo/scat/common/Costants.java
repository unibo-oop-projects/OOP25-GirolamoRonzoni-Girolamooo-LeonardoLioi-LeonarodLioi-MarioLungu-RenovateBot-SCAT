package it.unibo.scat.common;

/**
 * ...
 */
public final class Costants {
    // WORLD
    public static final int BORDER_RIGHT = 59;
    public static final int BORDER_BOTTOM = 36;
    public static final int BORDER_LEFT = 1;
    public static final int BORDER_UP = 1;

    public static final int INVADER_BOTTOM_LIMIT = 26;

    // POINTS
    public static final int POINTS_INVADER1 = 10;
    public static final int POINTS_INVADER2 = 20;
    public static final int POINTS_INVADER3 = 30;
    public static final int POINTS_BONUS_INVADER = 100;

    // GAMELOOP
    public static final int GAME_STEP_MS = 16;
    public static final int SHOT_STEP_MS = 100;
    public static final int BONUSINVADER_STEP_MS = 200;
    public static final int INVADER_STEP_MS = 500;

    // COOLDOWNS
    public static final int PLAYER_SHOOTING_COOLDOWN = 500;
    public static final int INVADERS_SHOOTING_COOLDOWN = INVADER_STEP_MS / 5;

    // UTIL
    public static final int ZERO = 0; // useful for some functions that have to return 0 in default case

    /**
     * Private constructor to avoid initialization.
     */
    private Costants() {
    }
}
