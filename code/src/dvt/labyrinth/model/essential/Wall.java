package dvt.labyrinth.model.essential;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * A wall item
 *
 * @author Arnaud
 */
public class Wall extends Item {
    public Wall(boolean vertical) {
        super((vertical) ? RESOURCES.WALL_VERTICAL : RESOURCES.WALL);
    }
}
