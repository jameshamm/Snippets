import java.util.*;

public class cellularAutomaton {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int rule = scan.nextInt();
		int iterationsMax = scan.nextInt();
		int currentIterations = 1;
		int cells = scan.nextInt();
		long seed = scan.nextLong();
		long value = seed;
		String r = Long.toBinaryString(rule);
		while(r.length() < cells) {
			r = "0" + r;
		}

		print(cells, currentIterations, value);
		for(int i = 0; i < iterationsMax - 1; i++) {
			currentIterations++;
			value = apply(cells, r, value);
			print(cells, currentIterations, value);
			if(value == seed) break;
			seed = value;
		}
		scan.close();
	}

	public static void print(int cells, int currentIterations, long value) {
		String s;
		if(currentIterations < 10) s = "   ";
		else if(currentIterations < 100) s = "  ";
		else s = " ";

		System.out.print(currentIterations + s + "-");
		for(int i = cells - 1; i >= 0; i--) {
			System.out.print(((value >>> i) & 1) != 0 ? "*":" ");
		}
		System.out.println("-");
	}

	public static long apply(int cells, String r, long value) {
		int index = 0;
		String t = "";
		String s = Long.toBinaryString(value);
		while(s.length() < cells) {
			s = "0" + s;
		}

		index = cells - (Integer.parseInt("0" + s.substring(0, 2), 2)) - 1;
		t += r.charAt(index);
		for(int i = 1; i < cells - 1; i++) {
			index = cells - (Integer.parseInt(s.substring(i - 1, i + 2), 2)) - 1;
			t += r.charAt(index);
		}
		index = cells - (Integer.parseInt(s.substring(cells - 2, cells) + "0", 2)) - 1;
		t += r.charAt(index);

		return Long.parseLong(t, 2);
	}
}