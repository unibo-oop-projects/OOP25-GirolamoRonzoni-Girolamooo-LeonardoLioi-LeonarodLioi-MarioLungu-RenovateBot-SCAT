package it.unibo.scat.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * ...
 */
public class AudioManager implements Audio {
    private static final float DEFAULT_VOLUME = 0.3f;

    private Clip clip;

    /**
     * The constructor creates a new Audio for each audio track.
     */
    public AudioManager() {
        // default constructor

    }

    /**
     * ...
     * 
     * @param music ...
     * @param loop  ...
     */
    @Override
    public void play(final AudioTrack music, final boolean loop) {
        try (AudioInputStream audioIn = AudioSystem
                .getAudioInputStream(ClassLoader.getSystemResource(music.getPath()))) {

            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);

            if (loop) {
                setVolume(DEFAULT_VOLUME);
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            this.clip.start();
        } catch (final UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new IllegalStateException("Unable to play audio track: " + music.getPath(), e);
        }
    }

    /**
     * ...
     * 
     * @param volume ...
     */
    @Override
    public void setVolume(final float volume) {
        if (clip == null) {
            return;
        }

        // 1. Salviamo il valore (assicurandoci che sia tra 0.0 e 1.0)
        final float normalizedVolume = Math.max(0.0f, Math.min(1.0f, volume));

        try {
            final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            final float dB = (float) (Math.log10(normalizedVolume <= 0 ? 0.0001 : normalizedVolume) * 20.0);
            gainControl.setValue(dB);

        } catch (final IllegalArgumentException e) {
            throw new IllegalStateException("Volume control not supported", e);
        }
    }

    /**
     * ...
     */
    @Override
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
