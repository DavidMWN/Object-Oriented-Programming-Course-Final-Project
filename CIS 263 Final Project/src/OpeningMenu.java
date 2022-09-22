
public class OpeningMenu implements GameState
{
	public void enterRoom()
	{
		GameMain.display = "****************************************************\n\n"
					+ "                     Raid\n\n"
					+ "                    on the\n\n"
					+ "               Dungeon of Death!\n\n"
					+ "****************************************************\n\n";
		GameMain.display += "           By: David Novak || CIS263\n"
					+ "                   May 2022\n\n\n\n"
					+ "To play the game, simply type the number of \nthe option you want and press enter.\n\n";
		
		
		GameMain.display += "1: Begin the Game || 2: Explain the Game || 5: Quit";
	}
	
	@Override
	public void inputProcessing(int input) 
	{
		switch (input)
		{
		case 1:
			GameMain.changeState(GameMain.hall);
			break;
		case 2:
			GameMain.display = "At the top of the display you will see important information like your Health, Healing Potions in your inventory, ";
			GameMain.display += "and the number of Keys you have collected.\n\n";
			
			GameMain.display += "The main display will show a description of the current scenario, and the results of the actions you have taken.\n\n";

			GameMain.display += "At the bottom of the display you will find all of your current options. Type the number of the action you want to take and press enter.\n";
			GameMain.display += "Only numbers are accepted as input.\n\n";
			
			GameMain.display += "You will always have the option to attack your enemy or block their next attack. Success is not guaranteed for these actions.\n";
			GameMain.display += "Successful blocks will give you bonus damage on your next successful attack.\n\n";
			
			GameMain.display += "Healing potions will restore a portion of your health.\nUp to your starting health of 100 points.\n\n";
			
			GameMain.display += "You can also try to befriend your opponent, but they may not be responsive if you've already attacked them.\n";
			//GameMain.display += "You might be surprised at how easy it is to make a new friend.\n\n";
			GameMain.display += "Defeat or befriend your opponent to clear a room and receive the key to the next room.\n\n";
			
			GameMain.display += "Clear each room to win.\n\n";
			GameMain.display += "1: Begin the Game || 2: Explain the Game || 5: Quit";
			break;
		case 5:
			GameMain.changeState(GameMain.quit);
			break;
		default:
			GameMain.display = "Not a valid option, try again\n\n";
					
			GameMain.display += "1: Begin the Game || 2: Explain the Game || 5: Quit";
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
