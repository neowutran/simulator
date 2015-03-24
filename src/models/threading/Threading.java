/**
 * @author Martini Didier
 *
 */
package models.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import models.Materiaux;
import models.Observable;
import models.Simulateur;

public abstract class Threading extends Observable{
  protected static final int NOMBRE_ITERATION=60;
  protected int nombre_iteration;
  private final Lock lock = new ReentrantLock();
  private List<Integer> computed = new ArrayList();
  protected Simulateur simulateur;
  protected Boolean isDone = false;

  protected Threading(){

    simulateur = new Simulateur();

    notifyObservers(simulateur.getMur(), 0);
    nombre_iteration = NOMBRE_ITERATION;
  }

  /**
   * Recupere l'index a calculer
   *
   */
  protected Integer getAndRemoveIteration(){

    lock.lock();
    Integer coucheNumber = null;
    if(!computed.isEmpty()){

      coucheNumber = computed.get(0);
      computed.remove(0);

    }
    lock.unlock();
    return coucheNumber;
  }

  /**
   * Cette methode est appeler ca chaque reunion des thread (barriere), elle initialiser les elements necessaire pour la suite des calcul
   */
  protected void nextTime(){
    notifyObservers(simulateur.getMur(), NOMBRE_ITERATION - simulateur.getNombreTranche()); 
    simulateur.mur1EqualMur2();
    if(nombre_iteration == 0){
      isDone = true;
      return;
    }

    for(int i = 1; i < simulateur.getNombreTranche()-1; i++){
      computed.add(i);
    }
    nombre_iteration--;

  } 

  // Mettre dans thread, separation de chaque case
  abstract public void run();




}
