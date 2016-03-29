package dvt.labyrinth.test;

import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.essential.Tray;
import dvt.labyrinth.model.player.HumanPlayer;
import dvt.labyrinth.model.player.Player;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
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

        assertTrue(player.isBlocked(tray));



    }

}


