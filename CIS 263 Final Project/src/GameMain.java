import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameMain extends JFrame implements ActionListener
{
	static JTextArea txtDisplay = new JTextArea();
	static JTextArea statDisplay = new JTextArea();
	static JTextField txtInput = new JTextField();
	static JScrollPane scroll = new JScrollPane(txtDisplay);
		
	public static String display = "";
	public static String stats = "";
	
	private static GameState gameState;
	public static GameState roomOne = new RoomOne();
	public static GameState roomTwo = new RoomTwo();
	public static GameState roomThree = new RoomThree();
	public static GameState opening = new OpeningMenu();
	public static GameState hall = new MainHallway();
	public static GameState pressCont = new Continue();
	public static GameState finalCont = new FinalContinue();
	public static GameState gameComplete = new GameComplete();
	public static GameState deathContinue = new DeathContinue();
	public static GameState playerDeath = new PlayerDeath();
	public static GameState quit = new Quit();
	
	public static final int MAX_PLAYER_HEALTH = 100;
	public static final int PLAYER_POTION_START = 1;
	public static final int ENEMY_START_HEALTH = 50;
	public static final int BOSS_START_HEALTH = 80;
	
	public static PlayerChar player1 = new PlayerChar("player", MAX_PLAYER_HEALTH);
	public static EnemyChar enemy1 = new EnemyChar("The goblin", ENEMY_START_HEALTH);
	public static EnemyChar enemy2 = new EnemyChar("The skeleton", ENEMY_START_HEALTH);
	public static EnemyChar enemy3 = new EnemyChar("The ogre", BOSS_START_HEALTH);
	public static StatDisplay statDis = new StatDisplay();
	
	Weapon sword = new Weapon("sword", 15, 20, 16);
	Weapon enemySword = new Weapon("sword", 7, 15, 15);
	Weapon enemyClub = new Weapon("club", 10, 17, 15);
	Defense shield = new Defense("shield", 10, 20, 15, 10);
	Potion hlthPotion = new Potion("Potion", 20);
	
	
	public GameMain()
	{
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		add(statDisplay, BorderLayout.NORTH);
		add(txtInput, BorderLayout.SOUTH);
		
		player1.setWeapon(sword);
		player1.setDefense(shield);
		player1.setHealthPotion(hlthPotion);
		enemy1.setWeapon(enemySword);
		enemy1.setAltName("the goblin");
		enemy2.setWeapon(enemySword);
		enemy2.setAltName("the skeleton");
		enemy3.setWeapon(enemyClub);
		enemy3.setAltName("the ogre");
		
		txtInput.addActionListener(this);
		
		setSize(1400, 800);
		setResizable(true);
		
		getContentPane().setBackground(Color.black);
		Font font = new Font("DialogInput", Font.BOLD, 18);
		txtDisplay.setBackground(Color.black);
		txtDisplay.setFont(font);
		txtDisplay.setForeground(Color.white);
		txtDisplay.setLineWrap(true);
		txtDisplay.setWrapStyleWord(true);
		txtDisplay.setEnabled(false);
		
		statDisplay.setBackground(Color.black);
		statDisplay.setFont(font);
		statDisplay.setForeground(Color.white);
		statDisplay.setEnabled(false);
		txtInput.setFont(font);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		changeState(opening);
		
		txtDisplay.setText(display);
		statDisplay.setText(stats);
	}
	
	public static void main(String[] args)
	{
		new GameMain();
	}
	
	public static void changeState(GameState state)
	{
		gameState = state;
		gameState.enterRoom();
	}
	
	public static void gameReset()
	{
		// Reset all stats to beginning status: player health, potion count, keys, hints, enemy health, enemy friend state, room complete state
					
		player1.setHealth(GameMain.MAX_PLAYER_HEALTH);
		player1.resetPotionCount();
		player1.setHeartHint(false);
		player1.resetKeyCount();
			
		enemy1.setHealth(GameMain.ENEMY_START_HEALTH);
		enemy1.setFriendState(0);
		roomOne.resetRoomComplete();
				
		enemy2.setHealth(GameMain.ENEMY_START_HEALTH);
		enemy2.setFriendState(0);
		roomTwo.resetRoomComplete();
		
		enemy3.setHealth(GameMain.BOSS_START_HEALTH);
		enemy3.setFriendState(0);
		roomThree.resetRoomComplete();
		stats = "";
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int selection = 0;
		
		try {selection = Integer.parseInt(txtInput.getText());}
		
		catch (Exception ex) {return;}
		
		txtInput.setText("");
		
		gameState.inputProcessing(selection);
		
		txtDisplay.setText(display);
		txtDisplay.setCaretPosition(0);
		statDisplay.setText(stats);
	}
}
