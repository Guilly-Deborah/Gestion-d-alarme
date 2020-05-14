import java.util.ArrayList;

public class RadiationSource  {
    private ArrayList<RadiationListener> RadiationListener;                 //Liste des écouteurs
    private int importance;
    private String localisation;
    private int niveauRadiation;

    public RadiationSource(int importance, String lieu, String radiation){
        this.importance=importance;
        this.localisation=lieu;
        this.niveauRadiation=Integer.valueOf(radiation);
        RadiationListener = new ArrayList<RadiationListener>();
    }
    public void ajoutMoniteurB(MoniteurB moniteurB){                        //Ajout de moniteurB
        RadiationListener.add(moniteurB);
    }

    public void envoieAlarme(){
        RadiationEvent radiationEvent = new RadiationEvent(this, localisation, importance, niveauRadiation);    // Création de l'événement
        for (RadiationListener listener : RadiationListener){               //Parcours de la liste des ecouteurs
            listener.newAlarmeRadiation(radiationEvent);                    //Envoie de l'alarte à l'écouteur
        }
    }

}
