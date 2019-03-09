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
    int[][] lake = new int[info[0]][info[1]];
    initLake(lake, lines, info[0], info[1]);
    for (int idx = 0; idx < lake.length; idx ++){
      for (int idx2 = 0; idx2 < lake[0].length; idx2 ++){
        System.out.print(lake[idx][idx2] + " ");
      }
      System.out.print("\n");
    }
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
    s.remove(0);
  }
  private static void initLake(int[][] ary, ArrayList<String> lines, int r, int c){
    for (int idx = 0; idx < r; idx ++){
      String row = lines.get(idx);
      row += " ";
      for (int idx2 = 0; idx2 < c; idx2 ++){
        int space = row.indexOf(" ");
        int depth = Integer.parseInt(row.substring(0, space));
        ary[idx][idx2] = depth;
        row = row.substring(space + 1, row.length());
      }
    }
  }







}
