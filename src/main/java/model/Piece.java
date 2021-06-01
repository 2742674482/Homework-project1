package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import model.operation.Direction;

/**
 * save the position and color of piece.
 */
@Data
public class Piece {
    private final PieceType type;
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    /**
     * piece init.
     * @param type piece type
     * @param position piece position
     */
    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }

    /**
     * piece move to another position.
     * @param direction piece direction
     */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    /**
     * get the position.
     * @return the position of piece
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
