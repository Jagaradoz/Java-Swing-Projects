import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// normal enum
public enum TestValue {
    VALUE_1(1),
    VALUE_2(2);

    private final int number;

    TestValue(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

// enum with abstract method
enum Animal {
    DOG {
        @Override
        public String makeSound() {
            return "Bark";
        }
    },
    CAT {
        @Override
        public String makeSound() {
            return "Meow";
        }
    };

    public abstract String makeSound();
}

// implements interface (ActionListener for education purposes)
enum Car implements ActionListener {
    SEDAN {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SEDAN");
        }
    },
    SUV {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SUV");
        }
    };
}