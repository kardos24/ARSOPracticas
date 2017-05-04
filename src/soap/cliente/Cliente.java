package soap.cliente;
import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

import soap.NumberService;
import soap.NumberServiceException;
import soap.NumberServiceImplService;

public class Cliente {

	public static void main(String[] args) {
		IP2Geo servicio = new IP2Geo();

		IP2GeoSoap puerto = servicio.getIP2GeoSoap();

		IPInformation respuesta1 = puerto.resolveIP("155.54.1.1", "0");

		System.out.println("Respuesta IP Ciudad: " + respuesta1.getCity() + "; País: " + respuesta1.getCountry());

		
		NumberServiceImplService servicioPropio = new NumberServiceImplService();
		NumberService puertoPropio = servicioPropio.getNumberServiceImplPort();
		
		try {
			Integer resp = puertoPropio.getNumber("20");
			System.out.println("LLamada al servicio propio :" + resp);
			resp = puertoPropio.getNumber("5959");
			System.out.println("LLamada al servicio propio :" + resp);
			resp = puertoPropio.getNumber("0");
			System.out.println("LLamada al servicio propio :" + resp);
			resp = puertoPropio.getNumber("po8l");
			System.out.println("LLamada al servicio propio :" + resp);
		} catch (NumberServiceException e) {
			System.out.println("Error en el servicio propio\n" + e.getMessage());
		}
	}

}
