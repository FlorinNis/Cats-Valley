package Object;

import Entity.Entity;
import Main.GamePanel;

public class lamp extends Entity {

    GamePanel gp;
    public lamp(GamePanel gp) {
        super(gp);

        name = "lamp";
        down1 = setup("/Object/lamp");
        //itemDescription = "What does it open?...";
        collision = true;

    }
}
