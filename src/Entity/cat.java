package Entity;

import Main.GamePanel;

import java.util.Random;

public class cat extends Entity {

    public int text = 0;

    public cat(GamePanel gp) {
        super(gp);

        name = "pisica1";
        draw_direction = "stand";
        entity_type = "NPC";
        speed = 0;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        checkNPC.width = 140;
        checkNPC.height = 140;

        getImage();
        setDialogue();
    }

    public void getImage(){

        stand1 = setup("/NPC/cat");
        stand2 = setup("/NPC/cat");
        right1 = setup("/NPC/cat");
        right2 = setup("/NPC/cat");
        up1 = setup("/NPC/cat");
        up2 = setup("/NPC/cat");
        left1 = setup("/NPC/cat");
        left2 = setup("/NPC/cat");
        down1 = setup("/NPC/cat");
        down2 = setup("/NPC/cat");
    }
    public void setDialogue() {

        dialogues[0] = "Miau...";

    }
    public void setAction() {
        move_direction = "down";
//        actionLockCounter ++;
//
//        if (actionLockCounter == 120) {
//
//            Random random = new Random();
//            int i = random.nextInt(0, 120) + 1;
//            System.out.println("I = " + i);
//
//            if (i <= 15) {
//                System.out.println("Up");
//                move_direction = "up";
//            }
//            if (i > 15 && i <= 30) {
//                move_direction = "down";
//            }
//            if (i > 30 && i <= 45) {
//                move_direction = "left";
//            }
//            if (i > 60 && i <= 75) {
//                move_direction = "right";
//            }
//            if (i > 90 && i <= 105) {
//                move_direction = "up_right";
//            }
//            if (i > 105 && i <= 110) {
//                move_direction = "up_left";
//            }
//            if (i > 110 && i <= 115) {
//                move_direction = "down_right";
//            }
//            if (i > 115 && i <= 120) {
//                move_direction = "down_left";
//            }
//            //if (i > 100 && i <= 125) {
//            //    direction = "stand";
//            //}
//            actionLockCounter = 0;
//        }
    }
    public void speak() {

        if(text == 0) {
            super.speak();
            if(dialogueIndex == 1) {
                text++;
            }
        } else if(text == 1) {
            gp.gameState = gp.playState;
            dialogueIndex = 0;
            text=0;
        }
    }


}
