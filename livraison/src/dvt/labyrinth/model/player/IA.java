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
    Queue<ConstantesLabyrinth.DIRECTIONS> decision = new LinkedList<>();
    DIRECTIONS previous;

    /**
     * We create an IA with a predefined name (and soon enough a predefined pawn)
     * @param pawn
     * @param pos the initial position of the IA
     * @param tray
     */
    public IA(Pawn pawn, Position pos, Tray tray, int nbWall,Game game){
        super("Robot",pawn,pos,tray,nbWall,true,game);
        setPos(pos);
    }

    /**
     * The method to move the IA
     * @param directions here will be null
     * @return if we moved or not
     */
    @Override
    public boolean move(DIRECTIONS directions){
        decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);
        if ((!decision.isEmpty()) && canMove(previous = decision.poll())) {
                updatePlayerPos(convertDirectionToPosition(previous));
                return true;
        }

        decision.clear();
        strategyIA();
        move(null);
        return true;
    }


    /**
     * A strategy where we choose to move to the front if we can, if not to the right,
     * and then depending of the possible movements we apply other strategies
     *
     */
    public void strategyIA(){
        if (canMove(ConstantesLabyrinth.DIRECTIONS.BACK)) decision.add(ConstantesLabyrinth.DIRECTIONS.BACK);

        else if (!canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT) && !canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)) {
            strategyIAFront();
        }
        else if (!canMove(ConstantesLabyrinth.DIRECTIONS.RIGHT) && canMove(ConstantesLabyrinth.DIRECTIONS.LEFT)){
            strategyIALeft();
        }
        else strategyIARight();

    }


    /**
     *  A strategy where we choose to move to the front if we can, if not to the left,
     *  and then depending of the possible movements we apply other strategies
     */
    public void strategyIALeft(){
        int i=0;
        while(checkMoveFromPosition(DIRECTIONS.LEFT, new Position(pos.getX()-(2*i), pos.getY()))
                && !checkMoveFromPosition(DIRECTIONS.BACK, new Position(pos.getX()-(2*i),pos.getY()))){
            System.out.println("i = " + i);
            i++;
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        }

    }


    /**
     *  A strategy where we choose to move to the front if we can, if not to the left,
     *  and then depending of the possible movements we apply other strategies
     */
    public void strategyIARight(){
        int i=0;
        while(checkMoveFromPosition(DIRECTIONS.RIGHT,new Position(pos.getX()+(2*i),pos.getY()))
                && !(checkMoveFromPosition(DIRECTIONS.BACK,new Position(pos.getX()+(2*i),pos.getY())))){
            i++;
            decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
        }
    }



    /**
     * A strategy only used when being in death ends
     */
    /*public void strategyIADE(DIRECTIONS directions){
        switch (directions){
            case FRONT:
                strategyIAF();
                break;
            case RIGHT:
                strategyIAR();
                break;
            case LEFT:
                strategyIAL();
                break;
            default:
                return;
        }
    }*/


    /**
     * A strategy to escape from front dead ends
     */
    public void strategyIAFront(){
        int i =0;
        while(!checkMoveFromPosition(DIRECTIONS.RIGHT,new Position(pos.getX(),pos.getY()-(2*i)))
                && !checkMoveFromPosition(DIRECTIONS.LEFT,new Position(pos.getX(),pos.getY()-(2*i)))){
            i++;
            decision.add(ConstantesLabyrinth.DIRECTIONS.FRONT);
        }
        if(!checkMoveFromPosition(DIRECTIONS.RIGHT,new Position(pos.getX(),pos.getY()-(2*i))))
            decision.add(ConstantesLabyrinth.DIRECTIONS.LEFT);
        decision.add(ConstantesLabyrinth.DIRECTIONS.RIGHT);
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

    /**
     * The full move method
     *
     * @return if we moved or not
     */
    public boolean completeMove(){
        boolean  i = move(null);
        hasMoved(previous);
        return i;
    }

    public abstract DIFFICULTY getType();
}
