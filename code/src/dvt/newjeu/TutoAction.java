package dvt.newjeu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Adrian on 03/02/2016.
 */
public class TutoAction extends AbstractAction {
    private NewJeu jeu;

    public TutoAction(NewJeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(" "))
            jeu.moveTheo();
        else if (e.getActionCommand().equals("r"))
            jeu.reset();
    }
}
