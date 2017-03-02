package procesamiento;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ProgramaXSLT {

	public static void main(String[] args) {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador;
//		try {
//			transformador = factoria.newTransformer(new StreamSource("xml/poema.xsl"));
//
//			Source origen = new StreamSource("xml/poema.xml");
//			Result destino = new StreamResult("xml/poema_solucion.html");
//
//			transformador.transform(origen, destino);
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			transformador = factoria.newTransformer(new StreamSource("xml/poema_invertido.xsl"));

			Source origen = new StreamSource("xml/poema.html");
			Result destino = new StreamResult("xml/poema_invertido.xml");

			transformador.transform(origen, destino);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
