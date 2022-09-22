
public class RoomTwo implements GameState
{
	private int dmg;
	private int dmgReduce = 0;
	private boolean blockAttempted = false;
	private boolean potionAttempted = false;
	private boolean completeFriend = false;
	private boolean completeBattle = false;
	
	public void enterRoom()
	{
		GameMain.display = ("The rusty lock turns with difficulty, and the door does not move at first, forcing you to shove it open with much noise.\n\n"
				+ "As you enter the room you notice an unnatural chill in the air. Looking at the center you see a human-sized skeleton standing there.\n\n"
				+ "Its eye sockets are filled with cold blue glow. Somehow it is moving of its own accord and is able to sense your presence.\n\n"
				+ "Around its neck is a cord with an ornate key strung from it. In its bony hand is a rusty, but sturdy sword.\n\n"
				+ "The skeleton makes no sound as it readies itself for combat.\n\n");
		
		PlayerChar player1 = GameMain.player1;
				
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		GameMain.display += "1: Attack || 2: Block || ";
		if (player1.isHeartHint())
			GameMain.display += "3: Offer Your Cloak || ";
		else
			GameMain.display += "3: Offer Greeting || ";
		
		GameMain.display += "4: Use Healing Potion || 5: Quit";
	}
	
	public void inputProcessing(int input)
	{
		PlayerChar player1 = GameMain.player1;
		EnemyChar enemy2 = GameMain.enemy2;
		
		GameMain.display = "";
		
		switch (input)
		{
		case 1:
		{
			dmg = player1.useWeapon(enemy2.getAltName());
			
			if (enemy2.getFriendState() > 0)
			{
				GameMain.display += "\nYou can see the confusion in the skeleton's movements as you attack."
						+ "\nIt clearly trusted your friendly actions (you think; its hard to tell with a skeleton).\n";
				enemy2.setFriendState(-2);
			}
			else if (enemy2.getFriendState() == 0)
				enemy2.setFriendState(-1);
			
			if (dmg > 0)
			{
				enemy2.setHealth(enemy2.getHealth()-dmg);
			}
				
			GameMain.display += System.lineSeparator();
			break;
		}
		case 2:
		{
			if (enemy2.getFriendState() == 1)
			{
				GameMain.display += "You hold out your arms in the universal gesture of offering a hug. "
						+"\"Come here, buddy,\" you say, even though you're not sure if it can actually hear you.\n";
				enemy2.setFriendState(2);
				break;
			}
			
			if (enemy2.getFriendState() == 2)
			{
				GameMain.display += "Sensing its loneliness you continue to hug the poor, bony creature for a few more moments, then release it.\n";
				enemy2.setFriendState(3);
				break;
			}
			
			GameMain.display += "You attempt to block the skeleton's next attack with your shield.\n";
			blockAttempted = true;
			dmgReduce = player1.useDefense();
			break;
		}
		case 3:
		{
			switch (enemy2.getFriendState())
			{
			case -2:
				GameMain.display += "\"Um, oops, my bad!\" you stammer, holding your hand up in apology.\n"
						+ "But the skeleton cannot hear you and is no longer receptive to your mixed actions.\n";
				break;
			case -1:
				GameMain.display += "The skeleton is already agitated and will not entertain friendly overtures from one who has attacked it.\n";
				break;
			case 0:
				if (player1.isHeartHint() == false)
				{
					GameMain.display += "You try to find an appropriate way to compliment the skeleton, and come up with, "
							+ "\"My, what excellent... bone structure you have there.\n\n"
							+ "Unfortunately, the skeleton has no ears with which to hear your words.\n"
							+ "You shiver in the freezing air and wonder if the skeleton also feels the cold.\n";
					player1.setHeartHint(true);
					break;
				}
				else
				{
					GameMain.display += "You make an intuitive leap and reason that the skeleton might be bound by the cold, instead of causing it. "
							+ "You remove your cloak and approach the skeleton, saying \"Here, you look freezing! Put this on.\"\n\n"
							+ "You move to place it around the skeleton's shoulders.\n";
					enemy2.setFriendState(1);
					break;
				}
			case 1:
				GameMain.display += "Feeling awkward from this display of affection towards a cursed creature, you give it a hearty "
						+ "pat on the back. Unfortunately, you misjudge how fragile the skeleton is, and hear the soft crack of a rib.\n";			
				enemy2.setFriendState(-3);
				break;
			case 2:
				GameMain.display += "Uncertain of how much longer you can safely keep this cursed thing close to you, you reach "
						+ "for the cord around its neck, hoping to discreetly snatch the key.\n\n"
						+ "The skeleton is more aware than you realized, however, and as soon as you begin to slide the key upward "
						+ "its head jerks up and it breaks free from your embrace.\n";
				enemy2.setFriendState(-3);
				break;
			}
			
			break;
		}
		case 4:
		{
			player1.setHealth(player1.usePotion() + player1.getHealth());
			GameMain.display += System.lineSeparator();
			potionAttempted = true;
			break;
		}
		case 5:
			GameMain.changeState(GameMain.quit);
			return;
		default:
			GameMain.display += "Not a valid option. You have wasted your opportunity to make a move.\n";
			break;
		}
		
		GameMain.display += System.lineSeparator();
		
		if (enemy2.getHealth() > 0)
		{
			if (enemy2.getFriendState() <= 0)
			{
				if (enemy2.getFriendState() == -3)
				{
					GameMain.display += "The skeleton jumps back and brandishes its sword. Your cloak falls from its shoulders.\n\n"
							+ "You think you see anger in its glowing eye sockets, caused by your untimely social faux pas.\n\n";
					
					enemy2.setFriendState(-2);
				}
				
				dmg = enemy2.useWeapon();
				GameMain.display += "\n\n";
				
				if (dmg > 0)
				{
					if (dmgReduce > 0)
					{
						GameMain.display += "You succesfully block " + dmgReduce + " points of damage and gain " + player1.getNextAtkBonus() + " points of bonus damage on your next attack.\n\n";
						
						if (dmgReduce > dmg)
							dmgReduce = dmg;
						
						player1.setHealth(player1.getHealth() - dmg + dmgReduce);
					}
					else
					{
						if (blockAttempted == true)
							GameMain.display += "You failed to block the attack.\n\n";
						
						player1.setHealth(player1.getHealth()-dmg);
					}
				}
				else
				{
					if (dmgReduce > 0)
					{
						GameMain.display += "Your block attempt will give you " + (player1.getNextAtkBonus() / 2) + " points of bonus damage on your next attack.\n\n";
						player1.setNextAtkBonus(player1.getNextAtkBonus() / 2);
					}
					else
					{
						if (blockAttempted == true) 
							GameMain.display += "You fumbled the block attempt anyway and gain no bonus on your next attack.\n\n";
					}
				}
				
				if (enemy2.getHealth() == GameMain.ENEMY_START_HEALTH)
				{
					GameMain.display += "The skeleton is still perfectly intact and in a fighting stance.\n\n";
				}
				
				if (enemy2.getHealth() < GameMain.ENEMY_START_HEALTH && enemy2.getHealth() > GameMain.ENEMY_START_HEALTH/2)
				{
					GameMain.display += "A few of the skeleton's bones are cracked from where you have struck it, but it does not appear to have noticed.\n\n";
				}
				
				if (enemy2.getHealth() <= GameMain.ENEMY_START_HEALTH/2 && enemy2.getHealth() > GameMain.ENEMY_START_HEALTH/4)
				{
					GameMain.display += "The skeleton's bones now bear several cracks, and few of the ribs are missing, yet it persists in its attack.\n\n";
				}
				
				if (enemy2.getHealth() <= GameMain.ENEMY_START_HEALTH/4)
				{
					GameMain.display += "The chipped and cracked skeleton is now missing several bones, but its combat ability is somehow unhindered. "
							+ "Yet from the flicker of the glowing eye sockets, you can tell that whatever curse is holding this thing together is failing.\n\n";
				}
			}
			else
			{
				if (potionAttempted == true)
				{
					GameMain.display += "The skeleton pauses for moment, unsure of what it is you plan on doing.\n\n";
				}
				else
				{
					switch (enemy2.getFriendState())
					{
					case 1:
						GameMain.display += "The skeleton, apparently sensing your kind motives (or at least your non-threatening motions) stays its sword hand and ";
						GameMain.display += "allows you to place your warm cloak over its shoulders.\n\n";
						GameMain.display += "Its movements suggest it feels relief, as if it indeed is feeling warmth for the first time in ages.\n\n";
						break;
					case 2:
						GameMain.display += "Strangely, the skeleton also allows this. It shudders as you embrace it, "
								+ "as though it has almost forgotten what it felt like to feel the kind touch of a living thing.\n\n";
						break;
					case 3:
						GameMain.display += "The skeleton seems satisfied, now. You get the feeling it might be crying if it still had the proper fleshy bits for that. "
								+ "Instead, the glow in its eyes fades to nothing as a faint wisp of light rises from its body.\n\n"
								+ "You hear a soft sigh of relief as the light departs. The skeleton, now having no spirit bound to it, collapses to the floor.\n\n"
								+ "The unnatural chill leaves the room along with the spirit.\n\n"
								+ "You pause for a moment to reflect on this strange encounter, then bend down to take the ornate key\nthat was hanging around the skeleton's neck.\n\n"
								+ "You also pick up your cloak and put it back on.\n\n";
						break;
					}	
				}
			}
		}
		
		dmgReduce = 0;
		blockAttempted = false;
		potionAttempted = false;
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		//GameMain.display += "DEBUG LINE: " + enemy2.getName() + " is now at " + enemy2.getHealth() + " HP.\n";
		//GameMain.display += "DEBUG LINE: " + enemy2.getName() + " is now at Friend State " + enemy2.getFriendState() + ".\n\n\n";
		
		if (player1.getHealth() > 0 && enemy2.getHealth() > 0 && enemy2.getFriendState() < 3)
		{
			GameMain.display += "1: Attack || ";
			
			switch (enemy2.getFriendState())
			{
			case -2:
			case -1:
				GameMain.display += "2: Block || 3: Apologize || ";
				break;
			case 0:
				if (!player1.isHeartHint())
					GameMain.display += "2: Block || 3: Compliment || ";
				else
					GameMain.display += "2: Block || 3: Offer Your Cloak || ";
				break;
			case 1:
				GameMain.display += "2: Offer A Hug || 3: Pat On The Back || ";
				break;
			case 2:
				GameMain.display += "2: Continue Hug || 3: Take Key || ";
				break;
			}	
			
			GameMain.display += "4: Use Healing Potion || 5: Quit";
		}
		else if (player1.getHealth() <= 0)
		{
			GameMain.changeState(GameMain.deathContinue);
		}
		else if (enemy2.getFriendState() == 3)
		{
			GameMain.display += "Taking one last glance around the room, you spot a health potion on a small table in the corner. "
					+ "You wonder briefly why it is there, but after smelling the contents you are satisfied of its qualty and pocket it.\n\n";
			
			player1.increaseKeyCount();
			player1.addPotionCount(1);
			completeFriend = true;
			GameMain.changeState(GameMain.pressCont);
		}
		else if(enemy2.getHealth() < 0)
		{
			GameMain.display += "The skeleton finally falls apart with your last blow. You can still see the glow of its eye sockets flickering. "
					+ "Thinking quickly, you stomp the skull with your boot. You hear a faint shriek as the ancient bone crumbles.\n\n"
					+ "The unnatural chill leaves the room as the spirit is destroyed.\n\n"
					+ "You take a moment to catch your breath, then bend down to pick up the ornate key that was hanging around the skeleton's neck.\n";
			
			if (enemy2.getFriendState() == -2)
			{
				GameMain.display += "You also pick up your cloak from off the floor and cast it about your shoulders.\n";
			}
						
			GameMain.display += "\nTaking one last glance around the room, you spot a health potion on a small table in the corner. "
					+ "You wonder briefly why it is there, but after smelling the contents you are satisfied of its qualty and pocket it.\n\n";
			player1.increaseKeyCount();
			player1.addPotionCount(1);
			
			completeBattle = true;
			GameMain.changeState(GameMain.pressCont);
		}	
	}
	
	public boolean getCompleteFriend()
	{
		return completeFriend;
	}
	
	public boolean getCompleteBattle()
	{
		return completeBattle;
	}
	
	public void resetRoomComplete()
	{
		completeFriend = false;
		completeBattle = false;
	}
}
