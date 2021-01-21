package des.core;

public abstract class SimulationObject
{
	private Long timeUtilized = 0l;
	private Long utilStart = null;

	
	// each simulationobject is simulated (until it returns false = no event was submitted)
	public abstract boolean simulate(long timeStep);
	
	
	public void utilStart(long timeStep)
	{
		utilStart = timeStep;
	}
	
	
	public void utilStop(long timeStep)
	{
		timeUtilized += timeStep - utilStart;
	}
	
	
	public Long getTimedUtilized()
	{
		return timeUtilized;
	}

	
	// TODO: CHECK REFERENCES! ADD TO UML Model
	public long addUtilization(long timeUtilizedDelta)
	{
		timeUtilized += timeUtilizedDelta;
		return timeUtilizedDelta;
	}
	
}
