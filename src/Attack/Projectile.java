package Attack;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class Projectile extends Entity {

    public Projectile(GamePanel gp) {
        super(gp);
        attackDamage = 1;
        entity_type = "Projectile";
        speed = 4;

        solidArea.x = 3;
        solidArea.y = 4;
        solidArea.width = 10;
        solidArea.height = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        projectile = setup("/Attack/projectile");
    }


    public void setTarget(int targetX, int targetY) {
        int deltaX = targetX - worldX;
        int deltaY = targetY - worldY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double directionX = deltaX / distance;
        double directionY = deltaY / distance;
        speedX = (int) (directionX * speed);
        speedY = (int) (directionY * speed);
    }

    public void setDirection(int directionX, int directionY) {
        speedX = directionX * speed;
        speedY = directionY * speed;
    }

}
