package java8;

public class _7_Underscores 
{
	
	public void java6()
	{
		long longNumber = 9876543210L;

		long _5 = 5;
		
		double pi = 3.1415926;
	}

	public void java7()
	{
		long longNumber = 9_876_543_210L;
		long longNumberOddFormat = 987__65______43__210L;
		
		long _5 = 5; 
		long invalidUnderscoreAtTheEnd = 98_;
		
		double pi = 3.141_592_6;
		double invalidPi = 3_.1415926;
		double anotherInvalidPi = 3._1415926;
	}
}
