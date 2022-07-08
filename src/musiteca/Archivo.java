package musiteca;

import java.io.IOException;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;


public class Archivo {
    
    // Método estático para cargar en memoria un archivo XML
    public static Document abrirDocumentoXML(String nombreArchivo) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(nombreArchivo);
        } catch (ParserConfigurationException pce) {
             System.out.println(pce.getMessage());
            return null;
        } catch (SAXException se) {
            return null;
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
            return null;
        }
    } 
    
}
