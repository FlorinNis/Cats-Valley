package Object;

import Entity.Entity;
import Main.GamePanel;

public class floare2 extends Entity {

    GamePanel gp;
    public floare2(GamePanel gp) {
        super(gp);

        name = "floare2";
        down1 = setup("/Object/New_Objects/floricica");
        entity_type = "Object";
        object_type = "floare2";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
