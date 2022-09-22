
public class DeathContinue implements GameState
{
	public void enterRoom()
	{
		PlayerChar player1 = GameMain.player1;
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		GameMain.display += "You have been vanquished!\n\n";
		
		GameMain.display += "1: Continue || 5: Quit";
	}
	
	public void inputProcessing(int input)
	{
		switch (input)
		{
		case 1:
			GameMain.changeState(GameMain.playerDeath);
			break;
		case 5:
			GameMain.changeState(GameMain.quit);
			break;
		default:
			GameMain.display = "Not a valid option.\n\n";
			GameMain.display += "You have been vanquished!\n\n";
			GameMain.display += "1: Continue || 5: Quit";
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
