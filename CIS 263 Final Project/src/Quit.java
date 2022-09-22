
public class Quit implements GameState
{
	public void enterRoom()
	{
		GameMain.stats = "";
		
		GameMain.display = "****************************************************\n\n"
				+ "              Thanks For Playing\n\n"
				+ "                     Raid\n\n"
				+ "                    on the\n\n"
				+ "               Dungeon of Death!\n\n"
				+ "****************************************************\n\n";
		
		//GameMain.display += "\n\nThank you for playing!\n\n";
		GameMain.display += "Enter any number.";
	}
	
	public void inputProcessing(int input)
	{
		System.exit(0);
	}
	
	public boolean getCompleteFriend()
	{
		return false;
	}
	
	public boolean getCompleteBattle()
	{
		return false;
	}
	
	public void resetRoomComplete()
	{}
}
