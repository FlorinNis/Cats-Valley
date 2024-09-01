package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class HealthUp extends Entity {
    GamePanel gp;
    public HealthUp(GamePanel gp) {
        super(gp);

        name = "HealthUp";
        entity_type = "Object";
        pickedUp = false;
        down1 = setup("/Hp/Full");

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
