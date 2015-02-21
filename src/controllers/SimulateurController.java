/*
 * @author Martini Didier
 */

package controllers;
import models.threading.*;

import views.ConsoleView;
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


      Threading simulateur = new Barriere();
      ConsoleView view = new ConsoleView();
     // WebView webview = new WebView();
     simulateur.addObserver(view); 
   // simulateur.addObserver(webview);
     simulateur.run(); 

    }

}
