package dvt.labyrinth.model.player;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;
import javafx.geometry.Pos;

import java.util.Queue;

/**
 * Created by user on 21/03/2016.
 */
public class IAMedium extends AdvancedIAs{
    private int counter =0;

    public IAMedium(Pawn pawn, Position pos, Tray tray,Game game){
        super(pawn,pos,tray,game);
    }

    /**
     * The IA moves or positions walls in front of the other player
     * @param tray
     * @param directions the direction we want to move to
     * @param position the position of the other player
     * @return if the IA has performed the action successfully or not
     */
    /**
     * The IA moves or positions walls in front of the other player
     * @param directions the direction we want to move to
     * @param position the position of the other player
     * @return if the IA has performed the action successfully or not
     */
   @Override
    public boolean moveAndWall(DIRECTIONS directions, Position position){
        counter++;
        if(position.getX() < config.get(CONFIG.LENGTH)-CASE_LENGTH && counter%3==0
                && tray.canSetAWall(DIRECTIONS.RIGHT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX(),position.getY()-1)).isOccupied()){
            tray.getTile(new Position(position.getX(), position.getY() - 1)).positionWall();
            tray.getTile(new Position(position.getX() + 2, position.getY() - 1)).positionWall();
            game.fillGap(DIRECTIONS.RIGHT,new Position(position.getX()+2,position.getY() - 1));
            putWall();
            return true;
        }
        else if (counter%3==0
                && tray.canSetAWall(DIRECTIONS.LEFT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX(),position.getY()-1)).isOccupied()){
            tray.getTile(new Position(position.getX(),position.getY() - 1)).positionWall();
            tray.getTile(new Position(position.getX() - 2, position.getY() - 1)).positionWall();
            game.fillGap(DIRECTIONS.LEFT,new Position(position.getX()-2,position.getY() -1));
            putWall();
            return true;
        }
        return completeMove(directions);
    }

    /**
     * The IA moves
     *
     * @return whether he moved or not
     */
    @Override
    public boolean move(DIRECTIONS directions){
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        if ((!decision.isEmpty())) {
            if (canMove(previous = decision.poll())){
                updatePlayerPos(convertDirectionToPosition(previous));
                return true;
            }

            else {
                decision.clear();
                strategyIA();
                move(null);
                return true;
            }
        }
        else {
            strategyIA();
            move(null);
            return true;
        }
    }

    /**
     * The full move method
     *
     * @param directions here will be null
     * @return if we moved or not
     */
    public boolean completeMove(DIRECTIONS directions){
        boolean  i = move(null);
        hasMoved(previous);
        return i;
    }
    @Override
    public DIFFICULTY getType() {
        return DIFFICULTY.MOYEN;
    }


}
