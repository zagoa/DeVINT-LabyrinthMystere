package dvt.labyrinth.difficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur l'appuie sur la touche bas
 * @author Justal Kevin
 */
public class UpDifficulty extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient SelectDifficulty fenetre;

    /*
     * Le constructeur appele lors de l'appuie sur la touche "HAUT"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public UpDifficulty(SelectDifficulty fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.up();
    }

}
