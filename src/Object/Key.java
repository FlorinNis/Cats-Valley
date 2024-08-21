package Object;

import Entity.Entity;
import Main.GamePanel;


public class Key extends Entity {
    GamePanel gp;

    public Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/Object/key");
        qty = 0;
        itemDescription = "It must open something...";
        equipable = false;
    }
}
