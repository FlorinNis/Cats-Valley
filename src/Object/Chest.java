package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends Entity {

    GamePanel gp;
    public Chest(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/Object/chest");
        entity_type = "Object";
        pickedUp = false;
        itemDescription = "What does it open?...";

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
