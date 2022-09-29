package fp.airbnb;

import java.time.LocalDate;

public interface Airbnb extends Comparable<Airbnb>{
	
	int getId();
	String getName();
	int getHost_id();
	ZonasCiudad getZone();
	double getLatitude();
	double getLongitude();
	Boolean getIs_entire_home();
	int getPrice();
	int getNumber_of_reviews();
	LocalDate getLast_review();
	String getPrecioEstancia(int noches);
	String cercanoACoordenadas(double latitud, double longitud);
	
}
