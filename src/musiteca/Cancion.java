package musiteca;

import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;

public class Cancion extends Item {

    String genero;
    String duracion;
    int año;
    String nombreArtista;

    public void actualizar(String[] datos) {
        if (datos != null && datos.length >= 4) {
            this.nombre = datos[0];
            this.duracion = datos[1];
            try {
                this.año = Integer.parseInt(datos[2]);
            } catch (Exception e) {
                this.año = 0;
            }

            this.genero = datos[3];

        }
    }

    public void actualizar(String[] datos, String nombreArtista) {
        actualizar(datos);
        this.nombreArtista = nombreArtista;
    }

    public void reproducir() {
        String nombreArchivo = rutaCanciones + "/" + this.nombreArtista + " - " + this.nombre + ".mp3";
        
        ReproductorMP3.reproducir(nombreArchivo);
    }

    //********* Atributos estaticos *********
    public static List<Cancion> canciones;
    public static Document dXML;
    public static String rutaCanciones;

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
                            c.actualizar(datos, artista.nombre);
                            //Agregar el artista a la lista
                            canciones.add(c);
                        }
                    }
                }
            }
        }
    }

    public static void mostrar(JTable tbl) {
        String[] encabezados = new String[]{"Titulo", "Duracion", "Año", "Genero"};

        String[][] datos = new String[canciones.size()][encabezados.length];

        for (int i = 0; i < canciones.size(); i++) {
            Cancion c = canciones.get(i);
            datos[i][0] = c.nombre;
            datos[i][1] = c.duracion;
            datos[i][2] = String.valueOf(c.año);
            datos[i][3] = c.genero;

        }

        tbl.setModel(new DefaultTableModel(datos, encabezados));
        tbl.getSelectionModel().removeListSelectionListener(Artista.escuchadorEventoSeleccion);

    }

}
