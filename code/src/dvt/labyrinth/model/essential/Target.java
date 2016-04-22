package dvt.labyrinth.model.essential;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.StretchIcon;

/**
 * Created by Adrian on 14/03/2016.
 */
public class Target extends Item {
    public Target() {
        super(ConstantesLabyrinth.RESOURCES.TARGET);
    }

    @Override
    public StretchIcon getIcon() {
        return new StretchIcon(res.getPath());
    }
}
