package Main;

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

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        int mapNum = 0;

        gp.obj[mapNum][0] = new Key(gp);

        gp.obj[0][1] = new Key(gp);
        gp.obj[0][1].worldX = 60 * gp.tileSize;
        gp.obj[0][1].worldY = 12 * gp.tileSize;

        gp.obj[0][2] = new Door(gp);
        gp.obj[0][2].locked = true;
        gp.obj[0][2].doorHouse = "DungeonEntrance";
        gp.obj[0][2].worldX = 62 * gp.tileSize;
        gp.obj[0][2].worldY = 13 * gp.tileSize;

        gp.obj[0][3] = new HealthUp(gp);
        gp.obj[0][3].worldX = 62 * gp.tileSize;
        gp.obj[0][3].worldY = 35 * gp.tileSize;


        gp.obj[0][6] = new sword1(gp);
        gp.obj[0][6].worldX = 33 * gp.tileSize;
        gp.obj[0][6].worldY = 47 * gp.tileSize;

        gp.obj[0][7] = new Door(gp);
        gp.obj[0][7].locked = false;
        gp.obj[0][7].worldX = 59 * gp.tileSize;
        gp.obj[0][7].worldY = 60 * gp.tileSize;

        gp.obj[0][8] = new Door(gp);
        gp.obj[0][8].locked = false;
        gp.obj[0][8].worldX = 62 * gp.tileSize;
        gp.obj[0][8].worldY = 56 * gp.tileSize;

        gp.obj[0][9] = new Door(gp);
        gp.obj[0][9].locked = false;
        gp.obj[0][9].worldX = 84 * gp.tileSize;
        gp.obj[0][9].worldY = 76 * gp.tileSize;

        gp.obj[0][10] = new Door(gp);
        gp.obj[0][10].locked = true;
        gp.obj[0][10].doorHouse = "Andre";
        gp.obj[0][10].worldX = 36 * gp.tileSize;
        gp.obj[0][10].worldY = 76 * gp.tileSize;

        //obstacol inceput joc
        gp.obj[0][11] = new obstacle_log(gp);
        gp.obj[0][11].worldX = 55 * gp.tileSize;
        gp.obj[0][11].worldY = 68 * gp.tileSize;

        gp.obj[0][12] = new obstacle_log(gp);
        gp.obj[0][12].worldX = 56 * gp.tileSize;
        gp.obj[0][12].worldY = 68 * gp.tileSize;

        gp.obj[0][13] = new obstacle_log(gp);
        gp.obj[0][13].worldX = 57 * gp.tileSize;
        gp.obj[0][13].worldY = 68 * gp.tileSize;

        gp.obj[0][14] = new obstacle_log(gp);
        gp.obj[0][14].worldX = 58 * gp.tileSize;
        gp.obj[0][14].worldY = 68 * gp.tileSize;

        gp.obj[0][15] = new obstacle_log(gp);
        gp.obj[0][15].worldX = 59 * gp.tileSize;
        gp.obj[0][15].worldY = 68 * gp.tileSize;

        gp.obj[0][16] = new obstacle_log(gp);
        gp.obj[0][16].worldX = 60 * gp.tileSize;
        gp.obj[0][16].worldY = 68 * gp.tileSize;

        gp.obj[0][17] = new obstacle_log(gp);
        gp.obj[0][17].worldX = 61 * gp.tileSize;
        gp.obj[0][17].worldY = 68 * gp.tileSize;

        gp.obj[0][18] = new obstacle_log(gp);
        gp.obj[0][18].worldX = 62 * gp.tileSize;
        gp.obj[0][18].worldY = 68 * gp.tileSize;

        gp.obj[0][19] = new obstacle_log(gp);
        gp.obj[0][19].worldX = 63 * gp.tileSize;
        gp.obj[0][19].worldY = 68 * gp.tileSize;


        //Aestetics Housing

        //House_1
        gp.obj[0][20] = new fridge(gp);
        gp.obj[0][20].worldX = 58 * gp.tileSize;
        gp.obj[0][20].worldY = 57 * gp.tileSize;

        gp.obj[0][21] = new table(gp);
        gp.obj[0][21].worldX = 63 * gp.tileSize;
        gp.obj[0][21].worldY = 58 * gp.tileSize;

        gp.obj[0][22] = new chair_left(gp);
        gp.obj[0][22].worldX = 64 * gp.tileSize;
        gp.obj[0][22].worldY = 58 * gp.tileSize;

        gp.obj[0][23] = new bed(gp);
        gp.obj[0][23].worldX = 60 * gp.tileSize;
        gp.obj[0][23].worldY = 52 * gp.tileSize;

        gp.obj[0][24] = new lamp(gp);
        gp.obj[0][24].worldX = 60 * gp.tileSize;
        gp.obj[0][24].worldY = 57 * gp.tileSize;

        gp.obj[0][25] = new flower(gp);
        gp.obj[0][25].worldX = 64 * gp.tileSize;
        gp.obj[0][25].worldY = 55 * gp.tileSize;

        gp.obj[0][26] = new Chest(gp);
        gp.obj[0][26].worldX = 62 * gp.tileSize;
        gp.obj[0][26].worldY = 52 * gp.tileSize;

        gp.obj[0][27] = new letter(gp);
        gp.obj[0][28] = new Key_house2(gp);
    }
    public void setNPC() {

        int mapNum = 0;
        //factory
        NPC_Factory factory = new NPC_Factory();

        gp.npc[0][0] = factory.getNPC(NPC_Type.PisicaSTART, gp);
        gp.npc[0][0].worldX = gp.tileSize * 58;
        gp.npc[0][0].worldY = gp.tileSize * 58;

        gp.npc[0][1] = factory.getNPC(NPC_Type.BOY, gp);
        gp.npc[0][1].worldX = gp.tileSize * 62;
        gp.npc[0][1].worldY = gp.tileSize * 31;

        gp.npc[0][2] = factory.getNPC(NPC_Type.PisicaCASA, gp);
        gp.npc[0][2].worldX = gp.tileSize * 33;
        gp.npc[0][2].worldY = gp.tileSize * 75;

        gp.npc[0][3] = factory.getNPC(NPC_Type.dungeonKeeper, gp);
        gp.npc[0][3].worldX = gp.tileSize * 75;
        gp.npc[0][3].worldY = gp.tileSize * 12;
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

        gp.monster[2][5] = new Green_Slime(gp);
        gp.monster[2][5].worldX = gp.tileSize * 71;
        gp.monster[2][5].worldY = gp.tileSize * 46;

        gp.monster[2][6] = new Green_Slime(gp);
        gp.monster[2][6].worldX = gp.tileSize * 30;
        gp.monster[2][6].worldY = gp.tileSize * 15;

        gp.monster[2][7] = new Green_Slime(gp);
        gp.monster[2][7].worldX = gp.tileSize * 30;
        gp.monster[2][7].worldY = gp.tileSize * 10;

        /*
        gp.monster[0][3] = new Green_Slime(gp);
        gp.monster[0][3].worldX = gp.tileSize * 24;
        gp.monster[0][3].worldY = gp.tileSize * 22;

         */
    }
}
