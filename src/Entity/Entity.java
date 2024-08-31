package Entity;

import Enemy.Green_Slime;
import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public boolean goingUp = true;
    //dash
    public boolean isDashing = false;
    public boolean isAttacking = false;
    public int attackCounter = 0;
    public int attackDuration = 20;
    public int dashDuration = 20;
    public int dashCounter = 0;
    public float dashSpeed;
    public int dashPauseDuration = 10;
    public boolean canDash = true;
    public boolean waitDash = false;
    public boolean hasSword = false;
    public boolean hitPlayer = false;
    public boolean isEnemy = false;
    public boolean interactRange = false;
    public boolean locked = false;
    public int npcIndex = 999;
    public boolean isNPC = false;
    public boolean dashable;
    public int attackDamage;
    public int enemyHit;
    public String enemy_type;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stand1, stand2;
    public BufferedImage up_dash, right_dash, left_dash, down_dash, right_diag_dash, left_diag_dash, up_dash1, up_dash2;
    public BufferedImage up1_sword, up2_sword, left1_sword, left2_sword, right1_sword, right2_sword, down1_sword, down2_sword, stand1_sword, stand2_sword;
    public BufferedImage up_attack, down_attack, left_attack, right_attack, jump1, jump2, jump3;
    public BufferedImage closed, open;
    public String  doorHouse;
    public int quest_letter_index = 0;
    public boolean quest_1_done = false, quest_2_done = false, quest_3_done = false;
    public boolean playedSound = false;

    public String move_direction = "down";
    //public String move_direction = "down";
    public String draw_direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteNumBoss = 1;
    public int spriteCounterBoss = 0;
    public boolean attackCollisionOn = false;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle checkNPC = new Rectangle(0,0 , 140, 140);
    public Rectangle attackBox = null;
    public int solidAreaDefaultX, solidAreaDefaultY, checkNPCDefaultX, checkNPCDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    String dialogues[] = new String[50];
    public int dialogueIndex = 0;

    public BufferedImage image, image2, image3;
    public String name;
    public int qty;
    public boolean collision = false;

    public boolean invincible = false;
    public int invincibleCounter = 0;

    //Character Status
    public int maxLife;
    public int ui_InventorySlots;
    public int full_InventorySlots;
    public String itemDescription;
    public boolean equipable, equiped;

    public int life;
    public Entity(GamePanel gp) {

        this.gp = gp;
        this.checkNPCDefaultX = checkNPC.x;
        this.checkNPCDefaultY = checkNPC.y;
    }

    public void setAction() {}
    public void speak() {
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.move_direction) {
            case "up":
                move_direction = "down";
                break;
            case "down":
                move_direction = "up";
                break;
            case "left":
                move_direction = "right";
                break;
            case "right":
                move_direction = "left";
                break;
        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);


        if (collisionOn == false) {

            switch (move_direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "up_left":
                    worldY -= speed;
                    worldX -= speed;
                    break;
                case "up_right":
                    worldY -= speed;
                    worldX += speed;
                case "down":
                    worldY += speed;
                    break;
                case "down_left":
                    worldY += speed;
                    worldX -= speed;
                    break;
                case "down_right":
                    worldY += speed;
                    worldX += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                case "Frog_Boss":
                    System.out.println("spriteNumBoss = " + spriteNumBoss);
                    if(goingUp) {
                        if (spriteNumBoss == 1) {
                            worldY -= speed / 2;
                            System.out.println("ar trebui sa mearga");
                        }
                        if (spriteNumBoss == 2) {
                            worldY -= speed / 2;
                        }
                        if (spriteNumBoss == 3) {
                            worldY -= speed / 2;
                            goingUp = false;
                        }
                    } else {
                        if (spriteNumBoss == 1) {
                            worldY += speed / 2;
                        }
                        if (spriteNumBoss == 2) {
                            worldY += speed / 2;
                        }
                        if (spriteNumBoss == 3) {
                            worldY += speed / 2;
                            goingUp = true;
                        }
                    }
                    break;
                case "stand":
                    break;
            }
        }

        gp.cChecker.checkPlayer(this);
        if(hitPlayer && isEnemy) {
            gp.player.contactMonster(1);
            hitPlayer = false;
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }


    }

    public void enemyTakeDamage() {
        if(enemyHit != 999 && !gp.monster[gp.currentMap][enemyHit].invincible) {
            gp.monster[gp.currentMap][enemyHit].life -= gp.player.handItem[0].attackDamage;
            if(gp.monster[gp.currentMap][enemyHit].life == 0) {
                gp.monster[gp.currentMap][enemyHit] = null;
                return;
            }
            gp.monster[gp.currentMap][enemyHit].invincible = true;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch(move_direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = left1;
                    }
                    if(spriteNum == 2) {
                        image = left2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = right1;
                    }
                    if(spriteNum == 2) {
                        image = right2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "stand":
                    if(spriteNum == 1) {
                        image = stand1;
                    }
                    if(spriteNum == 2) {
                        image = stand2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "up_left":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "up_right":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "down_left":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "down_right":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    break;
                case "Frog_Boss":
                    if(spriteNumBoss == 1) {
                        image = jump1;
                    }
                    if(spriteNumBoss == 2) {
                        image = jump2;
                    }
                    if(spriteNumBoss == 3) {
                        image = jump3;
                    }
                    g2.drawImage(image, screenX, screenY, gp.tileSize * 4, gp.tileSize * 4, null);
                    break;
            }


        }
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
    public BufferedImage setupScaleBoss(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
            image = uTool.scaleImage(image, gp.tileSize*4, gp.tileSize*4);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    public BufferedImage setupScaleForSword(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
            image = uTool.scaleImage(image, gp.tileSize*2, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    public BufferedImage setupScaleForAttack(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize*2);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    public int getDialogueIndex() {return dialogueIndex;}


}
