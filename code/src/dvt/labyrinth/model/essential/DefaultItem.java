package dvt.labyrinth.model.essential;

import dvt.labyrinth.model.essential.Item;
import dvt.labyrinth.tools.ConstantesLabyrinth;

/**
 * A default item (blank)
 *
 * @author Adrian
 */
public class DefaultItem extends Item {

    public DefaultItem() {
        super(ConstantesLabyrinth.RESOURCES.TRANSPARENT);
    }
}
