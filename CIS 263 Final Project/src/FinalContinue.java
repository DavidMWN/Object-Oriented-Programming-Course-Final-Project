
public class FinalContinue implements GameState
{
	public void enterRoom()
	{
		GameMain.statDis.displayVictory();
		
		GameMain.display += "1: Return Home || 5: Quit";
	}
	
	public void inputProcessing(int input)
	{
		switch (input)
		{
		case 1:
			GameMain.changeState(GameMain.gameComplete);
			break;
		case 5:
			GameMain.display = "You just beat the game, I won't let you quit now.\n\n";
			GameMain.display += "1: Return Home || 5: Quit";
			break;
		default:
			GameMain.display = "Not a valid option.\n\n";
			GameMain.display += "1: Return Home || 5: Quit";
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
