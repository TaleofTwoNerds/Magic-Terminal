public abstract class AbstractPlayer implements Player {
	
	protected int HP;
	protected int handSize=0;
	protected String[] hand = new String[10];
	protected String name;

	public AbstractPlayer(String name, int HP) {
		this.name = name;
		this.HP = HP;
	}
	
	public void setHP(int HP) 
	{
		this.HP = HP;
	}
	
	public void dHP(double dHP)
	{
		HP+=dHP;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addCard(String card)
	{
		for (int i=0;i<8;i++)
		{
			if(hand[i]==null)
			{
				hand[i]=card;
				handSize++;
				break;
			}
		}
	}
	
	public void removeCard(String card)
	{
		for(int i=0;i<8;i++)
		{
			if(hand[i].startsWith(card))
			{
				hand[i]=null;
				handSize-=1;
				break;
			}
		}
	}
	
	public int getHP() 
	{
		return HP;
	}
	
	public String getName()
	{
		return name;
	}
	public int getHandSize()
	{
		return handSize;
	}
	public String getHand(int position)
	{
		return hand[position];
	}
}