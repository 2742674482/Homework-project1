

import pojo.Position;
import pojo.operation.PawnDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    Position position;


    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.getRow()),
                () -> assertEquals(expectedCol, position.getCol())
        );
    }

    @BeforeEach
    void init() {
        position = new Position(0, 0);
    }

    @Test
    void getTarget() {
        assertPosition(-1, 0, position.moveTo(PawnDirection.UP));
        assertPosition(0, 1, position.moveTo(PawnDirection.RIGHT));
        assertPosition(1, 0, position.moveTo(PawnDirection.DOWN));
        assertPosition(0, -1, position.moveTo(PawnDirection.LEFT));
    }


    @Test
    void getPawnUP() {assertPosition(-1, 0, position.moveTo(PawnDirection.UP));}

    @Test
    void getPawnRIGHT() {  assertPosition(0, 1, position.moveTo(PawnDirection.RIGHT));}

    @Test
    void getPawnDOWN() {  assertPosition(1, 0, position.moveTo(PawnDirection.DOWN));}

    @Test
    void getPawnLEFT() {    assertPosition(0, -1, position.moveTo(PawnDirection.LEFT));}

    @Test
    void testEquals() {
        assertTrue(position.equals(position));
        assertTrue(position.equals(new Position(position.getRow(), position.getCol())));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, position.getCol())));
        assertFalse(position.equals(new Position(position.getRow(), Integer.MAX_VALUE)));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        assertFalse(position.equals(null));
        assertFalse(position.equals("Some string"));
    }

    @Test
    void testHashCode() {
        assertTrue(position.hashCode() == position.hashCode());
        assertTrue(position.hashCode() == new Position(position.getRow(), position.getCol()).hashCode());
    }


    @Test
    void testToString() { assertEquals("(0,0)", position.toString()); }
}
