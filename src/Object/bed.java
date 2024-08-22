package Object;

import Entity.Entity;
import Main.GamePanel;

public class bed extends Entity {

    GamePanel gp;
    public bed(GamePanel gp) {
        super(gp);

        name = "bed";
        down1 = setup("/Object/bed");
        //itemDescription = "What does it open?...";
        collision = true;

    }
}
