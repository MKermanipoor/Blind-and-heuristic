package common;

import static common.PublicValue.*;

public class State {
    private int[][] puzzle = new int[PUZZLE_SIZE][PUZZLE_SIZE];
    private int freeRow;
    private int freeColumn;

    public State(int[][] puzzle) {
        for (int i = 0; i < PUZZLE_SIZE ; i++) {
            for (int j = 0; j < PUZZLE_SIZE ; j++) {
                this.puzzle[i][j] = puzzle[i][j];
                if (puzzle[i][j] == 0) {
                    freeRow = j;
                    freeColumn = i;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State))
            return super.equals(obj);

        int[][] a = ((State) obj).puzzle;
        for (int i = 0; i < PUZZLE_SIZE ; i++) {
            for (int j = 0; j < PUZZLE_SIZE ; j++) {
                if (puzzle[i][j] != a[i][j])
                    return false;
            }
        }
        return true;
    }

    private State up() {
        if (freeColumn == PUZZLE_SIZE - 1)
            return null;

        State result = new State(this.puzzle);

        result.puzzle[result.freeColumn][result.freeRow] = result.puzzle[result.freeColumn + 1][result.freeRow];
        result.puzzle[++result.freeColumn][result.freeRow] = 0;

        return result;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for(int i=0 ; i<PUZZLE_SIZE ; i++){
            for (int j=0 ; j<PUZZLE_SIZE ; j++)
                st.append(puzzle[i][j] + "\t");
            st.append("\n");
        }
        return st.toString();
    }

    public static void main(String[] arg){
        int[][] p = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        for (int i = 0 ; i<PUZZLE_SIZE ; i++)
            for (int j=0 ; j<PUZZLE_SIZE ; j++)
                p[i][j] = scanner.nextInt();

        State s = new State(p);
        System.out.println("*****************\n" + s);
        System.out.println("*****************\n" + s.up());
        System.out.println("*****************\n" + s);
    }
}
