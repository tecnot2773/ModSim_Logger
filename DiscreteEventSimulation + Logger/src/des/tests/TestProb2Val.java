package des.tests;

import des.core.Randomizer;

public class TestProb2Val
{
	
	public static void main(String[] args)
	{
		
		Randomizer r = new Randomizer();
		
		r.addProbInt(0.3, 34);
		r.addProbInt(0.6, 38);
		r.addProbInt(1.0, 41);

		System.out.println(r.nextInt());;
		
	}

}