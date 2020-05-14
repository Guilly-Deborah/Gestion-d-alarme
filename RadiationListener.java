import java.awt.event.ActionListener;

public interface RadiationListener  extends ActionListener {
    void newAlarmeRadiation(RadiationEvent unRadiationEvent);       // Envoie d'une nouvelle alarme
}
