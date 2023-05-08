import javax.swing.ImageIcon;

public class Platillo {
    public String nombre;
    public String descripcion;
    public String categoria;
    public float precio;
    public String rutaImagen;
    public ImageIcon imagen; // pendiente

    public Platillo(String nombrePlatillo,String descripcion, String categoria, float precio, String rutaImagen){
        nombre=nombrePlatillo;
        this.descripcion=descripcion;
        this.categoria=categoria;
        this.precio=precio;
        this.rutaImagen= rutaImagen;
        imagen= new ImageIcon(rutaImagen);
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

    public ImageIcon getImagenIcon(){
        return imagen;
    }

    //sets para cambiar los datos
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setdescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }
    public void setPrecio(float precio){
        this.precio=precio;
    }
    public void setRutaImagen(String rutaImagen){
        this.rutaImagen= rutaImagen;
    }

    public void setImagenIcon(String rutaImagen){
        imagen= new ImageIcon(rutaImagen);
    }
}
