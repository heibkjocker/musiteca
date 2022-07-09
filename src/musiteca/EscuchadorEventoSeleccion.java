package musiteca;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.*;


/**
 *
 * @author Cristian Quintero
 */
//En esta clase se programa el evento para seleccionar artista o cancion desde la tabla
public class EscuchadorEventoSeleccion implements ListSelectionListener {
    
    private JTable tbl;
    private JPanel pnl;
    
    public EscuchadorEventoSeleccion(JTable tbl, JPanel pnl){
        this.tbl=tbl;
        this.pnl=pnl;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Obtener la fila seleccionada en el modelo de datos
        int fila = e.getFirstIndex();
        //Listar las canciones del artista
        Cancion.obtener(Artista.artistas.get(fila));
        //Mostrar las canciones
        Cancion.mostrar(tbl);
        //Mostrar la foto
        Artista.artistas.get(fila).mostrarFoto(pnl);
    }
    
}
