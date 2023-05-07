import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElementoPanelConsultar extends JPanel{
	Color naranja= new Color(233,98,65);
	Color azul= new Color(56,57,82);
	JPanel elemento;
	JLabel lblNombre;
	//JLabel descripcion ;
	//JLabel categoria;
	JLabel price;
	String nombrePlatillo;
	float precio;
	String rutaImagen;
	 //imagenOriginal;
	
	public ElementoPanelConsultar(String nombre, String ruta,float precio) {
		rutaImagen=ruta;
		this.precio=precio;
		nombrePlatillo=nombre;
		
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
        
        
    	// --- panel centro
        JPanel pCentro= new JPanel(new BorderLayout()); 
        pCentro.setBackground(Color.white);
        // redimensionar foto
        ImageIcon imagenOriginal = new ImageIcon(ruta);
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
        JLabel lblimagen = new JLabel(imagenRedimensionadaIcon);
         
        
        lblNombre = new JLabel(nombrePlatillo,JLabel.CENTER);
        lblNombre.setForeground(naranja);
        lblNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        lblNombre.setPreferredSize(new Dimension(200, 20)); // Establecemos tamaño preferido en lugar de setSize()
        
       
        price = new JLabel("$"+precio+" MXN",JLabel.CENTER);
        price.setForeground(naranja);
        price.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        price.setPreferredSize(new Dimension(66, 20)); 
        
        pCentro.add(lblimagen,BorderLayout.CENTER);
        elemento.add(lblNombre,BorderLayout.NORTH);
        pCentro.add(price,BorderLayout.SOUTH);
        
        
        // --- panel abajo
        JPanel pBotones= new JPanel(new FlowLayout( 0, 35, 10)); 
        pBotones.setBackground(Color.white);
        
        JButton btnEliminar= new JButton("Eliminar");
        btnEliminar.setBackground(naranja);
        btnEliminar.setForeground(Color.white);
        btnEliminar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        btnEliminar.setFocusPainted(false);
        
        JButton btnVer= new JButton("Ver");
        btnVer.setPreferredSize(new Dimension(75,30));
        btnVer.setBackground(naranja);
        btnVer.setForeground(Color.white);
        btnVer.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        btnVer.setFocusPainted(false);
        
        pBotones.add(btnEliminar);
        pBotones.add(btnVer);
        
        elemento.add(pCentro,BorderLayout.CENTER);
        elemento.add(pBotones,BorderLayout.SOUTH);
	}
}
