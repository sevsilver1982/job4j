package examples.array;

public class MatrixCheck {

    /*public static boolean checkVertical(char[][] board) {
        boolean result = true;
        int height = board.length - 1;
        int reference_index = FindLoop.indexOf(board[0], 'X');
        for (int i = 0; i <= height; i++) {
            if (reference_index != FindLoop.indexOf(board[i], 'X')) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean checkHorizontal(char[][] board) {
        boolean result = true;
        int width = board[board.length - 1].length - 1;
        int height = board.length - 1;
        int reference_index = -1;
        for (int i = 0; i <= height; i++) {
            if (board[i][0] == 'X') {
                reference_index = i;
                break;
            }
        }
        if (reference_index == -1) {
            result = false;
        } else {
            for (int j = 0; j <= width; j++) {
                if (board[reference_index][j] != 'X') {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }*/

    public static boolean isWin(char[][] board) {
        boolean result = false;
        int x = 0;
        int count1 = 0;
        int count2 = 0;
        for (int row = 0; row < board.length; row++) {
            if (board[row][row] == 'X') {
                for (int cell = 0; cell < board.length; cell++) {
                    if (board[row][cell] == 'X') {
                        count1++;
                    }
                    if (board[cell][row] == 'X') {
                        count2++;
                    }
                }
                if ((count1 == board.length) || (count2 == board.length)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] hasWinVertical = {
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
        };
        boolean win = isWin(hasWinVertical);
        System.out.println("A board has a winner : " + win);
        System.out.println();
        char[][] hasWinHor = {
                {'_', '_', '_', '_', '_'},
                {'X', 'X', 'X', 'X', 'X'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
        };
        boolean winHor = isWin(hasWinHor);
        System.out.println("A board has a winner : " + winHor);
        System.out.println();
        char[][] notWin = {
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', 'X', '_', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
        };
        boolean lose = isWin(notWin);
        System.out.println("A board has a winner : " + lose);
    }

}