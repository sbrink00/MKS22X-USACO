import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class USACO{
  public static void main(String[]args){
    try {System.out.println("\n" + silver("travel1.blah"));}
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
    int[][] instructions = new int[info[3]][3];
    initInstructions(instructions, lines, info[3]);
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

  public static int silver(String filename) throws FileNotFoundException{
    Scanner scan = new Scanner (new File(filename));
    ArrayList<String> lines = new ArrayList<String>();
    initLines(lines, scan);
    int[] info = new int[4];
    lines.set(0, lines.get(0) + " 4");
    initInfo(info, lines);
    //for (int idx = 0; idx < info.length; idx ++) System.out.println(info[idx]+ " ");
    char[][] pasture = new char[info[0]][info[1]];
    initPasture(pasture, lines, info[0], info[1]);
    int[] instructions = new int[4];
    initInfo(instructions, lines);
    for (int idx = 0; idx < instructions.length; idx ++) instructions[idx] --;
    int[][] vals = initVals(pasture);
    System.out.println(vals.length);
    System.out.println(vals[0].length);
    //System.out.println(intString(vals));
    fillMoves(vals, instructions, info[2]);
    return vals[instructions[2]][instructions[3]];
  }

  private static void initPasture(char[][] pasture, ArrayList<String> lines, int r, int c){
    int base = r;
    while (r > 0){
      String row = lines.get(0);
      for (int idx2 = 0; idx2 < c; idx2 ++) pasture[base - r][idx2] = row.charAt(idx2);
      lines.remove(0);
      r --;
    }
  }

  private static int[][] initVals(char[][] pasture){
    int[][] vals = new int[pasture.length][pasture[0].length];
    for (int idx = 0; idx < vals.length; idx ++){
      for (int idx2 = 0; idx2 < vals[0].length; idx2 ++){
        if (pasture[idx][idx2] == '*') vals[idx][idx2] = -1;
      }
    }
    return vals;
  }

  public static boolean square(int[][] ary, int r, int c){
    return r >= 0 && c >= 0 && r < ary.length && c < ary.length;
  }

  public static void fillMoves(int[][] vals, int[] instructions, int steps){
    vals[instructions[0]][instructions[1]] = 1;
    for (int idx = 0; idx < steps; idx ++){
      int[][] reference = vals;
      vals = new int[reference.length][reference[0].length];
      for (int r = 0; r < reference.length; r ++){
        for (int c = 0; c < reference.length; c ++){
          if (reference[r][c] == -1) vals[r][c] = -1;
          else{
            if (square(reference, r + 1, c) && reference[r + 1][c] != -1) vals[r][c] += reference[r + 1][c];
            if (square(reference, r - 1, c) && reference[r - 1][c] != -1) vals[r][c] += reference[r - 1][c];
            if (square(reference, r, c + 1) && reference[r][c + 1] != -1) vals[r][c] += reference[r][c + 1];
            if (square(reference, r, c - 1) && reference[r][c - 1] != -1) vals[r][c] += reference[r][c - 1];
          }
        }
      }
      //System.out.println(intString(vals) + "\n");
    }
  }

  public static String intString(int[][] ary){
    String output = "";
    for (int idx = 0; idx < ary.length; idx ++){
      for (int idx2 = 0; idx2 < ary[0].length; idx2 ++){
        if (ary[idx][idx2] < 10) output += "___" + ary[idx][idx2];
        else if (ary[idx][idx2] < 100) output += "__" + ary[idx][idx2];
        else if (ary[idx][idx2] < 1000) output += "_" + ary[idx][idx2];
        else output += ary[idx][idx2];
        output += " ";
      }
    }
    return output;
  }








}
