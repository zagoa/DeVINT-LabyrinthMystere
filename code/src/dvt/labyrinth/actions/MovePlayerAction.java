package dvt.labyrinth.actions;

import static dvt.labyrinth.ConstantesLabyrinth.*;
import dvt.labyrinth.Labyrinth;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Adrian on 08/03/2016.
 */
public class MovePlayerAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Labyrinth fenetre;
    private DIRECTIONS directions;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "BAS"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public MovePlayerAction(Labyrinth fenetre, DIRECTIONS d) {
        this.fenetre = fenetre;
        this.directions = d;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.movePlayer(directions);
    }
}
