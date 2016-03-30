package dvt.labyrinth.difficulty;

import dvt.labyrinth.menu.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur l'appuie sur la touche haut
 * @author Justal Kevin
 */
public class DownDifficulty extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient SelectDifficulty fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "BAS"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public DownDifficulty(SelectDifficulty fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.down();
    }

}
