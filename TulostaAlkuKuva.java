
public class TulostaAlkuKuva {
  public static void main (String[] args) {
    String teksti = "A S C I I A r t 1 7";
    char merkki = '-';
    int tekstiP = teksti.length();

    for (char r = 0; r < tekstiP + 4; r++) {
      System.out.print(merkki);
    }
    System.out.println();
    System.out.println("|" + " " + teksti + " " + "|");

    for (char r = 0; r < tekstiP + 4; r++) {
      System.out.print(merkki);
    }
    System.out.println();
  }
}
