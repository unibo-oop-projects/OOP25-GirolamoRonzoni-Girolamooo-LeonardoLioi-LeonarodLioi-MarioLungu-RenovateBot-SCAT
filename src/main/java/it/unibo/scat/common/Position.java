package it.unibo.scat.common;

/**
 * This class is used to manage the position of an entity.
 */
public class Position {
    private int x;
    private int y;

    /**
     * @param x ...
     * @param y ...
     * 
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return ...
     * 
     */
    public int getX() {
        return x;
    }

    /**
     * @return ...
     * 
     */
    public int getY() {
        return y;
    }

    /**
     * @param x ...
     * 
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @param y ...
     * 
     */
    public void setY(final int y) {
        this.y = y;
    }

}
