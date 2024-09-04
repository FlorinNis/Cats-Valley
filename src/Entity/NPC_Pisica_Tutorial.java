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

        stand1 = setup("/NPC/Pisica3/dungeonKeeper");
        stand2 = setup("/NPC/Pisica3/dungeonKeeper_2");
        down1 = setup("/NPC/Pisica3/dungeonKeeper");
        down2 = setup("/NPC/Pisica3/dungeonKeeper_2");
        left1 = setup("/NPC/Pisica3/dungeonKeeper");
        left2 = setup("/NPC/Pisica3/dungeonKeeper_2");
        right1 = setup("/NPC/Pisica3/dungeonKeeper");
        right2 = setup("/NPC/Pisica3/dungeonKeeper_2");
        up1 = setup("/NPC/Pisica3/dungeonKeeper");
        up2 = setup("/NPC/Pisica3/dungeonKeeper_2");

    }
    public void setDialogue() {

        dialogues[0] = "Hey! You are new here.";
        dialogues[1] = "Glad to see some new people.";
        dialogues[2] = "Here we are stranded on an island and we try to build our civilization.";
        dialogues[3] = "But there is a dungeon here, a lot of our cats have been captured by that dungeon.";
        dialogues[4] = "We are running short on cats...";
        dialogues[5] = "Anyway...";
        dialogues[6] = "Let me teach you how to play this game.";
        dialogues[7] = "I suppose you know how to move, right?";
        dialogues[8] = "You can move with the WASD keys.";
        dialogues[9] = "But let me tell you something you might not know.";
        dialogues[10] = "I've been cutting this tree here, it fell on the ground as you can see.";
        dialogues[11] = "You can't move through this tree.";
        dialogues[12] = "But you can dash through it!!.";
        dialogues[13] = "Come on, don't be shy, dash through it!.";
        dialogues[14] = "Yes! That's it!";
        dialogues[15] = "You can also dash through enemies and CATS TOO!.";
        dialogues[16] = "But only if your're dashing, remember, the dash could save your life.";
        dialogues[17] = "Go into the house just up the road, the mayor is waiting for you.";
        dialogues[18] = "Good luck!";
    }
    public void setAction() {

        move_direction = "stand";
//        if(dialogueIndex == 12) {
//            move_direction = "up";
//
//        }
    }
    public void speak() {

        if(text == 0) {
            super.speak();
            if(dialogueIndex == 13) {
                text++;
            }
        } else if(text == 1) {
            if(gp.player.hasDashed) {
                text = 3;
                dialogueIndex = 14;
            }
            else {
                dialogueIndex = 13;
                super.speak();
                text++;
            }
        } else if(text == 2) {
            gp.gameState = gp.playState;
            text = 1;
        } else if(text == 3) {
            super.speak();
            if(dialogueIndex == 18) {
                text++;
            }
        } else if (text == 4) {
            dialogueIndex = 18;
            super.speak();
            text++;
        } else if (text == 5) {
            gp.gameState = gp.playState;
            text = 4;
        }
    }


}
