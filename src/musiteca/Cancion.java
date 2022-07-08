package musiteca;

import java.util.*;
import org.w3c.dom.*;

public class Cancion extends Item {

    String genero;
    String duracion;
    int año;

    public void actualizar(String[] datos) {
        if (datos != null && datos.length >= 4) {
            this.nombre = datos[0];
            this.genero = datos[1];
            this.duracion = datos[2];
            this.año = Integer.parseInt(datos[3]);
        }
    }

    //********* Atributos estaticos *********
    public static List<Cancion> canciones;
    public static Document dXML;

    //********* Metodos estaticos *********
    public static void obtener(Artista artista) {
        if (dXML != null) {
            //Obtener los nodos que correspondan al tipo pedido
            NodeList nlCancion = dXML.getElementsByTagName("Cancion");
            canciones = new ArrayList<>();
            //Recorrer todos los nodos obtenidos
            for (int i = 0; i < nlCancion.getLength(); i++) {
                if (nlCancion.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    //Obtener el nodo padre
                    Element nodoPadre = (Element) nlCancion.item(i).getParentNode();
                    if (nodoPadre != null) {
                        //Obtener el nodo de filtro
                        NodeList nlNodoPadre = nodoPadre.getElementsByTagName("Nombre");
                        //Verificar si cumple la condición
                        if (nlNodoPadre.item(0).getTextContent().equals(artista.nombre)) {
                            Element nodo = (Element) nlCancion.item(i);

                            String[] datos = new String[4];
                            //Obtener el nodo del titulo
                            NodeList nl = nodo.getElementsByTagName("Titulo");
                            datos[0] = nl.item(0).getTextContent();
                            //Obtener el nodo de la duracion
                            nl = nodo.getElementsByTagName("Duracion");
                            datos[1] = nl.item(0).getTextContent();
                            //Obtener el nodo del año
                            nl = nodo.getElementsByTagName("Año");
                            datos[2] = nl.item(0).getTextContent();
                            //Obtener el nodo del genero
                            nl = nodo.getElementsByTagName("Genero");
                            datos[3] = nl.item(0).getTextContent();

                            Cancion c = new Cancion();
                            c.actualizar(datos);
                            //Agregar el artista a la lista
                            canciones.add(c);
                        }
                    }
                }
            }
        }
    }

}
