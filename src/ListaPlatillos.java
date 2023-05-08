import java.util.ArrayList;

public class ListaPlatillos {
    private ArrayList<Object> lista = new ArrayList<Object>();
   
    public ListaPlatillos(){}

    public void agregarRegistro(Platillo platillo){
        this.lista.add(platillo);
    }

    public void modificarRegistro(int posicion, Platillo platillo){
        this.lista.set(posicion, platillo);
    }
    
    public void eliminarRegistro(int posicion){
        this.lista.remove(posicion);
    }
    
    public Platillo obtenerRegistro(int posicion){
        return (Platillo)lista.get(posicion);
    }
    
    public int cantidadRegistro(){
        return this.lista.size();
    }
    
    public int buscaNombre(String nombre){
        for(int i = 0; i < cantidadRegistro(); i++){
           if(nombre.equals(obtenerRegistro(i).getNombre()))return i;
        }
        return -1;
    }
}
