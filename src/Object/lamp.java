package Object;

import Entity.Entity;
import Main.GamePanel;

public class lamp extends Entity {

    GamePanel gp;
    public lamp(GamePanel gp) {
        super(gp);

        name = "lamp";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Object/lamp");
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
