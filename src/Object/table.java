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
        pickedUp = false;
        //itemDescription = "What does it open?...";
        collision = true;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
