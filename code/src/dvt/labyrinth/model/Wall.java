package dvt.labyrinth.model;

import javax.swing.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * Created by Arnaud on 23/02/2016.
 */
public class Wall extends Item{

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Wall(int x1, int x2, int y1, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        if(y1 == y2){
            item = new JLabel(new ImageIcon(IMAGEPATH + "murHorizontal" + IMAGEEXT));
        }
        else{
            item = new JLabel(new ImageIcon(IMAGEPATH + "murVertical" + IMAGEEXT));
        }
    }

}
