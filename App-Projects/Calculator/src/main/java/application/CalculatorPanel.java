package application;

import config.LayoutConfig;
import config.StyleConfig;
import util.StyleUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPanel extends JPanel {
    private final JPanel buttonsPanel;
    private final JTextField displayBar;
    private final JTextField displayPlaceHolderBar;
    private final String[] characters = {
            "C", "CE", "←", "%",
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
    };

    private String num1;
    private String num2;
    private String result;
    private String operator;
    private boolean calculated;

    private final Font font24px;
    private final Font font30px;

    public CalculatorPanel() {
        // Reset values.
        resetNumC();

        // Load font.
        Font mainFont = StyleUtils.getFont();
        font24px = mainFont.deriveFont(24f);
        font30px = mainFont.deriveFont(30f);

        // Assign components.
        displayPlaceHolderBar = generateDisplayPlaceHolderBar();
        displayBar = generateDisplayBar();
        buttonsPanel = generateButtonsPanel();

        setBackground(StyleConfig.LIGHT_BG);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add child components.
        setLayout(new BorderLayout(0, 0));
        add(displayPlaceHolderBar, BorderLayout.NORTH);
        add(displayBar, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void resetNumCE() {
        // If num2 is 0, reset num1; otherwise, reset num2.
        if (num2.equals("0")) {
            num1 = "0";
            displayBar.setText(num1);
        } else {
            num2 = "0";
            displayBar.setText(num2);
        }
    }

    public void resetNumC() {
        num1 = "0";
        num2 = "0";
        result = "0";
        operator = "";
        calculated = false;

        // If the component is assigned, reset it.
        if (displayBar != null && displayPlaceHolderBar != null) {
            displayBar.setText("0");
            displayPlaceHolderBar.setText("0");
        }
    }

    public void calculate() {
        Double number1DoubleValue = Double.parseDouble(num1);
        Double number2DoubleValue = Double.parseDouble(num2);

        // Evaluate the numbers.
        switch (operator) {
            case "+":
                result = Double.toString(number1DoubleValue + number2DoubleValue);
                break;
            case "-":
                result = Double.toString(number1DoubleValue - number2DoubleValue);
                break;
            case "×":
                result = Double.toString(number1DoubleValue * number2DoubleValue);
                break;
            case "÷":
                result = Double.toString(number1DoubleValue / number2DoubleValue);
                break;
            case "%":
                result = Double.toString((number1DoubleValue / 100) * number2DoubleValue);
                break;
        }
    }

    public void setNum2() {
        num2 = displayBar.getText();
    }

    public void setOperator(String text) {
        operator = text;
        calculated = false;
        displayBar.setText("0");
    }

    public void backspace() {
        String displayBarText = displayBar.getText();

        // Delete the last digit from display bar.
        if (displayBarText.length() <= 1) {
            displayBar.setText("0");
        } else {
            displayBar.setText(displayBarText.substring(0, displayBarText.length() - 1));
        }
    }

    public void numberInput(String text, String displayBarText) {
        // If starting with 0, change it; otherwise append it.
        if (displayBarText.equals("0")) {
            displayBar.setText(text);
        } else {
            displayBar.setText(displayBarText + text);
        }
    }

    public void setNum1() {
        // Only input num1 once.
        if (num1.equals("0")) {
            num1 = displayBar.getText();
        }

        // Input placeholder bar whether calculated or not.
        if (calculated) {
            result = getRemovedZeroDecimal(result);
            num1 = result;

        }
    }

    public String getRemovedZeroDecimal(String number) {
        // Remove if there XX.000...
        if (number.contains(".")) {
            if (number.substring(number.indexOf(".") + 1).equals("0")) {
                return number.substring(0, number.indexOf("."));
            }
        }

        return number;
    }

    public JTextField generateDisplayBar() {
        JTextField textField = new JTextField("0");
        textField.setEditable(false);
        textField.setBackground(StyleConfig.LIGHT_BG);
        textField.setHorizontalAlignment(JLabel.RIGHT);
        textField.setCursor(null);
        textField.setFocusable(false);
        textField.setCaretPosition(0);
        textField.setHighlighter(null);
        textField.setPreferredSize(new Dimension(LayoutConfig.SCREEN_WIDTH, 50));
        textField.setFont(font30px);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));

        return textField;
    }

    public JTextField generateDisplayPlaceHolderBar() {
        JTextField textField = new JTextField("0");
        textField.setForeground(Color.GRAY);
        textField.setBackground(StyleConfig.LIGHT_BG);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JLabel.RIGHT);
        textField.setCursor(null);
        textField.setFocusable(false);
        textField.setCaretPosition(0);
        textField.setHighlighter(null);
        textField.setPreferredSize(new Dimension(LayoutConfig.SCREEN_WIDTH, 25));
        textField.setFont(font30px);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));

        return textField;
    }

    public JPanel generateButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(StyleConfig.LIGHT_BG);
        panel.setPreferredSize(new Dimension(LayoutConfig.SCREEN_WIDTH, 325));
        panel.setLayout(new GridLayout(5, 4, 3, 3));

        for (int i = 0; i < characters.length; i++) {
            JButton button = new ButtonComponent(i);

            panel.add(button);
            button.addActionListener(new ButtonListener());
        }

        return panel;
    }

    public class ButtonComponent extends JButton {
        public ButtonComponent(int index) {
            super(characters[index]);

            setBorder(null);
            setFont(font24px);
            setFocusable(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            if ((index + 1) % 4 == 0 || index < 4) {
                setBackground(CalculatorMenuBar.isDarkMode ? StyleConfig.DARK_BUTTON_BG_2 : StyleConfig.LIGHT_BUTTON_BG_2);
            } else {
                setBackground(CalculatorMenuBar.isDarkMode ? StyleConfig.DARK_BUTTON_BG_1 : StyleConfig.LIGHT_BUTTON_BG_1);
            }
        }
    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Text from button.
            // Text from display bar.
            String text = e.getActionCommand();
            String displayBarText = displayBar.getText();

            try {
                if (text.charAt(0) >= '0' && text.charAt(0) <= '9') {
                    // If calcultion is complete, clear the input.
                    if (calculated) {
                        resetNumC();
                        numberInput(text, "");
                    } else {
                        numberInput(text, displayBarText);
                        calculated = false;
                    }
                } else if (text.equals(".") && !displayBarText.contains(".")) {
                    // Input decimal point.
                    displayBar.setText(displayBarText + text);
                    calculated = false;

                } else if (text.equals("+") || text.equals("-") || text.equals("×") || text.equals("÷") || text.equals("%")) {
                    // Set num1.
                    setNum1();
                    displayPlaceHolderBar.setText(num1 + " " + text);

                    // Reset after input operator.
                    setOperator(text);

                } else if (text.equals("=")) {
                    // Set num2.

                    // If calculation is complete, set num1, otherwise set num2.
                    if (calculated) {
                        setNum1();
                        displayPlaceHolderBar.setText(num1 + " " + text);
                    } else {
                        setNum2();
                    }

                    // Caclulate.
                    calculate();

                    // Display the result.
                    calculated = true;
                    displayBar.setText(getRemovedZeroDecimal(result));
                    displayPlaceHolderBar.setText(String.format("%s %s %s =", num1, operator, num2));

                } else if (text.equals("←")) {
                    // Input backspace.
                    backspace();

                } else if (text.equals("C")) {
                    // Clear calculator.
                    resetNumC();

                } else if (text.equals("CE")) {
                    // Reset num1 or num2.
                    resetNumCE();
                }
            } catch (NumberFormatException _) {
                System.out.println("Can't format the number!");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

        }
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }

    public String getResult() {
        return result;
    }

    public String getOperator() {
        return operator;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public JTextField getDisplayPlaceHolderBar() {
        return displayPlaceHolderBar;
    }

    public JTextField getDisplayBar() {
        return displayBar;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }
}
