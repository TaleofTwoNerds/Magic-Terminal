public class cardData extends mainGame
{
	//Initialize Card array
	
	public static int numCards = 0;

	public static void create()
	{
		card[1] = new Card("Fireball",1,1,"",2,1);
		card[2] = new Card("Silver Myr",2,1,"",4,2);
		card[3] = new Card("Fury Sliver",3,1,"",3,3);
	}

	public static class Card extends AbstractCard
	{
		public Card(String name, int ID, int CMC, 
					String ability, int power, int toughness)
		{
			super(name,ID,CMC,ability,power,toughness);
		}
	}
	
	public static int cardToID(String cardName)
	{
		int cardID = 0;
		for(int i=1;i<=numCards;i++)
		{
			
			if(card[i].getName().startsWith(cardName))
			{
				cardID = card[i].getID();
				break;
			}
		}
		return cardID;
	}
	public static String IDToCard(int cardID)
	{	
		return card[cardID].getName();
	}
}