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
        itemDescription = "What does it open?...";

    }
}
