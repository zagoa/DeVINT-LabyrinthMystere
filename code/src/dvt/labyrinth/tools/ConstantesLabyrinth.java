package dvt.labyrinth.tools;


import t2s.SIVOXDevint;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    public static final SIZE DEFAULT_SIZE = SIZE.NORMAL;

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
    static {
        setConfig(DEFAULT_SIZE);
    }

    public static void setConfig(SIZE c) {
        switch (c) {
            case GRAND:
                config.put(CONFIG.LENGTH, NBRE_CASES-2);
                config.put(CONFIG.WALL  , WALL_LENGTH);
                break;

            case TRES_GRAND:
                config.put(CONFIG.LENGTH, NBRE_CASES-4);
                config.put(CONFIG.WALL  , WALL_LENGTH-1);
                break;

            default:
                config.put(CONFIG.LENGTH, NBRE_CASES);
                config.put(CONFIG.WALL  , WALL_LENGTH);
                break;
        }

        POSITIONS.updatePositions();
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
        DUMBO(PAWNPATH+"dumbo.jpg",true),
        FOOT(PAWNPATH+"FOOT.jpg",true),
        PICKA(PAWNPATH+"picka.png",true),
        HAND(PAWNPATH+"handball.jpg",true),
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
        TOP(null),
        BOTTOM(null);

        private Position pos;

        POSITIONS(Position pos) {
            this.pos = pos;
        }

        public Position getPos() {
            return pos;
        }

        public static void updatePositions() {
            for (POSITIONS p : values())
                p.updatePos();
        }

        public void updatePos() {
            switch (this) {
                case TOP:
                    pos = new Position(config.get(CONFIG.LENGTH)/2, 0);
                    break;

                case BOTTOM:
                    pos = new Position(config.get(CONFIG.LENGTH)/2, config.get(CONFIG.LENGTH)-1);
                    break;

                default:
                    break;
            }
        }
    };
    /* ******************** */

    private static final ArrayList<VOCAL> list1P = new ArrayList<>();
    private static final ArrayList<VOCAL> list2P = new ArrayList<>();

    public static VOCAL getRandomVocalTurn(int nPlayers) {
        return (nPlayers == 1) ? list1P.get((new Random()).nextInt(list1P.size())) : list2P.get((new Random()).nextInt(list2P.size()));
    }

    /* **** VOCAL **** */
    public enum VOCAL {
        START("%s commence à jouer !"),
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

        // TURN 1P
        TURN_1("C'est à %s de jouer ! ", 1),
        TURN_2("A toi de jouer ! ", 1),
        TURN_4("C'est ton tour ! ", 1),
        TURN_5("Vas-y, joues !", 1),

        // TURN 2P
        TURN_2P_1("C'est à %s de jouer ! ", 2),
        TURN_2P_2("%s, à toi de jouer ! ", 2),
        TURN_2P_3("%s, à toi ! ", 2),
        TURN_2P_4("%s, c'est ton tour ! ", 2),
        TURN_2P_5("Vas-y %s, joues !", 2),

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

        VOCAL(String str, int i) {
            this.str = str;
            addList(this, i);
        }

        private void addList(VOCAL v, int i) {
            if (i == 1)
                list1P.add(v);
            else
                list2P.add(v);
        }

        public static VOCAL getRandom(int i) {
            /*if (i == 1)
                return list1P.get((new Random()).nextInt(list1P.size()));
            return list2P.get((new Random()).nextInt(list2P.size()));*/
            return null;
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
