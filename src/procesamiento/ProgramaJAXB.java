package procesamiento;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import calificaciones.Calificaciones;
import calificaciones.TipoCalificacion;

public class ProgramaJAXB {

	public static void main(String[] args) {
		//C:\Users\Alumno\git\ARSOPracticas>"C:\Program Files (x86)\Java\jdk1.8.0_111\bin\xjc.exe" Calificaciones\calificaciones.xsd -p calificaciones

		try {
			System.out.println("Inicio JAXB");
			JAXBContext contexto = JAXBContext.newInstance("calificaciones");
			Unmarshaller unmarshaller = contexto.createUnmarshaller();

			Calificaciones calificaciones = (Calificaciones) unmarshaller
					.unmarshal(new File("Calificaciones/calificaciones.xml"));

			Calendar calendario = Calendar.getInstance();
			calendario.add(Calendar.DAY_OF_MONTH, -30);
			Date dateMothAgo = calendario.getTime();
			Long numDiligencias = calificaciones.getDiligencia().stream().map(d -> d.getFecha().toGregorianCalendar().getTime())
					.filter(d -> dateMothAgo.before(d) && d.before(new Date())).count();
			System.out.println("Numero de diliegencias: " + numDiligencias);
			
			Double notaMedia = calificaciones.getCalificacion().stream().mapToDouble(TipoCalificacion::getNota).average().getAsDouble();
			System.out.println("Nota media de calificaciones: " + notaMedia);
			
			calificaciones.getCalificacion().forEach((cali)->{
				double nota = cali.getNota();
				nota+=0.5;
				if(nota>10){
					nota = 10;
				}
				System.out.println("Nota de calificaciones cambiada de " + cali.getNota() + " a " + nota);
				cali.setNota(nota);
			});
			
			Marshaller marshaller = contexto.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty("jaxb.schemaLocation",
					"http://www.um.es/as Calificacioens/calificaciones.xsd");
			marshaller.marshal(calificaciones, new File("Calificaciones/calificaciones-modificado_JAXB.xml"));
			System.out.println("Fin JAXB");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
