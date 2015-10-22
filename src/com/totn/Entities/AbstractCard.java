public abstract class AbstractCard implements Card {
	
	protected boolean tapped;
	protected int CMC, power, toughness,ID;
	protected String name, ability,type;

	public AbstractCard(int ID, String type, String name, int CMC, 
					String ability, int power, int toughness) {
		this.name = name;
		this.ID = ID;
		this.CMC = CMC;
		this.power = power;
		this.toughness = toughness;
		this.type = type;
		cardData.numCards++;
	}
	
	/*	Can be used to set data, but since I don't want the
		data to be modified I will comment this out in the 
		Card and Abstract Card classes.
	
	public void setID(double HP) 
	{
		this.HP = HP;
	}
	
	public void setCMC(double CMC)
	{
		this.CMC = CMC;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAbility(String name)
	{
		this.name = name;
	}
	*/
	
	public void tap()
	{
		tapped=true;
	}
	
	public void unTap()
	{
		tapped=false;
	}
	
	public int getID() 
	{
		return ID;
	}
	
	public int getCMC() 
	{
		return CMC;
	}
	
	public int getPower() 
	{
		return power;
	}
	
	public int getToughness()
	{
		return toughness;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAbility()
	{
		return ability;
	}
	
	public String getType()
	{
		return type;
	}
	
	public boolean isTapped()
	{
		return tapped;
	}
}