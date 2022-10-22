public class StarPyramid {

  public static void main(String[] args) {
    int baris = 5, k = 0;

    for (int i = 1; i <= baris; ++i, k = 0) {
      for (int jarak = 1; jarak <= baris - i; ++jarak) {
        System.out.print("  ");
      }

      while (k != 2 * i - 1) {
        System.out.print("* ");
        ++k;
      }

      System.out.println();
    }
  }
}
