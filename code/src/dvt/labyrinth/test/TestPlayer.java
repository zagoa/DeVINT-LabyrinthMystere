package dvt.labyrinth.test;

import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.essential.Wall;
import dvt.labyrinth.model.player.HumanPlayer;
import dvt.labyrinth.model.player.Player;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Arnaud on 27/03/2016.
 */
public class TestPlayer {
    Tray tray  = new Tray();
    Pawn pawn = new Pawn(ConstantesLabyrinth.RESOURCES.ARROW_SMALL_LEFT);
    Position pos = new Position(0,0);
    Player player = new HumanPlayer("truc",pawn,pos,tray);


    @Test
    public void TestHasAccess(){
        player.hasAccessTo(tray);
        ArrayList<Tile> can = player.getCan();

        assertEquals(81,can.size());


    }

    @Test
    public void TestIsBlocked(){

        assertFalse(player.isBlocked(tray));

        for(int i =0; i<17;i++) {
            tray.getTile(i, 1).positionWall();
        }

        assertTrue(player.isBlocked(tray));


    }

    @Test
    public void TestCheckMoveFromPosition(){

        for(int i =0; i<17;i++) {
            tray.getTile(i, 1).positionWall();
        }
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(0,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(2,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(4,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(6,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(8,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(10,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(12,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(14,0)));
        assertFalse(player.checkMoveFromPosition(tray, ConstantesLabyrinth.DIRECTIONS.BACK,new Position(16,0)));



    }

}


