package logging;

import java.util.Date;

public class Fatal extends Logger
{
	Long timeStep;
	String level;
	String message;
	String simulationObject;

	public Fatal(Long timeStep, String level, String message, String simulationObject)
	{
		super(new Date(), level, timeStep, message, simulationObject);

	}

}