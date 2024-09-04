package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC_Pisica_2 extends Entity {

    public int text = 0;

    public NPC_Pisica_2(GamePanel gp) {
        super(gp);

        draw_direction = "down";
        entity_type = "NPC";
        speed = 0;

        solidArea.x = 20;
        solidArea.y = 18;
        solidArea.width = 70;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        checkNPC.x = 0;
        checkNPC.y = 0;
        checkNPC.width = gp.tileSize * 3;
        checkNPC.height = gp.tileSize * 3;

        getImage();
        setDialogue();
    }

    public void getImage(){

        right1 = setup("/NPC/Pisica2/pisica2_right_1");
        right2 = setup("/NPC/Pisica2/pisica2_right_2");
        up1 = setup("/NPC/Pisica2/pisica2_up_1");
        up2 = setup("/NPC/Pisica2/pisica2_up_2");
        left1 = setup("/NPC/Pisica2/pisica2_left_1");
        left2 = setup("/NPC/Pisica2/pisica2_left_2");
        down1 = setup("/NPC/Pisica2/pisica2_down_1");
        down2 = setup("/NPC/Pisica2/pisica2_down_2");
    }
    public void setDialogue() {

        dialogues[0] = "You gave the letter to Andre";
        dialogues[1] = "How did you get here?";
        dialogues[2] = "I see, so Mark gave you the key to my house";
        dialogues[3] = "What's this, a letter?";
        dialogues[4] = "...";
        dialogues[5] = "I must warn you...";
        dialogues[6] = "If you value your life...";
        dialogues[7] = "Don't go in the dungeon!";
        dialogues[8] = "Don't tell me I didn't warn you...";
        dialogues[9] = "If you'l have the chance to tell me";
        dialogues[10] = "Hahahahaha...";
        dialogues[11] = "Give this to mark, and he should give you the key.";
        dialogues[12] = "Did you give the letter to Mark?";


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
            if(dialogueIndex == 12) {
                gp.player.quest_1_done = true;
                text++;
            }
        } else if(text == 1) {
                dialogueIndex = 12;
                super.speak();
                text++;
        } else if(text == 2) {
            gp.gameState = gp.playState;
            text = 1;
        }
    }


}
