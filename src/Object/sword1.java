package Object;

import Entity.Entity;
import Main.GamePanel;


public class sword1 extends Entity {
    GamePanel gp;

    public sword1(GamePanel gp) {
        super(gp);

        name = "Sword of Beelzebub";
        down1 = setup("/Player/player_sword_1/sword_1");
        qty = 0;
        attackDamage = 2;
        itemDescription = "Does it work?";
        equipable = true;
        equiped = false;
    }
}
