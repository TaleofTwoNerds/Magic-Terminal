public interface Card {

	/*	Can be used to set data, but since I don't want the
		data to be modified I will comment this out in the 
		Card and Abstract Card classes.

	public void setID(int ID);
	public void setCMC(int CMC);
	public void setName(String name);
	public void setAbility(String name);
	*/
	public void summoned();
	public void notSummoned();
	public void tap();
	public void unTap();
	public int getID();
	public int getCMC();
	public int getPower();
	public int getToughness();
	public String getName();
	public String getAbility();
	public String getType();
	public boolean isTapped();
	public boolean wasSummoned();
	public boolean isLand();
}