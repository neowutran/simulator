/**
 * @author Martini Didier
 *
 */
package models.threading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Barriere extends Threading{
  private CyclicBarrier barrier;
  public Barriere(){
    super();  
  }

  // Mettre dans thread, separation de chaque case
  public void run() {
    int cores = Runtime.getRuntime().availableProcessors();
    threadLaunch(cores);
  }



  public void threadLaunch(int numberThread){

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

          long debut = System.currentTimeMillis();
          while(!isDone){


            Integer iteration = getAndRemoveIteration();
            if(iteration == null){
              try{
                barrier.await();
              }catch(InterruptedException e){
                System.out.println(e.getMessage());
              }catch(BrokenBarrierException ex){
                System.out.println(ex.getMessage());
              }finally{
                continue;
              }

            }else{
              simulateur.nextIteration(iteration);
            }
          }
          long fin = System.currentTimeMillis();
          System.out.println("Temps d'execution: "+(fin - debut)+" ms.");
        }

      }).start();

    }

  }

}
