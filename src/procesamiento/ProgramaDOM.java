package procesamiento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProgramaDOM {

	public static void main(String[] args) {
		ejercicio1_8();
		ejercicio1_9();
		ejercicio1_10();
	}

	private static void ejercicio1_8() {
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

			DocumentBuilder analizador = factoria.newDocumentBuilder();

			Document documento = analizador.parse("xml/practicas/calificaciones.xml");

			NodeList listNotas = documento.getElementsByTagName("nota");

			double sumTotal = 0.0;
			int numDiligencias = 0;

			for (int i = 0; i < listNotas.getLength(); i++) {
				Node nota = listNotas.item(i);
				if (Objects.equals(nota.getParentNode().getNodeName(), "calificacion")) {
					Double notanum = Double.valueOf(nota.getTextContent());
					sumTotal += notanum;
					numDiligencias++;
				}
			}
			double notaMedia = sumTotal / numDiligencias;
			System.out.println("Nota media de las diligencias. " + notaMedia);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	private static void ejercicio1_9() {
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

			DocumentBuilder analizador = factoria.newDocumentBuilder();

			Document documento = analizador.parse("xml/practicas/calificaciones.xml");

			NodeList listNotas = documento.getElementsByTagName("nota");

			for (int i = 0; i < listNotas.getLength(); i++) {
				Node nota = listNotas.item(i);
				if (Objects.equals(nota.getParentNode().getNodeName(), "calificacion")) {
					Double notanum = Double.valueOf(nota.getTextContent());
					notanum += 0.5;
					if (notanum > 10) {
						notanum = 10.0;
					}
					nota.setTextContent(notanum.toString());
				}
			}

			TransformerFactory tFactoria = TransformerFactory.newInstance();
			Transformer transformacion = tFactoria.newTransformer();

			Source input = new DOMSource(documento);

			Result output = new StreamResult("xml/practicas/calificaciones-modificado.xml");

			transformacion.transform(input, output);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void ejercicio1_10() {
		try {
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream("xml/practicas/calificaciones.xml"));

			int diligencias = 0;

			double sumaNotas = 0;
			int contadorNotas = 0;

			boolean dentroCalificacion = false;
			boolean dentroNota = false;

			while (reader.hasNext()) {
				int evento = reader.next();
				switch (evento) {
				
				case XMLStreamConstants.START_ELEMENT:
					switch (reader.getLocalName()) {
					case "calificacion":
						dentroCalificacion = true;
						break;

					case "nota":
						if (dentroCalificacion) {
							dentroNota = true;
						}
						break;

					case "diligencia":
						if (contarDiligencias(reader)) {
							diligencias++;
						}
						break;
					}
					break;
				
				case XMLStreamConstants.CHARACTERS:
					if (dentroNota) {
						
						String cadena = reader.getText();

						Double nota = null;

						nota = Double.parseDouble(cadena);

						sumaNotas += nota;

						contadorNotas++;
					}
					break;
				}

				double notaMedia = sumaNotas / contadorNotas;
				System.out.println("Numero de diligencias: " + diligencias);
				System.out.println("Nota media: " + notaMedia);
			}
		} catch (FileNotFoundException | XMLStreamException | SAXException e) {
			e.printStackTrace();
		}
	}

	private static boolean contarDiligencias(XMLStreamReader reader) throws SAXException {
		String fechaString = reader.getAttributeValue("", "fecha");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		;

		try {
			fecha = dateFormat.parse(fechaString);
		} catch (ParseException e) {
			throw new SAXException("Fecha incorrecta. El documento no ha sido validado");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);

		return (fecha.after(calendar.getTime()));
	}
}
