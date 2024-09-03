package Main;

import Enemy.Frog_Boss;
import Enemy.Green_Slime;
import Entity.NPC_Factory;
import Entity.NPC_Type;
import Object.Key;
import Object.Door;
import Object.HealthUp;
import Object.sword1;
import Object.obstacle_log;

import Object.tree;
import Object.grass1;
import Object.grass2;

import Object.floare4;

import Object.log;

import java.util.*;


public class AssetSetter {

    GamePanel gp;
    Random random = new Random();
    Set<int[]> treePositions = new HashSet<>();

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setGrass() {
        List<int[]> grassTiles = findTilesWithIndex(41);

        for (int[] tile : grassTiles) {
            int x = tile[0];
            int y = tile[1];
            System.out.println("x: " + x + " y: " + y);
            if (isFarFromTrees(x, y) && isFarFromCollisionTiles(x, y)) {
                int numberOfObjects = 5;
                for (int j = 0; j < numberOfObjects; j++) {
                    int var = random.nextInt(gp.grass[0].length);

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
                            gp.grass[0][var] = new grass1(gp);
                            System.out.println(gp.grass[0][var].name);
                            break;
                        case 10:
                            gp.grass[0][var] = new grass2(gp);
                            break;
                        case 11:
                            gp.grass[0][var] = new floare4(gp);
                            break;
                    }
                    gp.grass[0][var].worldX = x * gp.tileSize + random.nextInt(gp.tileSize);
                    gp.grass[0][var].worldY = y * gp.tileSize + random.nextInt(gp.tileSize);
                }
            }
        }
    }

    public void setObject(int index) {

        int mapNum = 0;

       int[] position = new int[2];
//
//        //Copaci
//
        switch (index) {
            case 0:
                gp.obj[0][1] = new tree(gp);
                gp.obj[0][1].worldX = 40 * gp.tileSize;
                gp.obj[0][1].worldY = 76 * gp.tileSize;
                position[0] = 40;
                position[1] = 74;
                treePositions.add(position);

                gp.obj[0][2] = new tree(gp);
                gp.obj[0][2].worldX = 32 * gp.tileSize;
                gp.obj[0][2].worldY = 70 * gp.tileSize;
                position[0] = 32;
                position[1] = 68;
                treePositions.add(position);

                gp.obj[0][3] = new tree(gp);
                gp.obj[0][3].worldX = 55 * gp.tileSize;
                gp.obj[0][3].worldY = 70 * gp.tileSize;
                position[0] = 55;
                position[1] = 68;
                treePositions.add(position);

                gp.obj[0][6] = new tree(gp);
                gp.obj[0][6].worldX = 64 * gp.tileSize;
                gp.obj[0][6].worldY = 64 * gp.tileSize;
                position[0] = 64;
                position[1] = 62;
                treePositions.add(position);

                gp.obj[0][8] = new tree(gp);
                gp.obj[0][8].worldX = 59 * gp.tileSize;
                gp.obj[0][8].worldY = 58 * gp.tileSize;
                position[0] = 59;
                position[1] = 56;
                treePositions.add(position);

                gp.obj[0][9] = new tree(gp);
                gp.obj[0][9].worldX = 51 * gp.tileSize;
                gp.obj[0][9].worldY = 57 * gp.tileSize;
                position[0] = 51;
                position[1] = 55;
                treePositions.add(position);

                gp.obj[0][10] = new tree(gp);
                gp.obj[0][10].worldX = 63 * gp.tileSize;
                gp.obj[0][10].worldY = 54 * gp.tileSize;
                position[0] = 63;
                position[1] = 52;
                treePositions.add(position);

                //obstacol inceput joc
                gp.obj[0][11] = new tree(gp);
                gp.obj[0][11].worldX = 57 * gp.tileSize;
                gp.obj[0][11].worldY = 50 * gp.tileSize;
                position[0] = 57;
                position[1] = 48;
                treePositions.add(position);

                gp.obj[0][12] = new tree(gp);
                gp.obj[0][12].worldX = 80 * gp.tileSize;
                gp.obj[0][12].worldY = 53 * gp.tileSize;
                position[0] = 80;
                position[1] = 46;
                treePositions.add(position);

                gp.obj[0][13] = new tree(gp);
                gp.obj[0][13].worldX = 74 * gp.tileSize;
                gp.obj[0][13].worldY = 47 * gp.tileSize;
                position[0] = 74;
                position[1] = 44;
                treePositions.add(position);

                gp.obj[0][14] = new tree(gp);
                gp.obj[0][14].worldX = 68 * gp.tileSize;
                gp.obj[0][14].worldY = 44 * gp.tileSize;
                position[0] = 68;
                position[1] = 42;
                treePositions.add(position);

                gp.obj[0][15] = new tree(gp);
                gp.obj[0][15].worldX = 72 * gp.tileSize;
                gp.obj[0][15].worldY = 36 * gp.tileSize;
                position[0] = 72;
                position[1] = 34;
                treePositions.add(position);

                gp.obj[0][16] = new tree(gp);
                gp.obj[0][16].worldX = 51 * gp.tileSize;
                gp.obj[0][16].worldY = 37 * gp.tileSize;
                position[0] = 51;
                position[1] = 35;
                treePositions.add(position);

                gp.obj[0][17] = new tree(gp);
                gp.obj[0][17].worldX = 44 * gp.tileSize;
                gp.obj[0][17].worldY = 28 * gp.tileSize;
                position[0] = 44;
                position[1] = 26;
                treePositions.add(position);

                gp.obj[0][18] = new tree(gp);
                gp.obj[0][18].worldX = 33 * gp.tileSize;
                gp.obj[0][18].worldY = 33 * gp.tileSize;
                position[0] = 33;
                position[1] = 31;
                treePositions.add(position);

                gp.obj[0][19] = new tree(gp);
                gp.obj[0][19].worldX = 25 * gp.tileSize;
                gp.obj[0][19].worldY = 39 * gp.tileSize;
                position[0] = 25;
                position[1] = 37;
                treePositions.add(position);

                gp.obj[0][20] = new obstacle_log(gp);
                gp.obj[0][20].worldX = 40 * gp.tileSize;
                gp.obj[0][20].worldY = 69 * gp.tileSize;

                gp.obj[0][21] = new obstacle_log(gp);
                gp.obj[0][21].worldX = 41 * gp.tileSize;
                gp.obj[0][21].worldY = 69 * gp.tileSize;

                gp.obj[0][22] = new obstacle_log(gp);
                gp.obj[0][22].worldX = 42 * gp.tileSize;
                gp.obj[0][22].worldY = 69 * gp.tileSize;

                gp.obj[0][23] = new obstacle_log(gp);
                gp.obj[0][23].worldX = 43 * gp.tileSize;
                gp.obj[0][23].worldY = 69 * gp.tileSize;

                gp.obj[0][24] = new obstacle_log(gp);
                gp.obj[0][24].worldX = 44 * gp.tileSize;
                gp.obj[0][24].worldY = 69 * gp.tileSize;

                gp.obj[0][25] = new obstacle_log(gp);
                gp.obj[0][25].worldX = 45 * gp.tileSize;
                gp.obj[0][25].worldY = 69 * gp.tileSize;

                gp.obj[0][26] = new obstacle_log(gp);
                gp.obj[0][26].worldX = 46 * gp.tileSize;
                gp.obj[0][26].worldY = 69 * gp.tileSize;

                gp.obj[0][27] = new Door(gp);
                gp.obj[0][27].locked = false;
                gp.obj[0][27].worldX = 11 * gp.tileSize;
                gp.obj[0][27].worldY = 52 * gp.tileSize;

                gp.obj[0][28] = new Door(gp);
                gp.obj[0][27].locked = false;
                gp.obj[0][28].worldX = 35 * gp.tileSize;
                gp.obj[0][28].worldY = 51 * gp.tileSize;

                gp.obj[0][29] = new Door(gp);
                gp.obj[0][27].locked = false;
                gp.obj[0][29].worldX = 36 * gp.tileSize;
                gp.obj[0][29].worldY = 51 * gp.tileSize;

                gp.obj[0][30] = new Door(gp);
                gp.obj[0][27].locked = false;
                gp.obj[0][30].worldX = 76 * gp.tileSize;
                gp.obj[0][30].worldY = 62 * gp.tileSize;

                gp.obj[0][31] = new log(gp);
                gp.obj[0][31].worldX = 39 * gp.tileSize;
                gp.obj[0][31].worldY = 69 * gp.tileSize;

                gp.obj[0][32] = new sword1(gp);
                gp.obj[0][32].worldX = 56 * gp.tileSize;
                gp.obj[0][32].worldY = 30 * gp.tileSize;

                gp.obj[0][33] = new log(gp);
                gp.obj[0][33].worldX = 55 * gp.tileSize;
                gp.obj[0][33].worldY = 30 * gp.tileSize;
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                gp.obj[3][34] = new Door(gp);
                gp.obj[3][34].locked = false;
                gp.obj[3][34].worldX = 36 * gp.tileSize;
                gp.obj[3][34].worldY = 31 * gp.tileSize;
                break;
            case 4:
                gp.obj[4][35] = new Door(gp);
                gp.obj[4][35].locked = false;
                gp.obj[4][35].worldX = 36 * gp.tileSize;
                gp.obj[4][35].worldY = 31 * gp.tileSize;
                break;
            case 5:
                gp.obj[5][36] = new Door(gp);
                gp.obj[5][36].locked = false;
                gp.obj[5][36].worldX = 36 * gp.tileSize;
                gp.obj[5][36].worldY = 31 * gp.tileSize;
                break;
        }


//        gp.obj[0][20] = new tree(gp);
//        gp.obj[0][20].worldX = 25 * gp.tileSize;
//        gp.obj[0][20].worldY = 39 * gp.tileSize;
//        position[0] = 25;
//        position[1] = 37;
//        treePositions.add(position);

//        gp.obj[0][19] = new tree(gp);
//        gp.obj[0][19].worldX = 25 * gp.tileSize;
//        gp.obj[0][19].worldY = 57 * gp.tileSize;
//        position[0] = 25;
//        position[1] = 57;
//        treePositions.add(position);




//        List<int[]> grassTiles = findTilesWithIndex(41);
//
//        int numberOfTrees = 20;
//        for (int i = 0; i < numberOfTrees; i++) {
//            int[] position;
//            boolean validPosition;
//            do {
//                position = grassTiles.get(random.nextInt(grassTiles.size()));
//                validPosition = isValidTreePosition(position[0], position[1]) && isFarFromCollisionTiles(position[0], position[1]);;
//            } while (!validPosition);
//
//            gp.obj[mapNum][i] = new tree(gp);
//            gp.obj[mapNum][i].worldX = position[0] * gp.tileSize;
//            gp.obj[mapNum][i].worldY = position[1] * gp.tileSize;
//            treePositions.add(position);
//        }


    }


    private boolean isFarFromCollisionTiles(int x, int y) {
        for (int col = x - 3; col <= x + 3; col++) {
            for (int row = y - 3; row <= y + 3; row++) {
                if (col >= 0 && col < gp.maxWorldCol && row >= 0 && row < gp.maxWorldRow) {
                    int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
                    if (gp.tileM.tile[tileNum] != null && gp.tileM.tile[tileNum].collision) {
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
            if (dx < 4 && dy < 4) {
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

//        gp.npc[3][0] = factory.getNPC(NPC_Type.PisicaSTART, gp);
//        gp.npc[3][0].worldX = gp.tileSize * 33;
//        gp.npc[3][0].worldY = gp.tileSize * 22;
//
        gp.npc[0][1] = factory.getNPC(NPC_Type.TUTORIAL, gp);
        gp.npc[0][1].worldX = gp.tileSize * 39;
        gp.npc[0][1].worldY = gp.tileSize * 72;

        gp.npc[0][2] = factory.getNPC(NPC_Type.CAT, gp);
        gp.npc[0][2].worldX = gp.tileSize * 40;
        gp.npc[0][2].worldY = gp.tileSize * 68;
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
