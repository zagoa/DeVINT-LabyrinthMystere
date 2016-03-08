package dvt.labyrinth;

/**
 * Created by oce_tah on 26/02/2016.
 */
public class ConstantesLabyrinth {
    public static final String TITLE_GAME = "Labyrinthe Mystère";
    public static final int NBRE_CASES = 17;
    public static final int CASE_LENGTH = 2;

    public static final String ONEPLAYER = "1 joueur";
    public static final String TWOPLAYERS = "2 joueurs";
    public static final String HELP = "Aide";
    public static final String QUIT = "Quitter";
    public static final String IMAGEPATH = "../ressources/images/";
    public static final String SOUNDPATH = "../ressources/sons/";
    public static final String IMAGEEXT = ".bmp";

    public static final int MARGE_LEFT_RIGHT = 80;
    public static final int MARGE_TOP_BOT = 3;

    public enum RESSOURCES {
        THEO(IMAGEPATH+"theo.jpg"),
        LEA(IMAGEPATH+"lea.jpg"),
        WALL(IMAGEPATH+"murHorizontal.png"),
        CLOUD(IMAGEPATH+"cloud.jpg"),
        TRANSPARENT(null);

        private String path;

        RESSOURCES(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    };

    public enum DIRECTIONS {
        BACK,
        FRONT,
        RIGHT,
        LEFT;
    };
}
