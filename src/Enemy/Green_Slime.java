package Enemy;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class
Green_Slime extends Entity {


    public Green_Slime(GamePanel gp) {
        super(gp);

        name = "Green Slime";
        speed = 3;
        maxLife = 4;
        life = maxLife;
        enemy_type = "Slime";
        entity_type = "Enemy";

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        checkPlayer.x = 0;
        checkPlayer.y = 0;
        checkPlayer.width = gp.tileSize * 7;
        checkPlayer.height = gp.tileSize * 7;

        getImage();
    }

    public void getImage() {

        up1 = setup("/Enemy/greenslime_down_1");
        up2 = setup("/Enemy/greenslime_down_2");
        down1 = setup("/Enemy/greenslime_down_1");
        down2 = setup("/Enemy/greenslime_down_2");
        left1 = setup("/Enemy/greenslime_down_1");
        left2 = setup("/Enemy/greenslime_down_2");
        right1 = setup("/Enemy/greenslime_down_1");
        right2 = setup("/Enemy/greenslime_down_2");
    }
    public void setAction() {

        actionLockCounter ++;
        System.out.println("playerNearby: " + playerNearby);

        if (playerNearby) {
            followPlayer(this);
            actionLockCounter = 0;
        }else if (actionLockCounter == 120) {

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
