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

    public IAMedium(Pawn pawn, Position pos, Tray tray,Game game){
        super(pawn,pos,tray,game);
    }

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
            // Get tile where to set a wall
            tray.getTile(new Position(position.getX(), position.getY() - 1)).positionWall();

            // If we have walls more than 1 unit
            if (config.get(CONFIG.WALL) > 0) {
                // Get the next tile where to set the wall
                tray.getTile(new Position(position.getX() + CASE_LENGTH, position.getY() - 1)).positionWall();

                // Fill the gap
                game.fillGap(DIRECTIONS.RIGHT, new Position(position.getX() + 2, position.getY() - 1));
            }

            // Speak & pause...
            putWall();

            return true;
        }
        else if (counter%3==0
                && tray.canSetAWall(DIRECTIONS.LEFT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX(),position.getY()-1)).isOccupied()){
            // Get tile where to set a wall
            tray.getTile(new Position(position.getX(),position.getY() - 1)).positionWall();

            // If we have walls more than 1 unit
            if (config.get(CONFIG.WALL) > 0) {
                // Get the next tile where to set the wall
                tray.getTile(new Position(position.getX() - 2, position.getY() - 1)).positionWall();

                // Fill the gap
                game.fillGap(DIRECTIONS.LEFT, new Position(position.getX() - 2, position.getY() - 1));
            }

            // Speak & pause...
            putWall();

            return true;
        }

        return completeMove(directions);
    }


    @Override
    public DIFFICULTY getType() {
        return DIFFICULTY.MOYEN;
    }


}
