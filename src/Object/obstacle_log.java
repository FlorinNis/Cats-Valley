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
        dashable = true;
        collision = true;

        solidArea.x = 0;
        solidArea.y = gp.tileSize/4*3;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize/4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
