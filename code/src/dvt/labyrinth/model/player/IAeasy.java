package dvt.labyrinth.model.player;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tray;
import dvt.labyrinth.model.Pawn;

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
        if ((!decision.isEmpty())) {
            if (canMove(tray,decision.peek())){
                updatePlayerPos(tray,convertDirectionToPosition(decision.peek()));
                decision.poll();
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
