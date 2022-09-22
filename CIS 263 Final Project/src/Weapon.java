
public class Weapon
{
	private String type;
	private int minDamage;
	private int maxDamage;
	private int accuracy;
	
	public Weapon(String name, int minDmg, int maxDmg, int attackSuccess)
	{
		type = name;
		minDamage = minDmg;
		maxDamage = maxDmg;
		accuracy = attackSuccess;
	}

	public int getAccuracy()
	{
		return accuracy;
	}
	
	public String getType()
	{
		return type;
	}

	public int getDamage() 
	{
		// low + Math.random() * rangeSize
		int rangeSize = maxDamage - minDamage;
		int damage = minDamage + (int)(Math.random() * rangeSize);
		
		return damage;
	}

	public String toString() 
	{
		return type + " (" 
				+ minDamage + "-" + maxDamage + " dmg, "
				+ accuracy + " accuracy)";
	}
}

