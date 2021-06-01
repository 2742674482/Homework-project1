import model.operation.PawnDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnDirectionTest {
    @Test
    void of() {

        assertSame(PawnDirection.UP, PawnDirection.of(-1, 0));

        assertSame(PawnDirection.RIGHT, PawnDirection.of(0, 1));

        assertSame(PawnDirection.DOWN, PawnDirection.of(1, 0));

        assertSame(PawnDirection.LEFT, PawnDirection.of(0, -1));
    }

    @Test
    void of_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> PawnDirection.of(0, 0));
    }
}
