package Main;

import Enemy.Frog_Boss;
import Enemy.Green_Slime;
import Entity.NPC_Factory;
import Entity.NPC_Type;
import Object.Key;
import Object.Door;
import Object.Chest;
import Object.HealthUp;
import Object.sword1;
import Object.obstacle_log;
import Object.bed;
import Object.chair_left;
import Object.chair_right;
import Object.flower;
import Object.fridge;
import Object.lamp;
import Object.table;
import Object.letter;
import Object.Key_house2;
import Object.tree;
import Object.grass1;
import Object.grass2;
import Object.floare1;
import Object.floare2;
import Object.floare3;
import Object.floare4;
import Object.floare5;

import java.util.*;


public class AssetSetter {

    GamePanel gp;
    Random random = new Random();
    Set<int[]> treePositions = new HashSet<>();

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        int mapNum = 0;

        gp.obj[mapNum][0] = new Key(gp);
//        int[] position = new int[2];
//
//        //Copaci
//
//        gp.obj[0][1] = new tree(gp);
//        gp.obj[0][1].worldX = 40 * gp.tileSize;
//        gp.obj[0][1].worldY = 76 * gp.tileSize;
//        position[0] = 40;
//        position[1] = 76;
//        treePositions.add(position);
//
//        gp.obj[0][2] = new tree(gp);
//        gp.obj[0][2].worldX = 32 * gp.tileSize;
//        gp.obj[0][2].worldY = 70 * gp.tileSize;
//        position[0] = 32;
//        position[1] = 70;
//        treePositions.add(position);
//
//        gp.obj[0][3] = new tree(gp);
//        gp.obj[0][3].worldX = 55 * gp.tileSize;
//        gp.obj[0][3].worldY = 70 * gp.tileSize;
//        position[0] = 55;
//        position[1] = 70;
//        treePositions.add(position);
//
//        gp.obj[0][6] = new tree(gp);
//        gp.obj[0][6].worldX = 64 * gp.tileSize;
//        gp.obj[0][6].worldY = 64 * gp.tileSize;
//        position[0] = 64;
//        position[1] = 64;
//        treePositions.add(position);
//
//        gp.obj[0][8] = new tree(gp);
//        gp.obj[0][8].worldX = 59 * gp.tileSize;
//        gp.obj[0][8].worldY = 58 * gp.tileSize;
//        position[0] = 59;
//        position[1] = 58;
//        treePositions.add(position);
//
//        gp.obj[0][9] = new tree(gp);
//        gp.obj[0][9].worldX = 51 * gp.tileSize;
//        gp.obj[0][9].worldY = 57 * gp.tileSize;
//        position[0] = 51;
//        position[1] = 57;
//        treePositions.add(position);
//
//        gp.obj[0][10] = new tree(gp);
//        gp.obj[0][10].worldX = 63 * gp.tileSize;
//        gp.obj[0][10].worldY = 54 * gp.tileSize;
//        position[0] = 63;
//        position[1] = 54;
//        treePositions.add(position);
//
//        //obstacol inceput joc
//        gp.obj[0][11] = new tree(gp);
//        gp.obj[0][11].worldX = 57 * gp.tileSize;
//        gp.obj[0][11].worldY = 50 * gp.tileSize;
//        position[0] = 57;
//        position[1] = 50;
//        treePositions.add(position);
//
//        gp.obj[0][12] = new tree(gp);
//        gp.obj[0][12].worldX = 80 * gp.tileSize;
//        gp.obj[0][12].worldY = 53 * gp.tileSize;
//        position[0] = 80;
//        position[1] = 53;
//        treePositions.add(position);
//
//        gp.obj[0][13] = new tree(gp);
//        gp.obj[0][13].worldX = 74 * gp.tileSize;
//        gp.obj[0][13].worldY = 47 * gp.tileSize;
//        position[0] = 74;
//        position[1] = 47;
//        treePositions.add(position);
//
//        gp.obj[0][14] = new tree(gp);
//        gp.obj[0][14].worldX = 68 * gp.tileSize;
//        gp.obj[0][14].worldY = 44 * gp.tileSize;
//        position[0] = 68;
//        position[1] = 44;
//        treePositions.add(position);
//
//        gp.obj[0][15] = new tree(gp);
//        gp.obj[0][15].worldX = 72 * gp.tileSize;
//        gp.obj[0][15].worldY = 36 * gp.tileSize;
//        position[0] = 72;
//        position[1] = 36;
//        treePositions.add(position);
//
//        gp.obj[0][16] = new tree(gp);
//        gp.obj[0][16].worldX = 51 * gp.tileSize;
//        gp.obj[0][16].worldY = 37 * gp.tileSize;
//        position[0] = 51;
//        position[1] = 37;
//        treePositions.add(position);
//
//        gp.obj[0][17] = new tree(gp);
//        gp.obj[0][17].worldX = 44 * gp.tileSize;
//        gp.obj[0][17].worldY = 28 * gp.tileSize;
//        position[0] = 44;
//        position[1] = 28;
//        treePositions.add(position);
//
//        gp.obj[0][18] = new tree(gp);
//        gp.obj[0][18].worldX = 33 * gp.tileSize;
//        gp.obj[0][18].worldY = 33 * gp.tileSize;
//        position[0] = 33;
//        position[1] = 33;
//        treePositions.add(position);
//
//        gp.obj[0][19] = new tree(gp);
//        gp.obj[0][19].worldX = 25 * gp.tileSize;
//        gp.obj[0][19].worldY = 39 * gp.tileSize;
//        position[0] = 25;
//        position[1] = 39;
//        treePositions.add(position);
//
//        gp.obj[0][19] = new tree(gp);
//        gp.obj[0][19].worldX = 25 * gp.tileSize;
//        gp.obj[0][19].worldY = 39 * gp.tileSize;
//        position[0] = 25;
//        position[1] = 39;
//        treePositions.add(position);
//
//        gp.obj[0][19] = new tree(gp);
//        gp.obj[0][19].worldX = 25 * gp.tileSize;
//        gp.obj[0][19].worldY = 57 * gp.tileSize;
//        position[0] = 25;
//        position[1] = 57;
//        treePositions.add(position);
        //copaci
        List<int[]> grassTiles = findTilesWithIndex(41);


        int numberOfTrees = 30;
        for (int i = 0; i < numberOfTrees; i++) {
            int[] position;
            boolean validPosition;
            do {
                position = grassTiles.get(random.nextInt(grassTiles.size()));
                validPosition = isValidTreePosition(position[0], position[1]) && isFarFromCollisionTiles(position[0], position[1]);;
            } while (!validPosition);

            gp.obj[mapNum][i] = new tree(gp);
            gp.obj[mapNum][i].worldX = position[0] * gp.tileSize;
            gp.obj[mapNum][i].worldY = position[1] * gp.tileSize;
            treePositions.add(position);
        }

        //iarba

        for (int[] tile : grassTiles) {
            int x = tile[0];
            int y = tile[1];
            if (isFarFromTrees(x, y) && isFarFromCollisionTiles(x, y)) {
                int numberOfObjects = 6;
                for (int j = 0; j < numberOfObjects; j++) {
                    int var = random.nextInt(gp.obj[0].length);
                    int randomObject = random.nextInt(12);
                    switch (randomObject) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            gp.obj[0][var] = new grass1(gp);
                            break;
                        case 10:
                            gp.obj[0][var] = new grass2(gp);
                            break;
                        case 11:
                            gp.obj[0][var] = new floare4(gp);
                            break;
                    }
                    gp.obj[0][var].worldX = x * gp.tileSize + random.nextInt(gp.tileSize);
                    gp.obj[0][var].worldY = y * gp.tileSize + random.nextInt(gp.tileSize);
                }
            }
        }
    }


    private boolean isFarFromCollisionTiles(int x, int y) {
        for (int col = x - 2; col <= x + 2; col++) {
            for (int row = y - 2; row <= y + 2; row++) {
                if (col >= 0 && col < gp.maxWorldCol && row >= 0 && row < gp.maxWorldRow) {
                    int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
                    if (gp.tileM.tile[tileNum].collision) { // Assuming 1 is the index for collision tiles
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isCollisionTile(int x, int y) {
        int tileNum = gp.tileM.mapTileNum[gp.currentMap][x][y];
        return gp.tileM.tile[tileNum].collision;
    }

    private List<int[]> findTilesWithIndex(int index) {
        List<int[]> tiles = new ArrayList<>();
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (gp.tileM.mapTileNum[gp.currentMap][col][row] == index) {
                    tiles.add(new int[]{col, row});
                }
            }
        }
        return tiles;
    }

    private boolean isFarFromTrees(int x, int y) {
        for (int[] pos : treePositions) {
            int dx = Math.abs(pos[0] - x);
            int dy = Math.abs(pos[1] - y);
            if (dx < 3 && dy < 3) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidTreePosition(int x, int y) {
        for (int[] pos : treePositions) {
            int dx = Math.abs(pos[0] - x);
            int dy = Math.abs(pos[1] - y);
            if (dx < 5 && dy < 5) {
                return false;
            }
        }
        return true;
    }

    public void setNPC() {

        int mapNum = 0;
        //factory
        NPC_Factory factory = new NPC_Factory();

        gp.npc[0][0] = factory.getNPC(NPC_Type.PisicaSTART, gp);
        gp.npc[0][0].worldX = gp.tileSize * 58;
        gp.npc[0][0].worldY = gp.tileSize * 58;
//
//        gp.npc[0][1] = factory.getNPC(NPC_Type.BOY, gp);
//        gp.npc[0][1].worldX = gp.tileSize * 62;
//        gp.npc[0][1].worldY = gp.tileSize * 31;
//
//        gp.npc[0][2] = factory.getNPC(NPC_Type.PisicaCASA, gp);
//        gp.npc[0][2].worldX = gp.tileSize * 33;
//        gp.npc[0][2].worldY = gp.tileSize * 75;
//
//        gp.npc[0][3] = factory.getNPC(NPC_Type.dungeonKeeper, gp);
//        gp.npc[0][3].worldX = gp.tileSize * 75;
//        gp.npc[0][3].worldY = gp.tileSize * 12;
    }

    public void setMonster() {

        gp.monster[1][0] = new Green_Slime(gp);
        gp.monster[1][0].worldX = gp.tileSize * 40;
        gp.monster[1][0].worldY = gp.tileSize * 28;

        gp.monster[1][1] = new Green_Slime(gp);
        gp.monster[1][1].worldX = gp.tileSize * 48;
        gp.monster[1][1].worldY = gp.tileSize * 28;

        gp.monster[1][2] = new Green_Slime(gp);
        gp.monster[1][2].worldX = gp.tileSize * 48;
        gp.monster[1][2].worldY = gp.tileSize * 24;

        gp.monster[1][3] = new Green_Slime(gp);
        gp.monster[1][3].worldX = gp.tileSize * 40;
        gp.monster[1][3].worldY = gp.tileSize * 17;

        gp.monster[1][4] = new Green_Slime(gp);
        gp.monster[1][4].worldX = gp.tileSize * 71;
        gp.monster[1][4].worldY = gp.tileSize * 40;

        gp.monster[1][5] = new Frog_Boss(gp);
        gp.monster[1][5].worldX = gp.tileSize * 26;
        gp.monster[1][5].worldY = gp.tileSize * 67;

        gp.monster[2][5] = new Green_Slime(gp);
        gp.monster[2][5].worldX = gp.tileSize * 71;
        gp.monster[2][5].worldY = gp.tileSize * 46;

        gp.monster[2][6] = new Green_Slime(gp);
        gp.monster[2][6].worldX = gp.tileSize * 30;
        gp.monster[2][6].worldY = gp.tileSize * 15;

        gp.monster[2][7] = new Green_Slime(gp);
        gp.monster[2][7].worldX = gp.tileSize * 30;
        gp.monster[2][7].worldY = gp.tileSize * 10;

//        gp.monster[0][8] = new Green_Slime(gp);
//        gp.monster[0][8].worldX = gp.tileSize * 34;
//        gp.monster[0][8].worldY= gp.tileSize * 45;

        /*
        gp.monster[0][3] = new Green_Slime(gp);
        gp.monster[0][3].worldX = gp.tileSize * 24;
        gp.monster[0][3].worldY = gp.tileSize * 22;

         */
    }
}
