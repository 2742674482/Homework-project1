package pojo;

import lombok.Data;
import pojo.operation.Direction;

/**
 * class of position.
 */
@Data
public class Position {
    int row;
    int col;

    /**
     *Class initialization load.
     * @param row row
     * @param col col
     */
    public Position(int row, int col){
        this.col = col;
        this.row = row;
    }

    /**
     * move method.
     * @param direction direction
     * @return row and col move to
     */
    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

    /**
     * Output printing.
     * @return row and col to string
     */
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}