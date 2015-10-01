import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class mainGame 
{ 
	
	public static String name;
	public static String[] playerList = new String[5];
	public static int players = 4,turn,textSpeed=40;
	public static String br = System.getProperty("line.separator");
		
	static Scanner user_input = new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException
	{
	//	Clear terminal and begin the game
		clearTerm();
		messages.creatorInfo();
		beginInput();
		setupPlayers();
		beginIntro();
		
	//	Clear the terminal for the ending of the game
		clearTerm();
	}
	public static void beginInput()
	{
		messages.slowPrint("Welcome to the world of Magic: TG, Players" + br);
		messages.slowPrint("How many players? (2-4) ");
		players = user_input.nextInt();
			user_input.nextLine();
		System.out.println("");
	}
	public static void clearTerm()
	{
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        messages.slowPrint(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}
	public static void setupPlayers()
	{
		int i;
		for(i=1;i<=players;i++)
		{	
			messages.slowPrint("Player " + i + "'s Name: ");
			playerList[i] = user_input.nextLine();
			
		/*
			Can be used if you prefer it to say "One" instead of "1"
				,but for simplicity and speed I choose to narrow it 
				to two lines and leave the extra lines for future desires. 

			if(i==1)
			{
				messages.slowPrint("Player One's Name: ");
				players[i] = user_input.nextLine();
			} else if(i==2)
			{
				messages.slowPrint("Player Two's Name: ");
				players[i] = user_input.nextLine();
			} else if(i==3)
			{
				messages.slowPrint("Player Three's Name: ");
				players[i] = user_input.nextLine();
			} else if(i==4)
			{
				messages.slowPrint("Player Four's Name: ");	
				players[i] = user_input.nextLine();
			}
		*/
		}
		messages.slowPrint(br);
		messages.slowPrint("Welcome to the game " + messages.playerList());
		messages.slowPrint(br + br + "Are these names correct (Yes/No)? ");
		String correct = user_input.nextLine();
		if(correct.startsWith("Y")||correct.startsWith("y"))
		{
		
		}
		else 
		{
			setupPlayers();
		}
	}
	public static void beginIntro()
	{
		messages.welcomeSpeech();
		int beginner = dice.diceToss();
		messages.slowPrint("Player " + beginner + " goes FIRST");
	}
	public static void beginTurn()
	{

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
}