package ffb.nsdb.moves;

public class Move {
  private static final int SQUARE_BITS = 63;
  private static final int PIECE_BITS = 7;
  private static final int FLAG_BITS = 7;

  private int move;

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
