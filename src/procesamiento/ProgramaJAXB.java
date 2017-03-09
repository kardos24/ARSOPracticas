package procesamiento;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ProgramaJAXB {

	public static void main(String[] args) {
		JAXBContext contexto = JAXBContext.newInstance("calificaciones");
		Unmarshaller unmarshaller = contexto.createUnmarshaller();

		Calificaciones calificaciones = (Calificaciones) unmarshaller.unmarshal(new File("xml/practicas/calificaciones.xml"));

	}

}
