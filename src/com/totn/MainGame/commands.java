public class commands extends mainGame
{
	public static boolean landCasted=false;
	
	public static void displayHand(int player)
	{
		//	Display the current player's hand and what they have.
		//	Can be used for cards which have ability "Reveal Hand"
	}
	public static void attack(String target)
	{
		int targetID = 0;
		//	Find player ID from String
		for(int i=1;i<=players;i++)
		{
			if(target.toLowerCase().startsWith(player[i].getName().toLowerCase()))
			{
				targetID = i;
			}
		}
		//	Declare attackers
		
		//	Attack target player
		messages.slowPrint("You targeted: (" + targetID + "): " + target);
		defend();
	}
	public static void defend() 
	{
		clearTerm();
		messages.slowPrint(player[activePlayer].getName()+" attacked you with:"+br);
		messages.slowPrint("How do you respond?"+br);
		messages.slowPrint("> ");
		String defense = user_input.nextLine();
		messages.slowPrint("You " + defense);
	}
	public static void drawCard(int player)
	{
		//	Draw a card. You have to decide whether to make it automatic
		//	Or to make it manual.
		// 	This (^) should be added to the settings page later in game dev
		
		messages.slowPrint("You have drawn a card" + br);
		cardDrawn=true;
	}
	public static void castSpell(int spellID, int target)
	{
		//	Cast the target spell from your hand
	}
	public static void castLand(int spellID)
	{
		//	Cast land from your hand and then set the variable to true
		//	so that you may only cast one land per turn
		messages.slowPrint("You played a land" + br);
		landCasted=true;
	}
	public static void endTurn()
	{
		//	Give priority to the next player going clockwise
		if(activePlayer==players) 
		{
			activePlayer=1;
		} else {
			activePlayer++;
		}
		// Setup the terminal to recieve the next player
		
		clearTerm();
		messages.slowPrint(player[activePlayer].getName() + "'s turn" + br);
		
		//	Reset the turn limit variables
		cardDrawn=false;
		landCasted=false;
		
		turn++;
		if(turn==10)
		{
			isRunning=false;	
		}
	}
	
	public static void checkForCommand(String commandText) 
	{
		//	Cross-check the user input with a list of commands
		if(commandText.toLowerCase().startsWith("end"))
		{
			endTurn();
		} else if(commandText.toLowerCase().startsWith("cast land")) {
			if(landCasted)
			{
				messages.slowPrint("You've already cast a land this turn."+br);
			} else {
				castLand(1);
			}
		} else if(commandText.toLowerCase().startsWith("draw")) {
			if(cardDrawn)
			{
				messages.slowPrint("You've already drawn a card this turn."+br);
			} else {
				drawCard(activePlayer);
			}
		} else if(commandText.toLowerCase().startsWith("attack")) {
			commandText = commandText.substring(7,commandText.length());
			if(!commandText.toLowerCase().startsWith(player[activePlayer].getName().toLowerCase()))
			{
				int targetID = 0;
				//	Find player ID from String
				for(int i=1;i<=players;i++)
				{
					if(commandText.toLowerCase().startsWith(player[i].getName().toLowerCase()))
					{
						targetID = i;
					}
				}
				if (targetID!=0)
				{
					attack(commandText);
				} else {
					messages.slowPrint("That player does not exist"+br);
				}
			} else {
				messages.slowPrint("You cannot attack yourself!"+br);
			}
		} else {
			messages.slowPrint("Command not recognized, try again?" + br);
		}
	}
}