package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UI;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    //Singleton
    private static Player instance;


    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public Entity[] itemsHeld = new Entity[35];
    public int itemsHeldSize = 0;
    int finalDialog = 0;

    private Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    //Singleton
    public static Player getInstance(GamePanel gp, KeyHandler keyH) {

        if(instance == null) {
            return new Player(gp, keyH);
        }
        return instance;
    }

    public void getPlayerImage(){

        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");
        stand1 = setup("/player/player_stand_1");
        stand2 = setup("/player/player_stand_2");
        updash = setup("/Effects/dash_up_down");
        downdash = setup("/Effects/dash_up_down");
        leftdash = setup("/Effects/dash_left_right");
        rightdash = setup("/Effects/dash_left_right");
        leftdiagdash = setup("/Effects/dash_left_diag");
        rightdiagdash = setup("/Effects/dash_right_diag");
        updash1 = setup("/Effects/up_dash_1");
        updash2 = setup("/Effects/up_dash_2");

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 28;
        worldY = gp.tileSize * 24;
        speed = 4;
        dashSpeed = speed * 3;
        direction = "stand";

        //PLAYER STATUS
        maxLife = 2; //2 - full heart
        ui_InventorySlots = 2;
        full_InventorySlots = 35;
        life = maxLife;

    }

    public void update() {
        if(isDashing){
            Dash();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            //diagonala
            if(keyH.upPressed == true && keyH.leftPressed == true){
                direction ="upleft";
            }
            else if(keyH.upPressed == true && keyH.rightPressed == true){
                direction ="upright";
            }
            else if(keyH.downPressed == true && keyH.leftPressed == true){
                direction ="downleft";
            }
            else if(keyH.downPressed == true && keyH.rightPressed == true){
                direction ="downright";
            }
            //up/down/left/right movement
            else if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }


            //checkTile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //checkObject collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex, gp.dialogueState);

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            System.out.println(npcIndex);

            //check event
            gp.eHandler.checkEvent();
            //check enemy collision
            int monsterIndex = gp.cChecker.checkEnemy(this, gp.monster);
            contactMonster(monsterIndex);
            gp.keyH.enterPressed = false;

            //if collision is false, knightu se misca
            if(collisionOn == false && keyH.enterPressed == false) {

                switch(direction) {
                    case "downleft":
                        worldY += speed;
                        worldX -= speed/2;
                        break;
                    case "downright":
                        worldY += speed;
                        worldX += speed/2;
                        break;
                    case "upleft":
                        worldY -= speed;
                        worldX -= speed/2;
                        break;
                    case "upright":
                        worldY -= speed/3;
                        worldX += speed/2;
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if(spriteCounter > 15) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            if(!canDash && waitDash) {
                if (dashCounter < dashPauseDuration)
                    dashCounter++;
                else {
                    dashCounter = 0;
                    canDash = true;
                    waitDash = false;
                }
            }
            //check if dashing
            if (keyH.spacePressed && canDash) {
                startDash();
            }

        }
        else {
            direction = "stand";
            spriteCounter++;
            if(spriteCounter > 15) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        //invincible
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(life <= 0) {
            gp.gameState = gp.gameOverState;
        }

    }

    private void Dash() {
        dashCounter++;
        //checkTile Collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        //check event
        gp.eHandler.checkEvent();

        gp.keyH.enterPressed = false;

        //if collision is false, se misca, enter-ul este ca sa nu se miste npc-urile in timpul unui dialog
        if(collisionOn == false && keyH.enterPressed == false) {
            switch (direction) {
                case "up":
                    worldY -= dashSpeed;
                    break;
                case "down":
                    worldY += dashSpeed;
                    break;
                case "left":
                    worldX -= dashSpeed;
                    break;
                case "right":
                    worldX += dashSpeed;
                    break;
                case "upleft":
                    worldY -= dashSpeed / 1.5;
                    worldX -= dashSpeed / 1.5;
                    break;
                case "upright":
                    worldY -= dashSpeed / 1.5;
                    worldX += dashSpeed / 1.5;
                    break;
                case "downleft":
                    worldY += dashSpeed / 1.5;
                    worldX -= dashSpeed / 1.5;
                    break;
                case "downright":
                    worldY += dashSpeed / 1.5;
                    worldX += dashSpeed / 1.5;
                    break;
            }
        }

        if (dashCounter >= dashDuration) {
            isDashing = false;
            dashCounter = 0;
        }

        spriteCounter++;
        if(spriteCounter > 15) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    private void startDash() {
        isDashing = true;
        dashCounter = 0;
        keyH.spacePressed = false;
    }
    public void pickUpObject(int i, int gameState) {

        if(i != 999) {

            String objectName = gp.obj[gp.currentMap][i].name;

            switch(objectName) {
                case "Key":
                    gp.gameState = gameState;
                    gp.playSF(2);
                    hasKey++;
                    itemsHeld[0] = gp.obj[gp.currentMap][i];
                    itemsHeld[0].qty++;
                    itemsHeldSize++;
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.currentDialogue = "You picked up a key!";
                    break;
                case "Door":
                    gp.gameState = gameState;
                    if(hasKey > 0) {
                        gp.playSF(3);
                        gp.obj[gp.currentMap][i] =  null;
                        hasKey--;
                        gp.ui.currentDialogue = "You opened the door!";
                    }
                    else {
                        gp.ui.currentDialogue = "You need a key!";
                    }
                    break;
                case "HealthUp":
                    gp.gameState = gameState;
                    maxLife += 2;
                    life += 2;
                    gp.playSF(1);
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.currentDialogue = "Hp UP!";
                    gp.keyH.enterPressed = false;
                    break;
                case "Chest":
                    gp.gameState = gameState;

                    break;
            }
        }
    }
    public void interactNPC(int i) {

        if (i != 999) {

            if(gp.keyH.enterPressed == true) {
                if(gp.gameState == gp.playState) {
                    gp.gameState = gp.dialogueState;
                    gp.npc[gp.currentMap][i].speak();
                } else if(gp.gameState == gp.dialogueState) {
                    if(gp.npc[gp.currentMap][i].dialogues[gp.npc[gp.currentMap][i].dialogueIndex] != null) {
                        gp.npc[gp.currentMap][i].speak();
                    } else {
                        finalDialog = 1;
                        gp.npc[gp.currentMap][i].dialogueIndex = 0;
                        gp.gameState = gp.playState;
                    }
                }
            }
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = updash;
                    }
                    if (spriteNum == 2) {
                        image = updash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                break;
            case "down":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = downdash;
                    }
                    if (spriteNum == 2) {
                        image = downdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                break;
            case "left":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = leftdash;
                    }
                    if (spriteNum == 2) {
                        image = leftdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                break;
            case "right":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = rightdash;
                    }
                    if (spriteNum == 2) {
                        image = rightdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                break;
            case "upright":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = rightdiagdash;
                    }
                    if (spriteNum == 2) {
                        image = rightdiagdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                break;
            case "upleft":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = leftdiagdash;
                    }
                    if (spriteNum == 2) {
                        image = leftdiagdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                break;
            case "downright":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = rightdiagdash;
                    }
                    if (spriteNum == 2) {
                        image = rightdiagdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                break;
            case "downleft":
                if(isDashing){
                    if (spriteNum == 1) {
                        image = leftdiagdash;
                    }
                    if (spriteNum == 2) {
                        image = leftdiagdash;
                    }
                }else {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                break;
            case "stand":
                if(spriteNum == 1) {
                    image = stand1;
                }
                if(spriteNum == 2) {
                    image = stand2;
                }
        }
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public void contactMonster(int i) {
        System.out.println(i);
        if(i != 999) {

            if(!invincible && !isDashing) {
                gp.playSF(7);
                life -= 1;
                invincible = true;
            }
        }
    }
    public void defaultPositions() {

        worldX = gp.tileSize * 28;
        worldY = gp.tileSize * 13;
    }
    public void restoreLife() {
        life = maxLife;
        invincible = false;
    }
}
