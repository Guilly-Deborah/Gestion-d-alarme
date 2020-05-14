import java.util.EventObject;
import java.util.GregorianCalendar;

public class EmissionGazEvent extends EventObject {
    // Variables utile à l'alarme
    public GregorianCalendar dateCreation;
    public String localisation;
    public int importance;
    public String typeGaz;

    public EmissionGazEvent(Object uneSource, String loca, int importance, String typeGaz){
        super(uneSource);                                   // super constructeur de la classe EventObject
        if (1<=importance && importance<=3) {               //Verification de l'intervalle pour l'importance
            dateCreation = new GregorianCalendar();
            this.localisation = loca;
            this.importance = importance;
            this.typeGaz = typeGaz;
        }else{
            System.out.println("Erreur dans la valeur de l'importance");
            System.exit(0);
        }
    }
    // Getters utile pour chaque paramètre
    public Object getSource(){return source;}
    public String getLocalisation(){ return localisation ;}
    public int getImportance() { return importance;}
    public GregorianCalendar getDateCreation(){ return dateCreation;}
    public String getTypeGaz(){ return typeGaz;}
}