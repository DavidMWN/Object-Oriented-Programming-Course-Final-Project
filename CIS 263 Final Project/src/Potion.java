
public class Potion
{
	private String description;
	private int heal;
	
	public Potion(String name, int healAmt)
	{
		description = name;
		heal = healAmt;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getHeal()
	{
		return heal;
	}
	
	public String toString()
	{
		return description + " healing potion";
	}
}
