public class dice
{
	public static String br = System.getProperty("line.separator");

	public static int diceToss()
	{
		messages.slowPrint("The dice have been cast!"+ br);
			
		int[] possibles = new int[mainGame.players+1];
		int winner = 1;
		
		for(int i=1;i<=mainGame.players;i++)
		{
			possibles[i] = 0 + (int)(Math.random() * (21));
		}
		if(mainGame.players==2)
		{
			if(possibles[1]>possibles[2])
			{
				winner = 1;
			} else {
				winner = 2;
			}
		} else if(mainGame.players==3)
		{
			if(possibles[1]>possibles[2]&&possibles[1]>possibles[3])
			{
				winner = 1;
			} else if (possibles[2]>possibles[1]&&possibles[2]>possibles[3]){
				winner = 2;
			} else {
				winner = 3;
			}
		} else if(mainGame.players==4)
		{
			if(possibles[1]>possibles[2]&&possibles[1]>possibles[3]&&possibles[1]>possibles[4])
			{
				winner = 1;
			} else if (possibles[2]>possibles[1]&&possibles[2]>possibles[3]&&possibles[1]>possibles[4])
			{
				winner = 2;
			} else if (possibles[3]>possibles[1]&&possibles[3]>possibles[2]&&possibles[3]>possibles[4])
			{
				winner = 3;
			} else {
				winner = 4;
			}
		}
		messages.slowPrint("Player " + winner + " won the dice toss!"+br);
		
		return winner;
	}
}