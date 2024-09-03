package Main;

import javax.swing.*;

public class Main {

    public static JFrame window;

    //public UI ui = new UI(gamePanel);

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Cat's Valley");
        window.setUndecorated(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        //gamePanel.loadCurrentMap(0);
        gamePanel.startGameThread();

    }
}
