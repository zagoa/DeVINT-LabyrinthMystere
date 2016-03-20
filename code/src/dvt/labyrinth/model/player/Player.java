package dvt.labyrinth.model.player;

import dvt.labyrinth.Position;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;
import dvt.labyrinth.model.Pawn;


import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

import static dvt.labyrinth.ConstantesLabyrinth.*;



public class Player {
    // His name
    protected String name;
    // Item
<<<<<<< HEAD:code/src/dvt/labyrinth/model/player/Player.java
    protected Pawn pawn;
=======
    private Pawn pawn;
>>>>>>> de46d472c8e4542e315bf76aa80dc77bd99a4614:code/src/dvt/labyrinth/model/Player.java

    // Check if can set a wall
    protected ArrayList<Tile> can = new ArrayList<>();

    // His original position
    protected Position originalPos;
    // His position
    protected Position pos;


    public Player(String name, Pawn pawn, Position pos, Tray tray){
        this.name = name;
        this.pawn = pawn;
        this.originalPos = pos;
        setPos(pos, tray);
    }


    /**
     *We want to know if we can move towards a direction from our actual position
     * @param tray the tray
     * @param direction where we want to go
     * @return whether or not we can move to the wanted position ( we can't go to a position
     * if the tile(wanted position) is occupied (Wall,Player)
     */
    public boolean canMove(Tray tray, DIRECTIONS direction){
        int x = pos.getX();
        int y = pos.getY();

        switch (direction) {
            case FRONT:
                return (y-CASE_LENGTH >= 0
                        && !tray.getTile(x, y-WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y-CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y+CASE_LENGTH <= NBRE_CASES-1
                        && !tray.getTile(x, y+WALL_LENGTH).isOccupied()
                        && !tray.getTile(x, y+CASE_LENGTH).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x+CASE_LENGTH <= NBRE_CASES-1
                        && !tray.getTile(x+WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x+CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            case LEFT:
                return (x-CASE_LENGTH >= 0
                        && !tray.getTile(x-WALL_LENGTH, y).isOccupied()
                        && !tray.getTile(x-CASE_LENGTH, y).isOccupied()); // In map && wall not present && tile not occupied

            default:
                return false;
        }
    }

    /**
     * Check if the player could end the game in the actual tray disposition
     * @param tray
     * @return if the player can access to the last tray's line which means he can win and he isn't blocked
     */
    public boolean isBlocked(Tray tray){
        hasAccessTo(tray);
        //It represent if the pan is on the last line, so he can finish the game
        for(int i=0;i<NBRE_CASES;i+=2) {
            if (can.contains(tray.getTile(i, 0))) return true;
        }
        return false;
    }


    /**
     * Add in a list all the tile where the pawn can move to
     * @param tray
     */
    public void hasAccessTo(Tray tray) {
        can.add(tray.getTile(getPosition().getX() , getPosition().getY()));
        int i=0;
        for(can.get(i); i == can.size(); i++){
            int x = can.get(i).getPosition().getX();
            int y = can.get(i).getPosition().getY();
            if (canMove(tray, DIRECTIONS.FRONT)&& !can.contains(tray.getTile(x-2,y))) {
                can.add(tray.getTile(x+2,y));
            }
            if (canMove(tray, DIRECTIONS.BACK) && !can.contains(tray.getTile(x+2,y))) {
                can.add(tray.getTile(x-2,y));
            }
            if (canMove(tray, DIRECTIONS.RIGHT) && !can.contains(tray.getTile(x,y+2))) {
                can.add(tray.getTile(x,y+2));
            }
            if (canMove(tray, DIRECTIONS.LEFT) && !can.contains(tray.getTile(x,y-2))) {
                can.add(tray.getTile(x,y-2));
            }
        }
    }


    /**
     * Update the pawn position to the wanted one
     * @param tray
     * @param newP the position we want to go to
     */
    public void updatePlayerPos(Tray tray, Position newP) {
        tray.getTile(newP).setPawn(pawn);
        tray.getTile(pos).setItem(null);

        pos = newP;
    }

<<<<<<< HEAD:code/src/dvt/labyrinth/model/player/Player.java

    /**
     *
     * @return whether or not the player has won
     */
=======
    public void setPos(Position pos, Tray tray) {
        this.pos = pos;

        tray.getTile(pos).setPawn(pawn);
    }

>>>>>>> de46d472c8e4542e315bf76aa80dc77bd99a4614:code/src/dvt/labyrinth/model/Player.java
    public boolean hasWon() {
        switch (originalPos.getY()) {
            case 0: // Top of the tray
                return (pos.getY() == NBRE_CASES-1);

            case NBRE_CASES-1:
                return (pos.getY() == 0);

            default:
                return false;
        }
    }

<<<<<<< HEAD:code/src/dvt/labyrinth/model/player/Player.java
    public void setPos(Position pos, Tray tray) {
        this.pos = pos;

        tray.getTile(pos).setPawn(pawn);
    }

    public Position getPosition() { return pos; }
=======
    public int getWonY() {
        switch (originalPos.getY()) {
            case 0: // Top of the tray
                return NBRE_CASES-1;

            case NBRE_CASES-1:
                return 0;

            default:
                return 0;
        }
    }
>>>>>>> de46d472c8e4542e315bf76aa80dc77bd99a4614:code/src/dvt/labyrinth/model/Player.java

    public String getName() {
        return name;
    }

<<<<<<< HEAD:code/src/dvt/labyrinth/model/player/Player.java
=======
    public Pawn getPawn() {
        return pawn;
    }
>>>>>>> de46d472c8e4542e315bf76aa80dc77bd99a4614:code/src/dvt/labyrinth/model/Player.java
}
