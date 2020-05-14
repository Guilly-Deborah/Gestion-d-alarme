import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class RadiationDetails extends JFrame implements ActionListener {
    // Élements pour l'interface graphique
    private JPanel container= new JPanel();
    private JButton fermer = new JButton(("Fermer"));
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4 = new JLabel();

    public RadiationDetails(int importance, String lieu, int radiation, GregorianCalendar date){
        //Création de la fenêtre graphique avec toutes les informations sur l'alarme
        this.setTitle("Détail alarme radiation");
        this.setSize(400, 150);
        this.setLocationRelativeTo(null);;
        label1.setText("Niveau d'importance : ".concat(String.valueOf(importance)));
        label2.setText("Localisation : ".concat(lieu));
        label3.setText("Niveau de radiation : ".concat(String.valueOf(radiation)));
        label4.setText("Date de déclanchement : ".concat(String.valueOf(date.getTime())));
        fermer.addActionListener(this);
        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(label4);
        container.add(fermer);
        this.setContentPane(container);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==fermer){                   //Gestion du bouton 'fermer'
            this.dispose();
        }
    }
}
