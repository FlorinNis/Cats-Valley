package Object;

import Entity.Entity;
import Main.GamePanel;

public class obstacle_log extends Entity {

    GamePanel gp;
    public obstacle_log(GamePanel gp) {
        super(gp);

        name = "log";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Object/obstacle_log");
        //itemDescription = "What does it open?...";
        dashable = true;
        collision = true;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
