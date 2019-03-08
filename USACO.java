import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class USACO{
  public static void main(String[]args){
    try {bronze("lake1.blah");}
    catch (FileNotFoundException e) {e.printStackTrace();}
  }

  public static int bronze(String filename) throws FileNotFoundException{
    Scanner scan = new Scanner (new File(filename));
    ArrayList<String> lines = new ArrayList<String>();
    initLines(lines, scan);
    int[] info = new int[4];
    initInfo(info, lines);
    System.out.println(info[0]);
    System.out.println(info[1]);
    System.out.println(info[2]);
    System.out.println(info[3]);
    //for (String line:lines) System.out.println(line);
    return 1;
  }

  private static void initLines(ArrayList<String> ary, Scanner in){while (in.hasNext()) ary.add(in.nextLine());}
  private static void initInfo(int[] ary, ArrayList<String> s){
    String info = s.get(0);
    ary[0] = Integer.parseInt(info.substring(0, info.indexOf(" ")));
    info = info.substring(info.indexOf(" ") + 1, info.length());
    ary[1] = Integer.parseInt(info.substring(0, info.indexOf(" ")));
    info = info.substring(info.indexOf(" ") + 1, info.length());
    ary[2] = Integer.parseInt(info.substring(0, info.indexOf(" ")));
    info = info.substring(info.indexOf(" ") + 1, info.length());
    ary[3] = Integer.parseInt(info);
  }







}
