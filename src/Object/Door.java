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
        down1 = setup("/Object/door");
        open = setup("/Object/door_unlocked");
        closed = setup("/Object/door");
        doorHouse = "Andre";

        collision = true;
        locked = false;
    }
}
