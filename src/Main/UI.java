package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.sql.SQLOutput;
import java.util.Objects;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Entity.Entity;
import Entity.Player;
import Object.Heart;
import Object.InventorySlot;

import javax.imageio.ImageIO;

public class UI {

    Player p;
    Entity e;
    GamePanel gp;
    Font Ancient_Modern_Tales, maruMonica;

    BufferedImage heart_full, heart_half, heart_blank, bg, inventory_Slot, textBoxImage;

    public boolean messageOn = false;
    public int equipmentIndex = 0;
    public String message = "";
    public boolean showKey = false;
    public int messageCounter = 0;
    private Graphics2D g2;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0: NEW GAME, 1:SETTINGS
    int subState = 0;
    public BufferedImage backgroundImage;
    public boolean equipPressed = false;
    public int selectedSlotIndex = 0;
    public int lastNpcIndex = 0;

    public UI(GamePanel gp) {

        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/AncientModernTales-a7Po.ttf");
            Ancient_Modern_Tales = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/Font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Create hud obj
        Entity heart = new Heart(gp);
        heart_full = heart.image3;
        heart_half = heart.image2;
        heart_blank = heart.image;

        Entity inventorySlot = new InventorySlot(gp);
        inventory_Slot = inventorySlot.image;
        textBoxImage = setupWithoutScale("/UI/text_box_npc");
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        //TitleState
        if(gp.gameState == gp.titleScreen) {
            drawTitleScreen();
        }

        //playState
        if(gp.gameState == gp.playState) {
            drawDialogueBoxNPC();
            drawPlayerLife();
            drawItemHolding();
        }
        //dialogueState
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            g2.setFont(maruMonica);
            g2.setColor(Color.black);
            drawDialogueScreen();
        }
        //nextDialogueState
        if(gp.gameState == gp.nextDialogueState) {
            g2.setFont(maruMonica);
            g2.setColor(Color.black);
            drawDialogueScreen();
        }
        //option state
        if(gp.gameState == gp.optionState) {
            drawOptionsScreen();
        }
        //GAME OVER
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        //INVENTORY
        if(gp.gameState == gp.inventoryState) {
            drawInventoryScreen();
        }
    }

    private void drawDialogueBoxNPC() {
        if (gp.player.npcIndex == 999 && gp.currentMap == 0 && lastNpcIndex != 1) {
            gp.npc[gp.currentMap][lastNpcIndex].speed = 1;
            return;
        }
        else if(gp.currentMap == 0  && gp.player.npcIndex != 999){
            lastNpcIndex = gp.player.npcIndex;
            if(gp.player.npcIndex != 1)
                gp.npc[gp.currentMap][gp.player.npcIndex].speed = 0;

            // Poz npc
            int worldX = gp.npc[gp.currentMap][gp.player.npcIndex].worldX;
            int worldY = gp.npc[gp.currentMap][gp.player.npcIndex].worldY;

            // Gasesc pozitia pe ecran relativ cu pozitia player-ului
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Calibrez pozitia cu un mic offset
            int boxX = screenX + 20;
            int boxY = screenY - gp.tileSize + 15;
            System.out.println(boxX + " y = " + boxY);

            // Draw the dialogue box
            g2.drawImage(textBoxImage, boxX, boxY, null);

            int x;
            int y;
            String text;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12f));

            text = "Press e to interact...";
            g2.setColor(Color.white);
            x = boxX + 10;
            y = boxY + 20;
            g2.drawString(text, x, y);
        }

    }


    private void drawInventoryScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));

        text = "Inventory";
        g2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize*2;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        //Escape
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        text = "Press ESC to exit Inventory...";
        g2.setColor(Color.black);
        x = gp.tileSize;
        y = gp.tileSize;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);


        //Draw empty inv
        int i = 0;
        x = gp.tileSize * 6;
        y = gp.tileSize * 4;
        while(i < gp.player.full_InventorySlots){
            if (i == selectedSlotIndex) {
                g2.setColor(Color.yellow);
                g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            }
            g2.drawImage(inventory_Slot, x, y, null);
            i++;
            x += gp.tileSize + 5;
            if(i % 7 == 0) {
                y += gp.tileSize;
                x = gp.tileSize * 6;
            }
        }
        x = gp.tileSize * 15;
        y = gp.tileSize * 4;
        g2.drawImage(inventory_Slot, x, y, null);
        y += gp.tileSize;
        g2.drawImage(inventory_Slot, x, y, null);
        //Populate Inv
        i = 0;
        int qty = 0;
        x = gp.tileSize * 6;
        y = gp.tileSize * 4;
        while(i < gp.player.itemsHeldSize){
            g2.drawImage(gp.player.itemsHeld[i].down1, x, y, null);
            if (i == selectedSlotIndex) {
                // Display larger image and description
                drawSelectedItemInfo(gp.player.itemsHeld[i]);
            }
            qty = gp.player.itemsHeld[i].qty;
            i++;
            x += gp.tileSize + 5;
            if(i % 7 == 0) {
                y += gp.tileSize;
                x = gp.tileSize * 6;
            }
        }
    }

    private void drawSelectedItemInfo(Entity item) {
        int infoBoxX = gp.tileSize;
        int infoBoxY = gp.tileSize * 2;
        int infoBoxWidth = gp.tileSize * 4;
        int infoBoxHeight = gp.tileSize * 6;

//        g2.setColor(new Color(0, 0, 0, 200));
//        g2.fillRect(infoBoxX, infoBoxY, infoBoxWidth, infoBoxHeight);
        BufferedImage descriptionBoxImage = null;
        try{
            descriptionBoxImage = ImageIO.read(getClass().getResourceAsStream("/UI/DescBox.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(descriptionBoxImage, infoBoxX, infoBoxY, infoBoxWidth, infoBoxHeight, null);
        g2.drawString(item.name, infoBoxX +10, infoBoxY+10);

        // Draw larger item image
        g2.drawImage(item.down1, infoBoxX + gp.tileSize, infoBoxY + gp.tileSize, gp.tileSize * 2, gp.tileSize * 2, null);

        // Draw item description
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15f));
        g2.setColor(Color.white);
        g2.drawString(item.itemDescription, infoBoxX + gp.tileSize / 2, infoBoxY + gp.tileSize * 4);
        if(item.equipable){
            if(gp.player.handItem[equipmentIndex] == null)
                g2.drawString("Press e to equip", infoBoxX + gp.tileSize / 2, infoBoxY + gp.tileSize * 5);
            else
                g2.drawString("Press e to remove", infoBoxX + gp.tileSize / 2, infoBoxY + gp.tileSize * 5);
            if(equipPressed){
                if(item.equiped){
                    gp.player.handItem[--equipmentIndex] = null;
                    equipPressed = false;
                    gp.player.hasSword = false;
                    item.equiped = false;
                }
                else if(gp.player.handItem[equipmentIndex] == null) {
                    gp.player.handItem[equipmentIndex] = item;
                    item.equiped = true;
                    equipmentIndex++;
                    equipPressed = false;
                    gp.player.hasSword = true;
                }
            }
        }else g2.drawString("Qty: " + item.qty, infoBoxX + gp.tileSize / 2, infoBoxY + gp.tileSize * 5);
        int i = 0;
        while(i < 2 && gp.player.handItem[i] != null){
            g2.drawImage(gp.player.handItem[i].down1, gp.tileSize * 15, gp.tileSize * 4, gp.tileSize, gp.tileSize, null);
            i++;
        }

    }

    public void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "You Died :(";
        g2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        //back to title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }
    public void drawTitleScreen() {
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/Background/background_menu.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        //backgroundImage = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        if(titleScreenState == 0) {
            //g2.setColor(new Color(70, 120, 80));
            //g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, null);
            //g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


            //Title Name
            g2.setFont(Ancient_Modern_Tales.deriveFont(Font.BOLD, 96F));
            String text = "Cat's Valley";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 5);
            //MAIN COLOR
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            //PLAYER IMAGE
            x = gp.screenWidth;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.stand1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            //MENU
            g2.setFont(maruMonica.deriveFont(Font.BOLD, 48F));

            text = "New Game";
            x = getXForCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Load Game";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            /*
            text = "Settings";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

             */
            text = "Quit Game";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1) {

            //stuff

        }
        else if(titleScreenState == 2) {

            //BACKGROUND
            //g2.setColor(new Color(70, 120, 80));
            //g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

            //TITLE SETTINGS
            g2.setFont(Ancient_Modern_Tales.deriveFont(Font.BOLD, 96F));
            String text = "Settings";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 5);

            //TITLE
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            //MENU SELECTIONS
            g2.setFont(maruMonica.deriveFont(Font.BOLD, 48F));
            text = "FullScreen";
            x = getXForCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Sound";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;


        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15F));
        String enter = "Press enter to continue...";
        g2.drawString(enter, gp.tileSize*14, gp.tileSize*4);

    }
    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub win
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        //TITLE
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //full screen on/of
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                if(gp.fulScreenOn == false) {
                    gp.fulScreenOn = true;
                    gp.setFullScreen();
                }
                else if(gp.fulScreenOn == true) {
                    gp.fulScreenOn = false;
                }
                subState = 1;
            }
        }

        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }

        //End game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }

        //Back
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //full screen check box
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fulScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }


        //music volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //se volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

    }
    public void options_fullScreenNotification(int frameX, int frameY) {

        int textX = frameY + gp.tileSize*6;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //back
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
            }
        }

    }
    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        //title
        String text = "Controls";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY+=gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY+=gp.tileSize;
        g2.drawString("Options", textX, textY); textY+=gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY+=gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY+=gp.tileSize;
        g2.drawString("ESC", textX, textY); textY+=gp.tileSize;

        //back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }

    }
    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //yes
        String text = "Yes!";
        textX = getXForCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleScreen;
            }
        }
        //no
        text = "Nope";
        textX = getXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);


    }
    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //Draw blank heart
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //draw current life
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawItemHolding() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2 * 20;
        int i = 0;

        //Draw blank inventory
        while(i < gp.player.ui_InventorySlots) {
            g2.drawImage(inventory_Slot, x, y, null);
            i++;
            x += gp.tileSize + 5;
        }

        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2 * 20;
        i = 0;

        //draw current inventory
        while(gp.player.handItem[i] != null) {
            g2.drawImage(gp.player.handItem[i].down1, x, y, null);
            i++;
            x += gp.tileSize;
        }
    }
    public int getXForCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage setupWithoutScale(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
