import java.util.ArrayList;

public class Orden {
    public int id;
    public String nombreCliente;
    public static int contador = 0;
    public float precioTotal;
    public int totalPlatillos;
     ArrayList<String> nombresPlatilos;
     ArrayList<Integer> cantidadesPlatillos;
	 String listaCantidades;
    
    public Orden(String nombreCliente,float precioTotal,int totalPlatillos, ArrayList<String> nombresPlatilos,ArrayList<Integer> cantidadesPlatillos) {
        this.id = ++contador;
        this.nombreCliente = nombreCliente;
        this.precioTotal=precioTotal;
        this.totalPlatillos=totalPlatillos;
        this.nombresPlatilos=nombresPlatilos;
        this.cantidadesPlatillos=cantidadesPlatillos;

		listaCantidades= cantidadesPlatillos.toString().replace("[", "")
                                        .replace("]", "")
                                        .replace(", ", ",");
		
    } 

    public Orden() {
		this.id = ++contador;
	}

	public int getId() {
		return id;
	} 
	public void setId(int id) {
		this.id=id;
	} 

    public String getNombreCliente() {
		return nombreCliente;
	}
	public String getStringCantidadesPlatillos() {
		return listaCantidades;
	}
	

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getTotalPlatillos() {
		return totalPlatillos;
	}

	public void setTotalPlatillos(int totalPlatillos) {
		this.totalPlatillos = totalPlatillos;
	}

	public ArrayList<String> getNombresPlatilos() {
		return nombresPlatilos;
	}

	public void setNombresPlatilos(ArrayList<String> nombresPlatilos) {
		this.nombresPlatilos = nombresPlatilos;
	}

	public ArrayList<Integer> getCantidadesPlatillos() {
		return cantidadesPlatillos;
	}

	public void setCantidadesPlatillos(ArrayList<Integer> cantidadesPlatillos) {
		this.cantidadesPlatillos = cantidadesPlatillos;
		listaCantidades= cantidadesPlatillos.toString().replace("[", "")
                                        .replace("]", "")
                                        .replace(", ", ",");
	}

	public void setCantidadesPlatillos2(String cantidadesPlatillos) {
		
		listaCantidades= cantidadesPlatillos.toString().replace("[", "")
                                        .replace("]", "")
                                        .replace(", ", ",");
		ArrayList<Integer> lista = new ArrayList<>();
		for (String numero :listaCantidades.split(",")) {
			lista.add(Integer.parseInt(numero));
		}
		this.cantidadesPlatillos = lista;
	}

	
}
