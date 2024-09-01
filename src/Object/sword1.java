package Object;

import Entity.Entity;
import Main.GamePanel;


public class sword1 extends Entity {
    GamePanel gp;

    public sword1(GamePanel gp) {
        super(gp);

        name = "Sword of Beelzebub";
        down1 = setup("/Player/player_sword_1/sword_1");
        entity_type = "Object";
        pickedUp = false;
        qty = 0;
        attackDamage = 2;
        itemDescription = "Does it work?";
        equipable = true;
        equiped = false;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
