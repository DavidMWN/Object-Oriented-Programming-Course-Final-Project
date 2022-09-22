
public class EnemyChar extends Character
{
	private int friendState;
	private String altName; // for when the enemy's name starts with "The" and you want to use "the" instead
	
	public EnemyChar(String enemyName, int health)
	{
		super(enemyName, health);
		friendState = 0;
	}
	
	public void setAltName(String alt)
	{
		altName = alt;
	}
	
	public String getAltName()
	{
		return altName;
	}
	
	public int useWeapon()
	{
		int strike = 1 + (int)(Math.random() * 20);
		
		//GameMain.display += ("DEBUG LINE: strike = " + strike\n\n);
		
		if (weapon.getAccuracy() >= strike)
		{	
			int damage = weapon.getDamage();
			
			GameMain.display += name + " attacks you with a ";
			GameMain.display += weapon.getType();
			GameMain.display += " for " + damage + " points of damage.";
			
			return damage;
		}
		else
		{
			GameMain.display += name + " tries to hit you with their " + weapon.getType();
			GameMain.display += ", but misses.";
				
			return 0;
		}
	}
	
	public int getFriendState()
	{
		/* The intention of this value is to monitor the player's interactions with the enemies. 
		 * A value of 0 indicates that the player has neither attacked nor attempted friendship with the enemy.
		 * A value of -1 indicates that the player has attacked the enemy without attempted friendship.
		 * A value of -2 indicates that the player attempted friendship, then decided to attack.
		 * A value of -3 indicates that the player attempted the friendship track, then picked the wrong option.
		 * To keep the logic simple, after reaching -3 a message will display once and then the value will switch to -2 for the remainder of the encounter.
		 * Raising or lowering the value should not be possible when it is -1 or -2.
		 * A value of 1 or 2 indicates that the player had engaged in progressively friendly actions.
		 * A value of 3 is the final state and will complete the first two encounters.
		*/
		return friendState;
	}
	
	public void setFriendState(int newFriendState)
	{
		friendState = newFriendState;
	}
}
