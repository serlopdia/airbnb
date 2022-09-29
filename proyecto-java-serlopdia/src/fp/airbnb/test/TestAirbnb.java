package fp.airbnb.test;

import java.time.LocalDate;

import fp.airbnb.AirbnbImpl;
import fp.airbnb.ZonasCiudad;

public class TestAirbnb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creación correcta
		AirbnbImpl a = new AirbnbImpl(2345, "Piso en calle Ramos", 23211, ZonasCiudad.Triana, 
				37.39939, -5.9992, true, 98, 27, LocalDate.of(2021, 3, 23));
		AirbnbImpl aStr = new AirbnbImpl("2345, Piso en calle Ramos, 23211, Triana, 37.39939, -5.9992, true, 98, 27, 23/03/2021");
		System.out.println(aStr);
		// Obtener precio estancia y si es cercano a unas coordenadas
		System.out.println("\n" + a.getPrecioEstancia(1));
		System.out.println("\n" + aStr.cercanoACoordenadas(37.39111, -5.9996));
		
		// Varias restricciones
		// Fecha no válida
		/*AirbnbImpl a1 = new AirbnbImpl(2345, "Piso en calle Ramos", 23211, ZonaBarrio.Triana, 
				37.39939, -5.9992, true, 98, 27, LocalDate.of(2021, 8, 23));
		System.out.println(a1);*/
		// Noches estancia no válidas
		//System.out.println("\n" + a.getPrecioEstancia(-3));
		// Precio no válido (GRATIS, 0€)
		/*AirbnbImpl a2 = new AirbnbImpl(2345, "Piso en calle Ramos", 23211, ZonaBarrio.Triana, 
				37.39939, -5.9992, true, 0, 27, LocalDate.of(2021, 3, 23));
		System.out.println(a2);*/
	}

}
