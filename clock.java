import java.util.*;

public class clock {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final int count = scan.nextInt();
		final int c = count - 1;
		final int d = 360000;
		int i;
		int[] array1 = new int[count];
		int[] array2 = new int[count];

		// get input
		for(i = 0; i < count; i++) {
			array1[i] = scan.nextInt();
		}
		for(i = 0; i < count; i++) {
			array2[i] = scan.nextInt();
		}
		// long start = System.nanoTime();
		// long startA = System.nanoTime();
		Arrays.sort(array1);
		Arrays.sort(array2);
		// long endA = System.nanoTime();

		// long startB = System.nanoTime();
		StringBuilder b1 = new StringBuilder();
		StringBuilder b2 = new StringBuilder();
		for(i = 0; i < c; i++) {
			b1.append(array1[i + 1] - array1[i]);
			b1.append(" ");
			b2.append(array2[i + 1] - array2[i]);
			b2.append(" ");
		}
		b1.append((array1[0] - array1[c]) + d);
        b1.append(" ");
		b2.append((array2[0] - array2[c]) + d);
		// long endB = System.nanoTime();

		// long startC = System.nanoTime();
		Boolean same = (search(b1.toString() + b1.toString(), b2.toString()) != b1.length() + b1.length());
		// long endC = System.nanoTime();
		System.out.println((same) ? "possible":"impossible");
		scan.close();
		// System.out.println("TOTAL time: "+(System.nanoTime()-start)/1000000+" ms");
		// System.out.println("SORTING: "+(endA-startA));
		// System.out.println("BUILDING: "+(endB-startB));
		// System.out.println("SEARCHING: "+(endC-startC));
		// System.out.println("SEARCHING: "+(endC-startC)/1000000);
	}

	public static int search(String a3, String a2) {
		final int R = 256; // the radix
		int M = a2.length();

		int[][] dfa = new int[R][M];
		dfa[a2.charAt(0)][0] = 1;
		int i, j;
		for(i = 0, j = 1; j < M; j++) {
			for(int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][i]; // Copy mismatch cases.
			dfa[a2.charAt(j)][j] = j + 1; // Set match case.
			i = dfa[a2.charAt(j)][i]; // Update restart state.
		}
		int N = a3.length();

		for(i = 0, j = 0; j < M && i < N; i++) {
			j = dfa[a3.charAt(i)][j];
		}
		return (j == M) ? i - M:N;
	}
}