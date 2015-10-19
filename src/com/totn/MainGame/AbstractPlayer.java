public abstract class AbstractPlayer implements Player {
	
	protected double HP;
	protected String name;

	public AbstractPlayer(String name, double HP) {
		this.name = name;
		this.HP = HP;
	}
	
	
	public void setHP(double HP) 
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
	
	
	public double getHP() 
	{
		return HP;
	}
	
	
	public String getName()
	{
		return name;
	}
}