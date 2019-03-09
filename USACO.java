import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class USACO{
  public static void main(String[]args){
    try {System.out.println(bronze("lake1.blah"));}
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
    /*for (int idx = 0; idx < lake.length; idx ++){
      for (int idx2 = 0; idx2 < lake[0].length; idx2 ++){
        System.out.print(lake[idx][idx2] + " ");
      }
      System.out.print("\n");
    }
    System.out.println("");*/
    int[][] instructions = new int[info[3]][3];
    initInstructions(instructions, lines, info[3]);
    /*for (int idx = 0; idx < instructions.length; idx ++){
      for (int idx2 = 0; idx2 < instructions[0].length; idx2 ++){
        System.out.print(instructions[idx][idx2] + " ");
      }
      System.out.print("\n");
    }*/
    return solve(lake, instructions, info[2]);
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
    int base = r;
    while (r > 0){
      String row = lines.get(0);
      row += " ";
      for (int idx2 = 0; idx2 < c; idx2 ++){
        int space = row.indexOf(" ");
        int depth = Integer.parseInt(row.substring(0, space));
        ary[base - r][idx2] = depth;
        row = row.substring(space + 1, row.length());
      }
      lines.remove(0);
      r --;
    }
  }
  private static void initInstructions(int[][] ins, ArrayList<String> lines, int r){
    int base = r;
    while (r > 0){
      String row = lines.get(0);
      row += " ";
      for (int idx2 = 0; idx2 < 3; idx2 ++){
        int space = row.indexOf(" ");
        int info = Integer.parseInt(row.substring(0, space));
        ins[base - r][idx2] = info - 1;
        if (idx2 == 2) ins[base - r][idx2] ++;
        row = row.substring(space + 1, row.length());
      }
      lines.remove(0);
      r --;
    }
  }

  private static int solve(int[][] lake, int[][] instructions, int depth){
    for (int idx = 0; idx < instructions.length; idx ++){
      int r = instructions[idx][0] - 1;
      int c = instructions[idx][1];
      int max = lake[r + 1][c];
      int dig = instructions[idx][2];
      for (int idx2 = 0; idx2 < 9; idx2 ++){
        int i = idx2 % 3;
        if (i == 0) r ++;
        if (lake[r][c + i] > max) max = lake[r][c + i];
      }
      r = instructions[idx][0] - 1;
      for (int idx2 = 0; idx2 < 9; idx2 ++){
        int i = idx2 % 3;
        if (i == 0) r ++;
        if (!(lake[r][c + i] < max - dig)) lake[r][c + i] = max - dig;
      }
    }
    return calcAnswer(lake, depth);
  }
  private static int calcAnswer(int[][] lake, int depth){
    int totalDepth = 0;
    for (int idx = 0; idx < lake.length; idx ++){
      for (int idx2 = 0; idx2 < lake[0].length; idx2 ++){
        if (depth > lake[idx][idx2]) totalDepth += depth - lake[idx][idx2];
      }
    }
    return totalDepth * 72 * 72;
  }






}
