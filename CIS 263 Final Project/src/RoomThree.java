
public class RoomThree implements GameState
{
	private int dmg;
	private int dmgReduce = 0;
	private boolean blockAttempted = false;
	private boolean potionAttempted = false;
	private boolean completeFriend = false;
	private boolean completeBattle = false;
	
	public void enterRoom()
	{
		PlayerChar player1 = GameMain.player1;
		
		GameMain.display = "You insert the ornate key into the lock of the door. Turning it firmly, the lock opens smoothly with a soft noise. "
				+ "The door opens with only the slightest creak. Thunder from the storm outside booms loudly.\n\n"
				+ "Sitting on a mighty throne in the center of the room is a rather large ogre. To your surprise he begins speaking intelligibly. "
				+ "\"So, human, you have dared to enter my lair! ";
		
		if (GameMain.roomOne.getCompleteFriend() && GameMain.roomTwo.getCompleteFriend())
			GameMain.display += "And I see you have dismissed my minions with your unanticipated kindness.\"\n\n";
		
		if (GameMain.roomOne.getCompleteBattle() && GameMain.roomTwo.getCompleteBattle())
			GameMain.display += "And I see that you are a fierce warrior.\"\n\n";
		
		if (GameMain.roomOne.getCompleteFriend() && GameMain.roomTwo.getCompleteBattle())
			GameMain.display += "And it seems you are as skilled with your tongue as you are with your blade.\"\n\n";
		
		if (GameMain.roomOne.getCompleteBattle() && GameMain.roomTwo.getCompleteFriend())
			GameMain.display += "And it is remarkable how inconsistant your vaunted human capacity for kindness is.\"\n\n";
		
		GameMain.display += "The ogre stands up, and you see that he is wielding a terrifyingly large club. "
				+ "He continues, \"Regardless, you will find it rather more difficult to dispatch me from my home!\"\n\n";				
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		GameMain.display += "1: Attack || 2: Block || 3: Explain Yourself || 4: Use healing potion || 5: Quit";
	}
	
	public void inputProcessing(int input)
	{
		PlayerChar player1 = GameMain.player1;
		EnemyChar enemy3 = GameMain.enemy3;
		
		GameMain.display = "";
		
		switch (input)
		{
		case 1:
		{
			dmg = player1.useWeapon(enemy3.getAltName());
			
			if (enemy3.getFriendState() > 0)
			{
				GameMain.display += "\nThe ogre is briefly taken aback by your attack. \"You dare try to trick me?!\" he roars. "
						+ "He raises his club above his head, ready to strike at you, \"I'll smash you for this!\"\n";
				enemy3.setFriendState(-2);
			}
			else if (enemy3.getFriendState() == 0)
				enemy3.setFriendState(-1);
			
			if (dmg > 0)
			{
				enemy3.setHealth(enemy3.getHealth()-dmg);
			}
				
			GameMain.display += System.lineSeparator();
			break;
		}
		case 2:
		{
			if (enemy3.getFriendState() == 1)
			{
				GameMain.display += "Shaking in fear of the giant ogre's giant club, you pull out your coin purse and say, \"Here, take it! "
						+ "There's about 80 gold pieces in there. That's enough, right?\"\n";
				
				enemy3.setFriendState(-3);
				break;
			}
			
			if (enemy3.getFriendState() == 2)
			{
				GameMain.display += "\"Well, one step at a time,\" you say cautiously. \"You know how things often go between ogres and humans. "
						+ "But if we can make a deal here and stick to it, I'll happily vouch for you as the King's personal Knight.\"\n\n"
						+ "You bravely hold out your hand to the ogre. \"So, do we have an agreement?\"\n";
						
				enemy3.setFriendState(3);
				break;
			}
			
			GameMain.display += "You attempt to block the ogre's next attack with your shield.";
			blockAttempted = true;
			dmgReduce = player1.useDefense();
			GameMain.display += System.lineSeparator();
			break;
		}
		case 3:
		{
			switch (enemy3.getFriendState())
			{
			case -2:
			case -1:
				GameMain.display += "\"I'm sorry,\" you begin to say, but the ogre just roars in your face. He is in no mood for apologies.\n";
				break;
			case 0:
				GameMain.display += "\"Please, I have desperate need of the relic that you possess,\" you say quickly as the ogre prepares to attack you. "
						+ "\"And it is well known that you do not use it. Can we not come to a peaceful arrangement? With payment!\"\n";
				enemy3.setFriendState(1);
				break;
			case 1:
				GameMain.display += "\"My lord the King can make this place an official outpost of the realm,\" you explain to the ogre. "
						+ "\"As its Warden, you'll be paid fairly and regularly. You'll be allowed to hire whatever guards you like. "
						+ "Plus, I know the King would be very interested in the magic of yours that enchanted that skeleton guardian.\"\n";
				enemy3.setFriendState(2);
				break;
			case 2:
				GameMain.display += "\"Of course,\" you say encouragingly. \"The University will be ecstatic to enroll someone of your "
						+ "stature... I mean, skill into their ranks!\" You hope desperately that the ogre did not catch your slip of the tongue.\n";
				enemy3.setFriendState(-4);
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
		
		if (enemy3.getHealth() > 0)
		{
			if (enemy3.getFriendState() <= 0)
			{
				if (enemy3.getFriendState() == -3)
				{
					GameMain.display += "The ogre glares at you, \"80 coin is hardly enough to keep this place up and pay for a new guard!\n\n"
							+ "And furthermore!\" it's voice begins to rise to a roar, \"I don't appreciate how you're holding your purse out "
							+ "and trembling like I'm some common highwayman! No more talk! This ends in smashing!\"\n\n";
					
					enemy3.setFriendState(-2);
				}
				
				if (enemy3.getFriendState() == -4)
				{
					GameMain.display += "The ogre gasps in offense. \"Wait a minute,\" it says, \"You're MOCKING me! How dare you!\"\n"
							+ "He roars in anger and brandishes his mighty club once again.\n";
					
					enemy3.setFriendState(-2);
				}
				
				dmg = enemy3.useWeapon();
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
				
				if (enemy3.getHealth() == GameMain.BOSS_START_HEALTH)
				{
					GameMain.display += "The ogre is untouched and looking as menacing as ever.\n\n";
				}
				
				if (enemy3.getHealth() < GameMain.BOSS_START_HEALTH && enemy3.getHealth() > GameMain.BOSS_START_HEALTH/2)
				{
					GameMain.display += "You've landed a few blows on the ogre with your sword, but he doesn't seem to have noticed them at all.\n\n";
				}
				
				if (enemy3.getHealth() <= GameMain.BOSS_START_HEALTH/2 && enemy3.getHealth() > GameMain.BOSS_START_HEALTH/4)
				{
					GameMain.display += "You've managed to bloody the ogre up somewhat. It's starting to gasp in pain, but shows no signs of relenting.\n\n";
				}
				
				if (enemy3.getHealth() <= GameMain.BOSS_START_HEALTH/4)
				{
					GameMain.display += "The ogre is a bloody mess now. You're sure it won't take much more effort to defeat this beast.\n\n";
				}
			}
			else
			{
				if (potionAttempted == true)
				{
					GameMain.display += "The ogre pauses for moment, unsure of what it is you plan on doing.";
				}
				else
				{
					switch (enemy3.getFriendState())
					{
					case 1:
						if (GameMain.roomOne.getCompleteFriend())
						{
							GameMain.display += "The ogre pauses his advance, eyeing you suspiciously. \"Well,\" he says, \"I am short on coin, "
									+ "which is why I wasn't able to pay little Fiabulx in a timely manner. Perhaps there is some merit in your proposal.\"\n\n";
							break;
						}
						else
						{
							GameMain.display += "And little Fiabulx's children needed their father, but you didn't consider that when you slaughtered him. "
									+ "Worse then that, you disrespected them be defiling their portrait! Coin will not suffice for this offense!\n\n";
							enemy3.setFriendState(-2);
							break;
						}
					case 2:
						if (GameMain.roomTwo.getCompleteFriend())
						{
							GameMain.display += "The ogre seems rather pleased with himself, \"Ah yes, that was quite the tricky spell to cast. "
									+ "I've only been able to bind lonely spirits so far, which is why you were able to dismiss it.\"\n\n"
									+ "\"But with royal resources and approval, I could expand my research. Maybe even earn a spot at the Mage's University.\"\n\n";
							break;
						}
						else
						{
							GameMain.display += "The ogre looks at you menacingly and says, \"Oh, you mean the skeleton that you destroyed? "
									+ "NOW you appreciate its value? Typical human! Just smash what you don't understand and lie about it later!\"\n\n";
							enemy3.setFriendState(-2);
							break;
						}
					case 3:
						GameMain.display += "The ogre looks at your small hand and grunts, \"Hmph. You're not wrong about your people and mine. "
									+ "But I'm willing to give it a try.\" The ogre sets down his club and gently shakes your fragile hand.\n\n"
									+ "He then turns around and goes behind the chair for a moment. You hear the clunk of a lock being opened. "
									+ "A moment later he returns, bearing a (comparatively) small, ornate case in his large hand.\n\n"
									+ "\"Here it is, human,\" he says as he hands you the case. \"The Hammer of Shattering. "
									+ "It is enchanted to destroy any object that it strikes. It sounded useful when I acquired it, but "
									+ "it's far too small for my hands. Any truthfully, smashing skulls with my club is much more fun.\"\n\n"
									+ "You take the case and open it to reveal a beautiful one-handed hammer, forged of dwarven steel, gold, and obsidian. "
									+ "As you close the case and stow it among your travel gear, the ogre inquires \"What does your King need it for?\"\n\n"
									+ "\"I'm not privy to all his designs,\" you reply, \"but he was clear that nothing less would suffice for needs of the realm.\"\n\n";
						break;
					}	
				}
			}
		}
		
		dmgReduce = 0;
		blockAttempted = false;
		potionAttempted = false;
		
		GameMain.statDis.updateStats(player1.getHealth(), player1.getPotionCount(), player1.getKeyCount());
		
		//GameMain.display += "DEBUG LINE: " + enemy3.getName() + " is now at " + enemy3.getHealth() + " HP.\n";
		//GameMain.display += "DEBUG LINE: " + enemy3.getName() + " is now at Friend State " + enemy3.getFriendState() + ".\n\n\n";
		
		if (player1.getHealth() > 0 && enemy3.getHealth() > 0 && enemy3.getFriendState() < 3)
		{
			GameMain.display += "1: Attack ";
			
			switch (enemy3.getFriendState())
			{
			case -2:
			case -1:
				GameMain.display += "|| 2: Block || 3: Apologize || ";
				break;
			case 0:
				GameMain.display += "|| 2: Block || 3: Explain Yourself || ";
				break;
			case 1:
				GameMain.display += "|| 2: Offer All Your Money || 3: Offer Job || ";
				break;
			case 2:
				GameMain.display += "|| 2: Temper Hopes || 3: Encourage Hopes || ";
				break;
			}	
			
			GameMain.display += "4: Use healing potion || 5: Quit";
		}
		else if (player1.getHealth() <= 0)
		{
			GameMain.changeState(GameMain.deathContinue);
		}
		else if (enemy3.getFriendState() == 3)
		{
			GameMain.display += "You nod respectfully to the ogre and leave his Dungeon of Death, having accomplished your goal.\n\n";
			
			completeFriend = true;
			GameMain.changeState(GameMain.finalCont);
		}
		else if(enemy3.getHealth() < 0)
		{
			GameMain.display += "After a pitched battle, you finally land the killing blow on the ogre. It falls to the ground with a loud thump. "
					+ "After taking a moment to catch your breath, you search the mighty corpse and find one final key.\n\n"
					+ "Taking the small key in your hand, you search the room for the lock that it goes to, and spot a safe behind the ogre's large chair. "
					+ "Unlocking and opening the safe, you find small, ornate case inside. You take it and open it with care.\n\n"
					+ "Inside is the relic you were sent by your King to retrieve: The Hammer Of Shattering. "
					+ "It's a beautiful one-handed hammer, forged of dwarven steel, gold, and obsidian, enchanted to destroy any object it strikes.\n\n"
					+ "Closing the case and stowing it in your travel pack, you thank your lucky stars the ogre didn't try to smash your head with it. "
					+ "You're not sure what the King wanted the hammer for, but you trusted him when he said it was for the good of the realm.\n\n"
					+ "You stand up and leave the Dungeon of Death, having accomplished your goal.\n\n";
	
			completeBattle = true;
			
			GameMain.changeState(GameMain.finalCont);
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
