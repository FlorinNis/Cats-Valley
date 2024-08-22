package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC_Pisica_2 extends Entity {

    public int text = 0;

    public NPC_Pisica_2(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

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

//        dialogues[0] = "How did you get here?";
//        dialogues[1] = "I see, so you found the key";
//        dialogues[2] = "I must warn you...";
//        dialogues[3] = "If you value your life...";
//        dialogues[4] = "Don't go in the dungeon!";
//        dialogues[5] = "Don't tell me I didn't warn you...";
//        dialogues[6] = "If you'l have the chance to tell me";
//        dialogues[7] = "Hahahahaha...";
//        dialogues[8] = "...";
        dialogues[0] = "How did you get here?";
        dialogues[1] = "I see, so you found the key";
        dialogues[2] = "I must warn you...";
        dialogues[3] = "If you value your life...";
        dialogues[4] = "Don't go in the dungeon!";
        dialogues[5] = "Don't tell me I didn't warn you...";
        dialogues[6] = "If you'l have the chance to tell me";
        dialogues[7] = "Hahahahaha...";
        dialogues[8] = "...";


    }
    public void setAction() {

        actionLockCounter ++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(0, 120) + 1;
            System.out.println("I = " + i);

            if (i <= 15) {
                direction = "up";
            }
            if (i > 15 && i <= 30) {
                direction = "down";
            }
            if (i > 30 && i <= 45) {
                direction = "left";
            }
            if (i > 60 && i <= 75) {
                direction = "right";
            }
            if (i > 90 && i <= 105) {
                direction = "up_right";
            }
            if (i > 105 && i <= 110) {
                direction = "up_left";
            }
            if (i > 110 && i <= 115) {
                direction = "down_right";
            }
            if (i > 115 && i <= 120) {
                direction = "down_left";
            }
            //if (i > 100 && i <= 125) {
            //    direction = "stand";
            //}
            actionLockCounter = 0;
        }
    }
    public void speak() {

        if(text == 0) {
            super.speak();
            if(dialogueIndex == 7) {
                text++;
            }
        } else if(text == 1) {
                dialogueIndex = 7;
                super.speak();
                text++;
        } else if(text == 2) {
            gp.gameState = gp.playState;
            text = 1;
        }
    }


}
