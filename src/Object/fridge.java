package Object;

import Entity.Entity;
import Main.GamePanel;

public class fridge extends Entity {

    GamePanel gp;
    public fridge(GamePanel gp) {
        super(gp);

        name = "fridge";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Object/fridge");
        //itemDescription = "What does it open?...";
        collision = true;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
