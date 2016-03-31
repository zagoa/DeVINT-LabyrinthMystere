package dvt.labyrinth.model.player;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;
import javafx.geometry.Pos;

import java.util.Queue;

/**
 * Created by user on 21/03/2016.
 */
public class IAMedium extends IA{
    private int counter =0;

    public IAMedium(Pawn pawn, Position pos, Tray tray){
        super(pawn,pos,tray,ConstantesLabyrinth.WALL_NUMBER);
    }

    /**
     * Read the direction from the queue in order to move the IA pawn
     * @param tray
     */
    @Override
    public boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions){
        counter++;
        if(counter%4==0) tray.getTile(new Position(getPosition().getX(),getPosition().getY()+1)).positionWall();
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
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
