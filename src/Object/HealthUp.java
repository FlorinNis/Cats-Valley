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
        down1 = setup("/Hp/Full");

    }
}
