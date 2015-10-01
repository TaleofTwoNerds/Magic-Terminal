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
	
	public static void creatorInfo()
	{
	// 	Information about me  because I'm vain
		clearTerm();
		slowPrint("Created by Blaine Harper"+br+br);
		slowPrint("");
		try
		{
			Thread.sleep(2000);
			slowPrint("In Association with Tale of Two Nerds");
			Thread.sleep(2000);
			clearTerm();
			slowPrint("Blaine Harper's"+br+br);
			slowPrint("Magic: TG -  Terminal Edition");
			Thread.sleep(4000);
		} catch(InterruptedException e) { 
   			Thread.currentThread().interrupt(); 
		}
		clearTerm();
	}
	public static String playerList()
	{
	//	Setup the list of players in the game
		String value=null;
		if (players==2)
		{
			value = playerList[1] + " and " 
				+ playerList[2];
		} else if (players==3)
		{
			value = playerList[1] + ", " 
				+ playerList[2] + ", and " 
				+ playerList[3];
		} else if (players==4)
		{
			value = playerList[1] + ", " 
				+ playerList[2] + ", " 
				+ playerList[3] + ", and " 
				+ playerList[4];
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