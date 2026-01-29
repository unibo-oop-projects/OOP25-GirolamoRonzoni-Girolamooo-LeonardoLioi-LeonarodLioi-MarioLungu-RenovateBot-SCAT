package it.unibo.scat.common;

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
        public static final String PLAYER_SHOT_PATH = "/entities/shots/player_shot.png";
        public static final String INVADER_SHOT_PATH = "/entities/shots/invader_shot.png";
        public static final String MENU_BACKGROUND_PATH = "/backgrounds/menu_background.jpg";
        public static final List<String> GAME_BACKGROUNDS_PATHS = List.of(
                        "/backgrounds/game_background1.jpg",
                        "/backgrounds/game_background2.jpg",
                        "/backgrounds/game_background3.jpg",
                        "/backgrounds/game_background4.jpg");
        public static final List<String> PLAYER_PATHS = List.of(
                        "/entities/player/ship1.png",
                        "/entities/player/ship2.png",
                        "/entities/player/ship3.png",
                        "/entities/player/ship4.png",
                        "/entities/player/ship5.png",
                        "/entities/player/ship6.png");
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
        public static final List<String> BONUS_INVADER_PATHS = List.of(
                        "/entities/invaders/invader_4_1.png",
                        "/entities/invaders/invader_4_2.png");
        public static final String NULL_PATH = "/entities/null.png";

        // COLORS
        public static final Color PANELS_BG_COLOR = new Color(255, 255, 255, 50);
        public static final Color WHITE_50_OPACITY = new Color(255, 255, 255, 125);

        // FONTS
        public static final Font TITLE_FONT;
        public static final Font TITLE_FONT_HOVER;
        public static final Font MEDIUM_FONT;
        public static final Font SMALL_FONT;

        static {
                final float titleBase = 60f;
                final float titleHover = 65f;
                final float medium = 32f;
                final float small = 20f;

                try (InputStream is = UIConstants.class.getResourceAsStream(FONT_PATH)) {

                        final Font base = Font.createFont(Font.TRUETYPE_FONT, is);
                        GraphicsEnvironment
                                        .getLocalGraphicsEnvironment()
                                        .registerFont(base);

                        TITLE_FONT = base.deriveFont(titleBase);
                        TITLE_FONT_HOVER = base.deriveFont(titleHover);
                        MEDIUM_FONT = base.deriveFont(medium);
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
