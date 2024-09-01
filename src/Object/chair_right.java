package Object;

import Entity.Entity;
import Main.GamePanel;

public class chair_right extends Entity {

    GamePanel gp;
    public chair_right(GamePanel gp) {
        super(gp);

        name = "chairRight";
        down1 = setup("/Object/chair_right");
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
