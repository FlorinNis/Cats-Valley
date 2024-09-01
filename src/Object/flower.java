package Object;

import Entity.Entity;
import Main.GamePanel;

public class flower extends Entity {

    GamePanel gp;
    public flower(GamePanel gp) {
        super(gp);

        name = "flower";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Object/flower");
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
