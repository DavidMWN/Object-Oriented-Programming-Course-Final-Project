
public class StatDisplay 
{
	private int health;
	private int potions;
	private int keys;
	
	public void updateStats(int hp, int pot, int ky)
	{
		health = hp;
		potions = pot;
		keys = ky;
		displayStats();
	}
	
	public void displayStats()
	{
		GameMain.stats = "Health: " + health + " || Healing Potions: " + potions + " || Keys: " + keys;
		GameMain.stats += "\n============================================";
	}
	
	public void displayVictory()
	{
		GameMain.stats = "Health: " + health + " || Healing Potions: " + potions + " || Keys: " + keys + " || Relics: 1";
		GameMain.stats += "\n=========================================================";
	}
}
