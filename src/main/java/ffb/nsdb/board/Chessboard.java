package ffb.nsdb.board;

public class Chessboard {

  // Various bitboards for different pieces
  private long[][] pieces;
  private long emptySquares;
  private long occupiedSquares;
  private long enPassantTarget;

  private byte castleAvailability;
  private byte halfMoveClock;
  private byte turnNumber;
  private byte activeSide;

  public Chessboard() {}

  public Chessboard(String fen) {}
}
