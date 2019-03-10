public class posibilities{
  public static void main(String[]args){
    //for (int idx = 0; idx < 11; idx ++){
    //  posibilities board = new posibilities(idx);
    //  board.fillMoves(idx, idx, 0);
    //  System.out.println(idx + ": ");
    //  System.out.println(board);
    //  System.out.println("\n");
    //}
    posibilities a = new posibilities(15);
    a.fillMoves(15, 15, 0);
    System.out.println("done: " + a.steps);
  }

  private int[][] moves;
  int steps;

  public posibilities(int move){
    moves = new int[move * 2 + 1][move * 2 + 1];
    steps = move;
  }

  public void fillMoves(int r, int c, int curStep){
    if (curStep == steps) moves[r][c] ++;
    else{
      fillMoves(r + 1, c, curStep + 1);
      fillMoves(r - 1, c, curStep + 1);
      fillMoves(r, c + 1, curStep + 1);
      fillMoves(r, c - 1, curStep + 1);
    }
  }

  public String toString(){
    String output = "";
    for (int idx = 0; idx < moves.length; idx ++){
      for (int idx2 = 0; idx2 < moves.length; idx2 ++){
        if (moves[idx][idx2] < 10) output += "____" + moves[idx][idx2];
        else if (moves[idx][idx2] < 100) output += "___" + moves[idx][idx2];
        else if (moves[idx][idx2] < 1000) output += "__" + moves[idx][idx2];
        else if (moves[idx][idx2] < 10000) output += "_" + moves[idx][idx2];
        else output += moves[idx][idx2];
        output += " ";
      }
      output += "\n";
    }
    return output;
  }
}
