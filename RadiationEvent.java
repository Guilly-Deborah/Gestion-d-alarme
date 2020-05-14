import java.util.EventObject;
import java.util.GregorianCalendar;

public class RadiationEvent extends EventObject {
    // Variables utile à l'alarme
    public GregorianCalendar dateCreation;
    public String localisation;
    public int importance;
    public int niveauRadia;

    public RadiationEvent(Object uneSource, String loca, int importance, int niveauRadiation){
        super(uneSource);                               // super constructeur de la classe EventObject
        if (1<=importance && importance<=3 && niveauRadiation>=1 && niveauRadiation<=100) {     //Verification de l'intervalle pour l'importance et pour le niveau de radiation
            dateCreation = new GregorianCalendar();
            this.localisation = loca;
            this.importance = importance;
            this.niveauRadia = niveauRadiation;
        }else{
            System.out.println("Erreur dans la valeur de l'importance (entre 1 et 3) ou de la radiation (entre 1 et 100)");
            System.exit(0);
        }
    }
    // Getters utile pour chaque paramètre
    public Object getSource(){return source;}
    public String getLocalisation(){ return localisation ;}
    public int getImportance() { return importance;}
    public GregorianCalendar getDateCreation(){ return dateCreation;}
    public int getNiveauRadiation(){ return niveauRadia;}
}
