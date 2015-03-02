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


      
      long debut = System.currentTimeMillis();
      Threading simulateur = new NoThread();
  
      /*
       *Choix des affichages present, ils fonctionnent selon le pattern observeur-observable.
       */
         ConsoleView view = new ConsoleView();
     // WebView webview = new WebView();
      simulateur.addObserver(view); 
   // simulateur.addObserver(webview);
    
      //Lancement de la simulation
      simulateur.run(); 
     long fin = System.currentTimeMillis();
     System.out.println("Temps d'execution: "+(fin - debut)+" ms");

    }

}
