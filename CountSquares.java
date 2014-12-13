import java.util.Scanner;

public class CountSquares {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int lines = scan.nextInt();
		scan.nextLine();

		int max = 1;
		double[][] lineMXYC = new double[5][lines];
		double x1 = 0.0, x2 = 0.0, y1 = 0.0, y2 = 0.0;
		double slope = 0.0;
		for(int i = 0; i < lines; i++) {
			String input = scan.nextLine();
			String[] arrayOfInput = input.split(" ");
			x1 = Integer.parseInt(arrayOfInput[0]);
			y1 = Integer.parseInt(arrayOfInput[1]);
			x2 = Integer.parseInt(arrayOfInput[2]);
			y2 = Integer.parseInt(arrayOfInput[3]);
			if(x1 == x2) slope = Double.POSITIVE_INFINITY;
			else slope = (y2 - y1) / (x2 - x1);
			// System.out.println(slope);
			lineMXYC[0][i] = slope;
			lineMXYC[1][i] = x1;
			lineMXYC[2][i] = y1;
			lineMXYC[3][i] = y1 - (slope * x1);

			boolean go = true;
			if(lineMXYC[0][i] == Double.POSITIVE_INFINITY || lineMXYC[0][i] == 0) {
				lineMXYC[3][i] = 0;
				go = !go;
			}
			for(int j = 1; go && j < max; j++) {
				if(lineMXYC[0][i] == lineMXYC[0][j] || lineMXYC[0][i] * lineMXYC[0][j] == -1) {
					lineMXYC[3][i] = lineMXYC[3][j];
					go = !go;
				}
			}
			if(go) {
				max++;
				lineMXYC[3][i] = max;
			}
		}

		double[][] pointsOfIntersectionX = new double[(int)(lines * lines / 4) + 1][2];
		double[][] pointsOfIntersectionY = new double[(int)(lines * lines / 4) + 1][2];

		int count = 0;
		for(int k = 0; k < lines; k++) {
			for(int n = k; n < lines; n++) {
				if(lineMXYC[3][n] == Double.POSITIVE_INFINITY && lineMXYC[0][k] == 0) {
					pointsOfIntersectionX[count][0] = lineMXYC[1][n];
					pointsOfIntersectionY[count][0] = lineMXYC[2][k];
					pointsOfIntersectionX[count][1] = lineMXYC[3][n];
					count++;
				} else if(lineMXYC[0][n] == Double.POSITIVE_INFINITY && lineMXYC[0][k] == 0) {
					pointsOfIntersectionX[count][0] = lineMXYC[1][k];
					pointsOfIntersectionY[count][0] = lineMXYC[2][n];
					pointsOfIntersectionX[count][1] = lineMXYC[3][n];
					count++;
				} else if(lineMXYC[0][n] * lineMXYC[0][k] == -1) {
					double m1 = lineMXYC[0][k];
					double m2 = lineMXYC[0][n];
					double c1 = lineMXYC[3][k];
					double c2 = lineMXYC[3][n];
					pointsOfIntersectionX[count][0] = (c1 - c2) / (m2 - m1);
					pointsOfIntersectionY[count][0] = (m2 * c1 - m1 * c2) / (m2 - m1);
					pointsOfIntersectionX[count][1] = lineMXYC[3][n];
					count++;
				}
			}
		}
		// for (int t = 0; t < count - 1; t++) {
		// System.out.println("POI at (" + pointsOfIntersectionX[t] + ", "
		// + pointsOfIntersectionY[t] + ")");
		// }

		int squares = 0;
		for(int y = 0; y < count - 1; y++) {
			for(int z = y; z < count; z++) {
				if(pointsOfIntersectionX[y][1] == pointsOfIntersectionX[z][1] && pointsOfIntersectionY[y][0] - pointsOfIntersectionY[z][0] == pointsOfIntersectionX[y][0] - pointsOfIntersectionX[z][0]) squares++;
			}
		}

		System.out.println(squares);
		scan.close();
	}
}