package dvt.NewJeu;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Arnaud on 03/02/2016.
 */
public class TutoAction extends javax.swing.AbstractAction  {
    private NewJeu jeu;

    public TutoAction(NewJeu jeu){
        this.jeu = jeu;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        jeu.down();
    }
}
