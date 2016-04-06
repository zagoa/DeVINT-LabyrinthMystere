package dvt.labyrinth.tools;


import t2s.SIVOXDevint;

import java.awt.*;

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
    public static final int WALL_NUMBER = 9;

    public static final Color DEFAULT_TRAY_COLOR = Color.BLACK;
    public static final int DEFAULT_SIZE_LEVEL = 50;
    /* ************** */

    /* **** TEXT **** */
    public static final String TITLE_GAME = "Labyrinthe Mystère";
    public static final String TITLE_GAME_DIFFICULTY = "Selectionnez la difficulté";
    public static final String ONEPLAYER = "1 joueur";
    public static final String TWOPLAYERS = "2 joueurs";
    public static final String TRAINING = "Entrainement";
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

    /* **** RESOURCES **** */
    public enum RESOURCES {

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

        RESOURCES(String path) {
            this.path = path;
            this.isAPawn = false;
        }

        RESOURCES(String path, boolean isAPawn) {
            this.path = path;
            this.isAPawn = isAPawn;
        }

        RESOURCES(String path, boolean isAPawn, boolean isABot) {
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

    /* **** VOCAL **** */
    public enum VOCAL {
        START("%s commence à jouer !"),
        TURN_1P("C'est à toi de jouer ! "),
        TURN_2P("C'est à %s de jouer ! "),
        PSEUDO_LENGTH("Veuillez entrer un pseudo pour le joueur %s !"),
        SELECT_PAWN("Veuillez selectionner un icone pour le joueur %s, en cliquant sur une des images !"),
        SAME_PSEUDO("Vous ne pouvez pas avoir le même pseudo que le joueur %s. Modifiez votre pseudo."),
        WIN("Félicitations %s ! Tu as remporté la partie. Appuie sur échap pour revenir au menu ou en cliquant sur le bouton Retour."),
        ERROR_WALL("Impossible de poser un mur ici ! "),
        BOT_WALL("Le bot vient de poser un mur"),
        HUMAN_WALL("Un mur a été posé, choisi là où le deuxième mur doit être, à l'aide des fléches directionnels"),
        NOT_ENOUGTH_WALL("Tu n'as plus de murs disponibles"),
        BLOCK("Tu n'a pas le droit de bloquer le jeu");
        private String str;

        VOCAL(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }


    public static String parse(VOCAL v, String s) {
        return String.format(v.toString(), s);
    }

    public static String parse(VOCAL v, int i) {
        return String.format(v.toString(), new Integer(i).toString());
    }

    public static void playText(SIVOXDevint sivox, String sentence) {
        sivox.stop();
        sivox.playText(sentence);
    }
    /* ******************** */

    public enum DIFFICULTY{
        EASY,
        MEDIUM,
        HARD;
    }
}
