package dvt.labyrinth;

import dvt.labyrinth.model.Item;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tile {
    private JPanel tile;
    private int x;
    private int y;
    private Item item;

    public Tile(int x, int y, Item item){
        this.x = x;
        this.y = y;
        this.item = item;
        this.tile = new JPanel();
        tile.setSize(new Dimension(50, 50));
        tile.setLocation(x,y);
        tile.setBackground(Color.blue); //TODO mettre des couleurs

    }
    public JPanel getTile() {
        return tile;
    }
}
