import java.util.*;

public class Square {
	public static void main(String args[]) {
		int squares = 0;
		String input = "";
		boolean go = true;

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();

		double[][] line = new double[5][n];
		for(int i = 0; i < n; i++) {
			input = scan.nextLine();
			String[] arrayOfInput = input.split(" ");
			line[0][i] = Integer.parseInt(arrayOfInput[0]);
			line[1][i] = Integer.parseInt(arrayOfInput[1]);
			line[2][i] = Integer.parseInt(arrayOfInput[2]);
			line[3][i] = Integer.parseInt(arrayOfInput[3]);
			line[4][i] = line[0][i] == line[2][i] ? Double.POSITIVE_INFINITY:line[1][i] == line[3][i] ? 0.0:(line[3][i] - line[1][i]) / (line[2][i] - line[0][i]);
		}

		Map<Double, Map<Double, Integer>> map = new HashMap<Double, Map<Double, Integer>>();
		Map<Double, Integer> inner = new HashMap<Double, Integer>();
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				if(line[4][i] == line[4][j]) {
					double dist = distance(line[0][i], line[1][i], line[2][i], line[3][i], line[0][j], line[1][j]);
					inner = map.containsKey(line[4][i]) ? map.get(line[4][i]):new HashMap<Double, Integer>();
					inner.put(dist, inner.containsKey(dist) ? inner.get(dist) + 1:1);
					map.put(line[4][i], inner);
				}
			}
		}

		List<Double> keys = new ArrayList<Double>();
		keys.addAll(map.keySet());
		for(int i = 0; i < keys.size();) { // bottleneck
			go = true;
			for(int j = i + 1; go && j < keys.size(); j++) {
				if(keys.get(i) == Double.POSITIVE_INFINITY && keys.get(j) == 0 || keys.get(i) == 0 && keys.get(j) == Double.POSITIVE_INFINITY
						|| Math.abs(keys.get(i) * keys.get(j) + 1.0) < 0.0000001) {
					Map<Double, Integer> arrayX = map.get(keys.get(i));
					Map<Double, Integer> arrayY = map.get(keys.get(j));

					Set<Double> keysIn = arrayX.keySet();
					keysIn.retainAll(arrayY.keySet());
					for(Double s : keysIn) {
						squares += arrayX.get(s) * arrayY.get(s);
					}
					keys.remove(j);
					keys.remove(i);
					go = false;
				}
			}
			if(go) {
				i++;
			}
		}
		System.out.println(squares);
		scan.close();
	}

	public static double distance(double x1, double y1, double x2, double y2, double x3, double y3) {
		double a = y1 - y2;
		double b = x2 - x1;
		return truncate(Math.abs(a * x3 + b * y3 + (x1 - x2) * y1 + (y2 - y1) * x1) / Math.sqrt(a * a + b * b));
	}

	public static double truncate(double value) {
		long factor = (long)Math.pow(10, 7);
		value *= factor;
		long TEMP = (long)value;
		return (double)TEMP / factor;
	}
}