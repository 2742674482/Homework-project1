import Entity.operation.PawnDirection;
import Entity.Piece;
import Entity.PieceType;
import Entity.Position;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    public void test(){
        Piece piece = new Piece(PieceType.BLUE, new Position(0, 0));
        piece.positionProperty().addListener((observableValue, oldPosition, newPosition) -> {
            System.out.printf("%s -> %s\n", oldPosition.toString(), newPosition.toString());
        });
        System.out.println(piece);
        piece.moveTo(PawnDirection.DOWN_RIGHT);
        System.out.println(piece);
    }
}
