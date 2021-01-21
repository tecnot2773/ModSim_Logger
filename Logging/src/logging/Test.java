package logging;

public class Test
{
	public static Integer run = null;

	public static void main(String[] args)
	{

		run = 1;
		logging.LogParser.logParser(1l, "INFORMATION", "this is a message", "LKW 1");
		logging.LogParser.logParser(1l, "FATAL", "this is a message", "LKW 4");

		run = 2;
		try
		{
			Thread.sleep(15000);
		} catch (InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}

		logging.LogParser.logParser(1l, "Error", "this is a message", "LKW 3");
		logging.LogParser.logParser(1l, "DEBUG", "this is a message", "LKW 2");
	}

}
