import java.awt.event.ActionListener;

public interface IncendieListener extends ActionListener {
    void newAlarmeIncendie(IncendieEvent unIncendieEvent);          // Envoie d'une nouvelle alarme
}
