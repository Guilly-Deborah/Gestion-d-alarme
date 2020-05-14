import java.util.ArrayList;

public class IncendieSource{
    private ArrayList<IncendieListener> IncendieListeners;                 //Liste des écouteurs
    private int importance;
    private String localisation;


    public IncendieSource(int importance, String lieu){
        this.importance=importance;
        this.localisation=lieu;
        IncendieListeners = new ArrayList<IncendieListener>();


    }
    public void ajoutMoniteurA(MoniteurA mA){                               //Ajout de moniteurA
        IncendieListeners.add(mA);
    }
    public void envoieAlarme(){
        IncendieEvent incendie = new IncendieEvent(this,localisation,importance);   // Création de l'événement
        for (IncendieListener listener: IncendieListeners){                 //Parcours de la liste des ecouteurs
            listener.newAlarmeIncendie(incendie);                           //Envoie de l'alarte à l'écouteur
        }
    }
}
