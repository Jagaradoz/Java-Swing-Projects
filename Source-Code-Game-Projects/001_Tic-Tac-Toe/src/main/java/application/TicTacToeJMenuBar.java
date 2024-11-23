package application;

import config.StyleConfig;
import util.StyleUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TicTacToeJMenuBar extends JMenuBar {
    public static boolean DARK_MODE = false;

    private final TicTacToePanel ticTacToePanel;
    private final Font mainFont;
    private final Font font14f;

    public TicTacToeJMenuBar(TicTacToePanel ticTacToePanel) {
        this.ticTacToePanel = ticTacToePanel;

        // INITIALIZE FONT
        mainFont = StyleUtils.getFont();
        font14f = mainFont.deriveFont(14f);

        setBackground(StyleConfig.LIGHT_BG);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        // ADD MENU ITEMS
        add(generateGameJMenu());
        add(generateHelpJMenu());
    }

    public JMenu generateGameJMenu() {
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setFont(mainFont);
        gameMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // MENU ITEMS
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem whiteTheme = new JMenuItem("White Theme");
        JMenuItem darkTheme = new JMenuItem("Dark Theme");

        // SET FONT
        restart.setFont(font14f);
        whiteTheme.setFont(font14f);
        darkTheme.setFont(font14f);

        // SET CURSORS
        restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        whiteTheme.setCursor(new Cursor(Cursor.HAND_CURSOR));
        darkTheme.setCursor(new Cursor(Cursor.HAND_CURSOR));

        restart.addActionListener(_ -> ticTacToePanel.startGame());

        whiteTheme.addActionListener(_ -> {
            if (TicTacToeJMenuBar.DARK_MODE) {
                switchTheme();
            }
        });

        darkTheme.addActionListener(_ -> {
            if (!TicTacToeJMenuBar.DARK_MODE) {
                switchTheme();
            }
        });

        gameMenu.add(restart);
        gameMenu.addSeparator();
        gameMenu.add(whiteTheme);
        gameMenu.add(darkTheme);

        return gameMenu;
    }

    public JMenu generateHelpJMenu() {
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(mainFont);
        helpMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JMenuItem gitHub = new JMenuItem("GitHub");
        gitHub.setFont(font14f);
        gitHub.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gitHub.addActionListener(_ -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/Jagaradoz/Java-Projects"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        helpMenu.add(gitHub);

        return helpMenu;
    }

    private void switchTheme() {
        TicTacToeJMenuBar.DARK_MODE = !TicTacToeJMenuBar.DARK_MODE;

        // SET CONTAINER BACKGROUND
        // SET GAME LABEL BACKGROUND
        // SET GAME PANEL BACKGROUND
        ticTacToePanel.setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
        ticTacToePanel.getGameLabel().setForeground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
        ticTacToePanel.getGamePanel().setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);

        // SET BUTTONS BACKGROUND
        JButton[][] buttons = ticTacToePanel.getButtons();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getBackground() == StyleConfig.WINNER_BG) continue;

                buttons[i][j].setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BUTTON_BG_1 : StyleConfig.LIGHT_BUTTON_BG_1);
                buttons[i][j].setForeground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
            }
        }

        // SET MENU BAR BACKGROUND
        setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
        for (int i = 0; i < getMenuCount(); i++) {
            JMenu menu = getMenu(i);
            menu.setForeground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null) {
                    item.setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
                    item.setForeground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
                }
            }
        }

        // RESET COMPONENTS
        repaint();
        revalidate();
    }

}
