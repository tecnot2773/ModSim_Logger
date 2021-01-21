package logging;

import java.util.Date;

public class Debug extends Logger
{
	Long timeStep;
	String level;
	String message;
	String simulationObject;

	public Debug(Long timeStep, String level, String message, String simulationObject)
	{
		super(new Date(), level, timeStep, message, simulationObject);

	}

}