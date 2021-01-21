package logging;

import java.util.Date;

public class Error extends Logger
{
	Long timeStep;
	String level;
	String message;
	String simulationObject;

	public Error(Long timeStep, String level, String message, String simulationObject)
	{
		super(new Date(), level, timeStep, message, simulationObject);

	}

}