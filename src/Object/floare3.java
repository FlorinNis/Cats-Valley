package Object;

import Entity.Entity;
import Main.GamePanel;

public class floare3 extends Entity {

    GamePanel gp;
    public floare3(GamePanel gp) {
        super(gp);

        name = "floare3";
        down1 = setup("/Object/New_Objects/planta");
        entity_type = "Object";
        object_type = "floare3";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
