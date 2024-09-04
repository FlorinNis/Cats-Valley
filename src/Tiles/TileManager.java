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
    int spriteNumAnim = 1;
    int spriteCounterAnim = 0;


    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[200];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];


        try {
            getTileImage();
        } catch (CustomException e) {
            e.printStackTrace();
        }
        loadMap("/Map/Sunny_Valley.txt", 0);
        loadMap("/Map/dungeon1.txt", 1);
        loadMap("/Map/dungeon2.txt", 2);
        loadMap("/Map/house1.txt", 3);
        loadMap("/Map/house1.txt", 4);
        loadMap("/Map/house1.txt", 5);
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

        //House
        setup(70, "/new_tiles/PC0", true);
        setup(71, "/new_tiles/PC1", true);
        setup(72, "/new_tiles/PC10", true);
        setup(73, "/new_tiles/PC11", true);
        setup(74, "/new_tiles/PC12", true);
        setup(75, "/new_tiles/PC13", true);
        setup(76, "/new_tiles/PC14", true);
        setup(77, "/new_tiles/PC15", true);
        setup(78, "/new_tiles/PC16", true);
        setup(79, "/new_tiles/PC17", false);
        setup(80, "/new_tiles/PC18", true);
        setup(81, "/new_tiles/PC19", true);
        setup(82, "/new_tiles/PC2", true);
        setup(83, "/new_tiles/PC20", false);
        setup(84, "/new_tiles/PC21", true);
        setup(85, "/new_tiles/PC22", true);
        setup(86, "/new_tiles/PC23", true);
        setup(87, "/new_tiles/PC24", true);
        setup(88, "/new_tiles/PC25", true);
        setup(89, "/new_tiles/PC26", true);
        setup(90, "/new_tiles/PC27", true);
        setup(91, "/new_tiles/PC28", true);
        setup(92, "/new_tiles/PC29", true);
        setup(93, "/new_tiles/PC3", true);
        setup(94, "/new_tiles/PC30", true);
        setup(95, "/new_tiles/PC31", true);
        setup(96, "/new_tiles/PC32", true);
        setup(97, "/new_tiles/PC33", true);
        setup(98, "/new_tiles/PC34", true);
        setup(99, "/new_tiles/PC35", true);
        setup(100, "/new_tiles/PC36", true);
        setup(101, "/new_tiles/PC37", true);
        setup(102, "/new_tiles/PC38", true);
        setup(103, "/new_tiles/PC39", true);
        setup(104, "/new_tiles/PC4", true);
        setup(105, "/new_tiles/PC40", true);
        setup(106, "/new_tiles/PC41", true);
        setup(107, "/new_tiles/PC42", true);
        setup(108, "/new_tiles/PC43", true);
        setup(109, "/new_tiles/PC44", true);
        setup(110, "/new_tiles/PC45", false);
        setup(111, "/new_tiles/PC46", false);
        setup(112, "/new_tiles/PC47", false);
        setup(113, "/new_tiles/PC48", false);
        setup(114, "/new_tiles/PC49", false);
        setup(115, "/new_tiles/PC5", true);
        setup(116, "/new_tiles/PC50", false);
        setup(117, "/new_tiles/PC51", false);
        setup(118, "/new_tiles/PC52", false);
        setup(119, "/new_tiles/PC53", false);
        setup(120, "/new_tiles/PC54", false);
        setup(121, "/new_tiles/PC55", false);
        setup(122, "/new_tiles/PC6", true);
        setup(123, "/new_tiles/PC7", true);
        setup(124, "/new_tiles/PC8", true);
        setup(125, "/new_tiles/PC9", true);
        //Dungeon
        setup(67, "/new_tiles/Dungeon/mossy_podea", false);
        setup(126, "/new_tiles/Dungeon/PD1", true);
        setup(127, "/new_tiles/Dungeon/PD10", true);
        setup(128, "/new_tiles/Dungeon/PD11", true);
        setup(129, "/new_tiles/Dungeon/PD12", true);
        setup(130, "/new_tiles/Dungeon/PD13", true);
        setup(131, "/new_tiles/Dungeon/PD14", true);
        setup(132, "/new_tiles/Dungeon/PD15", true);
        setup(133, "/new_tiles/Dungeon/PD16", true);
        setup(134, "/new_tiles/Dungeon/PD17", true);
        setup(135, "/new_tiles/Dungeon/PD18", true);
        setup(136, "/new_tiles/Dungeon/PD19", true);
        setup(137, "/new_tiles/Dungeon/PD2", true);
        setup(138, "/new_tiles/Dungeon/PD20", true);
        setup(139, "/new_tiles/Dungeon/PD21", true);
        setup(140, "/new_tiles/Dungeon/PD3", true);
        setup(141, "/new_tiles/Dungeon/PD4", true);
        setup(142, "/new_tiles/Dungeon/PD5", true);
        setup(143, "/new_tiles/Dungeon/PD6", true);
        setup(144, "/new_tiles/Dungeon/PD67", true);
        setup(145, "/new_tiles/Dungeon/PD68", true);
        setup(146, "/new_tiles/Dungeon/PD69", true);
        setup(147, "/new_tiles/Dungeon/PD7", true);
        setup(148, "/new_tiles/Dungeon/PD8", true);
        setup(149, "/new_tiles/Dungeon/PD9", true);

        setup(150, "/new_tiles/perete_casa", true);

        setup(156, "/new_tiles/podea", false);
        setup(157, "/new_tiles/podea_casa", false);
        setup(165, "/Water/New_Water/water_1", true);
        setup(166, "/Water/New_Water/water_2", true);
        setup(167, "/Water/New_Water/water_3", true);

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
        spriteCounterAnim++;
        if (spriteCounterAnim > 40) {
            if (spriteNumAnim == 1) {
                spriteNumAnim = 2;
            } else if (spriteNumAnim == 2) {
                spriteNumAnim = 3;
            } else if (spriteNumAnim == 3) {
                spriteNumAnim = 1;
            }
            spriteCounterAnim = 0;
        }

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            if (tileNum == 165 || tileNum == 166 || tileNum == 167) {
                if(spriteNumAnim == 1) {
                    tileNum = 165;
                } else if(spriteNumAnim == 2) {
                    tileNum = 166;
                } else if(spriteNumAnim == 3) {
                    tileNum = 167;
                }
            }

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
