import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class USACO{
  public static void main(String[]args){}

  private int[][] pasture;
  private ArrayList<String> lines;

  public USACO(String filename) throws FileNotFoundException{
    Scanner scan = new Scanner(new File(filename));
    while (scan.hasNext()) lines.add(scan.nextLine());
    
  }
}
