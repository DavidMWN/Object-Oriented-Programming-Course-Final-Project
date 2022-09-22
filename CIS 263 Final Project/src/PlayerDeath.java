
public class PlayerDeath implements GameState
{
	public void enterRoom()
	{
		GameMain.display = "It seems you were not up to the challenge.\n\n";
		
		GameMain.display += "1: Try Again || 5: Quit";
	}
	
	public void inputProcessing(int input)
	{
		switch (input)
		{
		case 1:
			GameMain.gameReset();
			GameMain.changeState(GameMain.opening);
			break;
		case 5:
			GameMain.changeState(GameMain.quit);
			break;
		default:
			GameMain.display = "Not a valid option.\n\n";
			GameMain.display += "It seems you were not up to the challenge.\n\n";
			GameMain.display += "1: Try Again || 5: Quit";
			break;
		}
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
