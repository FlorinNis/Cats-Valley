package Object;

import Entity.Entity;
import Main.GamePanel;

public class chair_right extends Entity {

    GamePanel gp;
    public chair_right(GamePanel gp) {
        super(gp);

        name = "chairRight";
        down1 = setup("/Object/chair_right");
        //itemDescription = "What does it open?...";
        collision = true;

    }
}
