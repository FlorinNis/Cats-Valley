package Entity;

import Main.GamePanel;

public class NPC_Pisica_3 extends Entity {

    public int text = 0;

    public NPC_Pisica_3(GamePanel gp) {
        super(gp);

        draw_direction = "down";
        entity_type = "NPC";
        speed = 1;

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

        stand1 = setup("/NPC/Pisica3/dungeonKeeper");
        stand2 = setup("/NPC/Pisica3/dungeonKeeper_2");

    }
    public void setDialogue() {

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

        move_direction = "stand";
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
