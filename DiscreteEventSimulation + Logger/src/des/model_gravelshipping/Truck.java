package des.model_gravelshipping;

import des.core.SimulationObject;
import des.core.SimulationObjects;

public class Truck extends SimulationObject
{
	private String name = null;
	private Integer loadedWithTons = null;
	
	
	public Truck(String name)
	{
		super();
		this.name = name;
		SimulationObjects.getInstance().add(this);
	}
	
	public void load(int weight) 
	{
		loadedWithTons = weight;
	}
	
	public void unload()
	{
		loadedWithTons = null;
	}
	
	public Integer getLoad()
	{
		return loadedWithTons;
	}

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String toString()
	{
		return name + (loadedWithTons != null ? "(" + loadedWithTons + "t" + ")" : "");
	}


	@Override
	public boolean simulate(long timeStep)
	{
		// intentionally doing nothing
		return false;
	}

}