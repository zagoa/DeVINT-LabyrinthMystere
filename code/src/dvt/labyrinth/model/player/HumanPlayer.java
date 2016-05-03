package dvt.labyrinth.model.player;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import static dvt.labyrinth.tools.ConstantesLabyrinth.CASE_LENGTH;
import static dvt.labyrinth.tools.ConstantesLabyrinth.WALL_NUMBER;


public class HumanPlayer extends Player{


    public HumanPlayer(String name, Pawn pawn, Position pos,Tray tray, Game game){
        super(name,pawn,pos,tray,WALL_NUMBER,false,game);
        setPos(pos);
    }


    /**
     * We want to move to the wanted direction, and know if the player has successfully moved
     *
     * @param d the direction we want to move to
     * @return whether or not the player has moved
     */
    public boolean move(ConstantesLabyrinth.DIRECTIONS d) {
        int x = pos.getX();
        int y = pos.getY();

        switch (d) {
            case FRONT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.FRONT)) {
                    updatePlayerPos(new Position(x, y - CASE_LENGTH));
                    return true;
                }
                return false;

            case BACK:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.BACK)) {
                    updatePlayerPos(new Position(x, y + CASE_LENGTH));
                    return true;
                }
                return false;

            case RIGHT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT)) {
                    updatePlayerPos(new Position(x + CASE_LENGTH, y));
                    return true;
                }
                return false;

            case LEFT:
                if(canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)) {
                    updatePlayerPos(new Position(x - CASE_LENGTH, y));
                    return true;
                }
                return false;

            default:
                return false;
        }
    }

}
