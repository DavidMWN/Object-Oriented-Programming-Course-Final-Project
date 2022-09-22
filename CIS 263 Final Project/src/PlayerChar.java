
public class PlayerChar extends Character
{
	private Potion healthPotion;
	private int potionCount;
	private int keyCount;
	private boolean heartHint;
	
	public PlayerChar (String charName, int hlth)
	{
		super(charName, hlth);
		potionCount = GameMain.PLAYER_POTION_START;
		keyCount = 0;
		heartHint = false;
	}
	
	public void setHealthPotion(Potion potion)
	{
		healthPotion = potion;
	}
	
	public int useWeapon(String opponent)
	{
		int strike = 1 + (int)(Math.random() * 20);
		
		//GameMain.display += ("DEBUG LINE: strike = " + strike);
		
		if (weapon.getAccuracy() >= strike)
		{	
			int damage = weapon.getDamage();
			
			GameMain.display += "You attack " + opponent + " with your ";
			GameMain.display += weapon.getType();
			GameMain.display += " for " + damage + " points of damage.";
			
			if (nextAtkBonus > 0)
			{
				damage += nextAtkBonus;

				GameMain.display += "\nBonus damage from successful block: " + nextAtkBonus;
				nextAtkBonus = 0;
			}
			
			return damage;
		}
		else
		{
			GameMain.display += "You miss " + opponent + " with your " + weapon.getType() + ".";
			
			if (nextAtkBonus > 0)
			{
				GameMain.display += "\nYou lose the advantage gained from your successful block.";
				nextAtkBonus = 0;
			}
				
			return 0;
		}
	}
	
	public int usePotion()
	{
		int healAmt;
		
		if ((healthPotion.getHeal() + super.getHealth()) > GameMain.MAX_PLAYER_HEALTH)
			healAmt = GameMain.MAX_PLAYER_HEALTH - super.getHealth();
		else
			healAmt = healthPotion.getHeal();
		
		if (super.getHealth() == GameMain.MAX_PLAYER_HEALTH)
		{
			GameMain.display += "You are already at full health. You instead do nothing.\n\n";
			return 0;
		}
		else
		{
			switch (potionCount)
			{
			case 0:
				GameMain.display += "You reach into your pocket only to find that you do not have any more health potions.\n";
				return 0;
			case 1:
				GameMain.display += "You use your " + healthPotion.getDescription() + " healing potion to recover " + 
						healAmt + " HP.\n";
				potionCount --;
				break;
			case 2:
			case 3:
				GameMain.display += "You use one of your " + healthPotion.getDescription() + " healing potions to recover " + 
						healAmt + " HP.\n";
				potionCount --;
				break;
			}
		}
		
		return healAmt;
	}
	
	public void addPotionCount(int change)
	{
		potionCount += change;
	}
	
	public void resetPotionCount()
	{
		potionCount = GameMain.PLAYER_POTION_START;
	}
	
	public int getPotionCount()
	{
		return potionCount;
	}
	
	public int getKeyCount()
	{
		return keyCount;
	}
	
	public void increaseKeyCount()
	{
		keyCount ++;
	}
	
	public void resetKeyCount()
	{
		keyCount = 0;
	}
	
	public boolean isHeartHint()
	{
		return heartHint;
	}
	
	public void setHeartHint(boolean set)
	{
		heartHint = set;
	}
}
