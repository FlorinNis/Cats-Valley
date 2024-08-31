package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.MouseHandler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;

    //Singleton
    private static Player instance;


    public final int screenX;
    public final int screenY;
    public boolean hasDungeonKey = true, hasAndreKey = false;
    public Entity[] itemsHeld = new Entity[35];
    public Entity[] handItem = new Entity[2];
    public int invContor = 0;
    public int itemsHeldSize = 0;
    public int openDoorIndex = 7;
    int finalDialog = 0;
    public String attack_direction;

    private Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    //Singleton
    public static Player getInstance(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {

        if(instance == null) {
            return new Player(gp, keyH, mouseH);
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
        up_dash = setup("/Effects/dash_up_down");
        down_dash = setup("/Effects/dash_up_down");
        left_dash = setup("/Effects/dash_left_right");
        right_dash = setup("/Effects/dash_left_right");
        left_diag_dash = setup("/Effects/dash_left_diag");
        right_diag_dash = setup("/Effects/dash_right_diag");
        up_dash1 = setup("/Effects/up_dash_1");
        up_dash2 = setup("/Effects/up_dash_2");

        up1_sword = setupScaleForSword("/Player/player_sword_1/up_1_sword_1");
        up2_sword = setupScaleForSword("/Player/player_sword_1/up_2_sword_1");
        down1_sword = setupScaleForSword("/Player/player_sword_1/down_1_sword_1");
        down2_sword = setupScaleForSword("/Player/player_sword_1/down_2_sword_1");
        left1_sword = setupScaleForSword("/Player/player_sword_1/left_1_sword_1");
        left2_sword = setupScaleForSword("/Player/player_sword_1/left_2_sword_1");
        right1_sword = setupScaleForSword("/Player/player_sword_1/right_1_sword_1");
        right2_sword = setupScaleForSword("/Player/player_sword_1/right_2_sword_1");
        stand1_sword = setupScaleForSword("/Player/player_sword_1/stand_1_sword_1");
        stand2_sword = setupScaleForSword("/Player/player_sword_1/stand_2_sword_1");

        up_attack = setupScaleForSword("/Player/player_attack/up_attack");
        down_attack = setupScaleForSword("/Player/player_attack/down_attack");
        left_attack = setupScaleForAttack("/Player/player_attack/left_attack");
        right_attack = setupScaleForAttack("/Player/player_attack/right_attack");
//        up_left_attack = setupScaleForAttack("/Player/player_attack/up_left_attack");
//        up_right_attack = setupScaleForAttack("/Player/player_attack/up_right_attack");
//        down_left_attack = setupScaleForAttack("/Player/player_attack/down_left_attack");
//        down_right_attack = setupScaleForAttack("/Player/player_attack/down_right_attack");

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 58;
        worldY = gp.tileSize * 86;
        speed = 4;
        dashSpeed = speed * 1.5f;
        move_direction = "stand";

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
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true || keyH.ePressed == true) {
            //diagonala
            if(keyH.upPressed == true && keyH.leftPressed == true){
                move_direction ="up_left";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            else if(keyH.upPressed == true && keyH.rightPressed == true){
                move_direction ="up_right";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            else if(keyH.downPressed == true && keyH.leftPressed == true){
                move_direction ="down_left";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            else if(keyH.downPressed == true && keyH.rightPressed == true){
                move_direction ="down_right";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            //up/down/left/right movement
            else if(keyH.upPressed == true) {
                move_direction = "up";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            else if(keyH.downPressed == true) {
                move_direction = "down";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            else if(keyH.leftPressed == true) {
                move_direction = "left";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }
            else if(keyH.rightPressed == true) {
                move_direction = "right";
                if(isAttacking)
                    draw_direction = attack_direction;
                else draw_direction = move_direction;
            }


            //checkTile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //checkObject collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex, gp.dialogueState);
            if(objIndex != 999 && gp.obj[gp.currentMap][objIndex] != null){
                if(Objects.equals(gp.obj[gp.currentMap][objIndex].name, "Door") && !gp.obj[gp.currentMap][objIndex].locked) {
                    gp.obj[gp.currentMap][objIndex].down1 = gp.obj[gp.currentMap][objIndex].open;
                    openDoorIndex = objIndex;
                    if(!playedSound) {
                        gp.playSF(3);
                        playedSound = true;
                    }
                }
            }else if(gp.obj[gp.currentMap][openDoorIndex] != null){
                gp.obj[gp.currentMap][openDoorIndex].down1 = gp.obj[gp.currentMap][openDoorIndex].closed;
                playedSound = false;
            }
            //check npc collision
            npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check event
            gp.eHandler.checkEvent();
            //check enemy collision
            int monsterIndex = gp.cChecker.checkEnemy(this, gp.monster);
            contactMonster(monsterIndex);


            //if collision is false, knightu se misca
            if(collisionOn == false && keyH.enterPressed == false && keyH.ePressed == false) {

                switch(move_direction) {
                    case "down_left":
                        worldY += speed;
                        worldX -= speed/2;
                        break;
                    case "down_right":
                        worldY += speed;
                        worldX += speed/2;
                        break;
                    case "up_left":
                        worldY -= speed;
                        worldX -= speed/2;
                        break;
                    case "up_right":
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
            gp.keyH.ePressed = false;

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
            if(isAttacking)
                draw_direction = attack_direction;
            else draw_direction = "stand";
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
    public void drawAttack(Graphics2D g2) {
        attackCounter++;
        int attackOffsetX = 0;
        int attackOffsetY = 0;

        if(hasSword && isAttacking){
            AffineTransform originalTransform = g2.getTransform();
            AffineTransform transform = new AffineTransform();

            switch (attack_direction) {
                case "up":
                    attackOffsetY = -gp.tileSize;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY, gp.tileSize * 2, gp.tileSize);
                    g2.drawImage(up_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "down":
                    attackOffsetY = gp.tileSize;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY, gp.tileSize * 2, gp.tileSize);
                    g2.drawImage(down_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "left":
                    attackOffsetX = -gp.tileSize;
                    attackOffsetY = -gp.tileSize/2;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY, gp.tileSize, gp.tileSize * 2);
                    g2.drawImage(left_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "right":
                    attackOffsetX = gp.tileSize*2;
                    attackOffsetY = -gp.tileSize/2;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY, gp.tileSize, gp.tileSize * 2);
                    g2.drawImage(right_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "up_left":
                    attackOffsetX = -gp.tileSize;
                    attackOffsetY = -gp.tileSize;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY, gp.tileSize * 2, gp.tileSize);
                    transform.rotate(Math.toRadians(-45), screenX + attackOffsetX + gp.tileSize, screenY + attackOffsetY + gp.tileSize);
                    g2.setTransform(transform);
                    g2.drawImage(up_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "up_right":
                    attackOffsetX = gp.tileSize;
                    attackOffsetY = -gp.tileSize - gp.tileSize/2;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY + gp.tileSize, gp.tileSize * 2, gp.tileSize);
                    transform.rotate(Math.toRadians(45), screenX + attackOffsetX, screenY + attackOffsetY + gp.tileSize);
                    g2.setTransform(transform);
                    g2.drawImage(up_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "down_left":
                    attackOffsetX = -gp.tileSize/2;
                    attackOffsetY = gp.tileSize;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY, gp.tileSize * 2, gp.tileSize);
                    transform.rotate(Math.toRadians(45), screenX + attackOffsetX + gp.tileSize, screenY + attackOffsetY);
                    g2.setTransform(transform);
                    g2.drawImage(down_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
                case "down_right":
                    attackOffsetX = gp.tileSize/2;
                    attackOffsetY = gp.tileSize + gp.tileSize/2 + 2;
                    attackBox = new Rectangle(attackOffsetX, attackOffsetY - gp.tileSize, gp.tileSize * 2, gp.tileSize);
                    transform.rotate(Math.toRadians(-45), screenX + attackOffsetX, screenY + attackOffsetY);
                    g2.setTransform(transform);
                    g2.drawImage(down_attack, screenX + attackOffsetX, screenY + attackOffsetY, null);
                    break;
            }
            g2.setTransform(originalTransform);
            if (attackBox != null) {
                g2.setColor(new Color(255, 0, 0, 100)); // Red with some transparency
                g2.fillRect(attackBox.x + worldX, attackBox.y + worldY, attackBox.width, attackBox.height);
            }
            gp.cChecker.checkAttackCollision(gp.monster);

            if(attackCollisionOn){
                enemyTakeDamage();
            }

            if (attackCounter >= attackDuration) {
                isAttacking = false;
                attackCounter = 0;

            }

        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }



        private void Dash() {
        dashCounter++;
        //checkTile Collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        //checkObject collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex, gp.dialogueState);

        //check event
        gp.eHandler.checkEvent();

        gp.keyH.enterPressed = false;
        gp.keyH.ePressed = false;
        System.out.println(collisionOn);
        if(objIndex != 999 && gp.obj[gp.currentMap][objIndex] != null) {
            if(Objects.equals(gp.obj[gp.currentMap][objIndex].name, "log"))
                    collisionOn = false;
        }

        //if collision is false, se misca, enter-ul este ca sa nu se miste npc-urile in timpul unui dialog
        if(collisionOn == false && keyH.enterPressed == false && keyH.ePressed == false) {
            switch (move_direction) {
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
                case "up_left":
                    worldY -= dashSpeed / 1.5;
                    worldX -= dashSpeed / 1.5;
                    break;
                case "up_right":
                    worldY -= dashSpeed / 1.5;
                    worldX += dashSpeed / 1.5;
                    break;
                case "down_left":
                    worldY += dashSpeed / 1.5;
                    worldX -= dashSpeed / 1.5;
                    break;
                case "down_right":
                    worldY += dashSpeed / 1.5;
                    worldX += dashSpeed / 1.5;
                    break;
            }
        }
        //ca sa nu se mai blocheze in perete cand dau dash, daca da de o coliziune se da mai in spate cu un pixel
        else{
            switch (move_direction) {
                case "up":
                    worldY += speed;
                    break;
                case "down":
                    worldY -= speed;
                    break;
                case "left":
                    worldX += speed;
                    break;
                case "right":
                    worldX -= speed;
                    break;
                case "up_left":
                    worldY += speed / 1.5;
                    worldX += speed / 1.5;
                    break;
                case "up_right":
                    worldY += speed / 1.5;
                    worldX -= speed / 1.5;
                    break;
                case "down_left":
                    worldY -= speed / 1.5;
                    worldX += speed / 1.5;
                    break;
                case "down_right":
                    worldY -= speed / 1.5;
                    worldX -= speed / 1.5;
                    break;
            }
        }
        //contor pentru a nu da dash la infinit
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
                    hasDungeonKey = true;
                    itemsHeld[invContor] = gp.obj[gp.currentMap][i];
                    itemsHeld[invContor++].qty++;
                    itemsHeldSize++;
                    gp.obj[gp.currentMap][i] = null;
                    //gp.drawToTempScreen();
                    gp.ui.currentDialogue = "You picked up a key!";
                    break;
                case "Door":
                    if(!gp.obj[gp.currentMap][i].locked){
                        gp.obj[gp.currentMap][i].collision = false;
                        //gp.obj[gp.currentMap][i].down1 = gp.obj[gp.currentMap][i].open;
                    }
                    else if(hasDungeonKey && Objects.equals(gp.obj[gp.currentMap][i].doorHouse, "DungeonEntrance")) {
                        removeFromInventory("Key Dungeon");
                        gp.gameState = gameState;
                        gp.playSF(3);
                        gp.obj[gp.currentMap][i].locked = false;
                        hasDungeonKey = false;
                        gp.ui.currentDialogue = "You opened the door!";
                    }
                    else if(hasAndreKey && Objects.equals(gp.obj[gp.currentMap][i].doorHouse, "Andre")) {
                        removeFromInventory("Key Andre House");
                        gp.gameState = gameState;
                        gp.playSF(3);
                        gp.obj[gp.currentMap][i].locked = false;
                        hasAndreKey = false;
                        gp.ui.currentDialogue = "You opened the door!";
                    }
                    else {
                        gp.gameState = gameState;
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
                    gp.keyH.ePressed = false;
                    break;
                case "Chest":
                    gp.gameState = gameState;

                    break;
                case "Sword of Beelzebub":
                    gp.gameState = gameState;
                    gp.playSF(2);
                    itemsHeld[invContor++] = gp.obj[gp.currentMap][i];
                    itemsHeldSize++;
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.currentDialogue = "You picked up a SWORD! :O  Press I to equip it!";
            }
        }
    }
    public void interactNPC(int i) {

        if (i != 999) {

            if(gp.keyH.enterPressed == true || gp.keyH.ePressed == true) {
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
        drawAttack(g2);

        switch(draw_direction) {
            case "up":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = up_dash;
                    }
                    if (spriteNum == 2) {
                        image = up_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = up1_sword;
                    }
                    if(spriteNum == 2){
                        image = up2_sword;
                    }
                }
                break;
            case "down":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = down_dash;
                    }
                    if (spriteNum == 2) {
                        image = down_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = down1_sword;
                    }
                    if(spriteNum == 2){
                        image = down2_sword;
                    }
                }
                break;
            case "left":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = left_dash;
                    }
                    if (spriteNum == 2) {
                        image = left_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }else{
                    if(spriteNum == 1){
                        solidArea = new Rectangle(24, 16, 32, 32);
                        image = left1_sword;
                    }
                    if(spriteNum == 2){
                        image = left2_sword;
                    }
                }
                break;
            case "right":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = right_dash;
                    }
                    if (spriteNum == 2) {
                        image = right_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = right1_sword;
                    }
                    if(spriteNum == 2){
                        image = right2_sword;
                    }
                }
                break;
            case "up_right":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = right_diag_dash;
                    }
                    if (spriteNum == 2) {
                        image = right_diag_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = right1_sword;
                    }
                    if(spriteNum == 2){
                        image = right2_sword;
                    }
                }
                break;
            case "up_left":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = left_diag_dash;
                    }
                    if (spriteNum == 2) {
                        image = left_diag_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = left1_sword;
                    }
                    if(spriteNum == 2){
                        image = left2_sword;
                    }
                }
                break;
            case "down_right":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = right_diag_dash;
                    }
                    if (spriteNum == 2) {
                        image = right_diag_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = right1_sword;
                    }
                    if(spriteNum == 2){
                        image = right2_sword;
                    }
                }
                break;
            case "down_left":
                if(isDashing){
                    if(hasSword)
                        solidArea = new Rectangle(24, 16, 32, 32);
                    else solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = left_diag_dash;
                    }
                    if (spriteNum == 2) {
                        image = left_diag_dash;
                    }
                }else if(!hasSword){
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }else{
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = left1_sword;
                    }
                    if(spriteNum == 2){
                        image = left2_sword;
                    }
                }
                break;
            case "stand":
                if(hasSword){
                    solidArea = new Rectangle(24, 16, 32, 32);
                    if(spriteNum == 1){
                        image = stand1_sword;
                    }
                    if(spriteNum == 2){
                        image = stand2_sword;
                    }
                }else{
                    solidArea = new Rectangle(8, 16, 32, 32);
                    if(spriteNum == 1) {
                        image = stand1;
                    }
                    if(spriteNum == 2) {
                        image = stand2;
                    }
                }
        }
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public void contactMonster(int i) {
        //System.out.println(i);
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

    public void removeFromInventory(String itemName){
        for(int i = 0; i < invContor; i++){
            if(Objects.equals(itemsHeld[i].name, itemName)){
                itemsHeld[i] = itemsHeld[++i];
                for(int j = i; j < invContor - 1; j++){
                    itemsHeld[j] = itemsHeld[j+1];
                }
                invContor--;
                gp.player.itemsHeldSize--;
                break;
            }
        }
    }
}
