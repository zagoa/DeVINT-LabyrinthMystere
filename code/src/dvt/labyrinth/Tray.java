package dvt.labyrinth;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tray {
    private final int nbCase = 18;//nombre de cases par lignes, prenant en compte les Tile Pawn+les Tile wall
    private Tile plateau[][];


    public Tray() {
        for (int i = 0; i < nbCase; i++) {
            for (int j = 0; j < nbCase; j++) {
                plateau[i][j] = new Tile(i, j);

            }
        }

    }



}