package it.unibo.scat.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ...
 */
public final class UIConstants {
        // PATHS
        public static final String FONT_PATH = "/fonts/PressStart2P.ttf";
        public static final String INVADER_SHOT_PATH = "/entities/shots/invader_shot.png";
        public static final List<String> PLAYER_SHOT_PATHS = List.of(
                        "/entities/shots/player_shot1.png",
                        "/entities/shots/player_shot2.png",
                        "/entities/shots/player_shot3.png");
        public static final String MENU_BACKGROUND1_PATH = "/backgrounds/menu_background1.jpg";
        public static final String MENU_BACKGROUND2_PATH = "/backgrounds/menu_background2.jpg";
        public static final List<String> GAME_BACKGROUNDS_PATHS = List.of(
                        "/backgrounds/game_background0.jpg",
                        "/backgrounds/game_background1.jpg",
                        "/backgrounds/game_background2.jpg",
                        "/backgrounds/game_background3.jpg");
        public static final List<String> PLAYER_PATHS = List.of(
                        "/entities/player/ship1.png",
                        "/entities/player/ship2.png",
                        "/entities/player/ship3.png");
        public static final List<String> BUNKER_PATHS = List.of(
                        "/entities/bunkers/bunker1.png",
                        "/entities/bunkers/bunker2.png",
                        "/entities/bunkers/bunker3.png");
        public static final List<String> INVADER1_PATHS = List.of(
                        "/entities/invaders/invader_1_1.png",
                        "/entities/invaders/invader_1_2.png");
        public static final List<String> INVADER2_PATHS = List.of(
                        "/entities/invaders/invader_2_1.png",
                        "/entities/invaders/invader_2_2.png");
        public static final List<String> INVADER3_PATHS = List.of(
                        "/entities/invaders/invader_3_1.png",
                        "/entities/invaders/invader_3_2.png");
        public static final String BONUS_INVADER_PATH = "/entities/invaders/invader_4_1.png";
        public static final List<String> PAUSE_BUTTON_PATHS = List.of(
                        "/images/pause/pause1.png",
                        "/images/pause/pause2.png");
        public static final List<String> RESUME_BUTTON_PATHS = List.of(
                        "/images/pause/resume1.png",
                        "/images/pause/resume2.png");
        public static final List<String> LIFE_PANEL_PATHS = List.of(
                        "/images/life/life_0.png",
                        "/images/life/life_1.png",
                        "/images/life/life_2.png",
                        "/images/life/life_3.png");
        public static final String NULL_PATH = "/entities/null.png";

        // COLORS
        public static final Color PANELS_BG_COLOR = new Color(255, 255, 255, 50);
        public static final Color WHITE_50_OPACITY = new Color(255, 255, 255, 125);
        public static final Color ARCADE_BLACK = Color.BLACK;
        public static final Color ARCADE_GREEN = new Color(51, 255, 51);

        // FONTS
        public static final Font TITLE_FONT;
        public static final Font TITLE_FONT_HOVER;
        public static final Font MEDIUM_FONT;
        public static final Font MEDIUM_SMALL_FONT;
        public static final Font SMALL_FONT;

        static {
                final float titleBase = 50f;
                final float titleHover = 55f;
                final float medium = 30f;
                final float mediumSmall = 24f;
                final float small = 18f;

                try (InputStream is = UIConstants.class.getResourceAsStream(FONT_PATH)) {

                        final Font base = Font.createFont(Font.TRUETYPE_FONT, is);
                        GraphicsEnvironment
                                        .getLocalGraphicsEnvironment()
                                        .registerFont(base);

                        TITLE_FONT = base.deriveFont(titleBase);
                        TITLE_FONT_HOVER = base.deriveFont(titleHover);
                        MEDIUM_FONT = base.deriveFont(medium);
                        MEDIUM_SMALL_FONT = base.deriveFont(mediumSmall);
                        SMALL_FONT = base.deriveFont(small);

                } catch (final IOException | FontFormatException e) {
                        throw new ExceptionInInitializerError(e);
                }
        }

        /**
         * Private constructor to avoid initialization.
         */
        private UIConstants() {
        }

}
