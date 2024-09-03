package Object;

import Entity.Entity;
import Main.GamePanel;

public class floare4 extends Entity {

    GamePanel gp;
    public floare4(GamePanel gp) {
        super(gp);

        name = "floare4";
        down1 = setup("/Object/New_Objects/trandafir");
        entity_type = "Object";
        object_type = "floare4";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
