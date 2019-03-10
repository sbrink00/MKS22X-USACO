public class posibilities{
  public static void main(String[]args){
    //for (int idx = 0; idx < 11; idx ++){
    //  posibilities board = new posibilities(idx);
    //  board.fillMoves(idx, idx, 0);
    //  System.out.println(idx + ": ");
    //  System.out.println(board);
    //  System.out.println("\n");
    //}
    posibilities recursive = new posibilities(3);
    recursive.fillMoves(3, 3, 0);
    System.out.println("recursive: " + "\n" + recursive + "\n");

    posibilities loops = new posibilities(3);
    loops.fillMoves();
    System.out.println("loops: " + "\n" + loops + "\n");
  }

  private int[][] moves;
  int steps;

  public posibilities(int move){
    moves = new int[move * 2 + 1][move * 2 + 1];
    steps = move;
  }

  public boolean square(int r, int c){
    return r >= 0 && c >= 0 && r < moves.length && c < moves.length;
  }

  public void fillMoves(){
    moves[steps][steps] = 1;
    for (int idx = 0; idx < steps; idx ++){
      int[][] reference = moves;
      moves = new int[steps * 2 + 1][steps * 2 + 1];
      for (int r = 0; r < reference.length; r ++){
        for (int c = 0; c < reference.length; c ++){
          //System.out.println(this);
          if (square(r + 1, c)) moves[r][c] += reference[r + 1][c];
          if (square(r - 1, c)) moves[r][c] += reference[r - 1][c];
          if (square(r, c + 1)) moves[r][c] += reference[r][c + 1];
          if (square(r, c - 1)) moves[r][c] += reference[r][c - 1];
        }
      }
    }
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
