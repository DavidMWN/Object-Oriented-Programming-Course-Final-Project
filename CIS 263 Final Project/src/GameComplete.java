
public class GameComplete implements GameState
{
	public void enterRoom()
	{
		if (GameMain.roomThree.getCompleteBattle())
			GameMain.display = "The storm continues for the duration of your journey home, making progress a miserable experience.\n\n";
		if (GameMain.roomThree.getCompleteFriend())
			GameMain.display = "The skies clear and the sun comes out as you leave the Dungeon.\n"
					+ "The weather remains fair for the entirety of your journey home, making for a swift and easy experience.\n\n";
		
		GameMain.display += "At last you return to the King's castle, and are swiftly escorted to his dining chamber. "
				+ "\"Ah!\" he exclaims as you approach, \"My personal Knight! Did you achieve your quest?\"\n\n"
				+ "\"I did, my lord,\" you reply as you kneel before him. Presenting the case you say, \"Here it is.\"\n\n"
				+ "The King opens the case and gasps in astonishment, \"Oh my! The fabled Hammer of Shattering itself! "
				+ "This is indeed exactly the device I required!\"\n\n"
				+ "He turns back to his plate, on which are set a number of walnuts, and a small hammer beside them. "
				+ "Without hesitating, he uses the hammer to smash open one of the walnuts with a small tap. "
				+ "It breaks open instantly, revealing the nut meat inside. \"This is much easier on my wrist!\" he remarks.\n\n"
				+ "You stare in disbelief as the King starts smashing open more walnuts.\n\n"
				+ "As he begins eating, he says to you, \"Come, now, tell me all about your little adventure!\"\n\n"
				+ "*******************************************************************************************\n\n"
				+ "                                        The End\n\n"
				+ "*******************************************************************************************\n\n";
		
		GameMain.display += "1: Play Again || 5: Quit";
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
			
			GameMain.display += "1: Play Again || 5: Quit";
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
