package motorway;

import tollbooth.Tollbooth;
import vehicle.Vehicle;

interface Toll {
	
	float tollCalc(Tollbooth in, Tollbooth out, Vehicle v);
	int routeCalc(Tollbooth in, Tollbooth out);
	
}
