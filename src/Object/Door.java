package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends Entity {

    GamePanel gp;
    public Door(GamePanel gp) {
        super(gp);

        name = "Door";
        entity_type = "Object";
        object_type = "door";
        pickedUp = false;
        down1 = setup("/Object/door");
        open = setup("/Object/door_unlocked");
        closed = setup("/Object/door");
        doorHouse = "none";

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        collision = true;
        locked = true;
    }
}
