public class Main {

    public static void main(String[] args) {
        // Creation de l'interface graphique pour les nouvelles alarmes
        InterfaceGraphique graph = new InterfaceGraphique();
        // Creation de deux moniteurs
        MoniteurA mA = new MoniteurA();
        MoniteurB mB = new MoniteurB();
        //Ajout des moniteurs a l'interface graphique
        graph.ajoutMA(mA);
        graph.ajoutMB(mB);
        //Affichage des moniteurs
        mA.affichage();
        mB.affichage();

    }
}
