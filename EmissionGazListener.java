import java.awt.event.ActionListener;

public interface EmissionGazListener  extends ActionListener {
    void newAlarmeEmissionGaz(EmissionGazEvent unEmissionGazEvent);     // Envoie d'une nouvelle alarme
}
