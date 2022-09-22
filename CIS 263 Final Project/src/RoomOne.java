
public class RoomOne implements GameState
{
	private int dmg;
	private int dmgReduce = 0;
	private boolean blockAttempted = false;
	private boolean potionAttempted = false;
	private boolean completeFriend = false;
	private boolean completeBattle = false;
	
	public void enterRoom()
	{	
		GameMain.display = "As the rainfall continues outside and you hear the booms of distant thunder, you open the wooden door.\n\n"
				+ "The door creaks softly as you open it. You enter into dank room, water drips from the ceiling into puddles on the floor.\n\n"
				+ "In front of you is a small, slobbering goblin.\n"
				+ "It hisses at you and brandishes its small, notched sword.\n\n";

		PlayerChar player1 = GameMain.player1;
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		GameMain.display += ("1: Attack || 2: Block || 3: Compliment || 4: Use healing potion || 5: Quit");
	}

	@Override
	public void inputProcessing(int input) 
	{
		PlayerChar player1 = GameMain.player1;
		EnemyChar enemy1 = GameMain.enemy1;
		
		GameMain.display = "";
		
		switch (input)
		{
		case 1:
		{
			dmg = player1.useWeapon(enemy1.getAltName());
			
			if (enemy1.getFriendState() > 0)
			{
				GameMain.display +=("\n\nYou can see the confusion in the goblin's eyes as you attack. He clearly thought you were serious in your attempt at parley.");
				enemy1.setFriendState(-2);
			}
			else if (enemy1.getFriendState() == 0)
				enemy1.setFriendState(-1);
			
			if (dmg > 0)
			{
				enemy1.setHealth(enemy1.getHealth()-dmg);
			}
			
			GameMain.display += System.lineSeparator();
			break;
		}
		case 2:
		{
			if (enemy1.getFriendState() == 1)
			{
				GameMain.display += "You continue, \"I see no need for any violence here. Surely two reasonable people such as "
						+ "ourselves can come to a peaceful arrangement where we both get something of value. Wouldn't you agree?\"\n";
				enemy1.setFriendState(2);
				break;	
			}
			
			if (enemy1.getFriendState() == 2)
			{
				GameMain.display += "You say to the goblin, \"Well, you goblins all like shiny things, right? If you give "
						+ "me the key you are holding, I'll give you this polished coin.\" You hold out a gold coin for the goblin.\n";
				enemy1.setFriendState(-3);
				break;
			}
			
			GameMain.display += "You attempt to block the goblin's next attack with your shield.\n";
			blockAttempted = true;
			dmgReduce = player1.useDefense();
			break;
		}
		case 3:
		{
			switch (enemy1.getFriendState())
			{
			case -2:
				GameMain.display += "\"I'm sorry! That was a mistake!\" you try to say to the goblin.\n\n"
						+ "The goblin hisses angrily at you and yells \"I will not listen to your poisoned words again, human!\"\n";
				break;
			case -1:
				GameMain.display += "You feel a moment of remorse from attacking this living creature and say, \"I'm sorry! "
						+ "Can we talk this over?\"\n\n"
						+ "The goblin hisses at you in anger. It will not listen to someone who has tried to hurt it!\n";
				break;
			case 0:
				GameMain.display += "You raise your hand in a gesture of peace and say to the goblin, "
						+ "\"I didn't realize this room would be guarded by such a handsome young creature!\"\n";
				enemy1.setFriendState(1);
				break;
			case 1:
				GameMain.display += "You continue, \"Well, as you can clearly see, I outmatch you in both strength and size. "
					+ "Since you have no chance at defeating me in combat, you should just surrender now. Wouldn't you agree?\n";
				enemy1.setFriendState(-3);
				break;
			case 2:
				GameMain.display += "You say to the goblin, \"It's simple: All I seek is the relic at the heart of this Dungeon. Since it appears "
					+ "that you are bound to guard this thing, if you let me take it then you can leave this dreary place and be free.\"\n";
				enemy1.setFriendState(3);
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
		
		if (enemy1.getHealth() > 0)
		{
			if (enemy1.getFriendState() <= 0)
				{
					if (enemy1.getFriendState() == -3)
					{
						GameMain.display += "The goblin hisses and spits at your insult to his honor, \"Tricky human! I should have known better "
								+ "than to listen to your pretty words!\" He lunges at you, swinging his sword.\n\n";
						
						enemy1.setFriendState(-2);
					}
						
					dmg = enemy1.useWeapon();
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
					
					if (enemy1.getHealth() == GameMain.ENEMY_START_HEALTH)
					{
						GameMain.display += "The goblin still looks fresh and ready for combat.\n\n";
					}
					
					if (enemy1.getHealth() < GameMain.ENEMY_START_HEALTH && enemy1.getHealth() > GameMain.ENEMY_START_HEALTH/2)
					{
						GameMain.display += "Though injured, the goblin is still feisty and ready to continue the battle.\n\n";
					}
					
					if (enemy1.getHealth() <= GameMain.ENEMY_START_HEALTH/2 && enemy1.getHealth() > GameMain.ENEMY_START_HEALTH/4)
					{
						GameMain.display += "The goblin is wincing in pain from the wounds you have dealt him, but he is not ready to give up yet.\n\n";
					}
					
					if (enemy1.getHealth() <= GameMain.ENEMY_START_HEALTH/4)
					{
						GameMain.display += "The goblin is looking haggard as he bleeds profusely from his grevious wounds, but the ferocity in his "
								+ "eyes burns all the fiercer for it. He will not relent while he still breathes.\n\n";
					}
				}
			else
				{
					if (potionAttempted == true)
					{
						GameMain.display += "The goblin hessitates for a moment, unsure of what it is you are planning to do next.\n\n";
					}
					else
					{
						switch (enemy1.getFriendState())
						{
						case 1:
							GameMain.display += "The goblin is intrigued by your pretty words and stays his hand for the moment.\n\n";
							break;
						case 2:
							GameMain.display += "The goblin looks at you carefully and replies in his slobbery voice, \"What is it you propose, human?\"\n\n";
							break;
						case 3:
							GameMain.display += "The goblin strokes his pointy, pimply chin for a moment as he carefully considers your suggestion. Finally, he says, "
								+ "\"I suppose it would be nice to see my children again. And I haven't been paid for the last two weeks. "
								+ "You've got a deal!\" The goblin sheathes his sword and pulls an iron key out from under his shirt.\n\n"
								+ "He hands you the key and then steps around you. Just before he leaves the room he turns to you and says, "
								+ "\"The guardian of the next room will not be swayed by pretty words. You will need to find another way to... warm its heart.\"\n\n"
								+ "He chuckles softly to himself as he leaves the room. A few moments later you hear the door of the main entrance open "
								+ "and then close. The goblin has left the building.\n\n"
								+ "\"Well, that was easy,\" you say to yourself as you pocket the key.\n\n";
							break;
						}
					}
				}
		}
		
		dmgReduce = 0;
		blockAttempted = false;
		potionAttempted = false;
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		//GameMain.display += "DEBUG LINE: " + enemy1.getName() + " is now at " + enemy1.getHealth() + " HP.\n";
		//GameMain.display += "DEBUG LINE: " + enemy1.getName() + " is now at Friend State " + enemy1.getFriendState() + ".\n\n\n";

		
		if (player1.getHealth() > 0 && enemy1.getHealth() > 0 && enemy1.getFriendState() < 3)
		{
			GameMain.display += "1: Attack || ";
			
			switch (enemy1.getFriendState())
			{
			case -2:
			case -1:
				GameMain.display += "2: Block || 3: Apologize || ";
				break;
			case 0:
				GameMain.display += "2: Block || 3: Compliment || ";
				break;
			case 1:
				GameMain.display += "2: Reason || 3: Threaten || ";
				break;
			case 2:
				GameMain.display += "2: Pay Off || 3: Bargain || ";
				break;
			}
			
			GameMain.display += "4: Use Healing Potion || 5: Quit";
		}
		else if (player1.getHealth() <= 0)
		{
			GameMain.changeState(GameMain.deathContinue);
		}
		else if (enemy1.getFriendState() == 3)
		{
			player1.setHeartHint(true);
			player1.increaseKeyCount();
			completeFriend = true;
			GameMain.changeState(GameMain.pressCont);
		}
		else if(enemy1.getHealth() <= 0)
		{
			GameMain.display += "With a final stroke of your sword, the goblin lies dead at your feet. You search its bloody corpse and find an iron key "
					+ "under its shirt, and a health potion at its belt.\n\n"
					+ "You also find a small, simple frame protecting a fine portrait of two goblin children within it.\n"
					+ "Shuddering at the thought of this fiend procreating, you break the frame and cast the paper drawing into the nearest puddle.\n\n";
			
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
