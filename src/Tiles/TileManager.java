package Tiles;

import Main.GamePanel;
import Main.UtilityTool;
import TratareExceptii.CustomException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[150];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];


        try {
            getTileImage();
        } catch (CustomException e) {
            e.printStackTrace();
        }
        loadMap("/Map/Sunny_Valley.txt", 0);
        loadMap("/Map/dungeon1.txt", 1);
        loadMap("/Map/dungeon2.txt", 2);
    }

    public void getTileImage() throws CustomException {

        setup(0, "/Water/New_Water/apa_colt_dreapta_jos", true);
        setup(1, "/Water/New_Water/apa_dreapta jos", true);
        setup(2, "/Water/New_Water/apa_dreapta", true);
        setup(3, "/Water/New_Water/apa_dreapta_2", true);
        setup(4, "/Water/New_Water/apa_dreapta_3", true);
        setup(5, "/Water/New_Water/apa_jos", true);
        setup(6, "/Water/New_Water/apa_jos_2", true);
        setup(7, "/Water/New_Water/apa_jos_3", true);
        setup(8, "/Water/New_Water/apa_jos_4", true);
        setup(9, "/Water/New_Water/apa_jos_stanga", true);
        setup(10, "/Water/New_Water/apa_stanga", true);
        setup(11, "/Water/New_Water/apa_stanga_3", true);
        setup(12, "/Water/New_Water/apa_stanga_sus", true);
        setup(13, "/Water/New_Water/apa_sus", true);
        setup(14, "/Water/New_Water/apa_sus_2", true);
        setup(15, "/Water/New_Water/apa_sus_3", true);
        setup(16, "/Water/New_Water/apa_sus_4", true);
        setup(17, "/Water/New_Water/apa_sus_dreapta_2", true);

        setup(21, "/new_tiles/cevA", true);
        setup(27, "/new_tiles/drum_flori", false);
        setup(32, "/new_tiles/geam", true);
        setup(35, "/new_tiles/geam_2", true);
        setup(36, "/new_tiles/geam_3", true);
        setup(37, "/new_tiles/geam_4", true);
        setup(38, "/new_tiles/grass", false);
        setup(39, "/new_tiles/grass1", false);
        setup(44, "/new_tiles/grass2", false);
        setup(45, "/new_tiles/grass3", false);
        setup(42, "/new_tiles/grass4", false);
        setup(43, "/new_tiles/grass5", false);
        setup(40, "new_tiles/grass6", false);
        setup(50, "/new_tiles/grass7", false);
        setup(41, "/new_tiles/grass8", false);
        setup(51, "/new_tiles/grass9", false);
        setup(48, "/new_tiles/grass10", false);
        setup(49, "/new_tiles/grass11", false);
        setup(48, "/new_tiles/grass12", false);
        setup(47, "/new_tiles/grass13", false);

        //Dungeon
        setup(67, "/new_tiles/Dungeon/mossy_podea", false);
        setup(70, "/new_tiles/Dungeon/PD1", true);
        setup(71, "/new_tiles/Dungeon/PD10", true);
        setup(72, "/new_tiles/Dungeon/PD11", true);
        setup(73, "/new_tiles/Dungeon/PD12", true);
        setup(74, "/new_tiles/Dungeon/PD13", true);
        setup(75, "/new_tiles/Dungeon/PD14", true);
        setup(76, "/new_tiles/Dungeon/PD15", true);
        setup(77, "/new_tiles/Dungeon/PD16", true);
        setup(78, "/new_tiles/Dungeon/PD17", true);
        setup(79, "/new_tiles/Dungeon/PD18", true);
        setup(80, "/new_tiles/Dungeon/PD19", true);
        setup(81, "/new_tiles/Dungeon/PD2", true);
        setup(82, "/new_tiles/Dungeon/PD20", true);
        setup(83, "/new_tiles/Dungeon/PD21", true);
        setup(84, "/new_tiles/Dungeon/PD3", true);
        setup(85, "/new_tiles/Dungeon/PD4", true);
        setup(86, "/new_tiles/Dungeon/PD5", true);
        setup(87, "/new_tiles/Dungeon/PD6", true);
        setup(91, "/new_tiles/Dungeon/PD7", true);
        setup(92, "/new_tiles/Dungeon/PD8", true);
        setup(93, "/new_tiles/Dungeon/PD9", true);
        setup(94, "/new_tiles/perete_casa", true);

        setup(100, "/new_tiles/podea", false);
        setup(101, "/new_tiles/podea_casa", false);
        setup(109, "/Water/New_Water/water_1", true);
        setup(110, "/Water/New_Water/water_2", true);
        setup(111, "/Water/New_Water/water_3", true);


    }
    public void setup(int index, String imageName, boolean collision) throws CustomException {

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e) {
            throw new CustomException("Eroare la incarcare tile");
        }
    }
    public void loadMap(String filePath, int map){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {
            System.out.println("Map could not be loaded");
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            //int worldZ = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //randeaza doar in jurul caracterului
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - gp.tileSize*2 &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize*2 &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - gp.tileSize*2 &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize*2) {
                if(tile[tileNum] != null) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }


            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;

                worldRow++;
            }
        }
    }
}
