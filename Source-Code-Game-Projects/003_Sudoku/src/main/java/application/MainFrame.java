package application;

import config.LayoutConfig;

import javax.swing.*;
import java.util.Objects;

public class MainFrame extends JFrame {

    public MainFrame() {
        add(new SudokuPanel());

        setVisible(true);
        setTitle("Sudoku");
        setResizable(false);
        setSize(LayoutConfig.SCREEN_WIDTH, LayoutConfig.SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png"))).getImage());
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
