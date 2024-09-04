package Main;


public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row]  = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }


    }

    public void checkEvent() {

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }



        if(canTouchEvent == true) {
            //Casa stanga
            if (hit(0, 11, 52, "any") == true) {
                //gp.playSF(5);
                gp.loadCurrentMap(3);
                teleport(3, 36, 30);
                gp.player.stopMoving();

                //casa mare
            } else if (hit(0, 35, 51, "any") == true) {
                gp.playSF(5);
                gp.loadCurrentMap(4);
                teleport(4, 36, 30);
                gp.player.stopMoving();
            } else if (hit(0, 36, 51, "any") == true) {
                gp.playSF(5);
                gp.loadCurrentMap(4);
                teleport(4, 36, 30);
                gp.player.stopMoving();

                //casa dreapta
            } else if (hit(0, 76, 62, "any") == true) {
                gp.playSF(5);
                gp.loadCurrentMap(5);
                teleport(5, 36, 30);
                gp.player.stopMoving();

                //plecat casa stanga
            } else if (hit(3, 36, 31, "any") == true) {
                gp.playSF(5);
                teleport(0, 11, 52);
                gp.player.stopMoving();

                //plecat casa mare
            } else if (hit(4, 36, 31, "any") == true) {
                gp.playSF(5);
                teleport(0, 36, 51);
                gp.player.stopMoving();
            } else if (hit(4, 36, 31, "any") == true) {
                gp.playSF(5);
                teleport(0, 35, 51);
                gp.player.stopMoving();

                //plecat casa dreapta
            } else if (hit(5, 36, 31, "any") == true) {
                gp.playSF(5);
                teleport(0, 76, 62);
                gp.player.stopMoving();
            }
            else if (hit(0, 57, 19, "any") == true){
                teleport(0, 76, 62);
                gp.playSF(5);
                teleport(1, 15, 14);
            }
        }


    }
    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row])) {
                if(gp.player.move_direction.equals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }
        public void teleport(int map, int col, int row) {

        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }


}
