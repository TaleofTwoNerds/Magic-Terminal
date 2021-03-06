public interface Player {
	public void setHP(int HP);
	public void setName(String name);
	public void dHP(double dHP);
	public void addCard(String card);
	public void removeCard(String card);
	public void moveToField(Card card);
	public int getHP();
	public String getName();
	public String getHand(int position);
	public int getHandSize();
	public int getFieldSize();
	public int getCreatures();
	public Card getField(int position);
}