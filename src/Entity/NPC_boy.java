package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC_boy extends Entity {

    public int text = 0;

    public NPC_boy(GamePanel gp) {
        super(gp);

        draw_direction = "down";
        entity_type = "NPC";

        solidArea.x = 10;
        solidArea.y = 60;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        checkNPC.x = 0;
        checkNPC.y = 0;
        checkNPC.width = 140;
        checkNPC.height = 140;

        getImage();
        setDialogue();
    }

    public void getImage() {

        down1 = setup("/NPC/boy/boy1");
        down2 = setup("/NPC/boy/boy2");
        up1 = setup("/NPC/boy/boy3");
        up2 = setup("/NPC/boy/boy4");
        left1 = setup("/NPC/boy/boy5");
        left2 = setup("/NPC/boy/boy6");
    }

    public void setDialogue() {

        dialogues[0] = "...";
        dialogues[1] = "Leave me alone...";

    }

    public void speak() {

        if(text == 0) {
            super.speak();
            if(dialogueIndex == 2) {
                text++;
            }
        } else if(text == 1) {
            dialogueIndex = 1;
            super.speak();
            text++;
        } else if(text == 2) {
            gp.gameState = gp.playState;
            text = 1;
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - gp.tileSize * 2 &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize * 2 &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - gp.tileSize * 2 &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize * 2) {

            switch(move_direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = left1;
                    }
                    if(spriteNum == 2) {
                        image = left2;
                    }
                    break;
                //case "stand":
                //    if(spriteNum == 1) {
                //        image = stand1;
                //    }
                //    if(spriteNum == 2) {
                //        image = stand2;
                //    }
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
            // Draw the checkNPC rectangle
            g2.setColor(Color.RED);
            g2.drawRect(screenX + checkNPC.x, screenY + checkNPC.y, checkNPC.width, checkNPC.height);

            // Draw the solidArea rectangle
            g2.setColor(Color.BLUE);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}