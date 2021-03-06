package model.operation;

/**
 * implement Direction then refactor the getRowChange and getColChange.
 */
public enum PawnDirection implements Direction {

    /**
     * up.
     */
    UP(-1, 0),

    /**
     * right.
     */
    RIGHT(0, 1),

    /**
     * down.
     */
    DOWN(1, 0),

    /**
     * left.
     */
    LEFT(0, -1);

    private final int rowChange;
    private final int colChange;

    PawnDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     *
     * Get the return value of the row.
     * @return Return the number of rows
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     *
     * Get the return value of the col.
     * @return Return the number of cols
     */
    public int getColChange() {
        return colChange;
    }

    /**
     * Direction find if its exist.
     * @param rowChange change of row
     * @param colChange change of col
     * @return {@code direction} if its true ,or throw IllegalArgumentException
     */
    public static PawnDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }


}
