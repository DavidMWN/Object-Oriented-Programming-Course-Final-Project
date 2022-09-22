
public class MainHallway implements GameState
{	
	public void enterRoom()
	{
		PlayerChar player1 = GameMain.player1;
		
		GameMain.display = "";
		
		if (player1.getKeyCount() == 0)
		{
			GameMain.display += "You have been sent on a quest by the King of the realm to retrieve a relic of great power from the dreaded Dungeon of Death.\n\n";
			GameMain.display += "After weeks of weary travel you have finally reached it on a dark and stormy night. You are now ready to begin your search.\n\n";
		}
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		options();
	}
	
	@Override
	public void inputProcessing(int input)
	{
		PlayerChar player1 = GameMain.player1;
		
		GameMain.display = "";
		
		switch (input)
		{
		case 1:
			if (GameMain.roomOne.getCompleteFriend() == true)
			{
				GameMain.display += "You have already convinced the goblin guarding that room to give you their key and leave.\n"
						+ "There is nothing else for you to do in that room.\n\n";
				options();
				break;
			}
			else if (GameMain.roomOne.getCompleteBattle() == true)
			{
				GameMain.display += "You have already slain the goblin that was within that room and taken its key.\n";
				GameMain.display += "Its blood still stains your sword.\n\n";
				options();
				break;
			}
			else
				GameMain.changeState(GameMain.roomOne);
			break;
		case 2:
			if (GameMain.roomTwo.getCompleteFriend() == true)
			{
				GameMain.display += "You have already completed that room.";
				options();
				break;
			}
			else if (GameMain.roomTwo.getCompleteBattle() == true)
			{
				GameMain.display += "You have already defeated the skeleton within that room and claimed its key.\n";
				GameMain.display += "The dust of its cursed bones still coats your clothing.\n\n";
				options();
				break;
			}
			else
			{
				if (player1.getKeyCount() < 1)
				{
					GameMain.display += "You do not yet have the key to that room.\n\n";
					options();
					break;
				}
				else
					GameMain.changeState(GameMain.roomTwo);
			}
			break;
		case 3:
			if (player1.getKeyCount() < 2)
			{
				GameMain.display += "You do not yet have the key to that room.\n\n";
				options();
				break;
			}
			else
				GameMain.changeState(GameMain.roomThree);
	
			break;
		case 4:
			player1.setHealth(player1.usePotion() + player1.getHealth());
			
			GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
			
			options();
			break;
		case 5:
			GameMain.changeState(GameMain.quit);
			break;
		default:
			GameMain.display += "Not a valid option. Try again.\n\n";
			options();
			break;
		}
	}
	
	private void options()
	{
		GameMain.display += "You are standing in a cold and damp hallway made of stone with three doors, one to your left, one to your right one in the middle.\n\n";
		
		if (GameMain.player1.getKeyCount() > 0)
			GameMain.display += "The storm outside rages on. Wind and rain beat against the main entrance and thunder booms in the distance.\n\n";
		
		GameMain.display += "The door on the left is wooden, emblazoned with the face of a hideous goblin. It has no lock.\n";
		GameMain.display += "The door on the right is also wooden; etched on the surface is a crude skull and crossbones. It has a lock.\n";
		GameMain.display += "The door in the middle is an ornate door of wood and iron. It has a lock.";
		GameMain.display += "\n\n1: Enter the left door || 2: Enter the right door || 3: Enter the middle door || 4: Use a healing potion || 5: Quit";
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
