import java.util.Scanner;
import java.io.*;

public class eclipsePrintFromSystemin {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/hello"));
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine())
			System.out.println(scan.nextLine());
		
		scan.close();
	}
}
