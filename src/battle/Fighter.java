package battle;

public class Fighter {
	private  boolean isAlive = true;
	private int vit, hit, dr, initiative, ap, apConsumption;
	private int[] currentHealth, maxHealth;
	private DamageRoller damage;

	public Fighter(int vit, int hit, int dr, int initiative, int ap, int apConsumption, String damage){
		this.vit= vit;
		this.hit = hit;
		this.dr = dr;
		this.initiative = initiative;
		this.ap = ap;
		this.apConsumption = apConsumption;
		try{
			this.damage = new DamageRoller(Integer.parseInt(damage));
		} catch (NumberFormatException e) {
			this.damage = new DamageRoller(damage);			
		}
		
	}

}
