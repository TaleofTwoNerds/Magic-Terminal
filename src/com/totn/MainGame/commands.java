public class commands extends mainGame
{
	public static boolean landCasted=false;
	public static String[] attackerName = new String[10];
	public static int[] attackerID = new int[10];
	public static int attackerNum;
	public static boolean attacking = true;
	private static int[] winner = new int[10];
	
	public static void displayHand(int playerID)
	{
		//	Display the current player's hand and what they have.
		//	Can be used for cards which have ability "Reveal Hand"
		System.out.println("You currently have "+player[playerID].getHandSize()+" cards in your hand");
		for(int i=0;i<player[playerID].getHandSize();i++)
		{
			if(!card[cardData.cardToID(player[playerID].getHand(i))].getType().toLowerCase().contains("land"))
			{
				System.out.println(player[playerID].getHand(i)+" ("+card[cardData.cardToID(player[playerID].getHand(i))].getCMC()+")");
			} else {
				System.out.println(player[playerID].getHand(i));
			}
			
		}
	}
	
	public static void displayField(Player targetPlayer)
	{
		//	Display the current player's field and what they have.
		System.out.println("You currently have "+targetPlayer.getFieldSize()+" cards in your battlefield");
		for(int i=0;i<targetPlayer.getFieldSize();i++)
		{
			if(targetPlayer.fieldCard[i].isTapped())
			{
				System.out.println(targetPlayer.getField(i).getName()+" (Tapped) ");
			} else if(targetPlayer.getField(i).getType().toLowerCase().contains("creature"))
			{
			
				System.out.println(targetPlayer.getField(i).getName() + " (" + targetPlayer.getField(i).getPower() + "/" + targetPlayer.getField(i).getToughness()+")");
			} else 
			{
				System.out.println(targetPlayer.getField(i).getName());
			}
		}
	}
	public static void attack(String target)
	{
		int[] cardToTap = new int[10];
		int numThatTap=0;
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
					boolean inField = false;
					boolean wasTapped = false;
					
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
					for(int j=0;j<player[activePlayer].getFieldSize();j++)
					{
						boolean attackerChosen = player[activePlayer].getField(j).getName().toLowerCase().contains(attackerName[i].toLowerCase());
						if(attackerChosen)
						{
						//	Check to see if it's in your field
							inField=true;
							if(!player[activePlayer].fieldCard[i].isTapped())
							{
								player[activePlayer].fieldCard[i].tap();
								cardToTap[j]=i-1;
								wasTapped=false;
							} else {
							
							}
						}
					}
					attackerID[i]=cardData.cardToID(attackerName[i]);
					if(attackerID[i]==0)
					{
						messages.slowPrint("That creature doesn't exist!"+br);
					} else if(!card[attackerID[i]].getType().toLowerCase().contains("creature")) 
					{
						messages.slowPrint("That's not a creature!"+br);
					} else if(!inField) 
					{
						messages.slowPrint("That creature is not in your battlefield."+br);
					} else if(wasTapped) 
					{
						messages.slowPrint("That creature is tapped out!"+br);
					} else 
					{
						break;
					}
				}
				if(!attacking){break;}
			}
			if(!attacking){break;}
			messages.slowPrint("You targeted: " + player[targetID].getName() + " with "+br);
			for(int i=1;i<=attackerNum;i++)
			{
				messages.slowPrint(card[attackerID[i]].getName()+" ("+card[attackerID[i]].getPower()+"/"+card[attackerID[i]].getToughness()+")"+br);
			}
			messages.slowPrint(br+"Are these creatures correct (Yes/No)? ");
		
			if(user_input.nextLine().toLowerCase().startsWith("y"))
			{
				break;	
			}
			//	Tap all attacking creatures.
			//	Set up for which checks for the number of creatures to tap and
			//	Their position in the field and then taps them.
			//	Position should be in cardToTap[] and num should be numThatTap.
			
			for(int i=0;i<=numThatTap;i++)
			{
				player[activePlayer].fieldCard[cardToTap[i]].tap();
			}
		}
		//	Attack target player
		//	But make sure that player is still attacking.
		//	This gives the player the chance to back out when
		//	Declaring which creatures to attack with.
		
		if(attacking){defend(player[targetID]);}
	}
	
	public static boolean[] defended = new boolean[10];
	
	public static void defend(Player defender) 
	{
		String[] defenderName = new String[10];
		int[] defenderID = new int[10];
		
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
		if(defense.toLowerCase().startsWith("defend"))
		{
			messages.slowPrint("You have chosen to defend"+br);
			for(int i=1;i<=attackerNum;i++)
			{
				messages.slowPrint("With what do you defend "+card[attackerID[i]].getName()+"?"+br);
				defenderName[i]=card[cardData.cardToID(user_input.nextLine())].getName();
				if(defenderName[i].toLowerCase().startsWith("none")||defenderName[i].toLowerCase().contains("nothing"))
				{
					defenderName[i]=null;
					defenderID[i]=0;
					defended[i]=false;
					System.out.println(i+" "+defended[i]);
				} else {
					defenderID[i]=cardData.cardToID(defenderName[i]);
					defended[i]=true;
				}
				System.out.println(defended[i]);
				if(defended[i])
				{
					if(card[defenderID[i]].getPower()>=card[attackerID[i]].getToughness()&&card[attackerID[i]].getPower()>=card[defenderID[i]].getPower())
					{
						winner[i]=0;
					} else {
						if(card[defenderID[i]].getPower()>=card[attackerID[i]].getToughness())
						{
							winner[i]=1;
						} else {
							winner[i]=2;
						}
					}
					System.out.println(winner[i]);
				}
				if(winner[i]==2)
				{
					for(int j=0;j<defender.getFieldSize();j++)
					{
						if(defender.fieldCard[j].getID()==defenderID[i])
						{							
							System.out.println(defender.fieldCard[j].getName()+" was destroyed!");
							defender.fieldCard[j]=null;
						}
					}
				} else if(winner[i]==1)
				{
					for(int j=0;j<player[activePlayer].getFieldSize();j++)
					{
						if(player[activePlayer].fieldCard[j].getID()==attackerID[i])
						{
							System.out.println(player[activePlayer].fieldCard[j].getName()+" was destroyed!");
							player[activePlayer].fieldCard[j]=null;
							wait(1);
						}
					}
				} else if(winner[i]==0)
				{
					for(int j=0;j<player[activePlayer].getFieldSize();j++)
					{
						if(player[activePlayer].fieldCard[j].getID()==attackerID[i])
						{
							System.out.println(player[activePlayer].fieldCard[j].getName()+" was destroyed!");
							player[activePlayer].fieldCard[player[activePlayer].getFieldSize()]=player[activePlayer].fieldCard[j];
							player[activePlayer].fieldCard[player[activePlayer].getFieldSize()]=null;
							player[activePlayer].fieldSize-=1;
							wait(1);
						}
					}
					for(int j=0;j<defender.getFieldSize();j++)
					{
						if(defender.fieldCard[j].getID()==defenderID[i])
						{							
							System.out.println(defender.fieldCard[j].getName()+" was destroyed!");
							defender.fieldCard[j]=null;
						}
					}
				} else
				{
					messages.slowPrint("Nobody died. :("+br);
				}
			}
		}
		dealDamage(defender);
	}
	public static void dealDamage(Player defender)
	{
		clearTerm();
		int totalDamage = 0;
		for(int i=1;i<=attackerNum;i++)
		{
			if(!defended[i])
			{
				totalDamage+=card[attackerID[i]].getPower();
			}
		}
		defender.dHP(-totalDamage);
		messages.slowPrint(player[activePlayer].getName() + "'s life: "+ player[activePlayer].getHP()+br);
		messages.slowPrint(defender.getName() + "'s life: "+ defender.getHP()+br);
		messages.slowPrint("Control now returns to " + player[activePlayer].getName()+br);
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
	public static void castSpell(String spellName)
	{
		//	Cast the target spell from your hand
		boolean inHand=false;
		boolean canAfford=true;
		int[] manaToTap = new int[10], manaPos = new int[10];
		int numToTap=0,totalMana=0,numTapped=0,tapping=0;
		
		int spellID = cardData.cardToID(spellName),cardPos=0;
		if(spellID!=0)
		{
			if(card[spellID].getType().toLowerCase().contains("land"))
			{
				for(int i=0;i<player[activePlayer].getHandSize();i++)
				{
					if(!player[activePlayer].getHand(i).contains("null")) 
					{
						if(player[activePlayer].getHand(i).toLowerCase().contains(spellName.toLowerCase()))
						{
							inHand = true;
							cardPos=i;
							break;
						}
					}
				}
			} else if (!card[spellID].getType().toLowerCase().contains("land")) 
			{
				canAfford = false;
				numToTap = card[spellID].getCMC();
				
				for(int i=0;i<player[activePlayer].getHandSize();i++)
				{
					if(!player[activePlayer].getHand(i).contains("null")) 
					{
						if(player[activePlayer].getHand(i).toLowerCase().contains(spellName.toLowerCase()))
						{
							inHand = true;
							cardPos=i;
							break;
						}
					}
				}
				if(inHand)
				{
				//	Check whether the player even has enough mana.
					for(int i=1;i<=player[activePlayer].getFieldSize();i++)
					{
						if(player[activePlayer].fieldCard[i-1].getType().toLowerCase().contains("land")&&!player[activePlayer].fieldCard[i-1].isTapped())
						{
							totalMana++;
						}
					}
					if(totalMana>=numToTap)
					{
						canAfford=true;
						for(int i=1;i<=player[activePlayer].getFieldSize();i++)
						{
							if(player[activePlayer].fieldCard[i-1].getType().toLowerCase().contains("land")&&!player[activePlayer].fieldCard[i-1].isTapped())
							{
								manaPos[tapping]=i;
							}
						}
						for(int j=0;j<numToTap;j++)
						{
							player[activePlayer].fieldCard[manaPos[j+1]].tap();
						}
					}
				}
			}
			if(!inHand)
			{
				messages.slowPrint("It appears you don't pocess this spell"+br);
			} else if (!canAfford)
			{
				messages.slowPrint("It appears you can't afford this spell"+br);
			} else 
			{
				if (card[spellID].getType().toLowerCase().contains("land")&&!landCasted)
				{
					messages.slowPrint("You played a land" + br);
					player[activePlayer].moveToField(card[cardData.cardToID(spellName)]);
					landCasted=true;
				} else if (card[spellID].getType().toLowerCase().contains("land")&&landCasted){
					messages.slowPrint("You already played a land this turn!" + br);
				}
				
				if (!card[spellID].getType().toLowerCase().contains("land"))
				{
					messages.slowPrint("You cast the spell" +br);
					player[activePlayer].moveToField(card[cardData.cardToID(spellName)]);
				}
			}
		} else
		{
			System.out.println("That card doesn't seem to exist.");
		}
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
			messages.slowPrint("You've removed a "+card[cardData.cardToID(disTarget)].getName()+br);
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
		
		for(int i=0;i<player[activePlayer].getFieldSize();i++)
		{
			if(player[activePlayer].fieldCard[i]!=null)
			{
				player[activePlayer].fieldCard[i].unTap();
			}
		}
		
		turn++;
		
		messages.slowPrint("Current Life Total: "+player[activePlayer].getHP()+br);

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
		} else if(commandText.toLowerCase().startsWith("draw")) 
		{
		//	Check for a command similar to draw.
		//	This is somewhat depriciated since draw is automatic and
		//	will almost always say you've already drawn a card this turn.
		
		
			if(cardDrawn)
			{
				messages.slowPrint("You've already drawn a card this turn."+br);
			} else {
				drawCard(activePlayer);
			}
		} else if(commandText.toLowerCase().startsWith("attack")) 
		{
		//	Check for a command similar to attack
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
		} else if (commandText.toLowerCase().startsWith("show")&&commandText.toLowerCase().contains("hand"))
		{
		//	Check for a command similar to Show Hand
			displayHand(activePlayer);
		} else if (commandText.toLowerCase().startsWith("show")&&commandText.toLowerCase().contains("field"))
		{
		//	Check for a command similar to Show and a possible math with field, battlfiend, anything containing field.
			displayField(player[activePlayer]);
		} else if (commandText.toLowerCase().startsWith("show")&&commandText.toLowerCase().contains("life"))
		{
		//	Check for a command similar to Show and a possible math with field, battlfiend, anything containing field.
			messages.slowPrint(br+messages.showLife()+br);
		} else if (commandText.toLowerCase().contains("cast"))
		{
		//	Check for a command similar to cast [spell]
			commandText = commandText.substring(5,commandText.length());
			castSpell(commandText);
		} else if (commandText.toLowerCase().contains("remove"))
		{
		//	Check for a command similar to remove [spell]
			commandText = commandText.substring(7,commandText.length());
			player[activePlayer].removeCard(commandText);
		} else if (commandText.toLowerCase().contains("help"))
		{
		//	Check for a command similar to remove [spell]
			messages.help(0);
		} else 
		{
		//	If you go through all of these checks and still typed it wrong.
			messages.slowPrint("Command '"+commandText+"' not recognized, try again?" + br);
		}
	}
}