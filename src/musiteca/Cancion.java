package musiteca;

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
    
    
    

}
