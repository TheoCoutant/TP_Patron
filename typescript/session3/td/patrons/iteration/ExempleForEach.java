package session3.demo.patrons.iteration;

import java.util.ArrayList;
import java.util.List;

public class ExempleForEach {
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<>();
		l.add(0);
		l.add(1);
		l.add(2);
		l.add(3);
		System.out.println("Enumération externe");
		for (int n : l) {
			System.out.println(n);
		}

		System.out.println("Enumération interne");

		l.forEach(n -> System.out.println(n));
	}
}
