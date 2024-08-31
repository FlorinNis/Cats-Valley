package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC_Pisica_1 extends Entity {

    public int text = 0;

    public NPC_Pisica_1(GamePanel gp) {
        super(gp);

        draw_direction = "down";
        entity_type = "NPC";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage(){

        //stand1 = setup("/NPC/OldMan2/oldman_down_1");
        //stand2 = setup("/NPC/OldMan2/oldman_down_1");
        right1 = setup("/NPC/Pisica1/pisica1_right_1");
        right2 = setup("/NPC/Pisica1/pisica1_right_2");
        up1 = setup("/NPC/Pisica1/pisica1_up_1");
        up2 = setup("/NPC/Pisica1/pisica1_up_2");
        left1 = setup("/NPC/Pisica1/pisica1_left_1");
        left2 = setup("/NPC/Pisica1/pisica1_left_2");
        down1 = setup("/NPC/Pisica1/pisica1_down_2");
        down2 = setup("/NPC/Pisica1/pisica1_down_3");
    }
    public void setDialogue() {

        dialogues[0] = "Hey!";
        dialogues[1] = "When did you get here?";
        dialogues[2] = "Where were you hiding?";
        dialogues[3] = "Does not matter right now.";
        dialogues[4] = "I need your help!";
        dialogues[5] = "You see, this place is not that safe. \n it's full of monsters";
        dialogues[6] = "For now, the dungeon is closed.";
        dialogues[7] = "But sometimes monsters find a way up here.";
        dialogues[8] = "If you plan to go in the dungeon I'm sorry \n but it's closed";
        dialogues[9] = "...";
        dialogues[10] = "Alright, I'l give you the key, but first you need to equip yourself \n with a weapon.";
        dialogues[11] = "You can find one in the old knight's house. \n He died in the dungeon, but I think \n you can find a weapon in there.";
        dialogues[12] = "Good luck!";
        dialogues[13] = "I'l wait for you in here...";
        dialogues[14] = "I see you found a sword!";
        dialogues[15] = "He died a couple of years ago, that's why the house is in ruins.";
        dialogues[16] = "The key? Hehe, not now, you are not ready!";
        dialogues[17] = "First you should explore here a little. Look, I'l give you a new task.";
        dialogues[18] = "Here is the key for the house down the road on the left.";
        dialogues[19] = "You should find the old farmer in there. Give him this letter";
        dialogues[20] = "You gave the letter to Mark";
        dialogues[21] = "I see, aha...";
        dialogues[22] = "Alright, you earned it!";
        dialogues[23] = "But just a tip, you should get to know everyone \n before you go in the dungeon";
        dialogues[24] = "I'l have something for you after you defeat your first enemy";
        dialogues[25] = "I won't give it to you now, what if you don't come back hahaha";
        dialogues[26] = "...";

    }
    public void setAction() {

        actionLockCounter ++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(0, 120) + 1;
            System.out.println("I = " + i);

            if (i <= 15) {
                move_direction = "up";
            }
            if (i > 15 && i <= 30) {
                move_direction = "down";
            }
            if (i > 30 && i <= 45) {
                move_direction = "left";
            }
            if (i > 60 && i <= 75) {
                move_direction = "right";
            }
            if (i > 90 && i <= 105) {
                move_direction = "up_right";
            }
            if (i > 105 && i <= 110) {
                move_direction = "up_left";
            }
            if (i > 110 && i <= 115) {
                move_direction = "down_right";
            }
            if (i > 115 && i <= 120) {
                move_direction = "down_left";
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
            if(dialogueIndex == 13) {
                text++;
            }
        } else if(text == 1) {
                dialogueIndex = 10;
                super.speak();
                text++;
        } else if(text == 2) {
            gp.gameState = gp.playState;
            if(gp.player.hasSword) {
                text = 3;
                dialogueIndex = 14;
            }else
                text = 1;
        } else if(text == 3){
            super.speak();
            if(dialogueIndex == 19) {
                //Primeste Misiunea sa duca scrisoarea
                text++;
                //Letter
                gp.player.itemsHeld[gp.player.invContor] = gp.obj[gp.currentMap][27];
                gp.player.quest_letter_index = gp.player.invContor;
                gp.player.itemsHeld[gp.player.invContor++].qty++;
                gp.player.itemsHeldSize++;
                //Key Andre House
                gp.player.itemsHeld[gp.player.invContor] = gp.obj[gp.currentMap][28];
                gp.player.itemsHeld[gp.player.invContor++].qty++;
                gp.player.itemsHeldSize++;
                gp.player.hasAndreKey = true;
            }
        } else if(text == 4){
            if(gp.player.quest_1_done) {
                gp.player.removeFromInventory("Letter");
                text++;
            }else{
                super.speak();
                gp.gameState = gp.playState;
            }
        } else if(text == 5){
            super.speak();
            System.out.println(dialogueIndex);
            if(dialogueIndex == 25) {
                gp.player.itemsHeld[gp.player.invContor] = gp.obj[gp.currentMap][0];
                gp.player.itemsHeld[gp.player.invContor++].qty++;
                gp.player.itemsHeldSize++;
                gp.player.hasDungeonKey = true;
                text++;
            }
        }else if(text == 6){
            dialogueIndex = 26;
            super.speak();
            text++;
        }else if(text == 7){
            gp.gameState = gp.playState;
            text--;
        }
    }


}
