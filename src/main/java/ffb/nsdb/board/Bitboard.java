package ffb.nsdb.board;

public class Bitboard {

  // Files
  public static final long FileA = 0x0101010101010101L;
  public static final long FileB = FileA << 1;
  public static final long FileC = FileB << 1;
  public static final long FileD = FileC << 1;
  public static final long FileE = FileD << 1;
  public static final long FileF = FileE << 1;
  public static final long FileG = FileF << 1;
  public static final long FileH = FileG << 1;

  // Ranks
  public static final long Rank1 = 0xffL;
  public static final long Rank2 = Rank1 << 8;
  public static final long Rank3 = Rank2 << 8;
  public static final long Rank4 = Rank3 << 8;
  public static final long Rank5 = Rank4 << 8;
  public static final long Rank6 = Rank5 << 8;
  public static final long Rank7 = Rank6 << 8;
  public static final long Rank8 = Rank7 << 8;

  // Index of Files and Ranks
  private static final long[] files = {FileA, FileB, FileC, FileD, FileE, FileF, FileG, FileH};
  private static final long[] ranks = {Rank1, Rank2, Rank3, Rank4, Rank5, Rank6, Rank7, Rank8};

  /**
   * Returns a board with the square represented by the file and rank set to 1 with all other
   * squares set to 0.
   *
   * @param file a string representing the file of a chess position
   * @param rank a string representing the rank of a chess position
   * @return a bitboard of zeros with the rank/file set to 1
   */
  public static long squareFromFileRank(String file, String rank) {
    long f = files[(int) file.charAt(0) - 65];
    long r = ranks[(int) Long.parseLong(rank) - 1];

    return r & f;
  }

  /**
   * Takes in an unsigned 64-bit integer (a "bitboard") and prints it out as an 8x8 square of bits
   * which is intended to represent a chessboard.
   *
   * @param board a 64-bit integer (a "bitboard")
   */
  public static void printAsBits(long board) {
    for (int row = 7; row >= 0; --row) {
      for (int col = 0; col <= 7; ++col) {
        if ((board & (1L << ((row * 8) + col))) != 0) {
          System.out.print("1 ");
        } else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  private enum FileIndex {
    A, B, C, D, E, F, G, H
  }
}
