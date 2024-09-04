package Main;

import Entity.Entity;
import Entity.Player;
import Tiles.TileManager;
import TratareExceptii.CustomException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static Main.Main.window;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 32; //32x32 tile
    int scale = 3;

    public final int tileSize = originalTileSize * scale; //96x96 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  //1920 pixels
    public final int screenHeight = tileSize * maxScreenRow; //1152 pixels

    //world settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int maxMap = 10;
    public int currentMap = 0;

    //FOR full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fulScreenOn = false;

    //FPS
    int FPS = 60;

    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //entity and object

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    //Singleton
    public Player player = Player.getInstance(this, keyH, mouseH);

    public Entity obj[][] = new Entity[maxMap][300];

    public Entity grass[][] = new Entity[maxMap][800];
    public Entity npc[][] = new Entity[maxMap][50];
    public Entity monster[][] = new Entity[maxMap][50];
    public ArrayList<Entity> projectiles = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    private boolean once[] = new boolean[10];

    //GAMESTATE
    public int gameState;
    public final int titleScreen = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int nextDialogueState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int inventoryState = 7;
    public final int youWinState = 8;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        projectiles = new ArrayList<>();
    }

    public void setupGame() {
        //gameState = loadingScreen;
        //ui.draw(g2);

//        aSetter.setGrass();
//        aSetter.setObject();
//        aSetter.setNPC();
//        aSetter.setMonster();
        loadCurrentMap(0);

        playMusic(0);
        music.fc.setValue(-20.0f);
        gameState = titleScreen;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

    }
    public void setFullScreen() {
        //get local screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(window);

        //get full screen width and height
        screenWidth2 = window.getWidth();;
        screenHeight2 = window.getHeight();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void loadCurrentMap(int index){
        if(index == 0) {
            if(!once[index]) {
                //showLoadingScreen(g2);
                aSetter.setObject(index);
                aSetter.setGrass();
                aSetter.setNPC(index);
                aSetter.setMonster();
                once[index] = true;
            }
            //casa dreapta
        } else if(index == 5) {
            if(!once[index]) {
                showLoadingScreen(g2);
                aSetter.setObject(index);
                aSetter.setNPC(index);
                once[index] = true;
            }
            //casa mare
        } else if(index == 4) {
            if(!once[index]) {
                showLoadingScreen(g2);
                aSetter.setObject(index);
                aSetter.setNPC(index);
                once[index] = true;
            }
            //casa stanga
        } else if(index == 3) {
            if(!once[index]) {
                showLoadingScreen(g2);
                aSetter.setObject(index);
                aSetter.setNPC(index);
                once[index] = true;
            };
        }

    }

    public void showLoadingScreen(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Loading...";
        int x = (getWidth() - g2.getFontMetrics().stringWidth(text)) / 2;
        int y = getHeight() / 2;
        g2.drawString(text, x, y);
        repaint();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 1 miliard nanoseconds/60  0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (timer > drawInterval * 10) {
                timer = (long) drawInterval * 10;
            }

            //UPDATE: update info such as character position
            while (timer >= drawInterval) {
                System.out.println("update");

                update();
                timer -= drawInterval;
            }

            //DRAW: draw the screen with the updated information
            drawToTempScreen();
            drawToScreen();

            drawCount++;

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {

        if(gameState == playState) {
            player.update();
            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for(int i = 0; i < monster.length; i++) {
                if(monster[currentMap][i] != null) {
                    monster[currentMap][i].update();
                }
            }
            for (int i = 0; i < projectiles.size(); i++) {
                System.out.println("update" + i);
                projectiles.get(i).update();
            }
        }
        if(gameState == pauseState) {

        }
        if(gameState == dialogueState) {
            player.update();
        }
        if(gameState == titleScreen) {
            if(keyH.fulscreen) setFullScreen();
        }

    }
    public void drawToTempScreen() {

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TitleScreen
        if (gameState == titleScreen) {
            ui.draw(g2);
        }

        //Others
        else {
            //tile
            tileM.draw(g2);

            //add entity to list
            entityList.add(player);

            for(int i = 0; i < npc[currentMap].length; i++) {
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i = 0; i < grass[currentMap].length; i++) {
                if(grass[currentMap][i] != null) {
                    entityList.add(grass[currentMap][i]);
                }
            }

            for(int i = 0; i < obj[currentMap].length; i++) {
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for(int i = 0; i < monster[currentMap].length; i++) {
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            //sort
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            //draw projectile
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).draw(g2);
            }

            //draw entity
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTY entity list
            for(int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

            //UI
            ui.draw(g2);

            //DEBUG
            if (keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;

                g2.setFont(new Font("Arial", Font.PLAIN, 20));
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
                g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
                g2.drawString("Coloana" + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
                g2.drawString("Rand" + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            }
        }
    }
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {

        try {
            music.setFile(i);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSF(int i) {

        try {
            se.setFile(i);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        se.play();
    }
    public void retry() {

        if(currentMap == 0) {
            player.defaultPositions();
            player.restoreLife();
        }
        else if(currentMap == 1) {
            player.worldX = tileSize * 28;
            player.worldY = tileSize * 13;
            player.restoreLife();
        } else if(currentMap == 2) {
            player.worldX = tileSize * 12;
            player.worldY = tileSize * 22;
            player.restoreLife();
        }
    }

}
