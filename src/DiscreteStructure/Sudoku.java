package DiscreteStructure;

public class Sudoku {
    private static final int EMPTY = 0; // empty cell
    private static final int SIZE = 9; // size of our Sudoku grids

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];

        // initialize the board with your puzzle here
        board[0][0] = 4;
        board[0][4] = 9;
        board[0][8] = 2;
        board[1][2] = 1;
        board[1][6] = 5;
        board[2][1] = 9;
        board[2][3] = 3;
        board[2][4] = 4;
        board[2][5] = 5;
        board[2][7] = 1;
        board[3][2] = 8;
        board[3][6] = 2;
        board[3][7] = 5;
        board[4][0] = 7;
        board[4][2] = 5;
        board[4][4] = 3;
        board[4][6] = 4;
        board[4][7] = 6;
        board[4][8] = 1;
        board[5][1] = 4;
        board[5][2] = 6;
        board[5][6] = 9;
        board[5][8] = 8;
        board[6][1] = 6;
        board[6][3] = 1;
        board[6][4] = 5;
        board[6][5] = 9;
        board[6][7] = 8;
        board[7][2] = 9;
        board[7][6] = 6;
        board[8][0] = 5;
        board[8][4] = 7;
        board[8][8] = 4;

        if (solve(board)) {
            print(board);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check the row
        for (int d = 0; d < board.length; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }

        // Check the column
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // Check the box
        int sqrt = (int) Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        // if there is no clash, it's safe
        return true;
    }

    private static boolean solve(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // we search an empty cell
                if (board[i][j] == EMPTY) {
                    // we try possible numbers
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, i, j, num)) {
                            // number ok. it respects sudoku constraints
                            board[i][j] = num;

                            if (solve(board)) {
                                // we start backtracking recursively
                                return true;
                            } else {
                                // if not a solution, we empty the cell and we continue
                                board[i][j] = EMPTY;
                            }
                        }
                    }

                    return false; // we return false
                }
            }
        }

        return true; // sudoku solved
    }

    private static void print(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("- - - - - - - - - - - - - ");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

}

