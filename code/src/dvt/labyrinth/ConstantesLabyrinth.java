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
<<<<<<< HEAD
        // PAWNS
        THEO(PAWNPATH+"theo.jpg"),
        LEA(PAWNPATH+"zago.jpg"),
        COMPUTER(PAWNPATH+"computer"),

=======
>>>>>>> de46d472c8e4542e315bf76aa80dc77bd99a4614
        // WALL
        WALL(WALLPATH+"murHorizontal.png"),
        WALL_VERTICAL(WALLPATH+"murVertical.png"),

        // PAWNS
        THEO(PAWNPATH+"theo.jpg", true),
        GERARD(PAWNPATH+"gerard.jpg", true),
        ZAGO(PAWNPATH+"zago.jpg", true),
        PION(PAWNPATH+"pion.png", true),
        TARGETT(IMAGEPATH+"target.png", true),
        BOT(PAWNPATH+"bot.png", true, true),

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
        private boolean isAPawn;
        private boolean isABot;

        RESSOURCES(String path) {
            this.path = path;
            this.isAPawn = false;
        }

        RESSOURCES(String path, boolean isAPawn) {
            this.path = path;
            this.isAPawn = isAPawn;
        }

        RESSOURCES(String path, boolean isAPawn, boolean isABot) {
            this.path = path;
            this.isAPawn = isAPawn;
            this.isABot = isABot;
        }

        public String getPath() {
            return path;
        }

        public boolean isAPawn() {
            return isAPawn;
        }

        public boolean isABot() {
            return isABot;
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

    /* **** POSITIONS **** */
    public enum POSITIONS {
        TOP(new Position(8, 0)),
        BOTTOM(new Position(8, NBRE_CASES-1));

        private Position pos;

        POSITIONS(Position pos) {
            this.pos = pos;
        }

        public Position getPos() {
            return pos;
        }
    };
    /* ******************** */
}
