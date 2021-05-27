package pojo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import pojo.operation.Direction;

/**
 class of piece.
 */
@Data
public class Piece {
    private final PieceType type;
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    /**
     * piece init.
     * @param type
     * @param position
     */
    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }

    /**
     * piece move to.
     * @param direction
     */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    /**
     * get the position.
     * @return get the position
     */
    public Position getPosition() {
        return position.get();
    }

    /**
     * get the property of position.
     * @return {@code position}
     */
    public ObjectProperty<Position> positionProperty() {
        return position;
    }

}
