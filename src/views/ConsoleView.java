
package views;
import config.Config;

import models.Materiaux;
import models.*;
import views.View;

public class ConsoleView implements View, IObserver{

  @Override
  public void update(Materiaux mur[], int time){
  
    afficher(mur, time);

  }

  public void afficher(Materiaux mur[], int time) {

    for (int i = 0; i < mur.length; i++) {

      Double temperature = mur[i].getValue() - Config.KELVIN;
      System.out.print(temperature);
      if (i < mur.length - 1) {
        System.out.print(",");
      }

    }
    System.out.println("");
  }


}

