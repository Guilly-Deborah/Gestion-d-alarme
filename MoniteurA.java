import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoniteurA extends JFrame implements IncendieListener,EmissionGazListener, ListSelectionListener, ActionListener {
    public int index;
    public Object element;
    public EmissionGazEvent uneEmission;
    public EmissionGazDetails emissionDetail;
    public IncendieEvent unIncendie;
    public IncendieDetails incendieDetail;
    // Élements de l'interface graphique
    public JPanel container= new JPanel();
    public JButton detail = new JButton(("Détails"));
    public JButton traitee = new JButton(("Traitée"));
    public DefaultListModel elements = new DefaultListModel();
    public JList liste = new JList(elements);                       // Liste qui apparait qui contient des String
    public DefaultListModel elementsNA = new DefaultListModel();
    public JList listeNA = new JList(elementsNA);                   // Liste qui n'apparait pas qui contient les events

    public void affichage(){  // Affiche la fenêtre de gestion du moniteur A
        this.setTitle("Moniteur A");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.addListSelectionListener( this);
        detail.addActionListener(this);
        traitee.addActionListener(this);
        container.add(liste);
        container.add(detail);
        this.setContentPane(container);
        this.setVisible(true);
    }

    @Override
    public void newAlarmeIncendie(IncendieEvent unIncendieEvent) {
        // Creation de la fenêtre de dialogue pour les nouvelles alarmes
        JDialog message = new JDialog();
        int reponse = JOptionPane.showConfirmDialog(this,   // la reponse de la fenêtre est un entier
                "Alarme INCENDIE",              // Message sur la fenêtre
                "Nouvelle Alarme",                 // Titre de la fenêtre
                JOptionPane.OK_CANCEL_OPTION);          // Choix possible ici "OK" ou "Annuler"
        if (reponse == JOptionPane.OK_OPTION){
            elements.addElement("Alarme Incendie");  // Ajout d'un string dans la liste qui apparait
            elementsNA.addElement(unIncendieEvent); // Ajout de l'event dans la liste qui n'apparait pas
        }
    }


    @Override
    public void newAlarmeEmissionGaz(EmissionGazEvent unEmissionGazEvent) {
        // Creation de la fenêtre de dialogue pour les nouvelles alarmes
        JDialog message = new JDialog();
        int reponse = JOptionPane.showConfirmDialog(this,  // la reponse de la fenêtre est un entier
                "Alarme Emission de gaz",   // Message sur la fenêtre
                "Nouvelle Alarme",              // Titre de la fenêtre
                JOptionPane.OK_CANCEL_OPTION);       // Choix possible ici "OK" ou "Annuler"
        if (reponse == JOptionPane.OK_OPTION){
            elements.addElement("Alarme Emissions de Gaz"); // Ajout d'un string dans la liste qui apparait
            elementsNA.addElement(unEmissionGazEvent);  // Ajout de l'event dans la liste qui n'apparait pas
        }
    }

    public void valueChanged(ListSelectionEvent listSelectionEvent) { // Gére l'element selectionné dans la liste
        index=listSelectionEvent.getLastIndex();    // Récupération de l'indice de l'element selectionné
        this.element=elementsNA.get(index);         // Récupération de l'event correspondant dans la liste qui n'apparait pas
        // ici on ne connait pas la nature de 'element' donc on a définit  'element' comme un Objet
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==detail){  // Appui sur le bouton "détails"
            if (element.getClass().getName()=="EmissionGazEvent"){              // Si l'élément est de la classe "EmissionGazEvent"
                this.uneEmission= (EmissionGazEvent) elementsNA.get(index);     // On relie l'élément à la variable prêt crée 'uneEmission'
                // Création de la fenêtre avec les détails de l'alarme
                this.emissionDetail = new EmissionGazDetails(uneEmission.getImportance(),uneEmission.getLocalisation(),uneEmission.getTypeGaz(),uneEmission.getDateCreation());
                container.add(traitee);
                this.setVisible(true);
            }else if(element.getClass().getName()=="IncendieEvent"){            // Si l'élément est de la classe "IncendieEvent"
                this.unIncendie= (IncendieEvent) elementsNA.get(index);         // On relie l'élément à la variable prêt crée 'unIncendie'
                // Création de la fenêtre avec les détails de l'alarme
                this.incendieDetail = new IncendieDetails(unIncendie.getImportance(),unIncendie.getLocalisation(),unIncendie.getDateCreation());
                container.add(traitee);
                this.setVisible(true);
            }
        }else if(actionEvent.getSource()==traitee){
            if(element.getClass().getName()=="EmissionGazEvent"){
                this.emissionDetail.dispose();
                elements.remove(index);
                elementsNA.remove(index);
            }else if (element.getClass().getName()=="IncendieEvent"){
                this.incendieDetail.dispose();
                elements.remove(index);
                elementsNA.remove(index);
            }
        }
    }


}
