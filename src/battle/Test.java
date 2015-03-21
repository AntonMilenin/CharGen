package battle;

import java.util.ArrayList;

public class Test {
	private final int testNumber = 1000;

	public void test(boolean b) {
		int survived = 0;
		for (int i = 0; i < testNumber; i++) {
			ArrayList<Fighter> list = new ArrayList<>();
			list.add(new Fighter(10, 5, 0, 0, 7, 5, 5, "2d6"));
			if (new Fight(new Fighter(10, 5, 0, 0, 7, 5, 5, "2d6"), list).fight(b)) {
				survived++;
			}
		}
		System.out.println("Character survived " + survived + " times from " + testNumber + ".");
	};

	public static void main(String[] argv) {
		new Test().test(true);
		new Test().test(false);
	}
}
