package Object;

import Entity.Entity;
import Main.GamePanel;

public class tree extends Entity {

    GamePanel gp;
    public tree(GamePanel gp) {
        super(gp);

        name = "tree";
        down1 = setupForTree("/Object/New_Objects/copac_1");
        down2 = setupForTree("/Object/New_Objects/copac_2");
        down3 = setupForTree("/Object/New_Objects/copac_3");
        entity_type = "Object";
        object_type = "tree";
        //itemDescription = "What does it open?...";
        collision = true;
        pickedUp = false;

        solidArea.x = 120;
        solidArea.y = 80;
        solidArea.width = 120;
        solidArea.height = 50;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
