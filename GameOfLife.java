import java.util.*;

class GameOfLife {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int s = scan.nextInt();
		scan.nextLine();
		String[] array = new String[s];
		for(int i = 0; i < s; i++) {
			array[i] = scan.nextLine();
		}
		int t = array[0].length();

		int[][] nextBoard = new int[s][t];
		for(int i = 0; i < s; i++) {
			for(int j = 0; j < t; j++) {
				nextBoard[i][j] = (array[i].charAt(j) == '.') ? 0:1;
			}
		}
		int[][] board = new int[s][t];

		for(int gen = 0; gen <= n; gen++) {
			for(int i = 0; i < s; i++) {
				for(int j = 0; j < t; j++) {
					board[i][j] = nextBoard[i][j];
					if(board[i][j] == 1 && !(countSurrounding(board, i, j) == 2 || countSurrounding(board, i, j) == 3)) {
						nextBoard[i][j] = 0;
					} else if(board[i][j] == 0 && countSurrounding(board, i, j) == 3) {
						nextBoard[i][j] = 1;
					}
				}
			}
		}
		printBoard(board);
		scan.close();
	}

	public static int countSurrounding(int[][] board, int a, int b) {
		int count = 0;
		int[][] surrounding = { {a - 1, b - 1}, {a - 1, b}, {a - 1, b + 1}, {a, b - 1}, {a, b + 1}, {a + 1, b - 1}, {a + 1, b}, {a + 1, b + 1}};
		for(int i[] : surrounding) {
			try {
				if(board[i[0]][i[1]] == 1) {
					count++;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
			}
		}
		return count;
	}

	public static void printBoard(int[][] board) {
		for(int[] i : board) {
			for(int j : i) {
				System.out.print(j == 1 ? "X":".");
			}
			System.out.println();
		}
		System.out.println();
	}
}