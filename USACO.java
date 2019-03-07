import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class USACO{
  public static void main(String[]args){
    try {bronze("test.blah");}
    catch (FileNotFoundException e) {e.printStackTrace();}
  }

  public static int bronze(String filename) throws FileNotFoundException{
    Scanner scan = new Scanner (new File(filename));
    ArrayList<String> lines = new ArrayList<String>();
    initLines(lines, scan);
    int[] info = new int[4];
    //for (String line:lines) System.out.println(line);
    return 1;
  }

  private static void initLines(ArrayList<String> ary, Scanner in){while (in.hasNext()) ary.add(in.nextLine());}
  private static void initInfo(int[] ary){
    for
  }







}
