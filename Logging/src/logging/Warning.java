package logging;

import java.util.Date;

public class Warning extends Logger
{
	Long timeStep;
	String level;
	String message;
	String simulationObject;

	public Warning(Long timeStep, String level, String message, String simulationObject)
	{
		super(new Date(), level, timeStep, message, simulationObject);

	}

}