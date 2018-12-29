package common;

import javafx.util.Pair;

import static common.PublicValue.PUZZLE_SIZE;
import static common.PublicValue.scanner;

public class State {
    static {
        int[][] temp = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        int cont = 1;
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                temp[i][j] = cont++;
            }
        }
        arrayCode = temp;
    }

    private static final int[][] arrayCode;

    private int[][] puzzle = new int[PUZZLE_SIZE][PUZZLE_SIZE];
    private int freeRow;
    private int freeColumn;

    public State(int[][] puzzle) {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                this.puzzle[i][j] = puzzle[i][j];
                if (puzzle[i][j] == 0) {
                    freeColumn = j;
                    freeRow = i;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State))
            return super.equals(obj);

        int[][] a = ((State) obj).puzzle;
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                if (puzzle[i][j] != a[i][j])
                    return false;
            }
        }
        return true;
    }

    public Pair<State, Integer> up() {
        if (freeRow == PUZZLE_SIZE - 1)
            return null;

        State result = new State(this.puzzle);

        result.puzzle[result.freeRow][result.freeColumn] = result.puzzle[result.freeRow + 1][result.freeColumn];
        int cost = Math.abs(arrayCode[result.freeRow][result.freeColumn] - result.puzzle[freeRow][freeColumn]);
        result.puzzle[++result.freeRow][result.freeColumn] = 0;

        return new Pair<>(result, cost);
    }

    public Pair<State, Integer> down() {
        if (freeRow == 0)
            return null;

        State result = new State(this.puzzle);

        result.puzzle[result.freeRow][result.freeColumn] = result.puzzle[result.freeRow - 1][result.freeColumn];
        int cost = Math.abs(arrayCode[result.freeRow][result.freeColumn] - result.puzzle[freeRow][freeColumn]);
        result.puzzle[--result.freeRow][result.freeColumn] = 0;

        return new Pair<>(result, cost);
    }

    public Pair<State, Integer> left() {
        if (freeColumn == PUZZLE_SIZE - 1)
            return null;

        State result = new State(this.puzzle);

        result.puzzle[result.freeRow][result.freeColumn] = result.puzzle[result.freeRow][result.freeColumn + 1];
        int cost = Math.abs(arrayCode[result.freeRow][result.freeColumn] - result.puzzle[freeRow][freeColumn]);
        result.puzzle[result.freeRow][++result.freeColumn] = 0;

        return new Pair<>(result, cost);

    }

    public Pair<State, Integer> right() {
        if (freeColumn == 0)
            return null;

        State result = new State(this.puzzle);

        result.puzzle[result.freeRow][result.freeColumn] = result.puzzle[result.freeRow][result.freeColumn - 1];
        int cost = Math.abs(arrayCode[result.freeRow][result.freeColumn] - result.puzzle[freeRow][freeColumn]);
        result.puzzle[result.freeRow][--result.freeColumn] = 0;

        return new Pair<>(result, cost);
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++)
                st.append(puzzle[i][j] + "\t");
            st.append("\n");
        }
        return st.toString();
    }

    public static State getFromInput() {
        int[][] p = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++)
            for (int j = 0; j < PUZZLE_SIZE; j++)
                p[i][j] = scanner.nextInt();

        return new State(p);
    }
}
