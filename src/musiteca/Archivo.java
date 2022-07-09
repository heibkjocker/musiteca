package musiteca;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class Archivo {

    public static BufferedReader abrirArchivo(String nombreArchivo) {
        //instancia de objeto archivo
        File f = new File(nombreArchivo);
        //existe el archivo
        if (f.exists()) {
            try {
                //instancia del objerto lector archivo
                FileReader fr = new FileReader(f);
                //abrir el archivo y retornar su contenido
                return new BufferedReader(fr);

            } catch (Exception ex) {

            }

        }
        return null;
    }

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

    public static void mostrarImagen(JPanel pnl, String nombreArchivo) {
        try {
            //Limpiar el panel
            pnl.removeAll();
            //Definir el archivo de la imagen
            File f = new File(nombreArchivo);
            //Verificar si existe el archivo
            if (f.exists()) {
                //Leer el archivo
                BufferedImage bimg = ImageIO.read(f);
                //Cargar la imagen en un objeto JLABEL
                ImageIcon icon = new ImageIcon(bimg);
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                //Definir un panel de desplazamiento
                JScrollPane jsp = new JScrollPane(lbl);
                //Ubicarlo en el panel
                jsp.setBounds(0, 0, pnl.getWidth(), pnl.getHeight());
                //Agregarlo al panel
                pnl.add(jsp);
                pnl.revalidate();
            }
            pnl.repaint();

        } catch (Exception ex) {
        }
    }

}
