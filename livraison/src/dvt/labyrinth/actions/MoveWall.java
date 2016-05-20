package dvt.labyrinth.actions;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.tools.ConstantesLabyrinth.DIRECTIONS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Arnaud on 16/03/2016.
 */
public class MoveWall implements ActionListener {
    private Tile origin;
    // The game
    private transient Game fenetre;
    // The direction of the movement
    private Tile tile;


    private DIRECTIONS dir;

    public MoveWall(Game fenetre, Tile tileClick, Tile origin, DIRECTIONS dir) {
        this.fenetre = fenetre;
        this.tile = tileClick;
        this.origin = origin;
        this.dir = dir;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // We need to check if tile is a wall
        fenetre.getSIVOX().stop();
        tile.positionWall();
        //if (!fenetre.otherPlayer().isBlocked(fenetre.getTray()) || !fenetre.getCurrentPlayer().isBlocked(fenetre.getTray())) {
        fenetre.fillGap(dir, tile.getPosition());
        fenetre.nextTurn();
        fenetre.setSettingWall(false);
        fenetre.unHighlightAll();
        ActionListener[] action = tile.getComponent().getActionListeners();
        tile.getComponent().removeActionListener(action[0]);


        if (tile.getPosition().getY() > origin.getPosition().getY()) {
            action = fenetre.getTray().getTile(origin.getPosition().getX(), origin.getPosition().getY() - 2).getComponent().getActionListeners();
            fenetre.getTray().getTile(origin.getPosition().getX(), origin.getPosition().getY() - 2).getComponent().removeActionListener(action[0]);
        }

        if (tile.getPosition().getY() < origin.getPosition().getY()) {
            action = fenetre.getTray().getTile(origin.getPosition().getX(), origin.getPosition().getY() + 2).getComponent().getActionListeners();
            fenetre.getTray().getTile(origin.getPosition().getX(), origin.getPosition().getY() + 2).getComponent().removeActionListener(action[0]);
        }
        if (tile.getPosition().getX() > origin.getPosition().getX()) {
            action = fenetre.getTray().getTile(origin.getPosition().getX() - 2, origin.getPosition().getY()).getComponent().getActionListeners();
            fenetre.getTray().getTile(origin.getPosition().getX() - 2, origin.getPosition().getY()).getComponent().removeActionListener(action[0]);
        }
        if (tile.getPosition().getX() < origin.getPosition().getX()) {
            action = fenetre.getTray().getTile(origin.getPosition().getX() + 2, origin.getPosition().getY()).getComponent().getActionListeners();
            fenetre.getTray().getTile(origin.getPosition().getX() + 2, origin.getPosition().getY()).getComponent().removeActionListener(action[0]);
        }


    }
}



