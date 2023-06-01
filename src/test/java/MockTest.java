import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockTest {
    private int distance;
    private int speed;

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
