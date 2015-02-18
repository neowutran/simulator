/*
 * @author Martini Didier
 */

package controllers;

/**
 * The Class MiniProjectController.
 */
public class SimulateurController {

    /** The instance. */
    private static SimulateurController         instance = null;

    /**
     * Gets the single instance of MiniProjectController.
     * 
     * @return single instance of MiniProjectController
     */
    public static SimulateurController getInstance() {

        if (SimulateurController.instance == null) {
            SimulateurController.instance = new SimulateurController();
        }
        return SimulateurController.instance;
    }

    /**
     * Instantiates a new mini project controller.
     */
    protected SimulateurController() {

    }

}
