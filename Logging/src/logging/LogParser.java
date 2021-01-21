package logging;

public class LogParser
{
	public static void logParser(Long timeStep, String level, String message, String simulationObject)
	{
		switch (level) {
			case "INFORMATION":
				Information info = new Information(timeStep, level, message, simulationObject);
				info.writeToTxt();
				info.writeToConsole();
				break;
			case "WARNING":
				Warning warn = new Warning(timeStep, level, message, simulationObject);
				warn.writeToTxt();
				warn.writeToConsole();
				break;
			case "FATAL":
				Fatal fatal = new Fatal(timeStep, level, message, simulationObject);
				fatal.writeToTxt();
				fatal.writeToConsole();
				break;
			case "DEBUG":
				Debug debug = new Debug(timeStep, level, message, simulationObject);
				debug.writeToTxt();
				debug.writeToConsole();
				break;
			case "ERROR":
				Error error = new Error(timeStep, level, message, simulationObject);
				error.writeToTxt();
				error.writeToConsole();
				break;
		}
	}
}
