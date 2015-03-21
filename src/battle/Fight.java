package battle;

import java.util.List;

public class Fight {
	private Fighter hero;
	private List<Fighter> enemies;

	public Fight(Fighter hero, List<Fighter> enemies) {
		this.hero = hero;
		this.enemies = enemies;
	}

	public boolean fight(boolean heroAttacsFirst) {
		// TODO: take initiative into account
		while (hero.isAlive() && !enemies.isEmpty()) {
			if (heroAttacsFirst) {
				heroAttac();
			}
			for (Fighter f : enemies) {
				f.startNewRound();
				while (f.canHit()) {
					f.hit(hero);
				}
			}
			if (!heroAttacsFirst) {
				heroAttac();
			}
		}
		return (hero.isAlive());
	}

	public void heroAttac() {
		hero.startNewRound();
		while (hero.canHit() && !enemies.isEmpty()) {
			hero.hit(enemies.get(0));
			if (!enemies.get(0).isAlive()) {
				enemies.remove(0);
			}
		}
	}
}
