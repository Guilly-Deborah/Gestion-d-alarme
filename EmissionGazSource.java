import java.util.ArrayList;

public class EmissionGazSource {
    private ArrayList<EmissionGazListener> EmissionGazListeners;            //Liste des écouteurs
    private int importance;
    private String localisation;
    private String typeGaz;

    public EmissionGazSource(int importance, String lieu,String type){
        this.importance=importance;
        this.localisation=lieu;
        this.typeGaz=type;
        EmissionGazListeners = new ArrayList<EmissionGazListener>();
    }
    public void ajoutMoniteurA(MoniteurA mA){                               //Ajout de moniteurA
        EmissionGazListeners.add(mA);
    }
    public void ajoutMoniteurB(MoniteurB mB){                               //Ajout de moniteurB
        EmissionGazListeners.add(mB);
    }
    public void envoieAlarme(){
        EmissionGazEvent gazEmission = new EmissionGazEvent(this,localisation,importance,typeGaz);      // Création de l'événement
        for (EmissionGazListener listener: EmissionGazListeners){           //Parcours de la liste des ecouteurs
            listener.newAlarmeEmissionGaz(gazEmission);                     //Envoie de l'alarte à l'écouteur
        }
    }
}
