package Object;

import Entity.Entity;
import Main.GamePanel;

public class chair_left extends Entity {

    GamePanel gp;
    public chair_left(GamePanel gp) {
        super(gp);

        name = "chairLeft";
        down1 = setup("/Object/chair_left");
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
