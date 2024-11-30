package application;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// JUNIT TEST (Class)
// @DisplayName             -> Specifies a custom name for class , method
// @TestInstance            -> Specifies the lifecycle of the test class (PER_CLASS,PER_METHOD)
//                          -> PER_CLASS (create an instance once only)
//                          -> PER_METHOD (create an instance everytime before every test method)
@DisplayName("Initialize MainTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    // JUNIT TEST (Method)
    // @Test                -> used for test method
    // @Nested              -> groups test methods in one class
    // @BeforeAll           -> runs once before processing every test
    // @BeforeEach          -> runs before processing any test method
    // @AfterEach           -> runs after processing any test method
    // @AfterAll            -> runs once after processing every test


    private Main main;

    @BeforeAll
    public void start() {
        System.out.println("Start testing!");
    }

    @BeforeEach
    public void setUp() {
        main = new Main();
    }

    @Test
    @DisplayName("Should get (Hello, World!)")
    public void shouldGetString() {
        assertEquals("Hello, World!", main.getString());
    }

    @Nested
    @DisplayName("TestCalculation Class")
    class TestCalculation {
        @Test
        @DisplayName("Should add properly")
        public void testAddition() {
            int result = main.add(15, 20);
            assertEquals(35, result, "15 + 20 should equal 35");
        }

        @Test
        @DisplayName("Should subtract properly")
        public void testSubtraction() {
            int result = main.subtract(30, 10);
            assertEquals(20, result, "30 - 10 should equal 20");
        }

        @Test
        @DisplayName("Should multiply properly")
        public void testMultiplication() {
            int result = main.multiply(5, 6);
            assertEquals(30, result, "5 * 6 should equal 30");
        }

        @Test
        @DisplayName("Should divide properly")
        public void testDivision() {
            int result = main.divide(20, 5);
            assertEquals(4, result, "20 / 5 should equal 4");
        }

        @Test
        @DisplayName("Should throw ArithmeticException when dividing by zero")
        public void testDivisionByZero() {
            assertThrows(ArithmeticException.class, () -> main.divide(20, 0), "Division by zero should throw ArithmeticException");
        }
    }

    @AfterEach
    public void setNull() {
        main = null;
    }

    @AfterAll
    public void finish() {
        System.out.println("Finished testing!");
    }
}