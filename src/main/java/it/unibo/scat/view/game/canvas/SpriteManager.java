package it.unibo.scat.view.game.canvas;

import java.awt.Image;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.ImageIcon;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.view.UIConstants;

/**
 * Centralized loader + scaler + cache for all sprites.
 * Images are loaded, scaled and then cached.
 */
public final class SpriteManager {
        private final Map<EntityType, Image[]> scaledImages = new EnumMap<>(EntityType.class);

        /**
         * ...
         *
         * @param scaleX horizontal scaling factor.
         * @param scaleY vertical scaling factor.
         */
        public SpriteManager(final int scaleX, final int scaleY) {
                loadAndScaleImages(scaleX, scaleY);
        }

        /**
         * ...
         * 
         * @param type  ...
         * @param frame ...
         * @return ...
         */
        public Image getImage(
                        final EntityType type,
                        final int frame) {

                final Image[] images = scaledImages.get(type);
                return images[Math.min(frame, images.length - 1)];
        }

        /**
         * ...
         * 
         * @param scaleX ...
         * @param scaleY ...
         */
        private void loadAndScaleImages(final int scaleX, final int scaleY) {
                put(EntityType.PLAYER,
                                UIConstants.PLAYER_PATHS,
                                Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.PLAYER_SHOT,
                                UIConstants.PLAYER_SHOT_PATHS,
                                Constants.SHOT_WIDTH, Constants.SHOT_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.INVADER_1,
                                UIConstants.INVADER1_PATHS,
                                Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.INVADER_2,
                                UIConstants.INVADER2_PATHS,
                                Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.INVADER_3,
                                UIConstants.INVADER3_PATHS,
                                Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.BONUS_INVADER,
                                UIConstants.BONUS_INVADER_PATHS,
                                Constants.BONUS_INVADER_WIDTH, Constants.BONUS_INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.BUNKER,
                                UIConstants.BUNKER_PATHS,
                                Constants.BUNKER_WIDTH, Constants.BUNKER_HEIGHT,
                                scaleX, scaleY);

                scaledImages.put(
                                EntityType.INVADER_SHOT,
                                new Image[] {
                                                scale(loadSingle(
                                                                UIConstants.INVADER_SHOT_PATH),
                                                                Constants.SHOT_WIDTH * scaleX,
                                                                Constants.SHOT_HEIGHT * scaleY),
                                });
        }

        /**
         * ...
         * 
         * @param type   ...
         * @param paths  ...
         * @param baseW  ...
         * @param baseH  ...
         * @param scaleX ...
         * @param scaleY ...
         */
        private void put(
                        final EntityType type,
                        final List<String> paths,
                        final int baseW,
                        final int baseH,
                        final int scaleX,
                        final int scaleY) {

                final Image[] out = new Image[paths.size()];
                for (int i = 0; i < paths.size(); i++) {
                        out[i] = scale(
                                        loadSingle(paths.get(i)),
                                        baseW * scaleX,
                                        baseH * scaleY);
                }
                scaledImages.put(type, out);
        }

        /**
         * ...
         * 
         * @param path ...
         * @return ...
         */
        private static Image loadSingle(final String path) {
                return new ImageIcon(
                                Objects.requireNonNull(SpriteManager.class.getResource(path))).getImage();
        }

        /**
         * ...
         * 
         * @param img ...
         * @param w   ...
         * @param h   ...
         * @return ...
         */
        private static Image scale(final Image img, final int w, final int h) {
                return img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        }
}
