package Object;

import Entity.Entity;
import Main.GamePanel;

public class table extends Entity {

    GamePanel gp;
    public table(GamePanel gp) {
        super(gp);

        name = "table";
        down1 = setup("/Object/table");
        entity_type = "Object";
        //itemDescription = "What does it open?...";
        collision = true;

    }
}
