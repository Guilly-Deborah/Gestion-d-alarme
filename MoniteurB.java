import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoniteurB extends JFrame implements EmissionGazListener,RadiationListener,ListSelectionListener, ActionListener {
    public int index;
    public Object element;
    public EmissionGazEvent uneEmission;
    public EmissionGazDetails emissionDetail;
    public RadiationEvent uneRadiation;
    public RadiationDetails radiationDetail;
    // Élements de l'interface graphique
    public JPanel container= new JPanel();
    public JButton detail = new JButton(("Détails"));
    public JButton traitee = new JButton(("Traitée"));
    public DefaultListModel elements = new DefaultListModel();
    public JList liste = new JList(elements);                       // Liste qui apparait qui contient les events
    public DefaultListModel elementsNA = new DefaultListModel();
    public JList listeNA = new JList(elementsNA);                   // Liste qui n'apparait pas qui contient les events

    public void affichage(){            // Affiche la fenêtre de gestion du moniteur B
        this.setTitle("Moniteur B");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);;
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
    public void newAlarmeEmissionGaz(EmissionGazEvent unEmissionGazEvent) {
        // Creation de la fenêtre de dialogue pour les nouvelles alarmes
        JDialog message = new JDialog();
        int reponse = JOptionPane.showConfirmDialog(this,
                "Alarme Emission de gaz",
                "Nouvelle Alarme",
                JOptionPane.OK_CANCEL_OPTION);
        if (reponse == JOptionPane.OK_OPTION){
            elements.addElement("Alarme Emission de gaz");      // Ajout d'un string dans la liste qui apparait
            elementsNA.addElement(unEmissionGazEvent);          // Ajout de l'event dans la liste qui n'apparait pas
        }
    }
    @Override
    public void newAlarmeRadiation(RadiationEvent unRadiationEvent) {
        // Creation de la fenêtre de dialogue pour les nouvelles alarmes
        JDialog message = new JDialog();
        int reponse = JOptionPane.showConfirmDialog(this,
                "Alarme Radiation",
                "Nouvelle Alarme",
                JOptionPane.OK_CANCEL_OPTION);
        if (reponse == JOptionPane.OK_OPTION){
            elements.addElement("Alarme Radiation");            // Ajout d'un string dans la liste qui apparait
            elementsNA.addElement(unRadiationEvent);            // Ajout de l'event dans la liste qui n'apparait pas
        }
    }

    public void valueChanged(ListSelectionEvent listSelectionEvent) {   // Gére l'element selectionné dans la liste
        index=listSelectionEvent.getLastIndex();                        // Récupération de l'indice de l'element selectionné
        this.element=elementsNA.get(index);                             // Récupération de l'event correspondant dans la liste qui n'apparait pas
        // ici on ne connait pas la nature de 'element' donc on a définit  'element' comme un Objet
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==detail){
            if (element.getClass().getName()=="EmissionGazEvent"){
                this.uneEmission= (EmissionGazEvent) elementsNA.get(index);     // On relie l'élément à la variable prêt crée 'uneEmission'
                // Création de la fenêtre avec les détails de l'alarme
                this.emissionDetail = new EmissionGazDetails(uneEmission.getImportance(),uneEmission.getLocalisation(),uneEmission.getTypeGaz(),uneEmission.getDateCreation());
                container.add(traitee);
                this.setVisible(true);
            }else if(element.getClass().getName()=="RadiationEvent"){
                this.uneRadiation= (RadiationEvent) elementsNA.get(index);      // On relie l'élément à la variable prêt crée 'uneRadiation'
                // Création de la fenêtre avec les détails de l'alarme
                this.radiationDetail = new RadiationDetails(uneRadiation.getImportance(),uneRadiation.getLocalisation(),uneRadiation.getNiveauRadiation(),uneRadiation.getDateCreation());
                container.add(traitee);
                this.setVisible(true);
            }
        }else if (actionEvent.getSource()==traitee){
            if (element.getClass().getName()=="EmissionGazEvent"){
                this.emissionDetail.dispose();
                elements.remove(index);
                elementsNA.remove(index);
            }else if(element.getClass().getName()=="RadiationEvent"){
                this.radiationDetail.dispose();
                elements.remove(index);
                elementsNA.remove(index);
            }
        }
    }


}
