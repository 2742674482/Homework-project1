package Entity;

import Entity.operation.PawnDirection;
import javafx.beans.property.ObjectProperty;

import java.util.*;

/**
 *Chessboard generation class.
 */
public class Checkerboard {


    private final Piece[] pieces;

    /**
     *Chessboard initialization.
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
     *Chessboard initialization.
     * @param pieces
     */
    public Checkerboard(Piece... pieces) {
        checkPieces(pieces);
        this.pieces = pieces.clone();
    }

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
     *Get the number of pieces.
     * @return number of pieces
     */
    public int getPieceCount() {
        return pieces.length;
    }

    /**
     *number of PieceType.
     * @param pieceNumber number of pieceNumber
     * @return number of pieceNumber
     */
    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    /**
     *number of Position.
     * @param pieceNumber number of pieceNumber
     * @return number of pieceNumber
     */
    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    /**
     *Get operation object.
     * @param pieceNumber
     * @return Operation object
     */
    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    /**
     *Chess pieces and direction control.
     * @param pieceNumber piece Number
     * @param direction Direction of movement
     * @return Can it move
     */
    public boolean isValidMove(int pieceNumber, PawnDirection direction) {
        if (pieceNumber < 0 || pieceNumber >= pieces.length) {
            throw new IllegalArgumentException();
        }
        Position newPosition = pieces[pieceNumber].getPosition().moveTo(direction);
        if (! isOnBoard(newPosition)) {
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
     *Get mobile data.
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
     *How to move.
     * @param pieceNumber piece Number
     * @param direction direction
     */
    public void move(int pieceNumber, PawnDirection direction) {
        pieces[pieceNumber].moveTo(direction);
    }

    /**
     *Direction movement judgment.
     * @param position
     * @return result
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.getRow() && position.getRow() < 5
                && 0 <= position.getCol() && position.getCol() < 4;
    }

    /**
     *Get mobile collection.
     * @return Get mobile collection return
     */
    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.getPosition());
        }
        return positions;
    }

    /**
     *Get all the directions you can move.
     * @param position Direction object
     * @return data
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
