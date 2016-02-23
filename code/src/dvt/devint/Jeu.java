package src.dvt.devint;

import static src.dvt.jeuchronometre.ConstantesJeu.OPTIMAL_TIME;

public abstract class Jeu extends Fenetre {
    private static final long serialVersionUID = 1L;

    /**
     * Permet de creer et initialiser la fenetre de jeu
     */
    public Jeu() {
        init();
        this.pack(); 
        this.requestFocusInWindow();
        this.setVisible(true);
    }
    
    /**
     * La loop du jeu qui permet de garder un FPS (frame per seconds) constant peu importe le PC
     */
    public void loop() {
        long lastLoopTime,timeLoop;

        // initialisation des valeurs
        reset();

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            // mise � jour des informations
            update();
            
            // r�-affichage � chaque tour
            // optimisation possible : afficher seulement quand certaines informations ont chang�
            // n�cessite de lisser l'affichage d'un temps t � t+delta
            render();

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }
    
    /**
     * m�thode appel�e � la cr�ation de l'objet
     * initialisation des composants graphiques et des variables de jeu
     * association des actions aux touches via les m�thodes addControl
     */
    public abstract void init();
    
    
    /**
     * m�thode appel�e � chaque tour de boucle
     * mise � jour des variables de jeu
     */
    public abstract void update();
    
    /**
     * m�thode appel�e � chaque tour de boucle
     * mise � jour des composants graphiques 
     */
    public abstract void render();
    
    /**
     * m�thode appel�e avant la boucle
     * utilis�e pour la r�initialisation
     */
    public abstract void reset();
}
