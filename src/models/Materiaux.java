package models;

import java.util.Map;

import config.Config;


/**
 * @author Martini Didier
 *
 */
public class Materiaux{

  private double value = 0;
  private String typeMateriaux;

  //Recupere tous les parametre du materiaux dans le fichier de configuration
  public double getChaleurMassique(){
    return (double)((Map<String,Double>)((Map<String,Map<String,Double>>)Config.getConfiguration().get(Config.MATERIAUX)).get(typeMateriaux)).get(Config.CHALEUR_MASSIQUE);
  }
  public double getMasseVolumique(){ 
    return (double)((Map<String,Double>)((Map<String,Map<String,Double>>)Config.getConfiguration().get(Config.MATERIAUX)).get(typeMateriaux)).get(Config.MASSE_VOLUMIQUE);
  }
  public double getConductiviteThermique()
  {
    return (double)((Map<String,Double>)((Map<String,Map<String,Double>>)Config.getConfiguration().get(Config.MATERIAUX)).get(typeMateriaux)).get(Config.CONDUCTIVITE_THERMIQUE);
  }
  public double getValue(){
    return value;
  }
  public void setValue(double newValue){
    value=newValue;
  }

  public String getName(){
    return typeMateriaux;
  }

  private Materiaux(){}

  public Materiaux(String newtypeMateriaux){

    this.typeMateriaux = newtypeMateriaux;
  }

}
