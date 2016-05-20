package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;

import static dvt.labyrinth.tools.ConstantesLabyrinth.CASE_LENGTH;
import static dvt.labyrinth.tools.ConstantesLabyrinth.config;

/**
 * Created by user on 02/04/2016.
 */
public abstract class AdvancedIAs extends IA {


    private int counter =0;

    /**
     * We create an IA with a predefined name (and soon enough a predefined pawn)
     *
     * @param pawn
     * @param pos  the initial position of the IA
     * @param tray
     */
    public AdvancedIAs(Pawn pawn, Position pos, Tray tray,Game  game) {
        super(pawn, pos, tray, ConstantesLabyrinth.WALL_NUMBER,game);
        setPos(pos);
    }


    /**
     * The IA moves or positions walls in front of the other player
     * @param directions the direction we want to move to
     * @param position the position of the other player
     * @return if the IA has performed the action successfully or not
     */
    public boolean moveAndWall(ConstantesLabyrinth.DIRECTIONS directions, Position position){

        counter++;

        if(position.getX() < config.get(ConstantesLabyrinth.CONFIG.LENGTH)-CASE_LENGTH && counter%3==0
                && tray.canSetAWall(ConstantesLabyrinth.DIRECTIONS.RIGHT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX(),position.getY()-1)).isOccupied()){

            // Get tile where to set a wall
            tray.getTile(new Position(position.getX(), position.getY() - 1)).positionWall();

            // If we have walls more than 1 unit
            if (config.get(ConstantesLabyrinth.CONFIG.WALL) > 0) {
                // Get the next tile where to set the wall
                tray.getTile(new Position(position.getX() + CASE_LENGTH, position.getY() - 1)).positionWall();

                // Fill the gap
                game.fillGap(ConstantesLabyrinth.DIRECTIONS.RIGHT, new Position(position.getX() + 2, position.getY() - 1));
            }

            // Speak & pause...
            putWall();

            return true;
        }
        else if (counter%3==0
                && tray.canSetAWall(ConstantesLabyrinth.DIRECTIONS.LEFT,new Position(position.getX(),position.getY()-1))
                && !tray.getTile(new Position(position.getX(),position.getY()-1)).isOccupied()){
            // Get tile where to set a wall
            tray.getTile(new Position(position.getX(),position.getY() - 1)).positionWall();

            // If we have walls more than 1 unit
            if (config.get(ConstantesLabyrinth.CONFIG.WALL) > 0) {
                // Get the next tile where to set the wall
                tray.getTile(new Position(position.getX() - 2, position.getY() - 1)).positionWall();

                // Fill the gap
                game.fillGap(ConstantesLabyrinth.DIRECTIONS.LEFT, new Position(position.getX() - 2, position.getY() - 1));
            }

            // Speak & pause...
            putWall();

            return true;
        }

        return completeMove(    );
    }



}
