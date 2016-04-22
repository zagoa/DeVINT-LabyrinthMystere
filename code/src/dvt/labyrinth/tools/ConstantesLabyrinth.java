package dvt.labyrinth.tools;


import t2s.SIVOXDevint;

import java.awt.*;
import java.util.HashMap;

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

    /* **** CONFIGURATION **** */
    public static final String CONFIG_FILE = "../ressources/configuration.xml";

    public enum CONFIG {
        LENGTH,
        WALL
    }

    public enum SIZE{
        NORMAL,
        GRAND,
        TRES_GRAND;

        @Override
        public String toString() {
            switch (this) {
                case GRAND:
                    return "GRAND";

                case TRES_GRAND:
                    return "TRES GRAND";

                default:
                    return "NORMAL";
            }
        }

        public static SIZE find(String s){
            for(SIZE v : values())
                if( SIZE.valueOf(s) == v)
                    return v;

            return null;
        }
    }

    public static HashMap<CONFIG, Integer> config = new HashMap<>();

    public static void setConfig(SIZE c) {
        switch (c) {
            case GRAND:
                config.put(CONFIG.LENGTH, NBRE_CASES-2);
                config.put(CONFIG.WALL  , WALL_LENGTH);
                break;

            default:
                config.put(CONFIG.LENGTH, NBRE_CASES);
                config.put(CONFIG.WALL  , WALL_LENGTH);
                break;
        }
    }
    /* ************** */

    public static final Color DEFAULT_TRAY_COLOR = Color.BLACK;
    public static final int DEFAULT_SIZE_LEVEL = 50;
    /* ************** */

    /* **** TEXT **** */
    public static final String TITLE_GAME = "Labyrinthe Mystère";
    public static final String TITLE_GAME_DIFFICULTY = "Selectionnez la difficulté";
    public static final String TITLE_GAME_SIZE = "Quelle taille de jeu préfères-tu ?";
    public static final String ONEPLAYER = "1 joueur";
    public static final String TWOPLAYERS = "2 joueurs";
    public static final String TRAINING = "Entrainement";
    public static final String SETTINGS = "Réglages";
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
        BOT(PAWNPATH+"bot.png", true, true),
        BASKET(PAWNPATH+"BASKET.jpg",true),
        TOUR(PAWNPATH+"tower.jpg",true),
        DRAGON(PAWNPATH+"dragon.jpg",true),
        SINGE(PAWNPATH+"singe.jpg",true),
        SINGE2(PAWNPATH+"singe2.jpg",true),
        VOITURE(PAWNPATH+"voiture.jpg",true),
        DUMBO(PAWNPATH+"dumbo.jpg",true),
        FOOT(PAWNPATH+"FOOT.jpg",true),
        NOEL(PAWNPATH+"noel.jpg",true),
        OISEAU(PAWNPATH+"oiseau.jpg",true),
        PICKA(PAWNPATH+"picka.png",true),
        HAND(PAWNPATH+"handball.jpg",true),
        PICKACHU(PAWNPATH+"pickachu.jpg",true),
        RUGBY(PAWNPATH+"rugby.jpg",true),

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
        BOTTOM(new Position(8, config.get(CONFIG.LENGTH)-1));

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
        ECHAP("Appuie sur échap pour revenir au menu ou clique sur le bouton Retour."),
        WIN("Félicitations %s ! Tu as remporté la partie. "+ECHAP),
        ERROR_WALL("Impossible de poser un mur ici ! "),
        BOT_WALL("Le bot vient de poser un mur"),
        HUMAN_WALL("Un mur a été posé, click sur une case jaune pour poser le deuxième"),
        NOT_ENOUGTH_WALL("Tu n'as plus de murs disponibles"),
        BLOCK("Tu n'a pas le droit de bloquer le jeu"),

        // TRAINING
        T_START("Bienvenue dans l'entrainement. Ici, nous allons t'apprendre à jouer au "+TITLE_GAME+". Ton personnage, représenté par un crabe, se trouve en bas du plateau. Ton but est de rejoindre l'endroit marqué par les cibles, en haut." +
                "Pour se faire, utilise les flèches directionnelles pour déplacer ton joueur. Vas-y, entraine toi un peu ; rejoins les cibles !"),
        T_FIRST_WALL("Oh ! Un mur a été posé devant toi. Dans l'entrainement, des murs vont régulièrement apparaitre. Tu peux toi aussi en poser, où tu le souhaites. Pour se faire, il te suffit d'utiliser ta sourie et de cliquer entre les cases, et choisir la direction" +
                " exacte du mur ! Mais attention, rappelles toi que chaque mur fait deux cases, et que tu ne peux pas bloquer le jeu ! Allez, vas-y, prends ta sourie et essaie, pose un mur !"),
        T_WIN("Bravo, tu as terminé l'entrainement. Maintenant, commence une vraie partie tout seul contre un robot, ou bien joue contre un ami !! "
                + ECHAP);

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

    public static void playText(SIVOXDevint sivox, VOCAL vocal) {
        playText(sivox, vocal.toString());
    }
    /* ******************** */

    public enum DIFFICULTY{
        FACILE,
        MOYEN,
        DIFFICILE;
    }
}
