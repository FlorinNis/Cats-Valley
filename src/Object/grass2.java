package Object;

import Entity.Entity;
import Main.GamePanel;

public class grass2 extends Entity {

    GamePanel gp;
    public grass2(GamePanel gp) {
        super(gp);

        name = "grass2";
        down1 = setup("/Object/New_Objects/grass_obiect");
        entity_type = "Object";
        object_type = "grass2";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
