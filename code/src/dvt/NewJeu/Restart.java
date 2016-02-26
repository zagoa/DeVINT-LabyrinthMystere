package dvt.NewJeu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Arnaud on 03/02/2016.
 */
public class Restart extends AbstractAction {
    private NewJeu jeu;

    public Restart(NewJeu jeu){
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jeu.reset();

    }
}
