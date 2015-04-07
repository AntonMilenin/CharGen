package battle;

import mechanics.HealthGenerator;

public class Fighter {
	private boolean isAlive = true;
	private int vit, hit, dr, initiative, ap, apConsumption, def;
	private int[] health, healthValues;
	private DamageRoller damage;
	private static Dice IIIdVI = new Dice(3, 6);
	private int currentAp = 0;

	public Fighter(int vit, int hit, int dr, int initiative, int ap, int apConsumption, int def, String damage) {
		this.vit = vit;
		this.hit = hit;
		this.dr = dr;
		this.initiative = initiative;
		this.ap = ap;
		this.apConsumption = apConsumption;
		this.def = def;
		try {
			this.damage = new DamageRoller(Integer.parseInt(damage));
		} catch (NumberFormatException e) {
			this.damage = new DamageRoller(damage);
		}
		health = HealthGenerator.generateHealthTriangle(vit);
		healthValues = new int[] { 1, 5, 10, 15, 20 };
	}

	private void takeHit(int incomingHit, int incomingDamage) {
		int damageTaken = incomingDamage - dr;
		if (IIIdVI.roll() + def > incomingHit) {
		} else {
			for (int i = 4; i >= 0; i--) {
				if (healthValues[i] < damageTaken) {
					takeDamage(i);
					break;
				}
			}
		}
	}

	private void takeDamage(int damageTier) {
		if (damageTier == 4) {
			isAlive = false;
		} else if (health[damageTier] == 0) {
			takeDamage(damageTier + 1);
		} else {
			health[damageTier] = health[damageTier] - 1;
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void hit(Fighter fighter) {
		fighter.takeHit(IIIdVI.roll() + hit, damage.rollDamage());
		currentAp -= apConsumption;
	}

	public void startNewRound() {
		currentAp += ap;
	}

	public boolean canHit() {
		return currentAp > apConsumption;
	}
}
