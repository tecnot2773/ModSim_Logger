package des.tests;

import des.core.Event;
import des.core.EventQueue;
import des.core.Time;
import des.model_gravelshipping.GravelShippingEvents;
import des.model_gravelshipping.Truck;

//import logging.LogParser;

public class TestEvent
{

	public static void main(String[] args)
	{
		Truck t1 = new Truck("T1");
		Event e = new Event(3000l, GravelShippingEvents.Loading, null, Truck.class, t1);
		System.out.println(e);
		
		EventQueue eq = EventQueue.getInstance();
		eq.add(e);
		eq.add(new Event(200l, GravelShippingEvents.Loading, null, Truck.class, t1));
		
		System.out.println("Current time : " + Time.stepsToString(250l));
		
		System.out.println("event found : " + 
				eq.next(250l, true, null, null, null));
		
		eq.remove(e);
	}
}