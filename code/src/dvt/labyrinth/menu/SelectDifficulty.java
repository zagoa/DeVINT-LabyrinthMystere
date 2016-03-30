package dvt.labyrinth.menu;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * Menu inspiré du menu principal.
 * @author Justal Kevin
 */
public class SelectDifficulty extends MenuGeneric {
    private static final long serialVersionUID = 1L;

    private Menu menu;

    /**
     * Le constructeur du menu
     * Permet de construire un Menu avec tout les composants
     */
    public SelectDifficulty(Menu menu) {
        super();

        addLabel(TITLE_GAME_DIFFICULTY);

        this.menu = menu;

        addMenu(DIFFICULTY.EASY.toString(),new ActionMenu(this,1));
        addMenu(DIFFICULTY.MEDIUM.toString(), new ActionMenu(this,2));
        addMenu(DIFFICULTY.HARD.toString(), new ActionMenu(this, 3));

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

                switch (gameChoice) {
                    case 1: // Easy
                        selectedDifficulty(DIFFICULTY.EASY);
                        return;

                    case 2: // Medium
                        selectedDifficulty(DIFFICULTY.MEDIUM);
                        return;

                    case 3: // Hard
                        selectedDifficulty(DIFFICULTY.HARD);
                        return;

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

    public void selectedDifficulty(DIFFICULTY d) {
        menu.setBotDifficulty(d);
        dispose();
    }
}
