package Entity;

import Main.GamePanel;

public class NPC_Pisica_Tutorial extends Entity {

    public int text = 0;

    public NPC_Pisica_Tutorial(GamePanel gp) {
        super(gp);

        name = "tutorial";
        draw_direction = "stand";
        entity_type = "NPC";
        //npc_name = "tutorial";
        speed = 0;

        solidArea.x = 3;
        solidArea.y = 18;
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

    public void getImage(){

        stand1 = setup("/NPC/NPC_pisica");
        stand2 = setup("/NPC/NPC_pisica");
        down1 = setup("/NPC/NPC_pisica");
        down2 = setup("/NPC/NPC_pisica");
        left1 = setup("/NPC/NPC_pisica");
        left2 = setup("/NPC/NPC_pisica");
        right1 = setup("/NPC/NPC_pisica");
        right2 = setup("/NPC/NPC_pisica");
        up1 = setup("/NPC/NPC_pisica");
        up2 = setup("/NPC/NPC_pisica");

    }
    public void setDialogue() {

        dialogues[0] = "Hey! You are new here.";
        dialogues[1] = "Glad to see some new people";
        dialogues[2] = "Here we are stranded on an island and we try to build our civilization";
        dialogues[3] = "But there is a dungeon here, a lot of our cats have been captured by that dungeon";
        dialogues[4] = "We are much less than we were";
        dialogues[5] = "Anyway...";
        dialogues[6] = "Let me teach you how to play this game";
        dialogues[7] = "I suppose you know how to move, right?";
        dialogues[8] = "You can move with the WASD keys";
        dialogues[9] = "But let me tell you something you might not know";
        dialogues[9] = "I've been cutting this tree here, it fell on the ground as you can see";
        dialogues[10] = "You can't move through this tree";
        dialogues[11] = "But you can dash through it!!";
        dialogues[12] = "Come on, don't be shy, dash through it!";
        dialogues[13] = "Yes! That's it!";
        dialogues[14] = "Go into the house just up the road, the mayor is waiting for you";


    }
    public void setAction() {

        move_direction = "down";
//        if(dialogueIndex == 12) {
//            move_direction = "up";
//
//        }
    }
    public void speak() {

        if(text == 0) {
            super.speak();
            if(dialogueIndex == 12) {
                text++;
            }
        } else if(text == 1) {
            if(gp.player.hasDashed)
                text = 3;
            else {
                dialogueIndex = 12;
                super.speak();
                text++;
            }
        } else if(text == 2) {
            gp.gameState = gp.playState;
            text = 1;
        } else if(text == 3) {
            dialogueIndex = 13;
            super.speak();
            if(dialogueIndex == 14) {
                text++;
            }
        } else if (text == 4) {
            dialogueIndex = 14;
            super.speak();
            text++;
        } else if (text == 5) {
            gp.gameState = gp.playState;
            text = 4;
        }
    }


}
