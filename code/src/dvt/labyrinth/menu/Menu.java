package dvt.labyrinth.menu;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.game.SelectPlayer;

import javax.swing.*;
import java.util.HashMap;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * Menu inspiré du menu principal.
 * @author Justal Kevin
 */
public class Menu extends MenuGeneric {
    private static final long serialVersionUID = 1L;

    private DIFFICULTY botDifficulty;
    private HashMap<String, RESOURCES> players;

    /**
     * Le constructeur du menu
     * Permet de construire un Menu avec tout les composants
     */
    public Menu() {
        super();

        addLabel(TITLE_GAME);

        // les options possibles
        // TODO : Faire One Player
        addMenu(ONEPLAYER,new ActionMenu(this,1));
        addMenu(TWOPLAYERS, new ActionMenu(this,2));
        addMenu(HELP, new ActionMenu(this, 3));
        addMenu(QUIT, new ActionMenu(this, 5));

        // la gestion des touches directionnelles haut et bas
        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));
    }

    /**
     * La loop du menu
     */
    public void loop() {
        long lastLoopTime,timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();
            if (gameChoice==5)
                this.dispose();
            else if (gameChoice != 0) {
                this.setVisible(false);
                this.getSIVOX().stop();

                // Wait for player(s)
                players = new HashMap<>();

                switch (gameChoice) {
                    case 1: // Un joueur
                        // We need one player...
                        new SelectPlayer(this, 1).loop();
                        new SelectDifficulty(this).loop();
                        players.put("Robot", RESOURCES.BOT);
                        // We have now two players (player + bot)
                        if (players.size() == 2)
                            new Game(players, botDifficulty).loop();
                        break;

                    case 2: // Deux joueurs
                        // Initiate the game
                        new SelectPlayer(this, 2).loop();
                        // Let's play!
                        // But maybe the player hit 'ECHAP' before the end, so we can't play
                        if (players.size() == 2)
                            new Game(players).loop();
                        break;

                    case 3: // Aide
                        JOptionPane.showMessageDialog(null, "Coming soon...", "Labyrinthe", JOptionPane.PLAIN_MESSAGE);
                        break;

                    default:
                        break;
                }
                this.getSIVOX().stop();
                this.setVisible(true);
            }

            gameChoice = 0;

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + 1000000000L/60) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

    public void setPlayers(HashMap<String, RESOURCES> players) {
        this.players = players;
    }

    public void setBotDifficulty(DIFFICULTY d){
        botDifficulty = d;
    }
}
