package dvt.labyrinth.model.player;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Pawn;

import java.util.Queue;


public abstract class IA extends Player{
    //A queue for the next decisions taken by the computer
    Queue<ConstantesLabyrinth.DIRECTIONS> decision;


    /**
     * We create an IA with a predefined name (and soon enough a predefined pawn)
     * @param pawn
     * @param pos the initial position of the IA
     * @param tray
     */
    public IA(Pawn pawn, Position pos, Tray tray, int nbWall){
        super("Computer",pawn,pos,tray,nbWall);
        setPos(pos, tray);
        isABot = true;
    }

    @Override
    public abstract boolean move(Tray tray, ConstantesLabyrinth.DIRECTIONS directions);

    /**
     * A strategy where we choose to move to the front if we can, if not to the right,
     * and then depending of the possible movements we apply other strategies
     * @param tray
     */
    public void strategyIA(Tray tray){
        if (canMove(tray, ConstantesLabyrinth.DIRECTIONS.FRONT)) {
            decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
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
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
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
                this.pos.setX(this.pos.getX()-1);
                break;
            case BACK:
                this.pos.setX(this.pos.getX()+1);
                break;
            case RIGHT:
                this.pos.setY(this.pos.getY()+1);
                break;
            case LEFT:
                this.pos.setY(this.pos.getY()-1);
                break;
            default:
                break;
        }
        return pos;
    }



}
