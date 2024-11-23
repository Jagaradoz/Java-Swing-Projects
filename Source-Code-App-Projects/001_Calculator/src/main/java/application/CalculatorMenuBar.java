package application;

import config.StyleConfig;
import util.StyleUtils;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CalculatorMenuBar extends JMenuBar {
    public static boolean isDarkMode = false;

    private final JFrame mainFrame;
    private final CalculatorPanel mainPanel;

    private final JPanel buttonsPanel;
    private final JTextField displayBar;
    private final JTextField displayPlaceHolderBar;

    private final Font mainFont;
    private final Font font14px;

    public CalculatorMenuBar(JFrame mainFrame, CalculatorPanel mainPanel) {
        // SET FRAME (MainFrame)
        // SET PANEL (CalculatorPanel)
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;

        // SET BUTTONS PANEL
        // SET DISPLAY BAR
        // SET DISPLAY PLACEHOLDER BAR
        this.buttonsPanel = mainPanel.getButtonsPanel();
        this.displayBar = mainPanel.getDisplayBar();
        this.displayPlaceHolderBar = mainPanel.getDisplayPlaceHolderBar();

        // LOAD FONT
        mainFont = StyleUtils.getFont();
        font14px = mainFont.deriveFont(14f);

        // SET BACKGROUND / BORDER
        setBackground(StyleConfig.LIGHT_BG);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        // ADD COMPONENTS
        add(getViewJMenu());
        add(getHelpJMenu());
    }

    public void switchTheme() {
        isDarkMode = !isDarkMode;

        // CHANGE MAIN PANEL BACKGROUND COLOR
        mainPanel.setBackground(isDarkMode ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);

        // CHANGE DISPLAY BAR AsND DISPLAY PLACEHOLDER BAR BACKGROUND COLOR
        displayBar.setBackground(isDarkMode ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
        displayBar.setForeground(isDarkMode ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
        displayPlaceHolderBar.setBackground(isDarkMode ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
        displayPlaceHolderBar.setForeground(isDarkMode ? StyleConfig.DARK_PLACEHOLDER : StyleConfig.LIGHT_PLACEHOLDER);

        // CHANGE BUTTON BACKGROUND COLOR
        buttonsPanel.setBackground(isDarkMode ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
        for (int i = 0; i < buttonsPanel.getComponents().length; i++) {
            if (buttonsPanel.getComponent(i) instanceof JButton button) {
                if ((i + 1) % 4 == 0 || i < 4) {
                    button.setBackground(isDarkMode ? StyleConfig.DARK_BUTTON_BG_2 : StyleConfig.LIGHT_BUTTON_BG_2);
                } else {
                    button.setBackground(isDarkMode ? StyleConfig.DARK_BUTTON_BG_1 : StyleConfig.LIGHT_BUTTON_BG_1);
                }

                button.setForeground(isDarkMode ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
            }
        }

        // CHANGE MENU BAR BACKGROUND COLOR
        JMenuBar menuBar = mainFrame.getJMenuBar();
        menuBar.setBackground(isDarkMode ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            menu.setForeground(isDarkMode ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null) {
                    item.setBackground(isDarkMode ? StyleConfig.DARK_BG : StyleConfig.LIGHT_BG);
                    item.setForeground(isDarkMode ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
                }
            }
        }


        repaint();
    }

    public JMenu getViewJMenu() {
        JMenu menu = new JMenu("View");
        menu.setFont(mainFont);
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // IMAGE ICONS
        URL lightModeUrl = getClass().getResource("/images/light-mode.png");
        URL nightModeUrl = getClass().getResource("/images/night-mode.png");

        ImageIcon lightIcon = null;
        ImageIcon darkIcon = null;

        if (lightModeUrl != null && nightModeUrl != null) {
            lightIcon = new ImageIcon(lightModeUrl);
            darkIcon = new ImageIcon(nightModeUrl);

            lightIcon = resizeIcon(lightIcon);
            darkIcon = resizeIcon(darkIcon);
        }

        JMenuItem whiteTheme = new JMenuItem("White Theme", lightIcon);
        whiteTheme.setFont(font14px);
        whiteTheme.setCursor(new Cursor(Cursor.HAND_CURSOR));
        whiteTheme.setPreferredSize(new Dimension(175, 25));
        whiteTheme.addActionListener(_ -> {
            if (isDarkMode) {
                switchTheme();
            }
        });

        JMenuItem darkTheme = new JMenuItem("Dark Theme", darkIcon);
        darkTheme.setFont(font14px);
        darkTheme.setCursor(new Cursor(Cursor.HAND_CURSOR));
        darkTheme.setPreferredSize(new Dimension(175, 25));
        darkTheme.addActionListener(_ -> {
            if (!isDarkMode) {
                switchTheme();
            }
        });


        menu.add(whiteTheme);
        menu.addSeparator();
        menu.add(darkTheme);

        return menu;
    }

    public JMenu getHelpJMenu() {
        JMenu menu = new JMenu("Help");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.setFont(mainFont);

        JMenuItem github = new JMenuItem("Git Hub");
        github.setFont(font14px);
        github.setCursor(new Cursor(Cursor.HAND_CURSOR));
        github.setPreferredSize(new Dimension(175, 25));
        github.addActionListener(_ -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/Jagaradoz/Java-Projects"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.add(github);

        return menu;
    }

    public ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
