package dvt.labyrinth;

import dvt.devint.Jeu;

import javax.swing.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Labyrinth extends Jeu{
    private JPanel world;
    private JLabel envi;




    @Override
    public void init() {
        world = new JPanel();
        envi.setVisible(true);
        world.setBackground(getBackground());
        Tile tile[][] = (new Tray()).getPlateau();

        for(int i = 0;i<18; i++){
            for(int j = 0;i<18; j++){
            world.add(tile[i][j].getTile());
            }

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public void reset() {

    }
}
