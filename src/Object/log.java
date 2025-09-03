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
        dashable = true;
        collision = true;

        solidArea.x = gp.tileSize/4;
        solidArea.y = gp.tileSize/2;
        solidArea.width = gp.tileSize/2;
        solidArea.height = gp.tileSize/4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
