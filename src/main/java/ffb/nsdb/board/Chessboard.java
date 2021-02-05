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

  public Chessboard() {
    this(Constants.START_FEN);
  }

  public Chessboard(String fen) {
    this.pieces = new long[2][7];
    setToFenString(fen);
  }

  public long getOccupiedSquares() {
    return this.occupiedSquares;
  }

  public void setToFenString(String fen) {
    clearBoard();

    String[] fenArr = fen.split(" ");

    // First, set the pieces in place
    setUpPieces(fenArr[0]);

    // Next, set the active side
    activeSide = fenArr[1].equals("w") ? Constants.WHITE : Constants.BLACK;

    // Third, handle castling availability
    setCastlingAvailability(fenArr[2]);

    // enPassant target
    enPassantTarget = fenArr[3].equals("-") ? Constants.NO_SQUARE : squareFromFen(fenArr[3]);

    // Half move clock and turn number
    halfMoveClock = Byte.parseByte(fenArr[4]);
    turnNumber = Byte.parseByte(fenArr[5]);

    setOccupiedSquares();
  }

  private void setUpPieces(String pieceString) {
    int pos = 56; // This corresponds to square A8 in my bitboard sb

    for (char current : pieceString.toCharArray()) {
      switch (current) {
        case 'p':
          this.pieces[Constants.BLACK][Constants.PAWN] |= (1L << pos++);
          break;
        case 'r':
          this.pieces[Constants.BLACK][Constants.ROOK] |= (1L << pos++);
          break;
        case 'n':
          this.pieces[Constants.BLACK][Constants.KNIGHT] |= (1L << pos++);
          break;
        case 'b':
          this.pieces[Constants.BLACK][Constants.BISHOP] |= (1L << pos++);
          break;
        case 'q':
          this.pieces[Constants.BLACK][Constants.QUEEN] |= (1L << pos++);
          break;
        case 'k':
          this.pieces[Constants.BLACK][Constants.KING] |= (1L << pos++);
          break;
        case 'P':
          this.pieces[Constants.WHITE][Constants.PAWN] |= (1L << pos++);
          break;
        case 'R':
          this.pieces[Constants.WHITE][Constants.ROOK] |= (1L << pos++);
          break;
        case 'N':
          this.pieces[Constants.WHITE][Constants.KNIGHT] |= (1L << pos++);
          break;
        case 'B':
          this.pieces[Constants.WHITE][Constants.BISHOP] |= (1L << pos++);
          break;
        case 'Q':
          this.pieces[Constants.WHITE][Constants.QUEEN] |= (1L << pos++);
          break;
        case 'K':
          this.pieces[Constants.WHITE][Constants.KING] |= (1L << pos++);
          break;
        case '/':
          pos -= 16;  // back to the beginning of row, then -8 to next row
          break;
        default:
          pos += (current - '0');
      }
    }
  }

  private void setCastlingAvailability(String castle) {
    this.castleAvailability = 0;

    for (char current : castle.toCharArray()) {
      switch (current) {
        case 'K':
          castleAvailability |= 0b0001;
          break;
        case 'Q':
          castleAvailability |= 0b0010;
          break;
        case 'k':
          castleAvailability |= 0b0100;
          break;
        case 'q':
          castleAvailability |= 0b1000;
          break;
      }
    }
  }

  private long squareFromFen(String fen) {
    String file = fen.split("")[0];
    String rank = fen.split("")[1];

    return Bitboard.squareFromFileRank(file, rank);
  }

  private void setOccupiedSquares() {
    this.occupiedSquares = Bitboard.EMPTY;

    for (int color = Constants.WHITE; color <= Constants.BLACK; ++color) {
      for (int piece = Constants.PAWN; piece < Constants.ALL; ++piece) {
        this.occupiedSquares |= pieces[color][piece];
      }
    }
  }

  public void clearBoard() {
    for (int color = Constants.WHITE; color < Constants.BLACK; ++color) {
      for (int piece = Constants.PAWN; piece < Constants.ALL; ++piece) {
        pieces[color][piece] = Bitboard.EMPTY;
      }
    }

    occupiedSquares = Bitboard.EMPTY;
    emptySquares = Bitboard.EMPTY;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder("8 ");
    int rank = 8;

    long position = 56L; // Square A8 on the board
    int processed = 0;

    while (processed < 64) {
      long sq = 1L << position;
      boolean occupied = (sq & this.occupiedSquares) != 0L;

      if (occupied) {
        if ((sq & pieces[Constants.WHITE][Constants.PAWN]) != 0) sb.append(" P ");
        if ((sq & pieces[Constants.BLACK][Constants.PAWN]) != 0) sb.append(" p ");

        if ((sq & pieces[Constants.WHITE][Constants.KNIGHT]) != 0) sb.append(" N ");
        if ((sq & pieces[Constants.BLACK][Constants.KNIGHT]) != 0) sb.append(" n ");

        if ((sq & pieces[Constants.WHITE][Constants.BISHOP]) != 0) sb.append(" B ");
        if ((sq & pieces[Constants.BLACK][Constants.BISHOP]) != 0) sb.append(" b ");

        if ((sq & pieces[Constants.WHITE][Constants.ROOK]) != 0) sb.append(" R ");
        if ((sq & pieces[Constants.BLACK][Constants.ROOK]) != 0) sb.append(" r ");

        if ((sq & pieces[Constants.WHITE][Constants.QUEEN]) != 0) sb.append(" Q ");
        if ((sq & pieces[Constants.BLACK][Constants.QUEEN]) != 0) sb.append(" q ");

        if ((sq & pieces[Constants.WHITE][Constants.KING]) != 0) sb.append(" K ");
        if ((sq & pieces[Constants.BLACK][Constants.KING]) != 0) sb.append(" k ");
      } else {
        sb.append(" . ");
      }

      ++processed;

  public Chessboard(String fen) {}
}
