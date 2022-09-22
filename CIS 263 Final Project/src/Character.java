public class Character
{
	protected String name;
	private int health;
	private Defense defense;
	protected Weapon weapon;
	protected int nextAtkBonus;
	
	public Character(String charName, int hlth)
	{
		name = charName;
		health = hlth;
		nextAtkBonus = 0;
	}

	public Weapon getWeapon()
	{
		return weapon;
	}
	
	public void setWeapon(Weapon weap)
	{
		weapon = weap;
	}

	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	public Defense getDefense()
	{
		return defense;
	}
	
	public void setDefense(Defense blocker)
	{
		defense = blocker;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setNextAtkBonus(int bonus)
	{
		nextAtkBonus = bonus;
	}
	
	public int getNextAtkBonus()
	{
		return nextAtkBonus;
	}
	
	public String toString() 
	{
		String output = name + " has a " + weapon.toString();
		
		output += ", a "  + defense.toString() + ",";
		output += " and " + health + " HP.";
		
		return output;
	}
	
	public int useWeapon(String opponent)
	{
		int strike = 1 + (int)(Math.random() * 20);
		
		//GameMain.display +=("DEBUG LINE: strike = " + strike);
		
		if (weapon.getAccuracy() >= strike)
		{	
			int damage = weapon.getDamage();
			
			GameMain.display += name + " attacks " + opponent + " with a ";
			GameMain.display += weapon.getType();
			GameMain.display += " for " + damage + " points of damage.";
			
			if (nextAtkBonus > 0)
			{
				damage += nextAtkBonus;
				GameMain.display += "\nBonus damage for successful block: " + nextAtkBonus + "\n";
				nextAtkBonus = 0;
			}
			
			return damage;
		}
		else
		{
			GameMain.display += name + "'s " + weapon.getType();
			GameMain.display += " misses " + opponent + ".";
			
			if (nextAtkBonus > 0)
			{
				GameMain.display += "You lose the advantage gained from your successful block.\n";
				nextAtkBonus = 0;
			}
				
			return 0;
		}
	
	}
	
	public int useDefense()
	{
		int block = 1 + (int)(Math.random() * 20);
		
		//GameMain.display += "DEBUG LINE: block = " + block + "\n\n";
		
		if (defense.getSuccess() >= block)
		{	
			int blocked = defense.getDamageBlocked();
			
			nextAtkBonus = defense.getAtkBonus();
			
			return blocked;
		}
		else
		{			
			return 0;
		}
	
	}
}
