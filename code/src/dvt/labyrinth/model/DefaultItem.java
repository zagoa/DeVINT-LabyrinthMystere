package dvt.labyrinth.model;

import dvt.labyrinth.ConstantesLabyrinth;

/**
 * Created by Adrian on 08/03/2016.
 */
public class DefaultItem extends Item {

    public DefaultItem(int x, int y) {
        super(ConstantesLabyrinth.RESSOURCES.TRANSPARENT, x, y);
    }
}
