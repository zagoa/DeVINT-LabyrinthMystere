package dvt.labyrinth;

/**
 * Class used for some constants all over the game.
 *
 * @author Adrian
 */
public class ConstantesLabyrinth {
    /* **** GAME **** */
    public static final int NBRE_CASES = 17;
    public static final int CASE_LENGTH = 2;
    public static final int WALL_LENGTH = 1;
    /* ************** */

    /* **** TEXT **** */
    public static final String TITLE_GAME = "Labyrinthe Mystère";
    public static final String ONEPLAYER = "1 joueur";
    public static final String TWOPLAYERS = "2 joueurs";
    public static final String HELP = "Aide";
    public static final String QUIT = "Quitter";
    /* ************** */

    /* **** IMAGES **** */
    public static final String IMAGEPATH = "../ressources/images/";
    public static final String ARROWPATH = IMAGEPATH+"arrows/";
    public static final String PAWNPATH = IMAGEPATH+"pawns/";
    public static final String WALLPATH = IMAGEPATH+"walls/";
    /* **************** */

    /* **** SOUND **** */
    public static final String SOUNDPATH = "../ressources/sons/";
    /* *************** */

    public static final int MARGE_LEFT_RIGHT = 80;
    public static final int MARGE_TOP_BOT = 3;

    /* **** RESSOURCES **** */
    public enum RESSOURCES {
        // PAWNS
        THEO(PAWNPATH+"theo.jpg"),
        GERARD(PAWNPATH+"gerard.jpg"),

        // WALL
        WALL(WALLPATH+"murHorizontal.png"),
        WALL_VERTICAL(WALLPATH+"murVertical.png"),

        // ARROWS
        ARROW_LEFT(ARROWPATH+"arrow_left.png"),
        ARROW_RIGHT(ARROWPATH+"arrow_right.png"),
        ARROW_UP(ARROWPATH+"arrow_up.png"),
        ARROW_DOWN(ARROWPATH+"arrow_down.png"),
        // ARROWS SMALL
        ARROW_SMALL_LEFT(ARROWPATH+"arrow_small_left.png"),
        ARROW_SMALL_RIGHT(ARROWPATH+"arrow_small_right.png"),
        ARROW_SMALL_UP(ARROWPATH+"arrow_small_up.png"),
        ARROW_SMALL_DOWN(ARROWPATH+"arrow_small_down.png"),

        // TRANSPARENT
        TRANSPARENT(null),

        // OTHERS
        TARGET(IMAGEPATH+"target.png");

        private String path;

        RESSOURCES(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    };
    /* ******************** */

    /* **** DIRECTIONS **** */
    public enum DIRECTIONS {
        BACK,
        FRONT,
        RIGHT,
        LEFT;
    };
    /* ******************** */
}
