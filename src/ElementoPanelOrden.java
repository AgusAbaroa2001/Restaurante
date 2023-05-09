import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElementoPanelOrden extends JPanel{
    JButton btninfo;
    JButton btnMenos;
    JButton btnMas;

	Color naranja= new Color(233,98,65);
	Color azul= new Color(56,57,82);

	JPanel elemento;
	JLabel lblcantidad;
	//JLabel descripcion ;
	//JLabel categoria;
	
	String nombrePlatillo;
	float precio;
	String rutaImagen;
	int cantidad;
    Platillo platillo;
	 //imagenOriginal;
	
	public ElementoPanelOrden(Platillo platillo) {
		cantidad=0;
        this.platillo=platillo;
		rutaImagen=platillo.getRutaImagen();
		this.precio=platillo.getPrecio();
		nombrePlatillo=platillo.getNombre();
		
		// --- panel principal
		elemento = new JPanel(new BorderLayout()); 
        elemento.setBackground(Color.white);
        
        // labels para rellenar marcos
        JLabel lblBorde1= new JLabel("    ", JLabel.CENTER);
        elemento.add(lblBorde1,BorderLayout.WEST);
		
		JLabel lblBorde2= new JLabel("    ", JLabel.CENTER);
		elemento.add(lblBorde2,BorderLayout.EAST);
		
		JLabel lblBorde3= new JLabel("    ", JLabel.CENTER);
		elemento.add(lblBorde3,BorderLayout.NORTH);
        
        
    	// ---------- panel centro ---------------
        JPanel pCentro= new JPanel(new BorderLayout()); 
        pCentro.setBackground(Color.white);
        // redimensionar foto
        ImageIcon imagenOriginal = new ImageIcon(rutaImagen);
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
        JLabel lblimagen = new JLabel(imagenRedimensionadaIcon);
         
        
        
       
        JLabel price = new JLabel("$"+precio+" MXN",JLabel.CENTER);
        price.setForeground(naranja);
        price.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        price.setPreferredSize(new Dimension(66, 20)); 
        
        pCentro.add(lblimagen,BorderLayout.CENTER);
        //elemento.add(lblNombre,BorderLayout.NORTH);
        pCentro.add(price,BorderLayout.SOUTH);
        
        //-------panel nombre platillo y boton info arriba 
        JPanel pArriba= new JPanel(new FlowLayout( FlowLayout.CENTER)); 
        pArriba.setBackground(Color.white);
        JLabel lblNombre = new JLabel(nombrePlatillo,JLabel.CENTER);
        lblNombre.setForeground(naranja);
        lblNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        lblNombre.setPreferredSize(new Dimension(200, 20)); // Establece tamaño preferido en lugar de setSize() para cuando se usan layouts
        
        btninfo= new JButton("i");
        btninfo.setBackground(naranja);
        btninfo.setForeground(Color.white);
        btninfo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        btninfo.setFocusPainted(false);
        
        pArriba.add(lblNombre);
        pArriba.add(btninfo);
        
        elemento.add(pArriba,BorderLayout.NORTH);
        
        // --- panel abajo----
        JPanel pAbajo= new JPanel(new FlowLayout( FlowLayout.CENTER)); 
        pAbajo.setBackground(Color.white);
        
        btnMenos= new JButton("-");
        btnMenos.setBackground(naranja);
        btnMenos.setForeground(Color.white);
        btnMenos.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        btnMenos.setFocusPainted(false);
        
        lblcantidad = new JLabel(""+cantidad,JLabel.CENTER);
        lblcantidad.setForeground(naranja);
        lblcantidad.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        lblcantidad.setPreferredSize(new Dimension(66, 20)); 
        
        btnMas= new JButton("+");
        btnMas.setBackground(naranja);
        btnMas.setForeground(Color.white);
        btnMas.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        btnMas.setFocusPainted(false);
       
        //---- acciones botones para aumentar o disminuir cantidades------
        /*btnMenos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cantidad>0) {
					cantidad--;
					lblcantidad.setText(""+cantidad);
				}
			}
       
        });
        
        btnMas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					cantidad++;
					lblcantidad.setText(""+cantidad);
			}
        	
        });*/
        
        pAbajo.add(btnMenos);
        pAbajo.add(lblcantidad);
        pAbajo.add(btnMas);
        
        elemento.add(pCentro,BorderLayout.CENTER);
        elemento.add(pAbajo,BorderLayout.SOUTH);
	}
    
    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        lblcantidad.setText(""+cantidad);
        this.cantidad=cantidad;
    }


    public JButton getbtnInfo(){
        return btninfo;
    }

    public JButton getbtnMenos(){
        return btnMenos;
    }

    public JButton getbtnMas(){
        return btnMas;
    }
}
