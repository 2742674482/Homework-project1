package Entity;

import lombok.Data;

/*
/格子，用于实现棋盘
 */
@Data
public class Lattice {
    int row;//行
    int column;//列
    int status;//状态，用于判断
    Piece piece;//用于装棋子类
}
