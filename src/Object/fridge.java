package Object;

import Entity.Entity;
import Main.GamePanel;

public class fridge extends Entity {

    GamePanel gp;
    public fridge(GamePanel gp) {
        super(gp);

        name = "fridge";
        down1 = setup("/Object/fridge");
        //itemDescription = "What does it open?...";
        collision = true;

    }
}
