package mechanics;

public class HealthGenerator {
	private static int[] baseHealth = new int[] { 4, 3, 2, 1 };
		
	public static int[] generateHealthTriangle(int vit){
		int[] health = new int[] { 4, 3, 2, 1 };

		for (int i = 0; i < health.length; i++) {
			if (vit >= 10) {
				health[i] = baseHealth[i] + (vit + baseHealth[i] - 11) / 4;
			} else {
				switch (vit) {			
				case 9:
					health = new int[] { 4, 3, 1, 1 };
					break;
				case 8:
					health = new int[] { 4, 2, 1, 1 };
					break;
				case 7:
					health = new int[] { 3, 2, 1, 1 };
					break;
				default:
					health = new int[] { 0, 1, 1, 1 };
				}
			}
		}
		return health;
	}
	
	public static int[] generateHealthValues(int vit){
		int[] healthValues =  new int[5];
		for (int i = 1; i < healthValues.length; i++) {
			healthValues[i] = Math.max(1, i * 5 + vit - 10);
		}
		healthValues[0] = 1;
		return healthValues;
	}
	
	
	
	
}
