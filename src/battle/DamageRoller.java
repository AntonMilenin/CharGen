package battle;

import java.util.ArrayList;
import java.util.Random;

public class DamageRoller {
	private int constantBonus = 0;
	private ArrayList<Dice> dices = new ArrayList<>();

	public DamageRoller(String damage) {
		String[] parsingResult = damage.split("-[ ]*");
		if (parsingResult.length == 2) {
			constantBonus = -Integer.parseInt(parsingResult[1]);
		}
		parsingResult = parsingResult[0].split("[+ ]+");
		for (String var : parsingResult) {
			String[] parsedVar = var.split("d");
			if (parsedVar.length == 1) {
				constantBonus = Integer.parseInt(parsedVar[0]);
			} else {
				dices.add(new Dice(Integer.parseInt(parsedVar[0]), Integer.parseInt(parsedVar[1])));
			}
		}
	}

	public DamageRoller(int constantDamage) {
		constantBonus = constantDamage;
	}

	public int rollDamage() {
		int result = constantBonus;
		for (Dice dice : dices) {
			result += dice.roll();
		}
		return result;
	}
}
