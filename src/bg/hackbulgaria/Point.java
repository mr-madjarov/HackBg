package bg.hackbulgaria;
/**
 * @author mr.madjarov
 *
 */
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Point {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int x = 0, y = 0;
		Boolean flag = false;
		System.out.println("Set current position");
		System.out.println("Enter x:");
		x = scanner.nextInt();

		System.out.println("Enter y:");
		y = scanner.nextInt();
		

		System.out.println("Current position:  (" + x + ", " + y + ")\n");

		
		System.out.println("Reading characters from console");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String str = br.readLine();
			char[] charArray = str.toCharArray();

			for (int i = 0; i < charArray.length; i++) {
				char c = charArray[i];
				if (c == '~') {
					flag = true;
				}
				if (!flag) {
					switch (c) {

					case '>':
						x++;
						;
						break;
					case '<':
						x--;
						break;
					case '^':
						y++;
						break;
					case 'v':
						y = y - 1;
						break;
					}
				} else {
					switch (c) {
					case '>':
						x = x - 1;
						break;
					case '<':
						x = x + 1;
						break;
					case '^':
						y = y - 1;
						break;
					case 'v':
						y = y + 1;
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		System.out.println("Final position: (" + x + ", " + y + ")");
	}
}
