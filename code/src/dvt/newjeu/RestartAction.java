package dvt.newjeu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Adrian on 08/02/2016.
 */
public class RestartAction extends AbstractAction {
    private NewJeu jeu;

    public RestartAction(NewJeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jeu.reset();
    }
}