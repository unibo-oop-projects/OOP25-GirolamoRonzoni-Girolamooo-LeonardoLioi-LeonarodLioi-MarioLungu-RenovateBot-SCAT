package it.unibo.scat;

import it.unibo.scat.control.Control;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.model.Model;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.model.api.ModelObservable;
import it.unibo.scat.view.View;
import it.unibo.scat.view.api.ViewInterface;

/**
 * Main application class for OOP25-SCAT.
 */
public final class App {

    private App() {
    }

    /**
     * Application entry point.
     * 
     * @param args command line arguments
     *
     */
    public static void main(final String[] args) {
        final ModelInterface model = new Model();
        final ModelObservable modelObs = (ModelObservable) model;

        final View view = new View();
        final ViewInterface viewInterface = view;

        final Control controller = new Control(viewInterface, model);
        final ControlInterface controlInterface = controller;

        // D.I.
        view.setControlInterface(controlInterface);
        view.setModelObservable(modelObs);

        controller.init();
    }

}
