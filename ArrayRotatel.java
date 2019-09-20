
public class ArrayRotatel {

  public static void main(String[] args) {
    System.out.println("Hello! I am an array copier.");
    System.out.println("Please, enter the number of rows:");
    int rivi = In.readInt();
    System.out.println("Please, enter the number of columns:");
    int sarake = In.readInt();

    char[][] taulukko = luoTaulukko(rivi, sarake);
    printArray(taulukko);
    taulukko = kopioiTaulukko(taulukko);
    printArray(taulukko);
  }
  /* operaatio luo taulukon */
  public static char[][] luoTaulukko(int rivi, int sarake) {
    try {
      String merkkiJ = "";
      //kysytään käyttäjältä alkiot ja tallennetaan taulukkoon
      char[][] taulukko = new char[rivi][sarake];
      if (rivi < 1 || sarake < 1) {
        System.out.println("Error!");
        return null;
      } else {
        for (int i = 0; i < rivi; i++) {
          System.out.println("Please, enter row " + (i + 1) + ":");
          merkkiJ = In.readString();
          for (int j = 0; j < sarake; j ++ ) {
            taulukko[i][j] = merkkiJ.charAt(j);
          }
        }
        return taulukko;
      }
    }
    catch (Exception e) {
      System.out.println("Error!");
      return null;
    }
  }
  /* operaatio kopioi annetun taulukon rivit vastakkaiseen järjestykseen */
  public static char[][] kopioiTaulukko(char[][]taulukko) {
    try {
      if (taulukko != null) {
        int korkeus = taulukko[0].length;
        int leveys = taulukko.length;
        char[][] rotaterTaulukko = new char[korkeus][leveys];
        for (int r = 0; r < korkeus; r++) {
          for (int s = 0; s < leveys;  s++) {
            rotaterTaulukko[korkeus - r - 1][s] = taulukko[s][r];
          }
        }
        return rotaterTaulukko;
      }
      return null;
    } catch(Exception e) {
      System.out.println(e);
      return null;
    }
  }
  /* operaatio tulostaa taulukon */
  public static void printArray(char[][]taulukko) {
    // taulukon täyttö
    if (taulukko != null) {
      for (int rivi = 0; rivi < taulukko.length; rivi++) {
        for (int sarake = 0; sarake < taulukko[rivi].length; sarake++)
        System.out.print(taulukko[rivi][sarake]);
        System.out.println();
      }
    }
  }
}
