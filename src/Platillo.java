import javax.swing.ImageIcon;

public class Platillo {
    public String nombre;
    public String descripcion;
    public String categoria;
    public float precio;
    public String rutaImagen;
    //public ImageIcon imagen; // pendiente

    public Platillo(String nombrePlatillo,String descripcion, String categoria, float precio){
        nombre=nombrePlatillo;
        this.descripcion=descripcion;
        this.categoria=categoria;
        this.precio=precio;
    }

    // gets para acceder a los datos
    public String getNombre(){
        return nombre;
    }
    public String getdescripcion(){
        return descripcion;
    }
    public String getCategoria(){
        return categoria;
    }
    public float getPrecio(){
        return precio;
    }
    public String getRutaImagen(){
        return rutaImagen;
    }

}
