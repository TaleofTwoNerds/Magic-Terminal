public class cardData extends mainGame
{
	//Initialize Card array
	
	public static int numCards = 0;

	public static void create()
	{	
		card[1] = new Card(1,"Creature","Battle Sliver",3,"target player",2,1);
		card[2] = new Card(2,"Creature","Silver Myr",3,"",2,2);
		card[3] = new Card(3,"Creature","Fury Sliver",3,"",3,3);
		card[4] = new Card(4,"Creature","Blur Sliver",2,"",2,3);
		card[5] = new Card(5,"Creature","Leeching Sliver",3,"",3,3);
		card[6] = new Card(6,"Creature","Striking Sliver",1,"",1,1);
		card[7] = new Card(7,"Creature","Root Sliver",3,"",3,3);
		card[8] = new Card(8,"Creature","Homing Sliver",1,"",1,1);
		card[9] = new Card(9,"Creature","Copper Myr",3,"",2,1);
		card[10] = new Card(10,"Creature","Firebrand",3,"",1,2);
		card[11] = new Card(11,"Creature","Destroyer",5,"",5,2);
		card[12] = new Card(12,"Creature","Flame On",3,"",2,2);
		for(int i=13;i<=18;i++)
		{
			card[i] = new Card(i,"Land","Mountain",0,"",0,0);
		}
	}

	public static class Card extends AbstractCard
	{
		public Card(int ID, String type, String name, int CMC, 
					String ability, int power, int toughness)
		{
			super(ID,type,name,CMC,ability,power,toughness);
		}
	}
	
	public static int cardToID(String cardName)
	{
		int cardID = 0;
		for(int i=1;i<=numCards;i++)
		{
			
			if(card[i].getName().toLowerCase().startsWith(cardName.toLowerCase()))
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