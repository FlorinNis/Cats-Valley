package Object;

import Entity.Entity;
import Main.GamePanel;


public class letter extends Entity {
    GamePanel gp;

    public letter(GamePanel gp) {
        super(gp);

        name = "Letter";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Object/letter");
        qty = 0;
        itemDescription = "Who knows what is says...";
        equipable = false;
    }
}
