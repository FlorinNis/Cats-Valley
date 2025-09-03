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

        solidArea.x = 0;
        solidArea.y = gp.tileSize/2;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize/2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
