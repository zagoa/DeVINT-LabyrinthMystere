package dvt.labyrinth.model.player;

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
     * Read the direction from the queue in order to move the IA pawn
     * @param tray
     */

    @Override
    public boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions){
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        if ((!decision.isEmpty())) {
            if (canMove(tray,decision.peek())){
                updatePlayerPos(tray,convertDirectionToPosition(decision.poll()));
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

    @Override
    public ConstantesLabyrinth.DIFFICULTY getType() {
        return ConstantesLabyrinth.DIFFICULTY.FACILE;
    }

}
