package fp.airbnb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.utiles.Checkers;

public class AirbnbImpl implements Airbnb {

	// 1. Atributos
	
	private int id;
	private String name;
	private int host_id;
	private ZonasCiudad zone;
	private double latitude;
	private double longitude;
	private Boolean is_entire_home;
	private int price;
	private int number_of_reviews;
	private LocalDate last_review;

	// 2. Constructores

	public AirbnbImpl(int id, String name, int host_id, ZonasCiudad zone, double latitude, 
			double longitude, Boolean is_entire_home, int price, int number_of_reviews, 
			LocalDate last_review) {
		this.id = id;
		this.name = name;
		this.host_id = host_id;
		this.zone = zone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.is_entire_home = is_entire_home;
		this.price = price;
		this.number_of_reviews = number_of_reviews;
		this.last_review = last_review;
	}
	
	public AirbnbImpl(String cadena) {
		String[] splited = cadena.split(",");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.id = Integer.valueOf(splited[0].trim());
		this.name = splited[1];
		this.host_id = Integer.valueOf(splited[2].trim());
		this.zone = ZonasCiudad.valueOf(splited[3].trim());
		this.latitude = Double.valueOf(splited[4].trim());
		this.longitude = Double.valueOf(splited[5].trim());
		this.is_entire_home = Boolean.valueOf(splited[6].trim());
		this.price = Integer.valueOf(splited[7].trim());
		this.number_of_reviews = Integer.valueOf(splited[8].trim());
		this.last_review = LocalDate.parse(splited[9].trim(), formato);
	}
	
	// 3. Otros métodos

	public int getId() {
		Checkers.check("Error en la id", id != 0);
		return id;
	}

	public void setId(int id) {
		Checkers.check("Error en la id", id != 0);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHost_id() {
		Checkers.check("Error en la id", id != 0);
		return host_id;
	}

	public void setHost_id(int host_id) {
		Checkers.check("Error en la id", id != 0);
		this.host_id = host_id;
	}

	public ZonasCiudad getZone() {
		Checkers.check("Error. La zona no es correcta", !ZonasCiudad.valueOf(zone));
		return zone;
	}

	public void setZone(ZonasCiudad zone) {
		Checkers.check("Error. La zona no es correcta", !ZonasCiudad.valueOf(zone));
		this.zone = zone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public Boolean getIs_entire_home() {
		return is_entire_home;
	}

	public void setIs_entire_home(Boolean is_entire_home) {
		this.is_entire_home = is_entire_home;
	}

	public int getPrice() {
		Checkers.check("Error en el precio", Integer.valueOf(price) != 0);
		return price;
	}

	public void setPrice(int price) {
		Checkers.check("Error en el precio", Integer.valueOf(price) != 0);
		this.price = price;
	}

	public int getNumber_of_reviews() {
		Checkers.check("Error en la cantidad de valoraciones", number_of_reviews != 0 && last_review != null);
		return number_of_reviews;
	}

	public void setNumber_of_reviews(int number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}

	public LocalDate getLast_review() {
		Checkers.check("Error en la fecha", number_of_reviews > 0 && last_review != null 
				&& last_review.isBefore(LocalDate.now()));
		return last_review;
	}

	public void setLast_review(LocalDate last_review) {
		Checkers.check("Error en la fecha", number_of_reviews > 0 && last_review != null 
				&& last_review.isBefore(LocalDate.now()));
		this.last_review = last_review;
		this.number_of_reviews = number_of_reviews + 1;
	}

	@Override
	public String getPrecioEstancia(int noches) {
		// TODO Auto-generated method stub
		Checkers.check("Error. El número de noches tienes que ser mayor que 0.", noches > 0);
		int res = Integer.valueOf(getPrice())*noches;
		return "Precio para una estancia de " + noches + " NOCHES: " + String.valueOf(res) + "€";
	}

	@Override
	public String cercanoACoordenadas(double latitud, double longitud) {
		// TODO Auto-generated method stub
		Checkers.check("Error en las coordenadas", latitud != 0 && longitud != 0);
		String res = "Sí";
		double difLat = Math.abs(this.latitude - latitud);
		double difLong = Math.abs(this.longitude - longitud);
		if(difLat > 0.0075 || difLong > 0.005) {
			res = "No";
		}
		return "¿Es cercano a " + "(" + String.valueOf(latitud) + ", " + String.valueOf(longitud) + ")?: " + res;
	}

	@Override
	public String toString() {
		return "[ALOJAMIENTO "+ getId() + ": " + getName() + ", " + "Usuario " + getHost_id() 
				+ ", " + getZone() + ", (" + getLatitude() + ", " + getLongitude() 
				+ "), \n" + Boolean.valueOf(getIs_entire_home()) + ", " + String.valueOf(getPrice()) + "€" 
				+ ", " + getNumber_of_reviews() + " valoraciones" + ", " + "Última valoración: " 
				+ getLast_review() + "]";
	}

	// Criterio de orden natural
	@Override
	public int compareTo(Airbnb a) {
		// TODO Auto-generated method stub
		int res = (this.getPrice()==a.getPrice())?1:0;
		if(res==0) {
			res = (this.getName()).compareTo(a.getName());
			if(res==0) {
				res = (this.getNumber_of_reviews() == a.getNumber_of_reviews())?1:0;				
			}
		}
		return 0;
	}

	// Criterio de igualdad
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + host_id;
		result = prime * result + id;
		result = prime * result + ((is_entire_home == null) ? 0 : is_entire_home.hashCode());
		result = prime * result + ((last_review == null) ? 0 : last_review.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number_of_reviews;
		result = prime * result + price;
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirbnbImpl other = (AirbnbImpl) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude) 
				|| Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	

}
