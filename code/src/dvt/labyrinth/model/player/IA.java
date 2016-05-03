package dvt.labyrinth.model.player;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import dvt.labyrinth.actions.MovePlayerAction;
import dvt.labyrinth.game.Game;
import dvt.labyrinth.model.essential.Arrow;
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
    public IA(Pawn pawn, Position pos, Tray tray, int nbWall,Game game){
        super("Computer",pawn,pos,tray,nbWall,true,game);
        setPos(pos);
    }

    /**
     * The method to move the IA
     * @param directions here will be null
     * @return if we moved or not
     */
    @Override
    public abstract boolean move(DIRECTIONS directions);

    /**
     * The full move method
     * @param directions here will be null
     * @return if we moved or not
     */
    public abstract boolean completeMove(DIRECTIONS directions);

    /**
     * A strategy where we choose to move to the front if we can, if not to the right,
     * and then depending of the possible movements we apply other strategies
     *
     */
    public void strategyIA(){
        if (canMove(ConstantesLabyrinth.DIRECTIONS.BACK)) {
            decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        }
        else if (!canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT) && !canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)) {
            strategyIABack();
        }
        else if (!canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT) && canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)){
            strategyIALeft();
        }
        else{
            decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
        }
    }

    /**
     *  A strategy where we choose to move to the front if we can, if not to the left,
     *  and then depending of the possible movements we apply other strategies
     */
    public void strategyIALeft(){
        decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        if(checkMoveFromPosition(ConstantesLabyrinth.DIRECTIONS.LEFT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.LEFT)))
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
    }

    /**
     * A strategy only used when moving back is the only possible option
     */
    public void strategyIABack(){
        decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
        decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
        if(checkMoveFromPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT)))
            decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
        else if (checkMoveFromPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT))){
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
            if (checkMoveFromPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT,convertDirectionToPosition(ConstantesLabyrinth.DIRECTIONS.RIGHT)))
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

    public void hasMoved(DIRECTIONS directions){
        if(pos.getY()>=2){
            switch (directions){
                case FRONT:
                    tray.getTile(new Position(pos.getX(),pos.getY()+2)).setHighlighted(new Arrow(RESOURCES.getArrow(directions)));
                    break;
                case BACK:
                    tray.getTile(new Position(pos.getX(),pos.getY()-2)).setHighlighted(new Arrow(RESOURCES.getArrow(directions)));
                    break;
                case RIGHT:
                    tray.getTile(new Position(pos.getX()-2,pos.getY())).setHighlighted(new Arrow(RESOURCES.getArrow(directions)));
                    break;
                case LEFT:
                    tray.getTile(new Position(pos.getX()+2,pos.getY())).setHighlighted(new Arrow(RESOURCES.getArrow(directions)));
                    break;
                default:
                    break;
            }
        }

        putWall(VOCAL.getVocalComputer(directions));
    }

    public void putWall() {
        putWall(VOCAL.BOT_WALL);
    }

    public void putWall(VOCAL v) {
        playText(game.getSIVOX(), v);
        game.pause(2000);
    }

    public abstract DIFFICULTY getType();
}
