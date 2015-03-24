/**
 * @author Martini Didier
 *
 */
package models;
import java.util.Map;
import java.util.HashMap;
public class Simulateur{

  private static final int TAILLE_MUR=32;
  private Integer nombre_tranche;
  private Materiaux mur_t1[];
  private Materiaux mur_t2[];
  private static final Double temperature_initiale_interieur = 293.15;
  private static final Double temperature_initiale_exterieur = 383.15;

  private static final Double TEMPS_ENTRE_CALCUL = 600.0;
  private static final Double EPAISSEUR_TRANCHE = 0.04;

  private Map<String, Double> coefficient_isolant = new HashMap<String, Double>();

  public Materiaux[] getMur(){
    return mur_t2;
  }

  public void mur1EqualMur2(){
    //par reference, ce n'est pas une copie membre a membre des valeur, mais juste un passage de reference
    mur_t1 = mur_t2;
   
    //On vide le tableau pour qu'il accepte les nouvelles valeur
    mur_t2 = new Materiaux[nombre_tranche];
    mur_t2[0] = mur_t1[0];
    mur_t2[nombre_tranche-1] = mur_t1[nombre_tranche-1];
  }

  public int getNombreTranche(){
    return nombre_tranche;
  }

  public Simulateur() {
    String materiaux1 = "brique";
    String materiaux2 = "laine_verre";
    Double nbtranche =  TAILLE_MUR / (EPAISSEUR_TRANCHE*100) +1;
    nombre_tranche = nbtranche.intValue();
    mur_t1 = new Materiaux[nombre_tranche];
    mur_t2 = new Materiaux[nombre_tranche];
    mur_t1[0] = new Materiaux(materiaux1, temperature_initiale_exterieur);
    mur_t2[0] = mur_t1[0];
    Double nbtranchebeton = 20 / (EPAISSEUR_TRANCHE*100);
    for (int i = 1; i < nbtranchebeton.intValue(); i++) {
      mur_t1[i] = new Materiaux(materiaux1, temperature_initiale_interieur);
       mur_t2[i] = new Materiaux(materiaux1, temperature_initiale_interieur);
    }

    for(int i = nbtranchebeton.intValue(); i < nombre_tranche; i++){
      mur_t1[i] = new Materiaux(materiaux2, temperature_initiale_interieur);
      mur_t2[i] = new Materiaux(materiaux2, temperature_initiale_interieur);
    }
    this.setCoefficient(materiaux1, 0);
    this.setCoefficient(materiaux2, nombre_tranche-1);
  }

  public void setCoefficient(String name, int x){
    this.coefficient_isolant.put(name, (mur_t1[x].getConductiviteThermique() * TEMPS_ENTRE_CALCUL)
        / (mur_t1[x].getMasseVolumique() * mur_t1[x].getChaleurMassique() * EPAISSEUR_TRANCHE * EPAISSEUR_TRANCHE));

  }

  public void nextIteration(int x) {

    switch(x){
      case 1:case 2: case 3: case 4:
        mur_t2[x] = new Materiaux(mur_t1[x].getName(), mur_t1[x].getValue()+ this.coefficient_isolant.get("brique")* (mur_t1[x-1].getValue() + mur_t1[x+1].getValue() - 2* mur_t1[x].getValue()));
        break;
      case 5:
        mur_t2[5] = new Materiaux(mur_t1[x].getName(),
            mur_t1[5].getValue() + this.coefficient_isolant.get("brique")
            * (mur_t1[4].getValue() - mur_t1[5].getValue()) + this.coefficient_isolant.get("laine_verre") * (mur_t1[6].getValue() - mur_t1[5].getValue()));

        break;
      case 6: case 7:
        mur_t2[x] = new Materiaux(mur_t1[x].getName(),mur_t1[x].getValue()+ this.coefficient_isolant.get("laine_verre")* (mur_t1[x-1].getValue() + mur_t1[x+1].getValue() - 2* mur_t1[x].getValue()));
        break;

    }
/*        
          mur_t2[x] = new Materiaux(mur_t1[x].getName(),
          mur_t1[x].getValue() + this.coefficient_isolant.get(mur_t1[x-1].getName())
     * (mur_t1[x - 1].getValue() - mur_t1[x].getValue()) + this.coefficient_isolant.get(mur_t1[x+1].getName()) * (mur_t1[x+1].getValue() - mur_t1[x].getValue()));*/
  }
}
