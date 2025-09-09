package Main;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JWindow{

    private JProgressBar progressBar;


    public LoadingScreen(){
        JLabel loadingLabel = new JLabel("Loading, please wait...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setOpaque(true);
        loadingLabel.setBackground(Color.BLACK);

        //progressBar = new JProgressBar(0, 100);
        //progressBar.setStringPainted(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(loadingLabel, BorderLayout.CENTER);
        //panel.add(progressBar, BorderLayout.SOUTH);

        add(panel);
        //add(loadingLabel);

        setSize(300, 100);
        setLocationRelativeTo(null);
    }

    public void showLoading() {
        setVisible(true);
    }

    //public void updateProgress(int value) {
       // progressBar.setValue(value);
    //}

    public void hideLoading() {
        setVisible(false);
        dispose();
    }
}
