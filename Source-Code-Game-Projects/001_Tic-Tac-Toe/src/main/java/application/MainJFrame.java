package application;

import config.LayoutConfig;

import javax.swing.*;
import java.util.Objects;

public class MainJFrame extends JFrame {
    public MainJFrame() {
        TicTacToePanel ticTacToePanel = new TicTacToePanel();

        add(ticTacToePanel);

        setJMenuBar(new TicTacToeJMenuBar(ticTacToePanel));

        setVisible(true);
        setResizable(false);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LayoutConfig.SCREEN_WIDTH, LayoutConfig.SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png"))).getImage());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainJFrame::new);
    }
}