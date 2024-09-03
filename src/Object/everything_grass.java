package Object;

import Entity.Entity;
import Main.GamePanel;

public class everything_grass extends Entity {

    GamePanel gp;
    public everything_grass(GamePanel gp) {
        super(gp);

        name = "everything_grass";
        down1 = setup("/Object/New_Objects/everything_grass");
        entity_type = "Object";
        object_type = "everything_grass";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;

    }
}
