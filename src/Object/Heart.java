package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends Entity {
    GamePanel gp;

    public Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        entity_type = "Object";
        pickedUp = false;
        image = setup("/Hp/Low");
        image2 = setup("/Hp/Half");
        image3 = setup("/Hp/Full");

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}

