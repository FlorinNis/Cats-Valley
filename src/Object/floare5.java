package Object;

import Entity.Entity;
import Main.GamePanel;

public class floare5 extends Entity {

    GamePanel gp;
    public floare5(GamePanel gp) {
        super(gp);

        name = "floare5";
        down1 = setup("/Object/New_Objects/trandafir_2");
        entity_type = "Object";
        object_type = "floare5";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
