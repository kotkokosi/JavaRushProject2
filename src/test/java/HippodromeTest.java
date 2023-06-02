import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
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

    @Test
    public void testVerifyMove(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        hippodrome.getHorses().forEach(x -> Mockito.verify(x).move());
    }
}
