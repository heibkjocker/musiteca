package musiteca;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;

public class Artista extends Item {

    String tipo;
    String pais;

    public void actualizar(String[] datos) {
        if (datos != null && datos.length >= 3) {
            this.nombre = datos[0];
            this.tipo = datos[1];
            this.pais = datos[2];
        }
    }
    
    public void mostrarFoto(JPanel pnl){
        String nombreArchivo=rutaFotos+"/"+this.nombre+".jpg";
        
        Archivo.mostrarImagen(pnl, nombreArchivo);
    }

    //********* Atributos estaticos *********
    public static List<Artista> artistas;
    public static Document dXML;
    public static EscuchadorEventoSeleccion escuchadorEventoSeleccion;
    public static String rutaFotos;

    //********* Metodos estaticos *********
    public static void obtener() {
        //Instanciar la coleccion de artistas
        artistas = new ArrayList<>();

        if (dXML != null) {
            //Consultar los nodos de artista
            NodeList nlArtista = dXML.getElementsByTagName("Artista");
            //recorrer la lista de nodos
            for (int i = 0; i < nlArtista.getLength(); i++) {
                if (nlArtista.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element nodo = (Element) nlArtista.item(i);

                    String[] datos = new String[3];
                    //Consultar el nombre del artista en elñ respectivo nodo
                    NodeList nl = nodo.getElementsByTagName("Nombre");
                    datos[0] = nl.item(0).getTextContent();
                    //Consultar el tipo del artista en el respectivo nodo
                    nl = nodo.getElementsByTagName("Tipo");
                    datos[1] = nl.item(0).getTextContent();
                    //Consultar el pais del artista en el respectivo nodo
                    nl = nodo.getElementsByTagName("Pais");
                    datos[2] = nl.item(0).getTextContent();

                    //agregar el artista a la lista
                    Artista a = new Artista();
                    a.actualizar(datos);
                    artistas.add(a);
                }
            }
        }
    }

    public static void mostrar(JTable tbl, JPanel pnl) {
        String[] encabezados = new String[]{"Nombre", "Tipo", "Pais"};

        String[][] datos = new String[artistas.size()][encabezados.length];

        for (int i = 0; i < artistas.size(); i++) {
            Artista a = artistas.get(i);
            datos[i][0] = a.nombre;
            datos[i][1] = a.tipo;
            datos[i][2] = a.pais;
        }

        tbl.setModel(new DefaultTableModel(datos, encabezados));
        escuchadorEventoSeleccion = new EscuchadorEventoSeleccion(tbl, pnl);
        tbl.getSelectionModel().addListSelectionListener(escuchadorEventoSeleccion);
    }

}
