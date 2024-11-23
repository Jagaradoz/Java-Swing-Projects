package application;

import config.LayoutConfig;

import javax.swing.*;
import java.util.*;

public class MainJFrame extends JFrame {
    public MainJFrame() {
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(MainJFrame.class.getResource("/images/icon.png")));
        add(new HangmanJPanel());

        setVisible(true);
        setTitle("Hangman");
        setResizable(false);
        setSize(LayoutConfig.SCREEN_WIDTH, LayoutConfig.SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(imageIcon.getImage());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainJFrame::new);
    }

}

// This project inspired by https://www.youtube.com/watch?v=HruO0JPU0_0&t=952s
// There are some images from TapTap YouTube channel used for this project