/**
 * @author Martini Didier
 *
 */
package views;
import lib.javasocket.*;
import models.IObserver;
import models.Materiaux;
import config.Config;
import views.View;

public class WebView implements View, IObserver{


  @Override
  public void update(Materiaux mur[], int time){

    afficher(mur, time);

  }

  public void afficher(Materiaux mur[], int time) {

    for (int i = 0; i < mur.length; i++) {


      Double temperature = mur[i].getValue() - Config.KELVIN;
      String message = "<elt><time>"+time+"</time><X>"+i+"</X><value>"+temperature.intValue()+"</value></elt>";
      JavaWebSocketServer.getInstance().broadcastMessage(message);

      System.out.print(temperature);
      if (i < mur.length - 1) {
        System.out.print(",");
      }

    }
    System.out.println("");
  }

  public WebView(){

    JavaWebSocketServer.getInstance();
      try {
          //Permet de laisser le temps a l'utilisateur de se connecter sur le site
          Thread.sleep(20000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }


}

