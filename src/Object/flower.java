package Object;

import Entity.Entity;
import Main.GamePanel;

public class flower extends Entity {

    GamePanel gp;
    public flower(GamePanel gp) {
        super(gp);

        name = "flower";
        down1 = setup("/Object/flower");
        //itemDescription = "What does it open?...";
        collision = true;

    }
}
