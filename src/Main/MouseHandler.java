package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{
    GamePanel gp;
    public boolean button1Pressed;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e){
        int code = e.getButton();
        int mouseX = e.getX();
        int mouseY = e.getY();
        calculateDirection(mouseX, mouseY);

        if(gp.gameState == gp.playState)
            if (code == MouseEvent.BUTTON1) {
                if(gp.player.hasSword) {
                    button1Pressed = true;
                    gp.player.isAttacking = true;
                    gp.player.attackCounter = 0;
                    System.out.println("Is attacking: " + gp.player.isAttacking);
                }
            }
    }

    private void calculateDirection(int mouseX, int mouseY) {
        int playerScreenX = gp.player.screenX;
        int playerScreenY = gp.player.screenY;
        System.out.println(mouseX + " " + mouseY);
        System.out.println(playerScreenX + " " + playerScreenY);
        double deltaX = playerScreenX - mouseX;
        double deltaY = playerScreenY - mouseY;
        System.out.println("deltaX: " + deltaX);
        System.out.println("deltaY: " + deltaY);

        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));

        if (angle < 0) {
            angle += 360;
        }
        System.out.println(angle);
        if (angle >= 337.5 || angle < 22.5) {
            gp.player.attack_direction = "left";
            gp.player.move_direction = "left";
        } else if (angle >= 22.5 && angle < 67.5) {
            gp.player.attack_direction = "up_left";
            gp.player.move_direction = "up_left";
        } else if (angle >= 67.5 && angle < 112.5) {
            gp.player.attack_direction = "up";
            gp.player.move_direction = "up";
        } else if (angle >= 112.5 && angle < 157.5) {
            gp.player.attack_direction = "up_right";
            gp.player.move_direction = "up_right";
        } else if (angle >= 157.5 && angle < 202.5) {
            gp.player.attack_direction = "right";
            gp.player.move_direction = "right";
        } else if (angle >= 202.5 && angle < 247.5) {
            gp.player.attack_direction = "down_right";
            gp.player.move_direction = "down_right";
        } else if (angle >= 247.5 && angle < 292.5) {
            gp.player.attack_direction = "down";
            gp.player.move_direction = "down";
        } else if (angle >= 292.5 && angle < 337.5) {
            gp.player.attack_direction = "down_left";
            gp.player.move_direction = "down_left";
        }

        System.out.println("Attack direction: " + gp.player.attack_direction);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();

        if (code == MouseEvent.BUTTON1) {
            button1Pressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
