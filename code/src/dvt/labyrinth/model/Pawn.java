package dvt.labyrinth.model;

import javax.swing.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

public class Pawn extends Item {
    String name;
    int x;
    int y;


    public Pawn(String name, String image, int x, int y) {
        this.name = name;
        item = new JLabel(new ImageIcon(IMAGEPATH + name + IMAGEEXT));
        this.x = x;
        this.y = y;
    }

}