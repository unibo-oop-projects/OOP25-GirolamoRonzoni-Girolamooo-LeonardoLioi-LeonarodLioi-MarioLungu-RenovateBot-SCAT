package it.unibo.scat.common;

/**
 * ...
 */
public interface Observable {

    /**
     * @param o ...
     * 
     */
    void setObserver(Observer o);

    /**
     * ...
     */
    void notifyObserver();
}
