
public class Defense
{
	private String blocker;
	private int maxDmgReduce;
	private int minDmgReduce;
	private int success;
	private int atkBonus;
	
	public Defense(String blkName, int maxReduce, int minReduce, int suc, int bonus)
	{
		blocker = blkName;
		maxDmgReduce = maxReduce;
		minDmgReduce = minReduce;
		success = suc;
		atkBonus = bonus;
	}
	
	public String getBlocker()
	{
		return blocker;
	}
	
	public int getSuccess()
	{
		return success;
	}
	
	public int getDamageBlocked() 
	{
		int rangeSize = maxDmgReduce - minDmgReduce;
		int blocked = minDmgReduce + (int)(Math.random() * rangeSize);
		
		return blocked;
	}
	
	public int getAtkBonus()
	{
		return atkBonus;
	}
	
	public String toString() 
	{
		return blocker + " (" 
				+ minDmgReduce + "-" + maxDmgReduce + " damage blocked, "
				+ success + " success chance)";
	}
}
