// package com.totn.MagicTG;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainGame { 
	
	public static String name;
	public static int players,turn,textSpeed=40;
	public static String br = System.getProperty("line.separator");
	
	// player variables
	public static String playerOne,playerTwo,playerThree,playerFour;
	
	static Scanner user_input = new Scanner( System.in );
	
	public static void main(String[] args) throws InterruptedException
	{
		clearTerm();
		Messages.creatorInfo();
		beginInput();
		setupPlayers();
		beginIntro();
		user_input.next();
		clearTerm();
	}
	public static void beginInput()
	{
		Messages.slowPrint("Welcome to the world of Magic: TG, Players" + br);
		Messages.slowPrint("How many players? (2-4) ");
		players = user_input.nextInt();
			user_input.nextLine();
		System.out.println("");
	}
	public static void clearTerm()
	{
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        Messages.slowPrint(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}
	public static void setupPlayers()
	{
		int i;
		for(i=1;i<=players;i++)
		{	
			if(i==1)
			{
				Messages.slowPrint("Player One's Name: ");
				playerOne = user_input.nextLine();
			} else if(i==2)
			{
				Messages.slowPrint("Player Two's Name: ");
				playerTwo = user_input.nextLine();
			} else if(i==3)
			{
				Messages.slowPrint("Player Three's Name: ");
				playerThree = user_input.nextLine();
			} else if(i==4)
			{
				Messages.slowPrint("Player Four's Name: ");	
				playerFour = user_input.nextLine();
			}
		}
		Messages.slowPrint(br);
		Messages.slowPrint("Welcome to the game " + Messages.playerList());
		wait(2);
	}
	public static void beginIntro()
	{
		Messages.welcomeSpeech();
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