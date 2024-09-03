package Object;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;


public class grass1 extends Entity {

    GamePanel gp;
    private int grass_animation = 1;

    public grass1(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "grass1";
        animation_1 = setupForGrass("/Object/New_Objects/grasss_1");
        animation_2 = setupForGrass("/Object/New_Objects/grasss_2");
        animation_3 = setupForGrass("/Object/New_Objects/grasss_3");
        animation_4 = setupForGrass("/Object/New_Objects/grasss_4");
        entity_type = "Object";
        object_type = "grass1";
        //itemDescription = "What does it open?...";
        collision = false;
        pickedUp = false;


    }

    private void animate() {
        spriteCounterAnim++;
        if (spriteCounterAnim > 100) {
            if (grass_animation == 1) {
                grass_animation = 2;
            } else if (grass_animation == 2) {
                grass_animation = 3;
            } else if (grass_animation == 3) {
                grass_animation = 1;
            }
        }

    }

    //@Override
//    public void draw(Graphics2D g2) {
//        animate();
//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - gp.tileSize * 4 &&
//                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize * 4 &&
//                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - gp.tileSize * 4 &&
//                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize * 4)
//        {
//
//
//
//        }
    }
//}
