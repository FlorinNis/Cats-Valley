package Object;

import Entity.Entity;
import Main.GamePanel;

public class floare1 extends Entity {

    GamePanel gp;
    public floare1(GamePanel gp) {
        super(gp);

        name = "floare1";
        down1 = setup("/Object/New_Objects/floare");
        entity_type = "Object";
        object_type = "floare1";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
