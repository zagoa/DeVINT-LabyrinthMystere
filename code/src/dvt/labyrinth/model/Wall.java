package dvt.labyrinth.model;

import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * A wall item
 *
 * @author Arnaud
 */
public class Wall extends Item{
    public Wall(boolean vertical) {
        super((vertical) ? RESSOURCES.WALL_VERTICAL : RESSOURCES.WALL);
    }
}
