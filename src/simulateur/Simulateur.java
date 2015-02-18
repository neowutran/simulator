/*
 * Temperature en kelvin
 *
 *
 *
 *
 *
 */
package simulateur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulateur {

  private int nombre_iteration = 10;
  private int nombre_tranche = 10;
  private double mur_t1[];
  private double mur_t2[];
  private CyclicBarrier barrier;
  private Boolean isDone = false;
  private final Lock lock = new ReentrantLock();
  private List<Integer> computed = new ArrayList();
  private static final double kelvin = 273.15;
  private static final double temperature_initiale_interieur = 293.15;
  private static final double temperature_initiale_exterieur = 383.15;

  private double conductivite_thermique = 0.84;
  private double masse_volumique = 1400;
  private double temps_entre_2calcul = 0.01;
  private double epaisseur_tranche = 0.001;
  private double chaleur_massique = 840;

  public Simulateur() {
    mur_t1 = new double[nombre_tranche];
    mur_t2 = new double[nombre_tranche];

    mur_t1[0] = temperature_initiale_exterieur;
    mur_t2[0] = temperature_initiale_exterieur;
    for (int i = 1; i < nombre_tranche; i++) {
      mur_t1[i] = temperature_initiale_interieur;
      mur_t2[i] = temperature_initiale_interieur;
    }
    afficher();
  }

  public Integer getAndRemoveIteration(){

    lock.lock();
    Integer coucheNumber = null;
    if(!computed.isEmpty()){

      coucheNumber = computed.get(0);
      computed.remove(0);

    }
    lock.unlock();
    return coucheNumber;
  }

  public void threadLaunch(int numberThread){

    System.out.println("cores: "+numberThread);
    barrier = new CyclicBarrier(numberThread,
        new Runnable(){
          public void run(){
            nextTime();
          }

        });

    for(int j = 0; j < numberThread; j++){

      new Thread(new Runnable(){

        @Override
        public void run(){

          while(!isDone){


            Integer iteration = getAndRemoveIteration();
            if(iteration == null){
              try{
                barrier.await();
              }catch(InterruptedException e){
                //TODO
                System.out.println("TODO");
              }catch(BrokenBarrierException ex){
                //TODO
                System.out.println("TODO");
              }finally{
                continue;
              }

            }else{
              nextIteration(iteration);
            }
          }
        }

      }).start();

    }

  }

  public void nextTime(){
    mur_t1 = mur_t2;
    afficher();
    if(nombre_iteration == 0){
      isDone = true;
      return;
    }

    for(int i = 1; i < nombre_tranche-1; i++){
      computed.add(i);
    }
    nombre_iteration--;

  } 

  // Mettre dans thread, separation de chaque case
  public void run() {
    int cores = Runtime.getRuntime().availableProcessors();
    threadLaunch(cores);
  }

  public static void main(String[] args) {
    Simulateur s1 = new Simulateur();
    s1.run();
  }

  public void afficher() {

    for (int i = 0; i < nombre_tranche; i++) {

      Double temperature = mur_t1[i] - kelvin;
      System.out.print(temperature);
      if (i < nombre_tranche - 1) {
        System.out.print(",");
      }

    }
    System.out.println("");
  }

  public void nextIteration(int x) {
    // T(x,t+1) = T(x,t)+C*(T(x+delta,t)+T(x-delta,t)-2T(x,t))
    // T au temps t: mur_t1
    // T au temps t+1: mur_t2
    int delta = 1;
    double C = (conductivite_thermique * temps_entre_2calcul)
      / (masse_volumique * chaleur_massique * epaisseur_tranche * epaisseur_tranche); // TODO
    mur_t2[x] = (mur_t1[x] + C
        * (mur_t1[x + delta] + mur_t1[x - delta] - 2 * mur_t1[x]));
  }
}
