import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JunitTest {
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
    public void testConstructorNameWithNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorNameWithEmptyList() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Test" + i, 1.0));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertArrayEquals(hippodrome.getHorses().toArray(), horses.toArray());
    }

    @Test
    public void testGetWinner() {
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Test", 0.1, 0.1));
        horses.add(new Horse("Test1", 0.1, 1.2));
        horses.add(new Horse("Test2", 0.1, 0.5));
        horses.add(new Horse("Test3", 0.1, 0.2));
        assertEquals(1.2, new Hippodrome(horses).getWinner().getDistance());
    }

    @Disabled
    @Test
    @Timeout(22)
    public void testMainExecutionTime() throws Exception{
        Main.main(new String[]{});
    }
}
