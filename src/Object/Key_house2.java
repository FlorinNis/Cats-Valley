package Object;

import Entity.Entity;
import Main.GamePanel;


public class Key_house2 extends Entity {
    GamePanel gp;

    public Key_house2(GamePanel gp) {
        super(gp);

        name = "Key Andre House";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Object/key");
        qty = 0;
        itemDescription = "Where was this house again...?";
        equipable = false;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
