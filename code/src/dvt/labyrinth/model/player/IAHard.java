package dvt.labyrinth.model.player;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;



public class IAHard extends IA{
    private static int counter =0;

    public IAHard(Pawn pawn, Position pos, Tray tray){
        super(pawn,pos,tray,ConstantesLabyrinth.WALL_NUMBER);
    }

    /**
     * Read the direction from the queue in order to move the IA pawn
     * @param tray
     */
    @Override
    public boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions){
        counter++;
        if(counter%4==1) tray.getTile(new Position(getPosition().getX(),getPosition().getY()+2)).positionWall();
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

}
