package battle;

import java.util.Random;

public class Dice {
	private int quantaty, value;
	private static Random random = new Random(System.currentTimeMillis());

	public Dice(int quantaty, int value) {
		this.quantaty = quantaty;
		this.value = value;
	}

	public int roll() {
		int result = 0;
		for (int j = 0; j < quantaty; j++) {
			result += random.nextInt(value) + 1;
		}
		return result;
	}
}
