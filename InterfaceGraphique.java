import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfaceGraphique extends JFrame  implements ActionListener {
    private int importance;
    private String lieu;
    private ArrayList<MoniteurA> listeMA = new ArrayList<MoniteurA>();      //Liste des moniteurA
    private ArrayList<MoniteurB> listeMB = new ArrayList<MoniteurB>();      //Liste des moniteurB
    // Élements graphique
    private JPanel container= new JPanel();
    private JButton boutonIncendie = new JButton("Incendie");
    private JButton boutonEG = new JButton("Emissions de gaz");
    private JButton boutonRA = new JButton("Radiation");
    private JButton boutonDEG = new JButton("Déclencher");      //Bouton de Déclanchement pour les Emissions de Gaz
    private JButton boutonDR = new JButton("Déclencher");       //Bouton de Déclanchement pour les Radiations
    private JCheckBox labo1 = new JCheckBox("Labo 1");
    private JCheckBox labo2 = new JCheckBox("Labo 2");
    private JCheckBox hall = new JCheckBox("Hall");
    private JRadioButton niveau1 = new JRadioButton(" Importance 1");
    private JRadioButton niveau2 = new JRadioButton(" Importance 2");
    private JRadioButton niveau3 = new JRadioButton(" Importance 3");
    private JTextField gaz = new JTextField("Type de Gaz");
    private JTextField radiation = new JTextField("Niveau de radiation");

    public InterfaceGraphique(){
        this.setTitle("Nouvelle alarme");
        this.setSize(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setLayout(new GridLayout(4,3));       //Grille pour la mise en forme
        boutonIncendie.addActionListener(this);
        boutonEG.addActionListener(this);
        boutonRA.addActionListener(this);
        boutonDEG.addActionListener(this);
        boutonDR.addActionListener(this);
        container.add(labo1);
        container.add(labo2);
        container.add(hall);
        container.add(niveau1);
        container.add(niveau2);
        container.add(niveau3);
        container.add(boutonIncendie);
        container.add(boutonEG);
        container.add(boutonRA);
        this.setContentPane(container);
        this.setVisible(true);
    }
    public void ajoutMA(MoniteurA moniteurA){listeMA.add(moniteurA); }      //Ajout de moniteurA à la liste global des moniteursA
    public void ajoutMB(MoniteurB moniteurB){listeMB.add(moniteurB); }      //Ajout de moniteurB à la liste global des moniteursB

    public void actionPerformed(ActionEvent actionEvent) {
        if (niveau1.isSelected()){                  //Choix de l'importance de l'alarme
            this.importance=1;
        }else if (niveau2.isSelected()){
            this.importance=2;
        }else if (niveau3.isSelected()){
            this.importance=3;
        }
        if (labo1.isSelected()){                    //Choix du lieu de l'alarme
            this.lieu="LABO 1";
        }else if (labo2.isSelected()){
            this.lieu="LABO 2";
        }else if (hall.isSelected()){
            this.lieu="HALL";
        }
        if (actionEvent.getSource()==boutonIncendie){                                       //Activation du bouton 'Incendie'
            IncendieSource incendie = new IncendieSource(this.importance,this.lieu);        //Création de la source de l'alarme
            for (MoniteurA moniteurA : listeMA){
                incendie.ajoutMoniteurA(moniteurA);                                         //Ajout des moniteursA à la source
            }
            incendie.envoieAlarme();                                                        //Envoie de l'alerte à tous les moniteurs
        }else if (actionEvent.getSource()==boutonEG){                                       //Activation du bouton 'Emission de gaz'
            container.add(gaz);                                                             //Ajout de la zone de texte
            container.add(boutonDEG);                                                       //et du bouton de validation
            this.setVisible(true);
        }else if (actionEvent.getSource()==boutonRA){                                       //Activation du bouton 'Radiation'
            container.add(radiation);                                                       //Ajout de la zone de texte
            container.add(boutonDR);                                                        //et du bouton de validation
            this.setVisible(true);
        }else if (actionEvent.getSource()==boutonDEG){                                      //Activation du bouton de validation du type de gaz
            EmissionGazSource emission = new EmissionGazSource(this.importance,this.lieu,gaz.getText());    //Création de la source de l'alarme
            for (MoniteurA moniteurA : listeMA){
                emission.ajoutMoniteurA(moniteurA);                                         //Ajout des moniteursA à la source
            }
            for (MoniteurB moniteurB : listeMB){
                emission.ajoutMoniteurB(moniteurB);                                         //Ajout des moniteursB à la source
            }
            emission.envoieAlarme();                                                        //Envoie de l'alerte à tous les moniteurs
            container.remove(gaz);                                                          //Disparition de la zone de texte
            container.remove(boutonDEG);                                                    //et du bouton
            this.setContentPane(container);
            this.setVisible(true);
        }else if (actionEvent.getSource()==boutonDR){                                       //Activation du bouton de validation du niveau de radiation
            RadiationSource radiationSource = new RadiationSource(this.importance, this.lieu, radiation.getText());     //Création de la source de l'alarme
            for (MoniteurB moniteurB : listeMB){
                radiationSource.ajoutMoniteurB(moniteurB);                                  //Ajout des moniteursB à la source
            }
            radiationSource.envoieAlarme();                                                 //Envoie de l'alerte à tous les moniteurs
            container.remove(radiation);                                                    //Disparition de la zone de texte
            container.remove(boutonDR);                                                     //et du bouton
            this.setContentPane(container);
            this.setVisible(true);
        }
    }
}
