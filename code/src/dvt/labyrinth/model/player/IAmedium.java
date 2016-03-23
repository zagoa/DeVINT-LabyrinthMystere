package dvt.labyrinth.model.player;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tray;
import dvt.labyrinth.model.Pawn;

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
        if(counter%4==0) tray.getTile(new Position(getPosition().getX(),getPosition().getY()-1)).positionWall();
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
