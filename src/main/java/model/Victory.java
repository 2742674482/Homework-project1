package model;

import java.util.ArrayList;
import java.util.List;

/**
 * class of how to victory.
 */
public class Victory {
    int[][] board = new int[5][4];

    List<Position> positions = new  ArrayList<Position>();

    /**
     * Convert Checkerboard object into the corresponding two-dimensional array.
     * @param checkerboard checkerboard
     * @param color color
     * @return boolean
     */


    public boolean victory(Checkerboard checkerboard, String color){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < checkerboard.getPieceCount(); i++) {
            String colornow = String.valueOf(checkerboard.getPieceType(i));
            if (color.equals(colornow)) {
                Position position = checkerboard.getPiecePosition(i);
                positions.add(position);
                board[position.getRow()][position.getCol()] = 1;
            }
        }
        for (int i = 0; i < positions.size(); i++) {
            if (winOrLoseCheck(board,positions.get(i).getRow(),positions.get(i).getCol())==true) {
                return true;
            }
        }

        return false;
    }

    /**
     * looking up and down find the win.
     * @param board
     * @param row
     * @param col
     * @return boolean
     */
    public static boolean winCol(int[][] board, final int row, final int col) {
        int rowCount = 1;
        /* * 向上查找 * col - i >= 0 是为了防止越界的条件 * board[row][col - i] == board[row][col] 由中心向外判断棋子是否相连的条件 */
        for (int i = 1; i <= 2; i++) {
            if (col - i >= 0 && board[row][col - i] == board[row][col]) {
                rowCount++;	//若是相连，该方向上的棋子数加一
            } else {
                break;	//若是遇到第一个空位或非己方棋子退出循环
            }
        }
        //向下查找
        //col + i < BOARD_SIZE 一样为了防止越界
        for (int i = 1; i <= 2; i++) {
            if (col + i < 4 && board[row][col + i]
                    == board[row][col]) {
                rowCount++;
            } else {
                break;
            }
        }
        //若是上下两个方向上相连的棋子相加大于等于3，返回true，不然返回false
        return rowCount >= 3 ? true : false;
    }

    /**
     * looking left and right find the win.
     * @param board
     * @param row
     * @param col
     * @return boolean
     */
    public static boolean winRow(int[][] board, final int row, final int col) {
        int colCount = 1;
        //向左查找
        for (int i = 1; i <= 2; i++) {
            if (row - i >= 0 && board[row - i][col] == board[row][col]) {
                colCount++;
            } else {
                break;
            }
        }
        //向右查找
        for (int i = 1; i <= 2; i++) {
            if (row + i < 5 &&
                    board[row + i][col] == board[row][col]) {
                colCount++;
            } else {
                break;
            }
        }
        return colCount >= 3 ? true : false;
    }

    /**
     * looking up left and down right find the win.
     * @param board
     * @param row
     * @param col
     * @return boolean
     */
    public static boolean winLeftLea(int[][] board, final int row, final int col) {
        int leftLeaCount = 1;
        //向左上查找
        for (int i = 1; i <= 2; i++) {
            if (row - i >= 0 && col - i >= 0 && board[row - i][col - i]
                    == board[row][col]) {
                leftLeaCount++;
            } else {
                break;
            }
        }
        //向右下查找
        for (int i = 1; i <= 2; i++) {
            if (row + i < 5 && col + i < 4 &&
                    board[row + i][col + i] == board[row][col]) {
                leftLeaCount++;
            } else {
                break;
            }
        }
        return leftLeaCount >= 3 ? true : false;
    }

    /**
     * looking down left and up right find the win.
     * @param board
     * @param row
     * @param col
     * @return boolean
     */
    public static boolean winRightLea(int[][] board, final int row, final int col) {
        int rightLeaCount = 1;
        //向左下查找
        for (int i = 1; i <= 2; i++) {
            if (row + i < 5 && col - i >= 0 &&
                    board[row + i][col - i] == board[row][col]) {
                rightLeaCount++;
            } else {
                break;
            }
        }
        //向右上查找
        for (int i = 1; i <= 2; i++) {
            if (row - i >= 0 && col + i < 4 &&
                    board[row - i][col + i] == board[row][col]) {
                rightLeaCount++;
            } else {
                break;
            }
        }
        return rightLeaCount >= 3 ? true : false;
    }

    /**
     *find if it win in all direction.
     * @param board board
     * @param row row
     * @param col col
     * @return  boolean
     */
    public static boolean winOrLoseCheck(
            /**
             * board.
             */
            int[][] board,
            /**
             * row.
             */
            final int row,
            /**
             * col.
             */
            final int col) {
        if (winCol(board, row, col) || winRow(board, row, col)
                || winLeftLea(board, row, col) || winRightLea(board, row, col)) {
            return true;
        } else {
            return false;
        }
    }

}
