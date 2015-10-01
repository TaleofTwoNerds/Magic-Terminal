public class Messages extends MainGame 
{
	public static void slowPrint(String message)
    {
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
			value = playerOne + " and " 
				+ playerTwo;
		} else if (players==3)
		{
			value = playerOne + ", " 
				+ playerTwo + ", and " 
				+ playerThree;
		} else if (players==4)
		{
			value = playerOne + ", " 
				+ playerTwo + ", " 
				+ playerThree + ", and " 
				+ playerFour;
		}
		return value;
	}
	public static void welcomeSpeech()
	{
		clearTerm();
	//	Messages
		String one = "Welcome to the infinite world of Magic." + br + br;
		String two = "We gather here as a long tradition has dictated in order to " +
			"test the prowess of young sorcerers such as yourselves. As the tradition dictates you two are "+
			"the chosen ones and as such must battle to the death." + br;
		String three = "This is tragic. I know, but it is how it must be.";
			
		slowPrint(one);
		wait(2);
		slowPrint(two);
		wait(2);
		slowPrint(three);

	//	Timing
	
	}
}