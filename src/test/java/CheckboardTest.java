import model.Checkerboard;
import model.Piece;
import model.PieceType;
import model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboardTest {
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
}
