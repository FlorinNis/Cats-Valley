package Object;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;


public class grass1 extends Entity {

    GamePanel gp;

    public grass1(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "grass1";
        animation_1 = setupForGrass("/Object/New_Objects/grasss_1");
        animation_2 = setupForGrass("/Object/New_Objects/grasss_2");
        animation_3 = setupForGrass("/Object/New_Objects/grasss_3");
        animation_4 = setupForGrass("/Object/New_Objects/grasss_4");
        entity_type = "Object";
        object_type = "grass1";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

        solidArea.x = 15;
        solidArea.y = 40;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }


}
