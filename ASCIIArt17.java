import java.io.*;
import java.util.*;


public class ASCIIArt17 {
  final static char[] MERKIT = new char[]{'#', '@', '&', '$', '%', 'x',
    '*', 'o', '|', '!', ';', ':', '\'', ',', '.', ' '};

  public static void main(String[] args) {
    // Ohjelma käynnistyy
    tulostaAlkuKuva();
    char[][] tiedosto = tiedostonLataaminen(args);

    if (tiedosto != null) {
      kaynnista(tiedosto);
    } else {
      tulostaVirheilmoitus();
      tulostaLoppuTervehdys();
    }
  }
  /*Operaatio suorittaa komentoja */
  public static void kaynnista(char[][] taulukko) {
    boolean suorita = true;
    char[][] kopio = taulukonKopiointi(taulukko);
    // niin kauan kun while on true suoritetaan komentokäsky ja kun false lopetetaan ja
    // kutsutaan loputervehdystä.
    while(suorita){
      String[] käskyt = lueKomentoKäskyt();
      switch(käskyt[0]) {
        case "print": tulostaTaulukko(taulukko);
        break;
        case "info": info(taulukko);
        break;
        case "rotater": taulukko = rotate(taulukko, true);
        break;
        case "rotatel": taulukko = rotate(taulukko, false);
        break;
        case "recolour": {
          String from = käskyt[1];
          String to = käskyt[2];
          taulukko = recolour(taulukko, from, to);
          break;
        }
        case "reset": taulukko = taulukonKopiointi(kopio);
        break;
        case "quit": suorita = false;
        default: break;
      }
    }
    tulostaLoppuTervehdys();
  }
  /* operaatio kopioi alkuperäisen ensimmäisen taulukon*/
  public static char[][] taulukonKopiointi(char[][] taulukko) {
    char[][] kopio = new char[taulukko.length][taulukko[0].length];
    for(int i = 0; i < taulukko.length; i++) {
      for(int j = 0; j < taulukko[0].length; j++) {
        kopio[i][j] = taulukko[i][j];
      }
    }
    return kopio;
  }
  /* Operaatio lataa tiedoston*/
  public static char[][] tiedostonLataaminen(String[] args) {
    if(args.length != 1) return null;
    try {
      //luetaan tiedosto
      String tiedostonNimi = args[0];
      File tiedostoOlionNimi = new File(tiedostonNimi);
      Scanner lukija = new Scanner(tiedostoOlionNimi);
      int[] rivilaskuri = riviLaskuri(lukija);
      if(rivilaskuri == null) {
        return null;
      }
      int rivienMaara = rivilaskuri[0];
      int sarakkeet = rivilaskuri[1];
      char[][] taulukko = new char[rivienMaara][sarakkeet];
      lukija = new Scanner(tiedostoOlionNimi);
      int i = 0;
      while(lukija.hasNextLine()) {
        String rivi = lukija.nextLine();
        for(int j = 0; j < rivi.length(); j ++) {
          taulukko[i][j] = rivi.charAt(j);
        }
        i++;
      }
      return taulukko;
    } catch(Exception e) {
      return null;
    }
  }
  /* Operaatio laskee rivien määrän ja pisimmän rivin, minkä perusteella
  taulukon kokomääräytyy */
  public static int[] riviLaskuri(Scanner lukija) {
    int rivienMäärä = 0;
    int pisinRivi = 0;
    try {
      while (lukija.hasNextLine()) {
        rivienMäärä++;
        String rivi = lukija.nextLine();
        if(rivi.length() > pisinRivi) {
          pisinRivi = rivi.length();
        }
      }
    } catch(Exception e) {
      return null;
    }
    return new int[]{rivienMäärä, pisinRivi};
  }
  /* operaatio tulostaa tervehdyskuvan */
  public static void tulostaAlkuKuva() {
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
  public static String[] lueKomentoKäskyt() {
    System.out.println("Please, enter a command:");
    String[] käskyt = new String[3];
    String käsky = In.readString();
    käskyt[0] = käsky.split(" ")[0];
    if(käsky.length() > 9) {
      käskyt[1] = käsky.charAt(9) + "";
      käskyt[2] = käsky.charAt(11) + "";
    }
    return käskyt;
  }
  public static void tulostaVirheilmoitus() {
    System.out.println("Invalid command-line argument!");
  }
  /* operaatio tulostaa taulukon */
  public static void tulostaTaulukko(char[][]taulukko) {
    // taulukon täyttö
    if (taulukko != null) {
      for (int rivi = 0; rivi < taulukko.length; rivi++) {
        for (int sarake = 0; sarake < taulukko[rivi].length; sarake++) {
          System.out.print(taulukko[rivi][sarake]);
        }
        System.out.println();
      }
    }
  }
  /* Operaatio tulostaa lasketut merkit merkin kohdalle*/
  public static void info(char[][] taulukko) {
    System.out.println(taulukko.length + " x " + taulukko[0].length);
    int[] lasketutMerkit = laskeMerkit(taulukko);
    for (int i = 0; i < MERKIT.length; i++) {
      System.out.println(MERKIT[i] + " " + lasketutMerkit[i]);
    }
  }
  /* Operaatio laskee merkit kuvasta */
  public static int[] laskeMerkit(char[][] taulukko) {
    int[] loydetyt = new int[MERKIT.length];
    for(int i = 0; i < loydetyt.length; i++){
      loydetyt[i] = 0;
    }
    for (int r = 0; r< taulukko.length; r++) {
      for (int s = 0; s < taulukko[r].length; s++) {
        for (int i = 0; i < MERKIT.length; i++) {
          if (MERKIT[i] == taulukko[r][s]) {
            loydetyt[i] += 1;
          }
        }
      }
    }
    return loydetyt;
  }
  /* Operaatio kääntää kuvan oikealle 90° tai vasemmalle 90°.*/
  public static char[][] rotate(char[][] taulukko, boolean oikealle) {
    try {
      if (taulukko != null) {
        int korkeus = taulukko[0].length;
        int leveys = taulukko.length;
        char[][] rotateTaulukko = new char[korkeus][leveys];
        for (int r = 0; r < korkeus; r++) {
          for (int s = 0; s < leveys;  s++) {
            if(oikealle) {
              rotateTaulukko[r][leveys - s - 1] = taulukko[s][r];
            } else {
              rotateTaulukko[korkeus - r - 1][s] = taulukko[s][r];
            }
          }
        }
        return rotateTaulukko;
      }
      return null;
    } catch(Exception e) {
      System.out.println(e);
      return null;
    }
  }
  /* Operaatio värjää kuvan parametrina annetulla merkillä.*/
  public static char[][] recolour(char[][] muokattavaTaulukko, String from, String to) {
    if (muokattavaTaulukko == null || from == null || to == null) return null;
    char fromChar = from.charAt(0);
    char toChar = to.charAt(0);

    for (int r = 0; r < muokattavaTaulukko.length; r++) {
      for (int s = 0; s < muokattavaTaulukko[r].length; s++) {
        if(muokattavaTaulukko[r][s] == fromChar) {
          muokattavaTaulukko[r][s] = toChar;
        }
      }
    }
    return muokattavaTaulukko;
  }
  /* Operaatio tulostaa lopputehvehdyksen*/
  public static void tulostaLoppuTervehdys() {
    System.out.println("Bye, see you soon.");
  }
}
