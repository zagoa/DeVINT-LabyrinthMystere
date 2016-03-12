package dvt.labyrinth.actions;

import dvt.devint.Fenetre;
import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Labyrinth;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Game1vs1;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Wall;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PutWall implements ActionListener {

    private Tile tile;
    private Labyrinth lab;
    private Game1vs1 game;

    public PutWall(Labyrinth lab, Tile tile){
        this.tile = tile;
        this.lab = lab;
    }

    public PutWall(Game1vs1 game, Tile tile){
        this.tile = tile;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tile.isAWall()) {
            tile.putWall();
            lab.unHighlightAll(); // If we've just blocked the player
        }
    }
}

