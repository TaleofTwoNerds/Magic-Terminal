public class commands extends mainGame
{
	public static boolean landCasted=false;
	public static String[] attackerName = new String[10];
	public static int[] attackerID = new int[10];
	public static int attackerNum;
	public static boolean attacking = true;
	
	public static void displayHand(int playerID)
	{
		//	Display the current player's hand and what they have.
		//	Can be used for cards which have ability "Reveal Hand"
		System.out.println("You currently have "+player[playerID].getHandSize()+" cards in your hand");
		for(int i=0;i<player[playerID].getHandSize();i++)
		{
			messages.slowPrint(player[playerID].getHand(i)+br);
		}
	}
	public static void attack(String target)
	{
		attacking=true;
		int targetID = 0;
		//	Find player ID from String
		for(int i=1;i<=players;i++)
		{
			if(target.toLowerCase().startsWith(player[i].getName().toLowerCase()))
			{
				targetID = i;
			}
		}
		
		while(true)
		{
			while(true)
			{
			//	Declare number of attackers
				messages.slowPrint("How many creatures do you attack with?: ");
				attackerNum = user_input.nextInt();
				if(attackerNum<=9){break;}
				else{messages.slowPrint("Attacking must contain between 1-9 attackers.");}
			}
			user_input.nextLine();
			for(int i=1;i<=attackerNum;i++)
			{
				while(true)
				{
				//	Declare attackers
					messages.slowPrint("Attacker " + i + ": ");
					attackerName[i]=user_input.nextLine();
					if(attackerName[i].toLowerCase().startsWith("end"))
					{
					//	If a player types end while attacking it will 
					//	break the loop and stop the attack.
					
						attacking=false;
						break;
					}
					attackerID[i]=cardData.cardToID(attackerName[i]);
					if(attackerID[i]==0)
					{
						messages.slowPrint("That creature doesn't exist!"+br);
					} else {
						break;
					}
				}
				if(!attacking){break;}
			}
			if(!attacking){break;}
			messages.slowPrint("You targeted: " + player[targetID].getName() + " with "+br);
			for(int i=1;i<=attackerNum;i++)
			{
				messages.slowPrint(attackerName[i]+" ("+card[attackerID[i]].getPower()+"/"+card[attackerID[i]].getToughness()+")"+br);
			}
			messages.slowPrint(br+"Are these creatures correct (Yes/No)? ");
		
			if(user_input.nextLine().toLowerCase().startsWith("y"))
			{
				break;	
			}
		}
		//	Attack target player
		//	But make sure that player is still attacking.
		//	This gives the player the chance to back out when
		//	Declaring which creatures to attack with.
		
		if(attacking){defend(player[targetID]);}
	}
	public static void defend(Player defender) 
	{
		clearTerm();
		messages.slowPrint(defender.getName()+"'s chance to defend"+br);
		messages.slowPrint(player[activePlayer].getName()+" is attacking you with:"+br);
		for(int i=1;i<=attackerNum;i++)
		{
			messages.slowPrint(attackerName[i]+" ("+card[attackerID[i]].getPower()+"/"+card[attackerID[i]].getToughness()+")"+br);
		}
		messages.slowPrint("How do you respond?"+br);
		messages.slowPrint("> ");
		String defense = user_input.nextLine();
		messages.slowPrint("You " + defense +br);
	}
	public static void drawCard(int playerID)
	{
		//	Draw a card. You have to decide whether to make it automatic
		//	Or to make it manual.
		// 	This (^) should be added to the settings page later in game dev
		player[playerID].addCard(card[dice.randomCard()].getName());
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
		String disTarget;
		
		//	Make sure you have less than 7 cards
		if(player[activePlayer].getHandSize()>=8)
		{
			messages.slowPrint("You have too many cards in your hand!"+br);
			messages.slowPrint("Select a card to remove!"+br+br);
			displayHand(activePlayer);
			while(true)
			{
				System.out.print("> ");
				disTarget = user_input.nextLine();
				int discard = cardData.cardToID(disTarget);
				if(discard==0)
				{
					messages.slowPrint("That card doesn't exist!"+br);
				} else {
					boolean possesion=false;
					for(int i=1;i<player[activePlayer].getHandSize();i++)
					{
						System.out.println(player[activePlayer].getHand(i));
						if(cardData.cardToID(player[activePlayer].getHand(i))==discard)
						{
							possesion=true;
							break;
						} else {
							possesion=false;
						}
					}
					if(possesion)
					{
						break;
					} else {
						messages.slowPrint("You don't own that card!"+br);
					}
				}
			}
			player[activePlayer].removeCard(disTarget);
			messages.slowPrint("You've removed a "+disTarget+br);
			wait(1);
		}
		
		messages.slowPrint("End of turn"+br);
		messages.slowPrint("Current Life Total: "+player[activePlayer].getHP());
		wait(1);
		
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
		} else if(commandText.toLowerCase().startsWith("cast land")) 
		{
			if(landCasted)
			{
				messages.slowPrint("You've already cast a land this turn."+br);
			} else {
				castLand(1);
			}
		} else if(commandText.toLowerCase().startsWith("draw")) 
		{
			if(cardDrawn)
			{
				messages.slowPrint("You've already drawn a card this turn."+br);
			} else {
				drawCard(activePlayer);
			}
		} else if(commandText.toLowerCase().startsWith("attack")) 
		{
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
		} else if (commandText.toLowerCase().startsWith("show hand"))
		{
			displayHand(activePlayer);
		} else 
		{
			messages.slowPrint("Command not recognized, try again?" + br);
		}
	}
}