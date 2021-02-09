package ffb.nsdb.moves;

public class Move {
  private static final int SQUARE_BITS = 63;
  private static final int PIECE_BITS = 7;
  private static final int FLAG_BITS = 7;

  private int move;

  public Move(int from, int to, int piece, int flags) {
    move = 0;
    move |= (piece & PIECE_BITS);
    move |= ((from & SQUARE_BITS) << 9);
    move |= ((to & SQUARE_BITS) << 15);
    move |= ((flags & FLAG_BITS) << 21);
  }

  public int getMove() {
    return move;
  }

  /**
   * Returns the square the piece is moving from
   * @return the integer representing the square number from the chess board
   */
  public int getFromSquare() {
    return ((move >>> 9) & SQUARE_BITS);
  }

  /**
   * Returns the destination square of the move
   * @return the integer representing the square number from chess board
   */
  public int getToSquare() {
    return ((move >>> 15) & SQUARE_BITS);
  }

  /**
   * Returns the type of piece being moved
   * @return the integer representing the type of piece being moved
   */
  public int getPieceType() {
    return (move & PIECE_BITS);
  }

  /**
   * Returns the type of piece being captured
   * @return the integer representing the type of piece being captured
   */
  public int getCapturedPiece() {
    return ((move >>> 3) & PIECE_BITS);
  }

  /**
   * Returns the piece the pawn will be promoted to
   * @return tne integer representing the piece the pawn will be promoted to
   */
  public int getPromotionPiece() {
    return ((move >>> 6) & PIECE_BITS);
  }

  /**
   * Returns the flag that marks the type of the move
   * @return an integer that represents the type of move
   */
  public int getFlags() {
    return ((move >>> 21) & FLAG_BITS);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Move)) {
      return false;
    }

    Move m = (Move) other;

    return (move & 0xffff) == (m.getMove() & 0xffff);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
