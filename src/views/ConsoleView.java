
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


    String materiaux = mur[0].getName();
    for (int i = 0; i < mur.length; i++) {

      Double temperature = mur[i].getValue() - Config.KELVIN;
      System.out.print(temperature.intValue());
      if (i < mur.length - 1) {
        if(mur[i+1].getName().equals(mur[i].getName())){

          System.out.print(",");
        }else{
          System.out.print("-");
        }
      }

    }
    System.out.println("");
  }


}

