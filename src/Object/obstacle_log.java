package Object;

import Entity.Entity;
import Main.GamePanel;

public class obstacle_log extends Entity {

    GamePanel gp;
    public obstacle_log(GamePanel gp) {
        super(gp);

        name = "log";
        entity_type = "Object";
        object_type = "obstacle_log";
        pickedUp = false;
        down1 = setup("/Object/New_Objects/log_dash");
        //itemDescription = "What does it open?...";
        dashable = true;
        collision = true;

        solidArea.x = 8;
        solidArea.y = 72;
        solidArea.width = 92;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
