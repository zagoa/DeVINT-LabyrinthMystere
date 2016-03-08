package dvt.labyrinth.model;

import dvt.labyrinth.ConstantesLabyrinth.RESSOURCES;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Etienne on 26/02/16.
 */
public abstract class Item {

    protected RESSOURCES res;
    protected int x;
    protected int y;

    public Item(RESSOURCES res) {
        this.res = res;
    }

    public RESSOURCES getRes() {
        return res;
    }

    public String getResPath() {
        return res.getPath();
    }

    public void setRes(RESSOURCES res) {
        this.res = res;
    }
}
