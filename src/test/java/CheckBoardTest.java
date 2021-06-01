import model.Checkerboard;
import model.Piece;
import model.PieceType;
import model.Position;
import model.operation.PawnDirection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckBoardTest {
    private static Checkerboard testModel;





    @Test
    void testSetInitial() {
        Checkerboard boardGame = new Checkerboard();
        assertEquals(new Checkerboard(new Piece[]{new Piece( PieceType.RED, new Position(0, 0)),
                new Piece( PieceType.BLUE, new Position(0, 1)),
                new Piece( PieceType.RED, new Position(0, 2)),
                new Piece( PieceType.BLUE, new Position(0, 3)),
                new Piece( PieceType.RED, new Position(4, 0)),
                new Piece( PieceType.BLUE, new Position(4, 1)),
                new Piece( PieceType.RED, new Position(4, 2)),
                new Piece( PieceType.BLUE, new Position(4, 3))}).toString(),boardGame.toString());
    }




    @Test
    void testToString() {
        Checkerboard checkerboard = new Checkerboard();
        assertEquals("[Piece(type=RED, position=(0,0))," +
                "Piece(type=BLUE, position=(0,1))," +
                "Piece(type=RED, position=(0,2))," +
                "Piece(type=BLUE, position=(0,3))," +
                "Piece(type=RED, position=(4,0))," +
                "Piece(type=BLUE, position=(4,1))," +
                "Piece(type=RED, position=(4,2))," +
                "Piece(type=BLUE, position=(4,3))]", checkerboard.toString());
    }

    @Test
    void getPricewCount() {
        Checkerboard model = new Checkerboard();
        assertEquals(8,model.getPieceCount());

    }

    @Test
    void canMoveUp() {
        testModel = new Checkerboard();
        assertTrue(testModel.isValidMove(4 , PawnDirection.UP));
        testModel.move(4 , PawnDirection.UP);
        assertFalse(testModel.isValidMove(5 , PawnDirection.UP));
        assertTrue(testModel.isValidMove(6 , PawnDirection.UP));
        testModel.move(6 , PawnDirection.UP);
        assertFalse(testModel.isValidMove(7 , PawnDirection.UP));

    }

    @Test
    void canMoveRight() {
        testModel = new Checkerboard();
        assertTrue(testModel.isValidMove(4 , PawnDirection.UP));
        testModel.move(4 , PawnDirection.UP);
        assertTrue(testModel.isValidMove(6 , PawnDirection.UP));
        testModel.move(6 , PawnDirection.UP);
        assertTrue(testModel.isValidMove(4 , PawnDirection.RIGHT));
        testModel.move(4 , PawnDirection.RIGHT);
        assertTrue(testModel.isValidMove(6 , PawnDirection.RIGHT));
        testModel.move(6 , PawnDirection.RIGHT);
    }

    @Test
    void canMoveDown() {
        testModel = new Checkerboard();
        assertTrue(testModel.isValidMove(0 , PawnDirection.DOWN));
        testModel.move(0 , PawnDirection.DOWN);
        assertTrue(testModel.isValidMove(2 , PawnDirection.DOWN));
        testModel.move(2 , PawnDirection.DOWN);
    }

    @Test
    void canMoveLeft() {
        testModel = new Checkerboard();
        testModel.setStatus(1);
        assertTrue(testModel.isValidMove(5 , PawnDirection.UP));
        testModel.move(5 , PawnDirection.UP);
        assertTrue(testModel.isValidMove(7 , PawnDirection.UP));
        testModel.move(7 , PawnDirection.UP);
        assertTrue(testModel.isValidMove(5 , PawnDirection.LEFT));
        testModel.move(5 , PawnDirection.LEFT);
        assertTrue(testModel.isValidMove(7 , PawnDirection.LEFT));
        testModel.move(7 , PawnDirection.LEFT);
    }

    @Test
    void canNotMove(){
        testModel = new Checkerboard();
        testModel.setStatus(2);
        assertFalse(testModel.isValidMove(4 , PawnDirection.UP));
        assertFalse(testModel.isValidMove(5 , PawnDirection.UP));
        assertFalse(testModel.isValidMove(6 , PawnDirection.UP));
        assertFalse(testModel.isValidMove(7 , PawnDirection.UP));
        assertFalse(testModel.isValidMove(4 , PawnDirection.RIGHT));
        assertFalse(testModel.isValidMove(6 , PawnDirection.RIGHT));
        assertFalse(testModel.isValidMove(0 , PawnDirection.DOWN));
        assertFalse(testModel.isValidMove(2 , PawnDirection.DOWN));
        assertFalse(testModel.isValidMove(5 , PawnDirection.UP));
        assertFalse(testModel.isValidMove(7 , PawnDirection.UP));
        assertFalse(testModel.isValidMove(5 , PawnDirection.LEFT));
        assertFalse(testModel.isValidMove(7 , PawnDirection.LEFT));
    }
}
