package model;

import lombok.Data;
import model.operation.Direction;

/**
 * let the row and col of piece coalesce to a position class.
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
     * let the current row and col to calculate with PawnDirection.
     * @param direction direction
     * @return Position
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