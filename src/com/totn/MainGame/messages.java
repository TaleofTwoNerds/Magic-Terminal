public class messages extends mainGame 
{
	/*
		This class contains all of the timing for the game's messages.
		These messages are called from other parts of the game depending
			which message you want at which point in the game
		Each set of messages is in their own class and has timing and words 
			included along with breaks.
	*/
	
	public String[] welcome = new String[12];	

	public static void slowPrint(String message)
    {
    //	Creates the slow scrolling text because it makes the game more
    //	dynamic
    	
        for (int i = 0; i < message.length(); i++)
        {
            System.out.print(message.charAt(i));
            
            try
            {
                Thread.sleep(textSpeed);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
	
	public static void showLife()
	{
	//	Setup the list of players in the game
		String value=null;
		for(int i=1;i<=players;i++)
		{
			System.out.println(player[i].getName()+": "+player[i].getHP());
		}
	}
	
	public static void cardInfo(Card card)
	{
		System.out.println("Name: "+card.getName());
		if(!card.isLand())
		{
			System.out.println("P/T: ("+card.getPower()+"/"+card.getToughness()+")");
		}
		if(card.getAbility()!=null)
		{
			System.out.println("Ability: "+card.getAbility());
		}
		System.out.println("Cost: "+card.getCMC());
		System.out.println("Type: "+card.getType());
		System.out.println("ID: "+card.getID());
	}
	
	public static void help(int type)
	{
	//	Type 0 = commands
		if(type==0)
		{
			String[] help = new String [10];
			help[0]="Draw - Draw a card";
			help[1]="Cast [Spell Name] - Cast a spell";
			help[2]="Show hand- Shows current player's hand";
			help[3]="Attack [Player Name] - Attacks target player";
			help[4]="End - Ends turn";
			help[5]="Describe [Card Name] - Gives data for specified card";
			for(int i=0;i<=5;i++)
			{
				slowPrint(help[i]+br);
			}
		}
	}
	
	public static void creatorInfo()
	{
	// 	Information about me  because I'm vain
		clearTerm();
		slowPrint("Created by Blaine Harper"+br+br);
		slowPrint("");
		wait(2);
		slowPrint("In Association with Tale of Two Nerds");
		wait(2);
		clearTerm();
		slowPrint("Blaine Harper's"+br+br);
		slowPrint("Magic: TG -  Terminal Edition");
		wait(2);
		clearTerm();
	}
	public static String playerList()
	{
	//	Setup the list of players in the game
		String value=null;
		if (players==2)
		{
			value = player[1].getName() + " and " 
				+ player[2].getName();
		} else if (players==3)
		{
			value = player[1].getName() + ", " 
				+ player[2].getName() + ", and " 
				+ player[3].getName();
		} else if (players==4)
		{
			value = player[1].getName() + ", " 
				+ player[2].getName() + ", " 
				+ player[3].getName() + ", and " 
				+ player[4].getName();
		}
		return value;
	}
	public static void welcomeSpeech()
	{
		clearTerm();
	//	Messages
	
		String[] welcome = new String[12];
		welcome[1] = "Welcome to the infinite world of Magic.";
		welcome[2] = "We gather here as a long tradition has dictated in order to " +
			"test the prowess of young sorcerers such as yourselves. As the tradition dictates you "+players+" are "+
			"the chosen ones and as such must battle to the death.";
		welcome[3] = "This is tragic. I know, but it is how it must be.";
		welcome[4] = "You will each be given a random deck of spells to begin with";
		welcome[5] = "Prepare for the die toss that will decide who begins the game";
	
	// 	Timing to display the messages
		for (int i=1;i<=5;i++)
		{
			slowPrint(welcome[i] + br + br);
			wait(2);
		}
	
	}
}