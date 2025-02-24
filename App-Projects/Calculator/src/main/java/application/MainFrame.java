package application;

import javax.swing.*;
import java.util.Objects;

public class MainFrame extends JFrame {
    public MainFrame() {
        CalculatorPanel mainPanel = new CalculatorPanel();
        CalculatorMenuBar menuBar = new CalculatorMenuBar(this, mainPanel);
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png")));

        add(mainPanel);

        setJMenuBar(menuBar);

        pack();
        setVisible(true);
        setIconImage(imageIcon.getImage());
        setTitle("Calculator");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}