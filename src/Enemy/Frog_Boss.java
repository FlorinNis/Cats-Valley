package Enemy;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class
Frog_Boss extends Entity {


    public Frog_Boss(GamePanel gp) {
        super(gp);

        name = "Frog Boss";
        speed = 1;
        maxLife = 50;
        life = maxLife;
        enemy_type = "Frog_Boss";
        entity_type = "Enemy";

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        move_direction = "Frog_Boss";

        getImage();
    }

    public void getImage() {

        jump1 = setupScaleBoss("/Enemy/Frog_Jump_1");
        jump2 = setupScaleBoss("/Enemy/Frog_Jump_2");
        jump3 = setupScaleBoss("/Enemy/Frog_Jump_3");

    }

    public void setAction() {

        actionLockCounter ++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(0, 100) + 1;
            System.out.println("I = " + i);

            if (i <= 25) {
                move_direction = "up";
            }
            if (i > 25 && i <= 50) {
                move_direction = "down";
            }
            if (i > 50 && i <= 75) {
                move_direction = "left";
            }
            if (i > 75 && i <= 100) {
                move_direction = "right";
            }
            actionLockCounter = 0;
        }
        spriteCounterBoss++;
        if (spriteCounterBoss > 15) {
            if (spriteNumBoss == 1) {
                spriteNumBoss = 2;
            } else if (spriteNumBoss == 2) {
                spriteNumBoss = 3;
            } else if (spriteNumBoss == 3) {
                spriteNumBoss = 1;
            }
            spriteCounterBoss = 0;
        }

        //invincible
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }


    public void hitPlayer(GamePanel gp){
        gp.cChecker.checkPlayer(this);
        if(hitPlayer && gp.player.isDashing) {
            gp.player.contactMonster(1);
            hitPlayer = false;
        }
    }
}
