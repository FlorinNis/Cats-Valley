package Main;

import Entity.Entity;

import java.awt.*;
import java.util.Objects;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.move_direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "up_left":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "up_right":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down_left":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down_right":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkAttackCollision(Entity[][] target) {
        //setam coliziunea in functie de pozitia player-ului
        gp.player.attackBox.x = gp.player.worldX + gp.player.attackBox.x;
        gp.player.attackBox.y = gp.player.worldY + gp.player.attackBox.y;
        gp.player.enemyHit = 999;

        for(int i = 0; i < target[gp.currentMap].length; i++) {
            if(target[gp.currentMap][i] != null) {
                //get object solid area pos
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                if(gp.player.attackBox.intersects(target[gp.currentMap][i].solidArea)) {
                    gp.player.attackCollisionOn = true;
                    gp.player.enemyHit = i;
                }

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
    }


    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for(int i = 0; i < gp.obj[1].length; i++) {

            if(gp.obj[gp.currentMap][i] != null) {
                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get object solid area pos
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch(entity.move_direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldY += entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldY -= entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldX += entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldX -= entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                        //diagonal collision
                    case "up_left":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldY += entity.speed;
                                    gp.player.worldX += entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                    case "up_right":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldY += entity.speed;
                                    gp.player.worldX -= entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                    case "down_left":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldY -= entity.speed;
                                    gp.player.worldX += entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                    case "down_right":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if(gp.obj[gp.currentMap][i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                if(Objects.equals(gp.obj[gp.currentMap][i].name, "Door") && gp.obj[gp.currentMap][i].locked){
                                    gp.player.worldY += entity.speed;
                                    gp.player.worldX += entity.speed;
                                }
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;

            }
        }

            //daca playeru atinge obiectul returneaza
        return index;
    }
    //NPC or Enemy
    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;

        for(int i = 0; i < target[1].length; i++) {

            if(target[gp.currentMap][i] != null) {
                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get object solid area pos
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
                //get NPC CheckLoc
                target[gp.currentMap][i].checkNPC.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].checkNPC.x - gp.tileSize;
                target[gp.currentMap][i].checkNPC.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].checkNPC.y - gp.tileSize;

                switch(entity.move_direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            entity.interactRange = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    //diagonal collision
                    case "up_left":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            entity.interactRange = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    case "up_right":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            entity.interactRange = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    case "down_left":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            entity.interactRange = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                    case "down_right":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            entity.interactRange = true;
                        }
                        if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                            index = i;
                            entity.interactRange = true;
                        }
                        break;
                }
                if(entity.solidArea.intersects((target[gp.currentMap][i].checkNPC))) {
                    index = i;
                    entity.interactRange = true;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
                target[gp.currentMap][i].checkNPC.x = target[gp.currentMap][i].checkNPCDefaultX;
                target[gp.currentMap][i].checkNPC.y = target[gp.currentMap][i].checkNPCDefaultY;
            }
        }

        return index;
    }
    public void checkPlayer(Entity entity) {
        //get entity solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //get player solid area pos
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        entity.checkPlayer.x = entity.worldX + entity.checkPlayer.x - gp.tileSize * 3;
        entity.checkPlayer.y = entity.worldY + entity.checkPlayer.y - gp.tileSize * 3;

        if(Objects.equals(entity.name, "Green Slime")) entity.isEnemy = true;


        switch (entity.move_direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            //diagonal collision
            case "up_left":
                entity.solidArea.y -= entity.speed;
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            case "up_right":
                entity.solidArea.y -= entity.speed;
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            case "down_left":
                entity.solidArea.y += entity.speed;
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
            case "down_right":
                entity.solidArea.y += entity.speed;
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.hitPlayer = true;
                }
                if(entity.checkPlayer.intersects((gp.player.solidArea))) {
                    entity.playerNearby = true;
                }
                else {
                    entity.playerNearby = false;
                }
                break;
        }
        if(entity.checkPlayer.intersects((gp.player.solidArea))) {
            entity.playerNearby = true;
        }
        else {
            entity.playerNearby = false;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        entity.checkPlayer.x = entity.checkPlayerDefaultX;
        entity.checkPlayer.y = entity.checkPlayerDefaultY;
    }


    private void drawCollisionBox(Graphics2D g2, Entity entity) {
        // Draw solidArea (collision box)
        g2.setColor(new Color(255, 0, 0, 100)); // Red with some transparency
        g2.fillRect(
                entity.worldX + entity.solidArea.x - entity.solidAreaDefaultX,
                entity.worldY + entity.solidArea.y - entity.solidAreaDefaultY,
                entity.solidArea.width,
                entity.solidArea.height
        );

        // Draw checkNPC (interaction box)
        g2.setColor(new Color(0, 0, 255, 100)); // Blue with some transparency
        g2.fillRect(
                entity.worldX + entity.checkNPC.x - entity.checkNPCDefaultX,
                entity.worldY + entity.checkNPC.y - entity.checkNPCDefaultY,
                entity.checkNPC.width,
                entity.checkNPC.height
        );
    }

    public int checkEnemy(Entity entity, Entity[][] target) {

        int index = 999;

        for(int i = 0; i < target[1].length; i++) {

            if(target[gp.currentMap][i] != null) {
                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get object solid area pos
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(entity.move_direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    //diagonal collision
                    case "up_left":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "up_right":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down_left":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down_right":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;

    }

    public boolean isPlayerNearNPC(Entity player, Entity npc) {
        Rectangle npcInteractionArea = new Rectangle(
                npc.worldX + npc.checkNPC.x - npc.checkNPCDefaultX,
                npc.worldY + npc.checkNPC.y - npc.checkNPCDefaultY,
                npc.checkNPC.width,
                npc.checkNPC.height
        );

        Rectangle playerArea = new Rectangle(
                player.worldX + player.solidArea.x - player.solidAreaDefaultX,
                player.worldY + player.solidArea.y - player.solidAreaDefaultY,
                player.solidArea.width,
                player.solidArea.height
        );

        return npcInteractionArea.intersects(playerArea);
    }


}
