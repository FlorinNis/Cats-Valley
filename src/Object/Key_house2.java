package Object;

import Entity.Entity;
import Main.GamePanel;


public class Key_house2 extends Entity {
    GamePanel gp;

    public Key_house2(GamePanel gp) {
        super(gp);

        name = "Key Andre House";
        down1 = setup("/Object/key");
        qty = 0;
        itemDescription = "Where was this house again...?";
        equipable = false;
    }
}
