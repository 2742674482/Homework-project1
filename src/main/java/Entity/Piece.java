package Entity;

import Entity.operation.Direction;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;

/**
 class of piece.
 */
@Data
public class Piece {
    private final PieceType type;
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    /**
     *
     * @param type
     * @param position
     */
    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }

    /**
     *
     * @param direction
     */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    /**
     *
     * @return
     */
    public Position getPosition() {
        return position.get();
    }

    /**
     *
     * @return
     */
    public ObjectProperty<Position> positionProperty() {
        return position;
    }

}
