import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class mainGame 
{ 	
	public static Player[] player = new Player[10];
	public static Card[] card = new Card[64];
	
	public static int[] playerHP = new int[5];
	public static int players=4,activePlayer = 1,turn=0,
				textSpeed=40,startingLife=20;
	public static String br = System.getProperty("line.separator");
	public static boolean isRunning=true,cardDrawn=false;
	
	static Scanner user_input = new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException
	{
	//	Clear terminal and begin the game
		clearTerm();
		cardData.create();
	//	messages.creatorInfo();
		beginInput();
		setupPlayers();
	//	beginIntro();
		while(isRunning)
		{
			playerTurn();
		}
		
	//	Clear the terminal for the ending of the game
		clearTerm();
	}
	public static void beginInput()
	{
		messages.slowPrint("Welcome to the world of Magic: TG, Players" + br);
		while(true)
		{
			messages.slowPrint("How many players? (2-4) ");
			players = user_input.nextInt();
			if(players <= 1)
			{
				messages.slowPrint("You cannot have less that 2 players"+br);
			} else if (players >= 5) 
			{
				messages.slowPrint("You cannot have more than 4 players"+br);
			} else {
				break;
			}
		}
		user_input.nextLine();
		System.out.println("");
	}
	public static void clearTerm()
	{
		//	Clear the terminal
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        messages.slowPrint(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}
	public static void setupPlayers()
	{
		//	Setup the players in the game and put them into the array
		int i;
		for(i=1;i<=players;i++)
		{	
			messages.slowPrint("Player " + i + "'s Name: ");
			player[i] = new Player(user_input.nextLine(),startingLife);
		}
		messages.slowPrint(br);
		messages.slowPrint("Welcome to the game " + messages.playerList());
		messages.slowPrint(br + br + "Are these names correct (Yes/No)? ");
				
		for(i=0;i<7;i++)
		{
			player[1].addCard(card[dice.randomCard()].getName());
		}
		
		if(!user_input.nextLine().toLowerCase().startsWith("y"))
		{
			setupPlayers();
		}
	}
	public static void beginIntro()
	{
		messages.welcomeSpeech();
		int beginner = dice.diceToss();
		activePlayer = beginner;
		messages.slowPrint(player[beginner].getName() + " goes FIRST" + br);
		wait(2);
		clearTerm();
		messages.slowPrint(player[activePlayer].getName() + "'s turn" + br);
	}
	public static void playerTurn()
	{
		messages.slowPrint("Current Life Total: "+player[activePlayer].getHP()+br);
		
	// 	Who is the ID of the player whose turn it currently is
		if(!cardDrawn){commands.drawCard(activePlayer);}
		System.out.print("> ");
		String action = user_input.nextLine();
		commands.checkForCommand(action);
	}
	public static void wait(int time)
	{
		try
		{
			Thread.sleep(time * 1000);
		} catch(InterruptedException e) { 
   			Thread.currentThread().interrupt(); 
		}
	}
	
	public static class Player extends AbstractPlayer
	{
		public Player(String name, int HP)
		{
			super(name, HP);
		}
	}
}	