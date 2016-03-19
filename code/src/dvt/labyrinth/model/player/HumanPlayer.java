package dvt.labyrinth.model.player;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tray;
import dvt.labyrinth.model.Pawn;

import static dvt.labyrinth.ConstantesLabyrinth.CASE_LENGTH;


public class HumanPlayer extends Player{


    public HumanPlayer(String name, Pawn pawn, Position pos, Tray tray){
        super(name,pawn,pos,tray);
        setPos(pos,tray);
    }


    /**
     * We want to move to the wanted direction, and know if the player has successfully moved
     * @param tray
     * @param d the direction we want to move to
     * @return whether or not the player has moved
     */
    public boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS d) {
        int x = pos.getX();
        int y = pos.getY();

        switch (d) {
            case FRONT:
                if(canMove(tray, ConstantesLabyrinth.DIRECTIONS.FRONT)) {
                    updatePlayerPos(tray, new Position(x, y - CASE_LENGTH));
                    return true;
                }
                return false;

            case BACK:
                if(canMove(tray, ConstantesLabyrinth.DIRECTIONS.BACK)) {
                    updatePlayerPos(tray, new Position(x, y + CASE_LENGTH));
                    return true;
                }
                return false;

            case RIGHT:
                if(canMove(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT)) {
                    updatePlayerPos(tray, new Position(x + CASE_LENGTH, y));
                    return true;
                }
                return false;

            case LEFT:
                if(canMove(tray, ConstantesLabyrinth.DIRECTIONS.LEFT)) {
                    updatePlayerPos(tray, new Position(x - CASE_LENGTH, y));
                    return true;
                }
                return false;

            default:
                return false;
        }
    }
}
