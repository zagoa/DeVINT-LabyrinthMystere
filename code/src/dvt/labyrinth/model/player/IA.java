package dvt.labyrinth.model.player;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;
import t2s.SIVOXDevint;

import java.util.LinkedList;
import java.util.Queue;


public abstract class IA extends Player{
    //A queue for the next decisions taken by the computer
    Queue<ConstantesLabyrinth.DIRECTIONS> decision = new LinkedList<>();;
    DIRECTIONS previous;

    /**
     * We create an IA with a predefined name (and soon enough a predefined pawn)
     * @param pawn
     * @param pos the initial position of the IA
     * @param tray
     */
    public IA(Pawn pawn, Position pos, Tray tray, int nbWall){
        super("Computer",pawn,pos,tray,nbWall,true);
        setPos(pos, tray);
    }

    /**
     * The method to move the IA
     * @param tray the actual tray with both of the players
     * @param directions here will be null
     * @return if we moved or not
     */
    @Override
    public abstract boolean move(Tray tray,DIRECTIONS directions);

    /**
     * The full move method
     * @param tray the actual tray with both of the players
     * @param game the  game
     * @param directions here will be null
     * @return if we moved or not
     */
    public abstract boolean completeMove(Tray tray, Game game, DIRECTIONS directions);

    /**
     * A strategy where we choose to move to the front if we can, if not to the right,
     * and then depending of the possible movements we apply other strategies
     * @param tray
     */
    public void strategyIA(Tray tray){
        if (canMove(tray, ConstantesLabyrinth.DIRECTIONS.BACK)) {
            decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        }
        else if (!canMove(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT) && !canMove(tray, ConstantesLabyrinth.DIRECTIONS.LEFT)) {
            strategyIABack(tray);
        }
        else if (!canMove(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT) && canMove(tray, ConstantesLabyrinth.DIRECTIONS.LEFT)){
            strategyIALeft(tray);
        }
        else{
            decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
        }
    }

    /**
     *  A strategy where we choose to move to the front if we can, if not to the left,
     *  and then depending of the possible movements we apply other strategies
     * @param tray
     */
    public void strategyIALeft(Tray tray){
        decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        if(checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.LEFT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.LEFT)))
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
    }

    /**
     * A strategy only used when moving back is the only possible option
     * @param tray
     */
    public void strategyIABack(Tray tray){
        decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
        decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
        if(checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT)))
            decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
        else if (checkMoveFromPosition(tray,ConstantesLabyrinth.DIRECTIONS.RIGHT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT))){
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
            if (checkMoveFromPosition(tray,ConstantesLabyrinth.DIRECTIONS.RIGHT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT)))
                decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        }
    }

    /**
     * @param direction the direction we want to go to
     * @return the position corresponding to the direction we want to go from the pawn position
     */
    public Position convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS direction){
        switch (direction) {
            case FRONT:
                return new Position(pos.getX(), pos.getY()-CASE_LENGTH);
            case BACK:
                return new Position(pos.getX(), pos.getY()+CASE_LENGTH);
            case RIGHT:
                return new Position(pos.getX()+CASE_LENGTH, pos.getY());
            case LEFT:
                return new Position(this.pos.getX()-CASE_LENGTH, pos.getY());
            default:
                return null;
        }
    }

    public void hasMoved(Game game, DIRECTIONS directions){
        switch (directions){
            case FRONT:
                playText(game.getSIVOX(),VOCAL.BACK);
                break;

            case BACK:
                playText(game.getSIVOX(),VOCAL.FRONT);
                break;

            case RIGHT:
                playText(game.getSIVOX(),VOCAL.RIGHT);
                break;

            case LEFT:
                playText(game.getSIVOX(),VOCAL.LEFT);
                break;
            default:
                playText(game.getSIVOX(),VOCAL.BOT_WALL);
                break;
        }
        game.pause(2000);
    }

    public void putWall(Game game){
        playText(game.getSIVOX(),VOCAL.BOT_WALL);
        game.pause(2000);
    }

    public abstract DIFFICULTY getType();
}
