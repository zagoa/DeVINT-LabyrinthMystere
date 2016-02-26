package dvt.labyrinth;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tray {
    private final int nbCase = 18;//nombre de cases par lignes, prenant en compte les Tile Pawn+les Tile wall
    private Tile plateau[][];


    public Tray() {
        for (int i = 1; i < nbCase; i++) {
            for (int j = 1; j < nbCase; j++) {
                plateau[i][j] = new Tile(i, j,null);

            }
        }

    }


    public Tile[][] getPlateau() {
        return plateau;
    }



}