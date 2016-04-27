package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by user on 21/03/2016.
 */
public class IAEasy extends IA {

    public IAEasy(Pawn pawn, Position pos, Tray tray){
        super(pawn,pos,tray,0);
    }


    /**
     * The full move method
     * @param tray the actual tray with both of the players
     * @param game the  game
     * @param directions here will be null
     * @return if we moved or not
     */
    public boolean completeMove(Tray tray, Game game, ConstantesLabyrinth.DIRECTIONS directions){
        boolean i = move(tray,null);
        hasMoved(game, previous);
        return i;
    }


    /**
     * Read the direction from the queue in order to move the IA pawn
     * @param tray
     */
    @Override
    public boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions){
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        if ((!decision.isEmpty())) {
            if (canMove(tray,previous = decision.poll())){
                updatePlayerPos(tray,convertDirectionToPosition(previous));
                return true;
            }

            else {
                decision.clear();
                strategyIA(tray);
                move(tray,null);
                return true;
            }
        }
        else {
            strategyIA(tray);
            move(tray,null);
            return true;
        }
    }

}
