package Object;

import Entity.Entity;
import Main.GamePanel;

public class InventorySlot extends Entity {
    GamePanel gp;

    public InventorySlot(GamePanel gp) {
        super(gp);

        name = "inventorySlot";
        entity_type = "Object";
        image = setup("/UI/inventory_Slot");
        image2 = setup("/UI/DescBox");

    }
}

