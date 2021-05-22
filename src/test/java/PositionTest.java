

import Entity.Position;
import Entity.operation.KnightDirection;
import Entity.operation.PawnDirection;
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
        assertPosition(-1, -2, position.moveTo(KnightDirection.LEFT_UP));
        assertPosition(1, -2, position.moveTo(KnightDirection.LEFT_DOWN));
        assertPosition(-2, -1, position.moveTo(KnightDirection.UP_LEFT));
        assertPosition(-2, 1, position.moveTo(KnightDirection.UP_RIGHT));
        assertPosition(-1, 2, position.moveTo(KnightDirection.RIGHT_UP));
        assertPosition(1, 2, position.moveTo(KnightDirection.RIGHT_DOWN));
        assertPosition(2, 1, position.moveTo(KnightDirection.DOWN_RIGHT));
        assertPosition(2, -1, position.moveTo(KnightDirection.DOWN_LEFT));

        assertPosition(-1, -1, position.moveTo(PawnDirection.UP_LEFT));
        assertPosition(-1, 0, position.moveTo(PawnDirection.UP));
        assertPosition(-1, 1, position.moveTo(PawnDirection.UP_RIGHT));
        assertPosition(0, 1, position.moveTo(PawnDirection.RIGHT));
        assertPosition(1, 1, position.moveTo(PawnDirection.DOWN_RIGHT));
        assertPosition(1, 0, position.moveTo(PawnDirection.DOWN));
        assertPosition(1, -1, position.moveTo(PawnDirection.DOWN_LEFT));
        assertPosition(0, -1, position.moveTo(PawnDirection.LEFT));
    }



    @Test
    void getKnightLEFT_UP() { assertPosition(-1, -2, position.moveTo(KnightDirection.LEFT_UP)); }

    @Test
    void getKnightLEFT_DOWN() { assertPosition(1, -2, position.moveTo(KnightDirection.LEFT_DOWN)); }

    @Test
    void getKnightUP_LEFT() { assertPosition(-2, -1, position.moveTo(KnightDirection.UP_LEFT)); }

    @Test
    void getKnightUP_RIGHT() { assertPosition(-2, 1, position.moveTo(KnightDirection.UP_RIGHT)); }

    @Test
    void getKnightRIGHT_UP() { assertPosition(-1, 2, position.moveTo(KnightDirection.RIGHT_UP));}

    @Test
    void getKnightRIGHT_DOWN() { assertPosition(1, 2, position.moveTo(KnightDirection.RIGHT_DOWN));}

    @Test
    void getKnightDOWN_RIGHT() { assertPosition(2, 1, position.moveTo(KnightDirection.DOWN_RIGHT));}

    @Test
    void getKnightDOWN_LEFT() {   assertPosition(2, -1, position.moveTo(KnightDirection.DOWN_LEFT));}


    @Test
    void getPawnUP_LEFT() { assertPosition(-1, -1, position.moveTo(PawnDirection.UP_LEFT));}

    @Test
    void getPawnUP() {assertPosition(-1, 0, position.moveTo(PawnDirection.UP));}

    @Test
    void getPawnUP_RIGHT() { assertPosition(-1, 1, position.moveTo(PawnDirection.UP_RIGHT)); }

    @Test
    void getPawnRIGHT() {  assertPosition(0, 1, position.moveTo(PawnDirection.RIGHT));}

    @Test
    void getPawnDOWN_RIGHT() { assertPosition(1, 1, position.moveTo(PawnDirection.DOWN_RIGHT));}

    @Test
    void getPawnDOWN() {  assertPosition(1, 0, position.moveTo(PawnDirection.DOWN));}

    @Test
    void getPawnDOWN_LEFT() {  assertPosition(1, -1, position.moveTo(PawnDirection.DOWN_LEFT));}

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
