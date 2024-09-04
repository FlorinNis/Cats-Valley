package Object;

import Entity.Entity;
import Main.GamePanel;

public class bush extends Entity {

    GamePanel gp;
    public bush(GamePanel gp) {
        super(gp);

        name = "Bush";
        down1 = setup("/Object/New_Objects/bush");
        entity_type = "Object";
        object_type = "bush";
        //itemDescription = "What does it open?...";
        collision = true;
        pickedUp = false;

        solidArea.x = 5;
        solidArea.y = 40;
        solidArea.width = 70;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
