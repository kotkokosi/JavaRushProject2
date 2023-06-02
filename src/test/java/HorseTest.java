import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void testNameInConstructorNameWithNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", " "})
    public void testNameInConstructorWithWhitespace(String whitespaceCharacters) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(whitespaceCharacters, 1.0, 1.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testSpeedInConstructorWithMinusNumber() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new Horse("Test", -1.0, 1.0);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testDistanceInConstructorWithMinusNumber() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new Horse("Test", 1.0, -1.0);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetName() {
        Horse horse = new Horse("Test", 1.0);
        assertEquals("Test", horse.getName());
    }

    @Test
    public void testGetSpeed() {
        Horse horse = new Horse("Test", 1.0);
        assertEquals(1.0, horse.getSpeed());
    }

    @Test
    public void testGetDistance() {
        Horse horse = new Horse("Test", 1.0, 1.0);
        assertEquals(1.0, horse.getDistance());
    }

    @Test
    public void testGetDistanceWithTwoParameterConstructor() {
        Horse horse = new Horse("Test", 1.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void testMethodCallsGetRandomDoubleWithParameters() {
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            Horse horse1 = new Horse("test", 0.1);
            horse1.move();
            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"10.0, 10.0", "20.0, 20.0", "30.0, 30.0"})
    public void testFormulaInMethod(double distance, double speed) {
        try(MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)){
            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse1 = new Horse("Test", speed, distance);
            horse1.move();

            assertEquals(distance + speed * 0.5, horse1.getDistance());
        }
    }
}

