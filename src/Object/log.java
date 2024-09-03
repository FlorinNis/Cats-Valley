package Object;

import Entity.Entity;
import Main.GamePanel;

public class log extends Entity {

    GamePanel gp;
    public log(GamePanel gp) {
        super(gp);

        name = "log";
        entity_type = "Object";
        object_type = "log";
        pickedUp = false;
        down1 = setup("/Object/New_Objects/log");
        //itemDescription = "What does it open?...";
        dashable = true;
        collision = true;

        solidArea.x = 24;
        solidArea.y = 50;
        solidArea.width = 45;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
