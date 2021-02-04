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
}
