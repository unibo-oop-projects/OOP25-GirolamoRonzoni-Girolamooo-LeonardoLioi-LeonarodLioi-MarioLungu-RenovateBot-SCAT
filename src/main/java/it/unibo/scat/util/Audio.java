package it.unibo.scat.util;

/**
 * ...
 * 
 */
public interface Audio {

    /**
     * ...
     * 
     * @param audioTrack ...
     * @param loop       ...
     */
    void play(AudioTrack audioTrack, boolean loop);

    /**
     * ...
     * 
     */
    void stop();

    /**
     * ...
     * 
     * @param volume ...
     */
    void setVolume(float volume);

}
