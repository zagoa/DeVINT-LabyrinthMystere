package dvt.labyrinth.actions;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import dvt.labyrinth.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * An action : move the player (keyboard arrows)
 */
public class MovePlayerAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    // The game
    private transient Game fenetre;
    // The direction of the movement
    private DIRECTIONS directions;

    /**
     * Method from http://stackoverflow.com/questions/4722835/how-to-temporarily-disable-event-listeners-in-swing
     * @author Sean Patrick Floyd
     */
    private static boolean active = true;
    public static void setActive(boolean active){
        MovePlayerAction.active = active;
    }

    public static boolean isActive() {
        return active;
    }

    /**
     * Constructor
     *
     * @param fenetre
     *          The game
     * @param d
     *          The direction of the movement we need to catch
     */
    public MovePlayerAction(Game fenetre, DIRECTIONS d) {
        this.fenetre = fenetre;
        this.directions = d;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Move the player
        if(!fenetre.isSettingWall() && active)
            fenetre.movePlayer(directions);
    }
}
