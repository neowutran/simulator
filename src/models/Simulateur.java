/*
 * Temperature en kelvin
 *
 *
 *
 *
 *
 */
package models;

import java.util.ArrayList;
import java.util.List;
import models.Observable;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulateur{

  private static final int NOMBRE_TRANCHE=10;
  private int nombre_tranche;
  private Materiaux mur_t1[];
  private Materiaux mur_t2[];
  private static final double temperature_initiale_interieur = 293.15;
  private static final double temperature_initiale_exterieur = 383.15;

  private double temps_entre_2calcul = 0.01;
  private double epaisseur_tranche = 0.001;

  private double C;

  public Materiaux[] getMur(){
    return mur_t1;
  }


  public void mur1EqualMur2(){
    mur_t1 = mur_t2;
  }

  public int getNombreTranche(){
    return nombre_tranche;
  }



  public Simulateur() {
    nombre_tranche = NOMBRE_TRANCHE;
    mur_t1 = new Materiaux[nombre_tranche];
    mur_t2 = new Materiaux[nombre_tranche];

    mur_t1[0] = new Materiaux("laine_verre");
    mur_t2[0] = new Materiaux("laine_verre");
    mur_t1[0].setValue(temperature_initiale_exterieur);
    mur_t2[0].setValue(temperature_initiale_exterieur);
    for (int i = 1; i < nombre_tranche; i++) {
      mur_t1[i] = new Materiaux("laine_verre");
      mur_t2[i] = new Materiaux("laine_verre");
      mur_t1[i].setValue(temperature_initiale_interieur);
      mur_t2[i].setValue(temperature_initiale_interieur);
    }
    setC(0);
  }

  public void setC(int x){
    this.C = (mur_t1[x].getConductiviteThermique() * temps_entre_2calcul)
      / (mur_t1[x].getMasseVolumique() * mur_t1[x].getChaleurMassique() * epaisseur_tranche * epaisseur_tranche); // TODO

  }

  public void nextIteration(int x) {
    // T(x,t+1) = T(x,t)+C*(T(x+delta,t)+T(x-delta,t)-2T(x,t))
    // T au temps t: mur_t1
    // T au temps t+1: mur_t2
    int delta = 1;
    mur_t2[x].setValue(mur_t1[x].getValue() + C
        * (mur_t1[x + delta].getValue() + mur_t1[x - delta].getValue() - 2 * mur_t1[x].getValue()));
  }
}
