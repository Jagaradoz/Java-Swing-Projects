package application;

import javax.swing.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Initialize CalculatorPanelTest")
class CalculatorPanelTest {
    private CalculatorPanel calculatorPanel;

    @BeforeEach
    public void checkIfCalculatorPanelIsNull() {
        calculatorPanel = new CalculatorPanel();
    }

    @Test
    @DisplayName("ResetNum (C) Works Properly")
    public void resetNumCValues() {
        calculatorPanel.resetNumC();

        assertEquals("0", calculatorPanel.getNum1());
        assertEquals("0", calculatorPanel.getNum2());
        assertEquals("0", calculatorPanel.getResult());
        assertEquals("", calculatorPanel.getOperator());
        assertFalse(calculatorPanel.isCalculated());

        assertNotNull(calculatorPanel.getDisplayBar());
        assertNotNull(calculatorPanel.getDisplayPlaceHolderBar());
        assertEquals("0", calculatorPanel.getDisplayBar().getText());
        assertEquals("0", calculatorPanel.getDisplayPlaceHolderBar().getText());
    }

    @Test
    @DisplayName("BackSpace Works Properly")
    public void backSpaceValues() {
        JTextField displayBar = calculatorPanel.getDisplayBar();

        displayBar.setText("123");

        calculatorPanel.backspace();
        assertEquals("12", displayBar.getText());

        calculatorPanel.backspace();
        assertEquals("1", displayBar.getText());

        calculatorPanel.backspace();
        assertEquals("0", displayBar.getText());
    }

    @Test
    @DisplayName("Display Bar Should Not Be Null")
    public void displayBarShouldNotBeNull() {
        assertNotNull(calculatorPanel.getDisplayBar());
    }

    @Test
    @DisplayName("Display PlaceHolder Bar Should Not Be Null")
    public void displayPlaceHolderBarShouldNotBeNull() {
        assertNotNull(calculatorPanel.getDisplayPlaceHolderBar());
    }

    @Nested
    @DisplayName("ResetNum (CE) Works Properly")
    class ResetNumCEValues {

        @Test
        @DisplayName("Should reset num1 when num2 is 0")
        void shouldResetNum1WhenNum2Is0() {
            JTextField displayBar = calculatorPanel.getDisplayBar();

            displayBar.setText("123");
            calculatorPanel.setNum1();

            calculatorPanel.resetNumCE();

            assertEquals("0", calculatorPanel.getNum1());
            assertEquals("0", calculatorPanel.getNum2());
        }

        @Test
        @DisplayName("Should reset num2 when num1 is not 0")
        void shouldResetNum2WhenNum1Is0() {
            JTextField displayBar = calculatorPanel.getDisplayBar();

            displayBar.setText("123");
            calculatorPanel.setNum1();

            displayBar.setText("456");
            calculatorPanel.setNum2();

            calculatorPanel.resetNumCE();

            assertEquals("123", calculatorPanel.getNum1());
            assertEquals("0", calculatorPanel.getNum2());
        }
    }

    @Nested
    @DisplayName("Calculation Works Properly")
    class CalculateValues {
        @Nested
        @DisplayName("Addition Works Properly")
        class Addition {
            @Test
            @DisplayName("15 + 30 = 45")
            public void basicAdditionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("15");
                calculatorPanel.setNum1();

                calculatorPanel.setOperator("+");

                displayBar.setText("30");
                calculatorPanel.setNum2();

                calculatorPanel.calculate();

                assertEquals("45.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("15.3 + 30.3 = 45.6")
            public void decimalAdditionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("15.3");
                calculatorPanel.setNum1();

                calculatorPanel.setOperator("+");

                displayBar.setText("30.3");
                calculatorPanel.setNum2();

                calculatorPanel.calculate();

                assertEquals("45.6", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("-25 + 50 = 25")
            public void negativeAndPositiveAdditionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("-25");
                calculatorPanel.setNum1();

                calculatorPanel.setOperator("+");

                displayBar.setText("50");
                calculatorPanel.setNum2();

                calculatorPanel.calculate();

                assertEquals("25.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("-30 + -20 = -50")
            public void negativeNumbersAdditionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("-30");
                calculatorPanel.setNum1();

                calculatorPanel.setOperator("+");

                displayBar.setText("-20");
                calculatorPanel.setNum2();

                calculatorPanel.calculate();

                assertEquals("-50.0", calculatorPanel.getResult());
            }
        }

        @Nested
        @DisplayName("Subtraction Works Properly")
        class Subtraction {
            @Test
            @DisplayName("50 - 30 = 20")
            public void basicSubtractionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("50");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("-");
                displayBar.setText("30");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("20.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("100.5 - 50.5 = 50.0")
            public void decimalSubtractionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("100.5");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("-");
                displayBar.setText("50.5");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("50.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("0 - 50 = -50")
            public void subtractFromZeroShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("0");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("-");
                displayBar.setText("50");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("-50.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("-25 - 25 = -50")
            public void subtractFromNegativeShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("-25");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("-");
                displayBar.setText("25");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("-50.0", calculatorPanel.getResult());
            }
        }

        @Nested
        @DisplayName("Multiplication Works Properly")
        class Multiplication {
            @Test
            @DisplayName("5 × 4 = 20")
            public void basicMultiplicationShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("5");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("×");
                displayBar.setText("4");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("20.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("2.5 × 3 = 7.5")
            public void decimalMultiplicationShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("2.5");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("×");
                displayBar.setText("3");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("7.5", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("-5 × 4 = -20")
            public void negativeMultiplicationShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("-5");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("×");
                displayBar.setText("4");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("-20.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("-3 × -2 = 6")
            public void negativeTimesNegativeShouldBePositive() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("-3");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("×");
                displayBar.setText("-2");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("6.0", calculatorPanel.getResult());
            }
        }

        @Nested
        @DisplayName("Division Works Properly")
        class Division {
            @Test
            @DisplayName("100 ÷ 20 = 5")
            public void basicDivisionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("100");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("÷");
                displayBar.setText("20");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("5.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("10 ÷ 4 = 2.5")
            public void decimalDivisionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("10");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("÷");
                displayBar.setText("4");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("2.5", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("-15 ÷ 3 = -5")
            public void negativeDivisionShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("-15");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("÷");
                displayBar.setText("3");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("-5.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("5 ÷ 0 = Infinity")
            public void divisionByZeroShouldReturnError() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("5");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("÷");
                displayBar.setText("0");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("Infinity", calculatorPanel.getResult());
            }
        }

        @Nested
        @DisplayName("Percentage Calculation Works Properly")
        class PercentageCalculation {
            @Test
            @DisplayName("50% of 100 = 50")
            public void basicPercentageShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("100");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("%");
                displayBar.setText("50");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("50.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("25.5% of 200 = 51")
            public void decimalPercentageShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("200");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("%");
                displayBar.setText("25.5");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("51.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("150% of 80 = 120")
            public void percentageOverHundredShouldWork() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("80");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("%");
                displayBar.setText("150");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("120.0", calculatorPanel.getResult());
            }

            @Test
            @DisplayName("0% of 100 = 0")
            public void zeroPercentageShouldReturnZero() {
                JTextField displayBar = calculatorPanel.getDisplayBar();

                displayBar.setText("100");
                calculatorPanel.setNum1();
                calculatorPanel.setOperator("%");
                displayBar.setText("0");
                calculatorPanel.setNum2();
                calculatorPanel.calculate();

                assertEquals("0.0", calculatorPanel.getResult());
            }
        }
    }

}