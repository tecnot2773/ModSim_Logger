package des.model_gravelshipping;

import des.core.Event;
import des.core.EventQueue;
import des.core.Randomizer;
import des.core.SimulationObject;
import des.core.SimulationObjects;

public class WeighingStation extends SimulationObject
{
	private static final int TIME_TO_WEIGH_TRUCK = 10;
	private static final int MAXLOAD = 40;
	
	private String name = null;
	private Truck truckCurrentlyWeighted = null;
	
	private static EventQueue eventQueue = EventQueue.getInstance();
	
	private static Randomizer drivingToUnloadingDock = null;
	private static Randomizer drivingToLoadingDock  = null;
	
	
	public WeighingStation(String name)
	{
		this.name = name;
		
		drivingToUnloadingDock = new Randomizer();
		drivingToUnloadingDock.addProbInt(0.5, 120);
		drivingToUnloadingDock.addProbInt(0.8, 150);
		drivingToUnloadingDock.addProbInt(1.0, 180);

		drivingToLoadingDock = new Randomizer();
		drivingToLoadingDock.addProbInt(0.5, 30);
		drivingToLoadingDock.addProbInt(1.0, 45);
		
		SimulationObjects.getInstance().add(this);
	}
	
	
	@Override
	public String toString()
	{
		return "Weighing Station: " + name + " Truck: " + (truckCurrentlyWeighted != null ? truckCurrentlyWeighted : "---");
	}
	
	
	// TODO: change logic 
	@Override
	public boolean simulate(long timeStep)
	{
		// truck to weigh?
		Event event = eventQueue.next(timeStep, true, GravelShippingEvents.Weighing, this.getClass(), null);
		if (truckCurrentlyWeighted == null
				&& event != null
				&& event.getObjectAttached() != null
				&& event.getObjectAttached().getClass() == Truck.class)
		{
			eventQueue.remove(event);
			
			truckCurrentlyWeighted = (Truck) event.getObjectAttached();
			eventQueue.add(new Event(timeStep + truckCurrentlyWeighted.addUtilization(TIME_TO_WEIGH_TRUCK),
					GravelShippingEvents.WeighingDone, truckCurrentlyWeighted, null, this));
			
			utilStart(timeStep);
			return true;
		}
		
		// weighing done ?
		event = eventQueue.next(timeStep, true, GravelShippingEvents.WeighingDone, null, this);
		if (event != null
				&& event.getObjectAttached() != null
				&& event.getObjectAttached().getClass() == Truck.class)
		{
			eventQueue.remove(event);
			final Integer truckToWeighLoad = truckCurrentlyWeighted.getLoad();
			long driveToUnLoadingDock = 0;
			
			
			
			// Logger
			if (truckToWeighLoad == 45)
			{
				logging.LogParser.logParser(timeStep, "ERROR", (truckToWeighLoad - MAXLOAD) + "t too much loaded", truckCurrentlyWeighted.getName());
			}
			else if (truckToWeighLoad != null && truckToWeighLoad > MAXLOAD)  
			{
				logging.LogParser.logParser(timeStep, "FATAL", (truckToWeighLoad - MAXLOAD) + "t too much loaded", truckCurrentlyWeighted.getName());
			}
			else if (truckToWeighLoad != null && truckToWeighLoad < MAXLOAD-2)
			{
				logging.LogParser.logParser(timeStep, "WARNING", (MAXLOAD - truckToWeighLoad) + "t too little loaded", truckCurrentlyWeighted.getName());
			}
			else
			{
				logging.LogParser.logParser(timeStep, "INFORMATION", "Truck was loaded optimally", truckCurrentlyWeighted.getName());
			}
			
			
			if (truckCurrentlyWeighted.getName().equals("T1"))
			{
				logging.LogParser.logParser(timeStep, "DEBUG", "Event Description: " + event.getDescription().get() + ". tons: " + truckToWeighLoad, truckCurrentlyWeighted.getName());
			}
			
			
			

			// drive back to loading dock! too much weight loaded
			if (truckToWeighLoad != null && truckToWeighLoad > MAXLOAD)
			{
				GravelShipping.gravelToShip += truckToWeighLoad;
				GravelShipping.unsuccessfullLoadingSizes += truckToWeighLoad;
				GravelShipping.unsuccessfullLoadings++;
				driveToUnLoadingDock = truckCurrentlyWeighted.addUtilization(drivingToUnloadingDock.nextInt());
			}
			else // unload at unloading dock! successful shipping
			{
				GravelShipping.gravelShipped += truckToWeighLoad;
				GravelShipping.successfullLoadingSizes += truckToWeighLoad;
				GravelShipping.successfullLoadings++;
				driveToUnLoadingDock = truckCurrentlyWeighted.addUtilization(drivingToLoadingDock.nextInt());				
			}
			
			// Logger
			logging.LogParser.logParser(timeStep + driveToUnLoadingDock, "INFORMATION", "Truck was unloaded", truckCurrentlyWeighted.getName());
			
			
			eventQueue.add(new Event(timeStep + driveToUnLoadingDock, GravelShippingEvents.Loading, 
					truckCurrentlyWeighted, LoadingDock.class, null));
			
			truckCurrentlyWeighted.unload();
			truckCurrentlyWeighted = null;
			utilStop(timeStep);
			return true;
		}
		
		return false;
	}
}