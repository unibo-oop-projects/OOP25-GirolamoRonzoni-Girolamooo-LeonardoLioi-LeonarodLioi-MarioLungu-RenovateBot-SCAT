package it.unibo.scat.util;

/**
 * Enum of audio tracks and sound effects.
 */
public enum AudioTrack {

    /**
     * The sound track of the principal menu.
     */
    SOUND_TRACK("audio/MenuSong.wav"),

    /**
     * The sound of the botton when it is pressed.
     */
    MOUSE_OVER("audio/MouseOverSound.wav"),

    OPTION_SELECTED("audio/OptionSelectedSound.wav");

    private final String path;

    AudioTrack(final String path) {
        this.path = path;
    }

    /**
     * @return the path of the track.
     */
    public String getPath() {
        return path;
    }

}
