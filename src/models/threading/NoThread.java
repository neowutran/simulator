package models.threading;

import models.threading.Threading;

public class NoThread extends Threading{
  public NoThread(){
    super();  
  }

  // Mettre dans thread, separation de chaque case
  public void run() {
    
    
     long debut = System.currentTimeMillis();
    for(int i = 0; i < NOMBRE_ITERATION; i++){

      for(int j=1; j < simulateur.getNombreTranche() -1; j++){
        simulateur.nextIteration(j);
      }
      simulateur.mur1EqualMur2();
      notifyObservers(simulateur.getMur(), NOMBRE_ITERATION - simulateur.getNombreTranche());

    }
     long fin = System.currentTimeMillis();
     System.out.println("Temps d'execution: "+(fin - debut)+" ms");
  }

}
