import java.util.ArrayList;

public class ListaOrdenes {
    private ArrayList<Object> lista = new ArrayList<Object>();

    public ListaOrdenes(){}
    public void agregarOrden(Orden orden){
        this.lista.add(orden);
    }

    public void modificarOrden(int posicion, Orden orden){
        this.lista.set(posicion, orden);
    }
    
    public void eliminarOrden(int posicion){
        this.lista.remove(posicion);
    }
    
    public Orden obtenerOrden(int posicion){
        return (Orden)lista.get(posicion);
    }
    
    public int cantidadOrdenes(){
        return this.lista.size();
    }
    
    public int buscaId(int id){
        for(int i = 0; i < cantidadOrdenes(); i++){
            if(id == obtenerOrden(i).getId())return i;
        }
        return -1;
    }
}
