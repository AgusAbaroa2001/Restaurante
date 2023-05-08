import java.util.ArrayList;

public class ListaPlatillos {
    private ArrayList<Object> lista = new ArrayList<Object>();
   
    public ListaPlatillos(){}

    public void agregarPlatillo(Platillo platillo){
        this.lista.add(platillo);
    }

    public void modificarPlatillo(int posicion, Platillo platillo){
        this.lista.set(posicion, platillo);
    }
    
    public void eliminarPlatillo(int posicion){
        this.lista.remove(posicion);
    }
    
    public Platillo obtenerPlatillo(int posicion){
        return (Platillo)lista.get(posicion);
    }
    
    public int cantidadPlatillos(){
        return this.lista.size();
    }
    
    public int buscaNombre(String nombre){
        for(int i = 0; i < cantidadPlatillos(); i++){
           if(nombre.equals(obtenerPlatillo(i).getNombre()))return i;
        }
        return -1;
    }
}
