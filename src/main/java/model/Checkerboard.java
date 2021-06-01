package model;


import javafx.beans.property.ObjectProperty;
import model.operation.PawnDirection;

import java.util.*;

/**
 * generation a model to control all data in this game.
 */
public class Checkerboard {


    private final Piece[] pieces;
    //状态控制
    private int status = 0;

    /**
     *initialization all pieces data.
     */
    public Checkerboard() {
        this(new Piece( PieceType.RED, new Position(0, 0)),
                new Piece( PieceType.BLUE, new Position(0, 1)),
                new Piece( PieceType.RED, new Position(0, 2)),
                new Piece( PieceType.BLUE, new Position(0, 3)),
                new Piece( PieceType.RED, new Position(4, 0)),
                new Piece( PieceType.BLUE, new Position(4, 1)),
                new Piece( PieceType.RED, new Position(4, 2)),
                new Piece( PieceType.BLUE, new Position(4, 3)));
    }

    /**
     * Chessboard initialization.
     * @param pieces
     */
    public Checkerboard(Piece... pieces) {
        checkPieces(pieces);
        this.pieces = pieces;
    }

    /**
     * find if the position of pieces over the board.If it did ,throw IllegalArgumentException.
     * @param pieces
     */
    private void checkPieces(Piece[] pieces) {
        var seen = new HashSet<Position>();
        for (var piece : pieces) {
            if (! isOnBoard(piece.getPosition()) || seen.contains(piece.getPosition())) {
                throw new IllegalArgumentException();
            }
            seen.add(piece.getPosition());
        }
    }

    /**
     * Get the number of pieces.
     * @return {@code pieces.length}
     */
    public int getPieceCount() {
        return pieces.length;
    }

    /**
     * Get the color of the pieces.
     * @param pieceNumber number of pieceNumber
     * @return the type of piece
     */
    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    /**
     * Get the position of the pieces.
     * @param pieceNumber number of pieceNumber
     * @return the position o piece
     */
    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    /**
     * Get operation object.
     * @param pieceNumber num of piece
     * @return Operation object
     */
    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    /**
     * Judge whether the movement of chess pieces is effective.
     * @param pieceNumber piece Number
     * @param direction Direction of movement
     * @return boolean
     */
    public boolean isValidMove(int pieceNumber, PawnDirection direction) {
        if (pieceNumber < 0 || pieceNumber >= pieces.length) {
            throw new IllegalArgumentException();
        }
        Position newPosition = pieces[pieceNumber].getPosition().moveTo(direction);
        if (! isOnBoard(newPosition)) {
            return false;
        }
        String color = String.valueOf(pieces[pieceNumber].getType());
        if ((status == 1 && "RED".equals(color))||(status == 0 && "BLUE".equals(color))||status == 2) {
            return false;
        }
        for (var piece : pieces) {
            if (piece.getPosition().equals(newPosition)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Move the pieces effectively.
     * @param pieceNumber piece Number
     * @return Can it move piece
     */
    public Set<PawnDirection> getValidMoves(int pieceNumber) {
        EnumSet<PawnDirection> validMoves = EnumSet.noneOf(PawnDirection.class);
        for (var direction : PawnDirection.values()) {
            if (isValidMove(pieceNumber, direction)) {
                validMoves.add(direction);
            }
        }
        return validMoves;
    }

    /**
     *Moving method of chess pieces.
     * @param pieceNumber piece Number
     * @param direction direction
     */
    public void move(int pieceNumber, PawnDirection direction) {
        pieces[pieceNumber].moveTo(direction);
    }

    /**
     *Set the range of chess pieces to move.
     * @param position position of piece
     * @return boolean
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.getRow() && position.getRow() < 5
                && 0 <= position.getCol() && position.getCol() < 4;
    }

    /**
     *Get moving collection.
     * @return {@code positions}
     */
    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            String color = String.valueOf(piece.getType());
            if (status == 0 && color.equals("RED")) {
                positions.add(piece.getPosition());
            }else if (status == 1 && color.equals("BLUE")) {
                positions.add(piece.getPosition());
            }

        }
        return positions;
    }

    /**
     *Get all the directions you can move.
     * @param position Direction object
     * @return int
     */
    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    /**
     * Get checkerboard the status.
     * @return int
     */
    public int getStatus() {
        return status;
    }

    /**
     *Set checkerboard the status.
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *All data are printed out.
     * @return character string
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }

}
