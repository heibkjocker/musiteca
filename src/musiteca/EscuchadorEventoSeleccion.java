package musiteca;

import javax.swing.JTable;
import javax.swing.event.*;


/**
 *
 * @author Cristian Quintero
 */
//En esta clase se programa el evento para seleccionar artista o cancion desde la tabla
public class EscuchadorEventoSeleccion implements ListSelectionListener {
    
    private JTable tbl;
    
    public EscuchadorEventoSeleccion(JTable tbl){
        this.tbl=tbl;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Obtener la fila seleccionada en el modelo de datos
        int fila = e.getFirstIndex();
        //Listar las canciones del artista
        Cancion.obtener(Artista.artistas.get(fila));
        //Mostrar las canciones
        Cancion.mostrar(tbl);
    }
    
}
