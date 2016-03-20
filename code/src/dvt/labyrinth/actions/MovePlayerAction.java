package dvt.labyrinth.actions;

import static dvt.labyrinth.ConstantesLabyrinth.*;
import dvt.labyrinth.game.TwoPlayers;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * An action : move the player (keyboard arrows)
 */
public class MovePlayerAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    // The game
    private transient TwoPlayers fenetre;
    // The direction of the movement
    private DIRECTIONS directions;

    /**
     * Constructor
     *
     * @param fenetre
     *          The game
     * @param d
     *          The direction of the movement we need to catch
     */
    public MovePlayerAction(TwoPlayers fenetre, DIRECTIONS d) {
        this.fenetre = fenetre;
        this.directions = d;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Move the player
        if(!fenetre.isSettingWall())
        fenetre.movePlayer(directions);
    }
}
