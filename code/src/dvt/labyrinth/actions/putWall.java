package dvt.labyrinth.actions;

import dvt.devint.Fenetre;
import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Labyrinth;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Wall;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PutWall implements ActionListener {

    private Tile tile;

    public PutWall(Tile tile){
        this.tile = tile;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tile.setItem(new Wall());

        }
    }

