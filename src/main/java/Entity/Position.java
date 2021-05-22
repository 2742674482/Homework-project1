package Entity;

import Entity.operation.Direction;
import lombok.Data;

/**
 * class of position.
 */
@Data
public class Position {
    int row;
    int col;

    /**
     *
      * @param row
     * @param col
     */
    public Position(int row, int col){
        this.col = col;
        this.row = row;
    }

    /**
     *
     * @param direction
     * @return
     */
    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

    /**
     *
      * @return
     */
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}