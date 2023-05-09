import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaName;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.Utilities;

public class Ventana extends JFrame{
    public JPanel panel;
    public String nombreUser;
    public String correoUser;
    public Color naranja= new Color(233,98,65);
	public Color azul= new Color(56,57,82);
    public int anterior;
    public int actual;

    //---- variables para hacer funcionar CRUD platillos---
    public String nombreImagen;
    public String rutaTxt = "platillos.txt"; 
    public Platillo platillo;
    public ListaPlatillos listaPlatillos;
    String [] opcs ={"Comida Rapida", "Mariscos", "Ensaladas", "Postres","Bebidas","Sushi","Pastas","Comida Mexicana"};
    //
    public Ventana() {

        this.setSize(900,700); // medidas provisionales, se pueden cambiar
        this.setLocationRelativeTo(null);
        this.setTitle("Gourmet Eats");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        listaPlatillos= new ListaPlatillos();
        cargarTxtPlatillos(); // try catch?
        
        panel= login(); // panel  principal
        //panel= crearPlatillo(); // para testear paneles
        panel.setSize(this.getWidth(), this.getHeight());;
        panel.setLocation(0,0);
        // panel.setBackground(Color.decode("#0665c0"));
        this.add(panel);

        this.revalidate();
        this.setVisible(true);
    }

    //------ mensaje de dialogo customizable----
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
    //-------funcion para agregar del archivo txt los platillos a la lista --------
    private void cargarTxtPlatillos() {
        File ruta = new File(rutaTxt);
        try{
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);

            String linea = null;
            while((linea = br.readLine())!=null){ // --este while agrega a la lista todos los Platillos del txt--
                StringTokenizer st = new StringTokenizer(linea, "|"); // esto separa el renglon del txt para poder asignarle al producto sus atributos
                platillo = new Platillo();
                platillo.setNombre(st.nextToken());
                platillo.setDescripcion(st.nextToken());
                platillo.setCategoria(st.nextToken());
                platillo.setPrecio(Float.parseFloat(st.nextToken())); //conversion a float
                platillo.setRutaImagen(st.nextToken()); // asigna la ruta de la imagen y su icono en caso de ocuparlo
                
                listaPlatillos.agregarPlatillo(platillo);
            }
            br.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    //----- funcion para pasar lo de la lista Platillos al txt----
    public void grabar_txt(){
        FileWriter fw;
        PrintWriter pw;
        try{
            fw = new FileWriter(rutaTxt);
            pw = new PrintWriter(fw);
            
            for(int i = 0; i < listaPlatillos.cantidadPlatillos(); i++){
                platillo = listaPlatillos.obtenerPlatillo(i);
                pw.println(String.valueOf(platillo.getNombre()+"|"+platillo.getDescripcion()+"|"+platillo.getCategoria()+"|"+platillo.getPrecio()+"|"+platillo.getRutaImagen())); //pasa lo del objeto al archivo
            }
             pw.close();
            
        }catch(Exception ex){
            mensaje("Error al grabar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    //---- funcion q agrega platillo a la lista y reescribe el txt---  ( hay q verificar que los txt field esten bien)
    public void crearNuevoPlatillo(String nombrePlatillo,String descripcion, String categoria, float precio, String rutaImagen ){
        platillo = new Platillo(nombrePlatillo,descripcion,categoria,precio,rutaImagen);
        
        //if(listaPlatillos.buscaNombre(platillo.getNombre())!= -1)mensaje("Este nombre ya existe"); // si ya existe
       // else {
        listaPlatillos.agregarPlatillo(platillo);
        mensaje("Creado correctamente");
        grabar_txt();
       // }
        
        
    }
    //---- funcion q modifica el platillo de la lista y reescribe el txt---  ( hay q verificar que los txt field esten bien)    
    public void modificarPlatillo(String nombrePlatillo,String descripcion, String categoria, float precio, String rutaImagen ){
        
        int posicion = listaPlatillos.buscaNombre(nombrePlatillo);
        platillo = new Platillo(nombrePlatillo, descripcion, categoria, precio,rutaImagen );
        
        if(posicion != -1) // si no lo encuentra en la lista
        listaPlatillos.modificarPlatillo(posicion, platillo);
        
        grabar_txt();
    }
    //---- funcion para eliminar platillo de la lista y reescribir txt---
    public void eliminarPlatillo(String nombrePlatillo){

        int posicion = listaPlatillos.buscaNombre(nombrePlatillo);

        if(posicion == -1) mensaje("codigo no existe");
        
        else{
            int s = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este producto","Si/No",0);
            if(s == 0){
                listaPlatillos.eliminarPlatillo(posicion);
                grabar_txt();
            }
        }
 
    }
     
    // -------------------------------MenuBar-------------------------------
    public void menu(JPanel panelActual){
        JMenuBar menuA = new JMenuBar();
        menuA.setLocation(0,0);
        menuA.setSize(900,20);
        
        JMenu menu1 = new JMenu("Menu");

        JMenuItem item1 = new JMenuItem("Inicio");
        JMenuItem item2 = new JMenuItem("Cerrar Sesion");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(2);
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(1);
            }
        });

        menu1.add(item1);
        menu1.add(item2);
        menuA.add(menu1);
        panelActual.add(menuA);

    }

    //-------------------------------pantalla de login--------------------------------
    public JPanel login() {

        JPanel login = new JPanel();
        login.setSize(this.getWidth(), this.getHeight());
        login.setBackground(Color.decode("#E96241"));
        login.setLocation(0, 0);
        login.setLayout(null);

        JPanel deco1 = new JPanel();
        deco1.setBackground(Color.decode("#383952"));
        deco1.setSize(450,500);
        deco1.setLocation(350, 100);
        deco1.setLayout(null);
        deco1.repaint();
        deco1.revalidate();
        login.add(deco1);

        JPanel deco2 = new JPanel();
        deco2.setBackground(Color.decode("#FFFFFF"));
        deco2.setSize(260,500);
        deco2.setLocation(90,100);
        deco2.setLayout(null);
        login.add(deco2);

        JLabel imageEats = new JLabel(new ImageIcon("logo (2).png"));
        imageEats.setLocation(0,150);
        imageEats.setSize(227,150);
        deco2.add(imageEats);

        JLabel welcum = new JLabel("Bienvenido");
        welcum.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        welcum.setForeground(Color.decode("#E96241"));
        welcum.setSize(250,70);
        welcum.setLocation(35,280);
        deco2.add(welcum);


        JLabel lblLogin = new JLabel("Iniciar Sesion");
        lblLogin.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        lblLogin.setForeground(Color.decode("#E96241"));
        lblLogin.setSize(400,100);
        lblLogin.setLocation(120,30);
        deco1.add(lblLogin);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        lblCorreo.setForeground(Color.decode("#FFFFFF"));
        lblCorreo.setSize(250,50);
        lblCorreo.setLocation(50,120);
        deco1.add(lblCorreo);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        campoCorreo.setBackground(Color.decode("#FFFFFF"));
        campoCorreo.setSize(290,40);
        campoCorreo.setLocation(50,170);
        campoCorreo.repaint();
        deco1.add(campoCorreo);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        lblPass.setForeground(Color.decode("#FFFFFF"));
        lblPass.setSize(250,50);
        lblPass.setLocation(50,250);
        deco1.add(lblPass);

        JPasswordField campoPass = new JPasswordField();
        campoPass.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        campoPass.setBackground(Color.decode("#FFFFFF"));
        campoPass.setSize(290,40);
        campoPass.setLocation(50,300);
        campoPass.repaint();
        deco1.add(campoPass);

        JButton btnLogin = new JButton();
        btnLogin.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        btnLogin.setSize(200, 40);
        btnLogin.setLocation(100, 380);
        btnLogin.setText("Acceder");
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(Color.decode("#F36B31"));
        btnLogin.setForeground(Color.decode("#FFFFFF"));
        btnLogin.repaint();
        btnLogin.revalidate();

        deco1.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String correo = campoCorreo.getText();
                String password = String.valueOf(campoPass.getPassword());
                String[] data;

                try{

                    BufferedReader BR = new BufferedReader(new FileReader("users.txt"));
                    String renglon;
                    boolean validacion = false;

                    while((renglon = BR.readLine()) != null ){

                        data = renglon.split(",");

                        if (data[2].equals(correo) && data[3].equals(password)) {

                            nombreUser=data[0];
                            correoUser=data[2];

                            JOptionPane.showMessageDialog(null, "Bienvenido "+ nombreUser,"Inicio con exito", JOptionPane.INFORMATION_MESSAGE);
                            actualizarPanel(2);

                            validacion = true;

                        }
                    }
                    if (validacion == false){
                        JOptionPane.showMessageDialog(null, "El usuario y contraseña no coindicen","Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    BR.close();

                }catch(Exception f){
                    System.err.println("No se encontro archivo");
                    
                }
            }
        });

        // this.add(login);
        return login;
    }

    //-----------------esta es la pantalla de inicio despues del login---------------------------------
    public JPanel pantallaInicio(){

        JPanel inicio = new JPanel();
        inicio.setSize(this.getWidth(),this.getHeight());
        inicio.setBackground(Color.decode("#E96241"));
        inicio.setLayout(null);
       
        // barra menu
        menu(inicio); 

        //panel de platillos
        JPanel plato = new JPanel();
        plato.setBackground(Color.decode("#383952"));
        plato.setSize(350,442);
        plato.setLocation(60, 120);
        plato.setLayout(null);
        plato.repaint();
        plato.revalidate();
        inicio.add(plato);

        JLabel imageTenedor = new JLabel(new ImageIcon("iconoTenedor.png"));
        imageTenedor.setLocation(50,30);
        imageTenedor.setSize(250,300);
        plato.add(imageTenedor);



        //panel de ordenes
        JPanel orden = new JPanel();
        orden.setBackground(Color.decode("#383952"));
        orden.setSize(350,442);
        orden.setLocation(500, 120);
        orden.setLayout(null);
        orden.repaint();
        orden.revalidate();
        inicio.add(orden);

        JLabel imageNota = new JLabel(new ImageIcon("iconoNota.png"));
        imageNota.setLocation(50,30);
        imageNota.setSize(250,300);
        orden.add(imageNota);

        JLabel titulo = new JLabel("Inicio");
        titulo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 60));
        titulo.setForeground(Color.decode("#FFFFFF"));
        titulo.setSize(350,100);
        titulo.setLocation(380,25);
        inicio.add(titulo);

        JLabel tituloOrden = new JLabel("Ordenes");
        tituloOrden.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 40));
        tituloOrden.setForeground(Color.decode("#FFFFFF"));
        tituloOrden.setSize(350,50);
        tituloOrden.setLocation(105,20);
        orden.add(tituloOrden);

        JLabel tituloPlato = new JLabel("Platillos");
        tituloPlato.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 40));
        tituloPlato.setForeground(Color.decode("#FFFFFF"));
        tituloPlato.setSize(350,50);
        tituloPlato.setLocation(105,20);
        plato.add(tituloPlato);

        //boton consulta de platillo
        JButton consulta = new JButton("Consultar");
        consulta.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        consulta.setSize(250, 40);
        consulta.setLocation(50, 280);
        consulta.setFocusPainted(false);
        consulta.setBackground(Color.decode("#E96241"));
        consulta.setForeground(Color.decode("#FFFFFF"));
        consulta.repaint();
        consulta.revalidate();
        consulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(4);
                

            }
        });

        //boton crear de platillo
        JButton crear = new JButton("Crear");
        crear.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        crear.setSize(250, 40);
        crear.setLocation(50, 350);
        crear.setFocusPainted(false);
        crear.setBackground(Color.decode("#E96241"));
        crear.setForeground(Color.decode("#FFFFFF"));
        crear.repaint();
        crear.revalidate();

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(3);

            }
        });


        //boton consulta ordenes
        JButton consultaOrden = new JButton("Consultar");
        consultaOrden.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        consultaOrden.setSize(250, 40);
        consultaOrden.setLocation(50, 280);
        consultaOrden.setFocusPainted(false);
        consultaOrden.setBackground(Color.decode("#E96241"));
        consultaOrden.setForeground(Color.decode("#FFFFFF"));
        consultaOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(9);
            }
        });

        consultaOrden.repaint();
        consultaOrden.revalidate();

        //boton crear ordenes
        JButton crearOrden = new JButton("Crear");
        crearOrden.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        crearOrden.setSize(250, 40);
        crearOrden.setLocation(50, 350);
        crearOrden.setFocusPainted(false);
        crearOrden.setBackground(Color.decode("#E96241"));
        crearOrden.setForeground(Color.decode("#FFFFFF"));
        crearOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(7);
            }
        });
        crearOrden.repaint();
        crearOrden.revalidate();


        //agregar botones a paneles
        plato.add(consulta);
        plato.add(crear);
        orden.add(consultaOrden);
        orden.add(crearOrden);


        return inicio;

    }


    //------------------panel creacion de platillos-----------------------
    public JPanel crearPlatillo(){
        nombreImagen = "iconoImagen.png";
        
        JPanel fondo = new JPanel();
        fondo.setBackground(Color.decode("#E96241"));
        fondo.setSize(this.getWidth(),this.getHeight());
        fondo.setLayout(null);

        //Bar menu
        menu(fondo);

        JPanel subfondo = new JPanel();
        subfondo.setBackground(Color.decode("#383952"));
        subfondo.setSize(810,600);
        subfondo.setLocation(40,30);
        subfondo.setLayout(null);
        fondo.add(subfondo);

        JLabel tituloCrear = new JLabel("Crear Platillo");
        tituloCrear.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloCrear.setSize(500,50);
        tituloCrear.setLocation(30,10);
        tituloCrear.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloCrear);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        nombre.setSize(150,40);
        nombre.setLocation(70,90);
        nombre.setForeground(Color.decode("#E96241"));
        subfondo.add(nombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtNombre.setSize(350,30);
        txtNombre.setLocation(70,140);
        subfondo.add(txtNombre);

        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        desc.setSize(250,40);
        desc.setLocation(70,190);
        desc.setForeground(Color.decode("#E96241"));
        subfondo.add(desc);

        JTextArea txtDesc = new JTextArea(10,20);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txtDesc.getText().length()>=100){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txtDesc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtDesc.setSize(350,90);
        txtDesc.setLocation(70,240);
        subfondo.add(txtDesc);

        JLabel lblSelec = new JLabel("Seleccione una categoría");
        lblSelec.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        lblSelec.setForeground(Color.decode("#E96241"));
        lblSelec.setSize(400,90);
        lblSelec.setLocation(70,310);
        subfondo.add(lblSelec);

        
        JComboBox <String> categorias = new JComboBox<>(opcs);
        categorias.getSelectedItem();
        categorias.setSize(250,20);
        categorias.setLocation(70,385);
        subfondo.add(categorias);

        JLabel precio = new JLabel("Precio");
        precio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        precio.setForeground(Color.decode("#E96241"));
        precio.setSize(200,90);
        precio.setLocation(70,390);
        subfondo.add(precio);

        JLabel signo = new JLabel("$");
        signo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        signo.setForeground(Color.decode("#E96241"));
        signo.setSize(200,90);
        signo.setLocation(50,420);
        subfondo.add(signo);

        JTextField txtPrecio = new JTextField();
        txtPrecio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtPrecio.setForeground(Color.decode("#E96241"));
        txtPrecio.setSize(150,30);
        txtPrecio.setLocation(70,450);
        subfondo.add(txtPrecio);
        txtPrecio.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char validar = e.getKeyChar();

                if(Character.isLetter(validar)){
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

         //  redimensiona y agrega la imagen del platillo
         int largo=200, ancho=200;
         /* 
         ImageIcon imagenOriginal = new ImageIcon(nombreImagen);
         Image imagen = imagenOriginal.getImage();
         Image imagenRedimensionada = imagen.getScaledInstance(largo, ancho, Image.SCALE_SMOOTH);*/
         
         //ImageIcon imagenRedimensionadaIcon = redimensionarImagen(nombreImagen,200,200);
         JLabel lblimagen = new JLabel(redimensionarImagen(nombreImagen,200,200));
         lblimagen.setSize(largo, ancho);
         lblimagen.setLocation(525,150);
         subfondo.add(lblimagen);

         // boton adjuntar foto---
        JButton fotobtn = new JButton("Adjuntar Foto");
        fotobtn.setSize(150,40);
        fotobtn.setLocation(550,360);
        fotobtn.setFocusPainted(false);
        fotobtn.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 12));
        fotobtn.setBackground(Color.decode("#E96241"));
        fotobtn.setForeground(Color.decode("#FFFFFF"));
        fotobtn.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // Mostrar el cuadro de diálogo para seleccionar un archivo
                JFileChooser selector = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("png,jpg", "png", "jpg");
                selector.setFileFilter(filtro);
                
                if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    // Si el usuario seleccionó un archivo, intentar cargarlo como imagen
                    File archivoImagen = selector.getSelectedFile();
                    try {
                            BufferedImage imagen = ImageIO.read(archivoImagen);
                            if(imagen!=null) {

                            // Guardar la imagen en la carpeta "imgPlatillos" en formato PNG
                            String nombreArchivo = archivoImagen.getName();
                            int extensionIndex = nombreArchivo.lastIndexOf(".");
                            nombreImagen= nombreArchivo.substring(0, extensionIndex) + ".png";
                            File carpetaImagenes = new File("imgPlatillos");
                            //carpetaImagenes.mkdir(); // Crear la carpeta si no existe
                            File archivoNuevo = new File(carpetaImagenes, nombreImagen);
                            ImageIO.write(imagen, "png", archivoNuevo);
                            
                        }
                            else
                                JOptionPane.showMessageDialog(null, "Seleccione un archivo fomato png o jpg");
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //ImageIcon imagenIcono = new ImageIcon("imgPlatillos/"+nombreImagen); 
                    lblimagen.setIcon(redimensionarImagen("imgPlatillos/"+nombreImagen,200,200));
                }
            }

        });

        subfondo.add(fotobtn);

        // Boton crear platillo
        JButton crearP = new JButton("Crear");
        crearP.setSize(200,50);
        crearP.setLocation(525,500);
        crearP.setFocusPainted(false);
        crearP.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        crearP.setBackground(Color.decode("#E96241"));
        crearP.setForeground(Color.decode("#FFFFFF"));
        crearP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre= txtNombre.getText();
                String descripcion=txtDesc.getText();
                String categoria=(String) categorias.getSelectedItem();; 
                String tprecio=txtPrecio.getText(); // primero pasarlo a string y luego a float
                
                String rutaImagen="imgPlatillos/"+nombreImagen;
                // condiciones
                
              if((!nombreImagen.equals("iconoImagen.png")&&!nombre.isEmpty())&&(!descripcion.isEmpty())&&(!tprecio.isEmpty())){

                float precio=Float.parseFloat(txtPrecio.getText());
                if(listaPlatillos.buscaNombre(nombre)!= -1)mensaje("Este platillo ya existe"); // si ya existe
                else {
                    crearNuevoPlatillo(nombre,descripcion,categoria,precio,rutaImagen);
                        actualizarPanel(2); // redirecciona a inicio
                }
                }
                else
                mensaje("Debe llenar todos los campos y adjuntar una imagen");
                
            }
        });
        subfondo.add(crearP);



        return fondo;
    }

    private ImageIcon redimensionarImagen(String rutaImagen,int largo, int ancho) {
        ImageIcon imagenOriginal = new ImageIcon(rutaImagen);
         Image imagen = imagenOriginal.getImage();
         Image imagenRedimensionada = imagen.getScaledInstance(largo, ancho, Image.SCALE_SMOOTH);
         ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
        return imagenRedimensionadaIcon;
    }

    //---------------------------informacion del platillo-----------------------------------------
    public JPanel infoPlatillo(Platillo platillo){
        //this.platillo=platillo;
        String nombrePlatillo=platillo.getNombre();
        String description=platillo.getDescripcion();
        String platoCategoria=platillo.getCategoria();
        float precioPlato=platillo.getPrecio();
        String nombreImagen=platillo.getRutaImagen();

        JPanel fondo = new JPanel();
        fondo.setBackground(Color.decode("#E96241"));
        fondo.setSize(this.getWidth(),this.getHeight());
        fondo.setLayout(null);

        //Bar menu
        menu(fondo);

        JPanel subfondo = new JPanel();
        subfondo.setBackground(Color.decode("#383952"));
        subfondo.setSize(810,600);
        subfondo.setLocation(40,30);
        subfondo.setLayout(null);
        fondo.add(subfondo);

        // alineacion X labels en comun
        int aligX=350;

        JLabel tituloInfo = new JLabel("Información del Platillo");
        tituloInfo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloInfo.setSize(600,50);
        tituloInfo.setLocation(30,10);
        tituloInfo.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloInfo);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        nombre.setSize(150,40);
        nombre.setLocation(aligX,90);
        nombre.setForeground(Color.decode("#E96241"));
        subfondo.add(nombre);

        // label para asignar nombre del platillo
        JLabel txtNombre = new JLabel(nombrePlatillo);
        txtNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtNombre.setSize(400,40);
        txtNombre.setBackground(Color.white);
        txtNombre.setLocation(aligX,130);
        txtNombre.setForeground(Color.white);
        subfondo.add(txtNombre);

        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        desc.setSize(250,40);
        desc.setLocation(aligX,190);
        desc.setForeground(Color.decode("#E96241"));
        subfondo.add(desc);

        //area para asigar la descripcion del platillo
        JTextArea txtDesc = new JTextArea(description);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setForeground(Color.white);
        txtDesc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtDesc.setSize(400,90);
        txtDesc.setLocation(aligX,240);
        txtDesc.setEditable(false);
        txtDesc.setBackground(azul);
        subfondo.add(txtDesc);

        //  redimensiona y agrega la imagen del platillo
        int largo=300, ancho=300;
        ImageIcon imagenOriginal = new ImageIcon(nombreImagen);
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(largo, ancho, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
        JLabel lblimagen = new JLabel(imagenRedimensionadaIcon);
        lblimagen.setSize(largo, ancho);
        lblimagen.setLocation(20,130);
        subfondo.add(lblimagen);
        /* 
        JLabel imagenTest = new JLabel(new ImageIcon(nombreImagen));
        imagenTest.setSize(230,400);
        imagenTest.setLocation(5,40);
        subfondo.add(imagenTest);*/

        JLabel categoria = new JLabel("Categoria");
        categoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        categoria.setSize(250,40);
        categoria.setLocation(aligX,390);
        categoria.setForeground(Color.decode("#E96241"));
        subfondo.add(categoria);

        // label para asignar la categoria del platillo
        JLabel txtCategoria= new JLabel(platoCategoria);
        txtCategoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtCategoria.setSize(250,30);
        txtCategoria.setLocation(aligX,440);
        txtCategoria.setForeground(Color.white);
        subfondo.add(txtCategoria);

        JLabel precio = new JLabel("Precio");
        precio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        precio.setSize(250,40);
        precio.setLocation(590,390);
        precio.setForeground(Color.decode("#E96241"));
        subfondo.add(precio);

        //label para asignar precio del platillo
        JLabel txtPrecio= new JLabel("$"+precioPlato);
        txtPrecio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtPrecio.setSize(250,30);
        txtPrecio.setLocation(590,440);
        txtPrecio.setForeground(Color.white);
        subfondo.add(txtPrecio);

        JButton regresar = new JButton("Regresar");
        regresar.setSize(150,50);
        regresar.setLocation(30,510);
        regresar.setFocusPainted(false);
        regresar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        regresar.setBackground(Color.decode("#E96241"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(4); // redirecciona a consultar platillos
            }
        });
        subfondo.add(regresar);

        JButton editar = new JButton("Editar");
        editar.setSize(150,50);
        editar.setLocation(600,510);
        editar.setFocusPainted(false);
        editar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        editar.setBackground(Color.decode("#E96241"));
        editar.setForeground(Color.decode("#FFFFFF"));
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel2(6,platillo); // redireccion a editar platillo y manda el platillo que será editado
               
            }
        });
        subfondo.add(editar);


        return fondo;
    }

    //-------------------------------informacion de la orden--------------------------------
    public JPanel infoOrden(){
        String nombrePlatillo="Pasta";
        String description="Deliciosa pasta con salsa de tomate ";
        String platoCategoria="Comida Rapida";
        float precioPlato=400;
        String nombreImagen="pasta.jpg";

        JPanel fondo = new JPanel();
        fondo.setBackground(Color.decode("#E96241"));
        fondo.setSize(this.getWidth(),this.getHeight());
        fondo.setLayout(null);

        //Bar menu
        menu(fondo);

        JPanel subfondo = new JPanel();
        subfondo.setBackground(Color.decode("#383952"));
        subfondo.setSize(810,600);
        subfondo.setLocation(40,30);
        subfondo.setLayout(null);
        fondo.add(subfondo);

        // alineacion X labels en comun
        int aligX=350;

        JLabel tituloInfo = new JLabel("Información del Platillo");
        tituloInfo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloInfo.setSize(600,50);
        tituloInfo.setLocation(30,10);
        tituloInfo.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloInfo);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        nombre.setSize(150,40);
        nombre.setLocation(aligX,90);
        nombre.setForeground(Color.decode("#E96241"));
        subfondo.add(nombre);

        // label para asignar nombre del platillo
        JLabel txtNombre = new JLabel(nombrePlatillo);
        txtNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtNombre.setSize(400,40);
        txtNombre.setBackground(Color.white);
        txtNombre.setLocation(aligX,130);
        txtNombre.setForeground(Color.white);
        subfondo.add(txtNombre);

        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        desc.setSize(250,40);
        desc.setLocation(aligX,190);
        desc.setForeground(Color.decode("#E96241"));
        subfondo.add(desc);

        //area para asigar la descripcion del platillo
        JTextArea txtDesc = new JTextArea(description);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setForeground(Color.white);
        txtDesc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtDesc.setSize(400,90);
        txtDesc.setLocation(aligX,240);
        txtDesc.setEditable(false);
        txtDesc.setBackground(azul);
        subfondo.add(txtDesc);

        //  redimensiona y agrega la imagen del platillo
        int largo=300, ancho=300;
        ImageIcon imagenOriginal = new ImageIcon(nombreImagen);
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(largo, ancho, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
        JLabel lblimagen = new JLabel(imagenRedimensionadaIcon);
        lblimagen.setSize(largo, ancho);
        lblimagen.setLocation(20,130);
        subfondo.add(lblimagen);
        /* 
        JLabel imagenTest = new JLabel(new ImageIcon(nombreImagen));
        imagenTest.setSize(230,400);
        imagenTest.setLocation(5,40);
        subfondo.add(imagenTest);*/

        JLabel categoria = new JLabel("Categoria");
        categoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        categoria.setSize(250,40);
        categoria.setLocation(aligX,390);
        categoria.setForeground(Color.decode("#E96241"));
        subfondo.add(categoria);

        // label para asignar la categoria del platillo
        JLabel txtCategoria= new JLabel(platoCategoria);
        txtCategoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtCategoria.setSize(250,30);
        txtCategoria.setLocation(aligX,440);
        txtCategoria.setForeground(Color.white);
        subfondo.add(txtCategoria);

        JLabel precio = new JLabel("Precio");
        precio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        precio.setSize(250,40);
        precio.setLocation(590,390);
        precio.setForeground(Color.decode("#E96241"));
        subfondo.add(precio);

        //label para asignar precio del platillo
        JLabel txtPrecio= new JLabel("$"+precioPlato);
        txtPrecio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtPrecio.setSize(250,30);
        txtPrecio.setLocation(590,440);
        txtPrecio.setForeground(Color.white);
        subfondo.add(txtPrecio);


        JButton regresar = new JButton("Regresar");
        regresar.setSize(150,50);
        regresar.setLocation(600,510);
        regresar.setFocusPainted(false);
        regresar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        regresar.setBackground(Color.decode("#E96241"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(anterior); //regresa a pantalla anterior (crear orden o editar orden usan esta misma)
            }
        });
        subfondo.add(regresar);

        return fondo;
    }

    //--------------------aqui se editan los platillos-------------------------------------ya
    public JPanel editarPlatillo(Platillo platillo){
        nombreImagen=platillo.getRutaImagen();

        JPanel fondo = new JPanel();
        fondo.setBackground(Color.decode("#E96241"));
        fondo.setSize(this.getWidth(),this.getHeight());
        fondo.setLayout(null);

        //Bar menu
        menu(fondo);

        JPanel subfondo = new JPanel();
        subfondo.setBackground(Color.decode("#383952"));
        subfondo.setSize(810,600);
        subfondo.setLocation(40,30);
        subfondo.setLayout(null);
        fondo.add(subfondo);

        JLabel tituloCrear = new JLabel("Editar Platillo");
        tituloCrear.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloCrear.setSize(500,50);
        tituloCrear.setLocation(30,10);
        tituloCrear.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloCrear);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        nombre.setSize(150,40);
        nombre.setLocation(70,90);
        nombre.setForeground(Color.decode("#E96241"));
        subfondo.add(nombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtNombre.setSize(350,30);
        txtNombre.setLocation(70,140);
        txtNombre.setText(platillo.getNombre());
        subfondo.add(txtNombre);

        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        desc.setSize(250,40);
        desc.setLocation(70,190);
        desc.setForeground(Color.decode("#E96241"));
        subfondo.add(desc);



        JTextArea txtDesc = new JTextArea(10,20);
        txtDesc.setText(platillo.getDescripcion());
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txtDesc.getText().length()>=100){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txtDesc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtDesc.setSize(350,90);
        txtDesc.setLocation(70,240);
        subfondo.add(txtDesc);

        JLabel lblSelec = new JLabel("Seleccione una categoría");
        lblSelec.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        lblSelec.setForeground(Color.decode("#E96241"));
        lblSelec.setSize(400,90);
        lblSelec.setLocation(70,310);
        subfondo.add(lblSelec);

        
        JComboBox <String> categorias = new JComboBox<>(opcs);
        int opcion=0;
        for (int i=0;i<opcs.length;i++){
            if(opcs[i].equals(platillo.categoria)){
                opcion=i;
            }
        }
        categorias.setSelectedItem(opcs[opcion]);
        //categorias.getSelectedItem();
        categorias.setSize(250,30);
        categorias.setLocation(70,385);
        subfondo.add(categorias);

        JLabel precio = new JLabel("Precio");
        precio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        precio.setForeground(Color.decode("#E96241"));
        precio.setSize(200,90);
        precio.setLocation(70,390);
        subfondo.add(precio);

        JLabel signo = new JLabel("$");
        signo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        signo.setForeground(Color.decode("#E96241"));
        signo.setSize(200,90);
        signo.setLocation(50,420);
        subfondo.add(signo);

        JTextField txtPrecio = new JTextField();
        txtPrecio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        txtPrecio.setForeground(Color.decode("#E96241"));
        txtPrecio.setSize(150,30);
        txtPrecio.setLocation(70,450);
        txtPrecio.setText(String.valueOf(platillo.getPrecio())); // convierte de float a String
        subfondo.add(txtPrecio);
        txtPrecio.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char validar = e.getKeyChar();

                if(Character.isLetter(validar)){
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        //  redimensiona y agrega la imagen del platillo
        int largo=200, ancho=200;
        ImageIcon imagenOriginal = new ImageIcon(nombreImagen);
        Image imagen = imagenOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(largo, ancho, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
        JLabel lblimagen = new JLabel(imagenRedimensionadaIcon);
        lblimagen.setSize(largo, ancho);
        lblimagen.setLocation(525,150);
        subfondo.add(lblimagen);


        JButton fotobtn = new JButton("Nueva Foto");
        fotobtn.setSize(150,40);
        fotobtn.setLocation(550,360);
        fotobtn.setFocusPainted(false);
        fotobtn.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        fotobtn.setBackground(Color.decode("#E96241"));
        fotobtn.setForeground(Color.decode("#FFFFFF"));

        fotobtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                 // Mostrar el cuadro de diálogo para seleccionar un archivo
                 JFileChooser selector = new JFileChooser();
                 FileNameExtensionFilter filtro = new FileNameExtensionFilter("png,jpg", "png", "jpg");
                 selector.setFileFilter(filtro);
                 
                 if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                     // Si el usuario seleccionó un archivo, intentar cargarlo como imagen
                     File archivoImagen = selector.getSelectedFile();
                     try {
                             BufferedImage imagen = ImageIO.read(archivoImagen);
                             if(imagen!=null) {
 
                             // Guardar la imagen en la carpeta "imgPlatillos" en formato PNG
                             String nombreArchivo = archivoImagen.getName();
                             int extensionIndex = nombreArchivo.lastIndexOf(".");
                             nombreImagen= nombreArchivo.substring(0, extensionIndex) + ".png";
                             File carpetaImagenes = new File("imgPlatillos");
                             //carpetaImagenes.mkdir(); // Crear la carpeta si no existe
                             File archivoNuevo = new File(carpetaImagenes, nombreImagen);
                             ImageIO.write(imagen, "png", archivoNuevo);
                             
                         }
                             else
                                 JOptionPane.showMessageDialog(null, "Seleccione un archivo fomato png o jpg");
                         
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     }
                     //ImageIcon imagenIcono = new ImageIcon("imgPlatillos/"+nombreImagen); 
                     lblimagen.setIcon(redimensionarImagen("imgPlatillos/"+nombreImagen,200,200));
                 }
            }
            
        });
        subfondo.add(fotobtn);

        //boton de actualizar 
        JButton crearP = new JButton("Actualizar");
        crearP.setSize(150,50);
        crearP.setLocation(600,500);
        crearP.setFocusPainted(false);
        crearP.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        crearP.setBackground(Color.decode("#E96241"));
        crearP.setForeground(Color.decode("#FFFFFF"));
        crearP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoNombre= txtNombre.getText();
                String descripcion=txtDesc.getText();
                String categoria=(String) categorias.getSelectedItem();; 
                String tprecio=txtPrecio.getText(); // primero pasarlo a string y luego a float
                String rutaImagen;
                if(nombreImagen.equals(platillo.getRutaImagen())){
                    rutaImagen=nombreImagen;
                }
                else{
                    rutaImagen="imgPlatillos/"+nombreImagen;
                }
                
                // condiciones
                
              if((!nuevoNombre.isEmpty())&&(!descripcion.isEmpty())&&(!tprecio.isEmpty())){

                float precio=Float.parseFloat(txtPrecio.getText());
                if (!(nuevoNombre.contentEquals(platillo.getNombre())) &&(listaPlatillos.buscaNombre(nuevoNombre)!= -1)){ // si nuevo nombre no es el mismo que el anterior y  se encuentra dentro de la lista
                    mensaje("Este platillo ya existe");
                }
                //if(listaPlatillos.buscaNombre(nombre)!= -1)mensaje("Este platillo ya existe"); // si ya existe
                else {
                    
                    modificarPlatillo(nuevoNombre,descripcion,categoria,precio,rutaImagen);
                    
                    actualizarPanel2(5,listaPlatillos.obtenerPlatillo(listaPlatillos.buscaNombre(nuevoNombre))); // lo regresamos a info platillo
                    mensaje("Platillo actualizado");
                }
                }
                else
                mensaje("Debe llenar todos los campos y adjuntar una imagen");
                
                
            }
        });
        subfondo.add(crearP);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setSize(150,50);
        cancelar.setLocation(70,500);
        cancelar.setFocusPainted(false);
        cancelar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        cancelar.setBackground(Color.decode("#E96241"));
        cancelar.setForeground(Color.decode("#FFFFFF"));
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //accion de boton a cambiar
                actualizarPanel2(anterior,platillo); // info platillo
            }
        });
        subfondo.add(cancelar);



        return fondo;
    }

    //--------------------aqui se consultan las ordenes--------------------------------
    public JPanel consultaOrden(){
        float totalPrice=0;
	    int totalPlatos=0;

        JPanel fondo = new JPanel();
        fondo.setBackground(Color.decode("#E96241"));
        fondo.setSize(this.getWidth(),this.getHeight());
        fondo.setLayout(null);

        //Bar menu
        menu(fondo);

        JPanel subfondo = new JPanel();
        subfondo.setBackground(Color.decode("#383952"));
        subfondo.setSize(810,600);
        subfondo.setLocation(40,30);
        subfondo.setLayout(null);
        fondo.add(subfondo);

        JLabel tituloCrear = new JLabel("Consultar Orden",JLabel.CENTER);
        tituloCrear.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloCrear.setSize(500,55);
        tituloCrear.setLocation(155,10);
        tituloCrear.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloCrear);

        String [] secciones2 = {"Nombre1", "Nombre2", "Nombre3"};
        JComboBox nombres = new JComboBox(secciones2);
        nombres.setBackground(naranja);
        nombres.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        nombres.setForeground(Color.white);
        nombres.setSize(300,35);
        nombres.setLocation(255,100);
        subfondo.add(nombres);

        String [] secciones = {"Platillo", "P.Unitario", "Cantidad"};
        String[][] datos ={
                {"a ", "b ","c " },
                {"d ", "e ","f " },
                {"g ", "h ","i " },
        };

        DefaultTableModel modelo = new DefaultTableModel(datos,secciones);

        JTable tabla = new JTable(modelo);
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setSize(600,250);
        scroll.setLocation(105,150);
        subfondo.add(scroll);

        JLabel lblTotalPrecio= new JLabel("Total a pagar: $"+totalPrice, JLabel.CENTER);
		lblTotalPrecio.setForeground(Color.white);
		lblTotalPrecio.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 20));
        lblTotalPrecio.setSize(300, 30);
		lblTotalPrecio.setLocation(20,450);
        subfondo.add(lblTotalPrecio);

		JLabel lblTotalPlatos= new JLabel("Total platillos: "+totalPlatos, JLabel.CENTER);
		lblTotalPlatos.setForeground(Color.white);
		lblTotalPlatos.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 20));
        lblTotalPlatos.setSize(300, 30);
        lblTotalPlatos.setLocation(450,450);
        subfondo.add(lblTotalPlatos);

        JButton eliminar = new JButton("Eliminar Orden");
        eliminar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        eliminar.setSize(250,50);
        eliminar.setFocusPainted(false);
        eliminar.setLocation(70,520);
        eliminar.setBackground(Color.decode("#E96241"));
        eliminar.setForeground(Color.white);
        subfondo.add(eliminar);

        JButton editar = new JButton("Editar Orden");
        editar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        editar.setSize(190,50);
        editar.setLocation(590,520);
        editar.setFocusPainted(false);
        editar.setBackground(Color.decode("#E96241"));
        editar.setForeground(Color.white);
        subfondo.add(editar);


        editar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                actualizarPanel(10); // redirecciona a editar orden
            }

        });

        return fondo;

    }

    public JPanel consultarPlatillos(){
        // ------------------panel Consultar Platillos--------------------
	        
		JPanel consultarPlatillos= new JPanel();
		consultarPlatillos.setLayout(new BorderLayout());
		consultarPlatillos.setOpaque(true);
		consultarPlatillos.setBackground(naranja);
		menu(consultarPlatillos); 
		JLabel lblConsultar= new JLabel("   ", JLabel.CENTER);
		lblConsultar.setForeground(Color.white);
		lblConsultar.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		consultarPlatillos.add(lblConsultar,BorderLayout.NORTH);
		
		JLabel lblBorde1= new JLabel("  ", JLabel.CENTER);
		consultarPlatillos.add(lblBorde1,BorderLayout.WEST);
		
		JLabel lblBorde2= new JLabel("  ", JLabel.CENTER);
		consultarPlatillos.add(lblBorde2,BorderLayout.EAST);
		
		JLabel lblBorde3= new JLabel("  ", JLabel.CENTER);
		lblBorde3.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		consultarPlatillos.add(lblBorde3,BorderLayout.SOUTH);
		
		JPanel panelElementos= new JPanel();
		panelElementos.setLayout(new GridLayout(0,3,10, 10));
		panelElementos.setOpaque(true);
		panelElementos.setBackground(azul);
		
		// añadir elementos de la lista al panel 
        for (int i=0;i<listaPlatillos.cantidadPlatillos();i++){
            platillo=listaPlatillos.obtenerPlatillo(i);
            ElementoPanelConsultar elemento =new ElementoPanelConsultar(listaPlatillos.obtenerPlatillo(i));
            
            elemento.getBtnVer().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    //redirecciona a info platillo
                    actualizarPanel2(5,elemento.platillo);// // le manda a que elemento se refiere para mostrar su info en el panel 
                    
                }
                
            });
            panelElementos.add(elemento.elemento);
            
        }
		
		// ---------scrollpane---------
		JScrollPane scrollPane = new JScrollPane(panelElementos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // ----- panel centro para bordes------------
        JPanel centro= new JPanel();
        centro.setBackground(azul);
        centro.setLayout(new BorderLayout());
        
        JLabel lblBordeRe= new JLabel("  ", JLabel.CENTER);
        centro.add(lblBordeRe,BorderLayout.WEST);
		
		JLabel lblBordeRe2= new JLabel("  ", JLabel.CENTER);
		centro.add(lblBordeRe2,BorderLayout.EAST);
		
		JLabel lblBordeRe3= new JLabel("  ", JLabel.CENTER);
		centro.add(lblBordeRe3,BorderLayout.SOUTH);
		
		JLabel lblBordeRe4= new JLabel("Consultar Platillos", JLabel.CENTER);
		
		lblBordeRe4.setForeground(Color.white);
		lblBordeRe4.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		centro.add(lblBordeRe4,BorderLayout.NORTH);
        
		
		centro.add(scrollPane,BorderLayout.CENTER);
		consultarPlatillos.add(centro,BorderLayout.CENTER);

        return consultarPlatillos;
    }

    public JPanel crearOrden(){
        // ------------------panel Crear nueva  Orden--------------------
        float totalPrice=0;
	    int totalPlatos=0;

        JPanel crearOrden= new JPanel();
		crearOrden.setLayout(new BorderLayout());
		crearOrden.setOpaque(true);
		crearOrden.setBackground(naranja);
		menu(crearOrden); 
		JLabel lblConsultar= new JLabel("   ", JLabel.CENTER);
		lblConsultar.setForeground(Color.white);
		lblConsultar.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		crearOrden.add(lblConsultar,BorderLayout.NORTH);
		
		JLabel lblBorde1= new JLabel("  ", JLabel.CENTER);
		crearOrden.add(lblBorde1,BorderLayout.WEST);
		
		JLabel lblBorde2= new JLabel("  ", JLabel.CENTER);
		crearOrden.add(lblBorde2,BorderLayout.EAST);
		
		// ---------panel abajo-------------------
		JPanel pAbajo= new JPanel();
		pAbajo.setBackground(naranja);;
		pAbajo.setLayout(new FlowLayout(0, 150, 10));
		
		JLabel lblTotalPrecio= new JLabel("Total: $"+totalPrice, JLabel.CENTER);
		lblTotalPrecio.setForeground(Color.white);
		lblTotalPrecio.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 20));
		
		JLabel lblTotalPlatos= new JLabel("Total platillos: "+totalPlatos, JLabel.CENTER);
		lblTotalPlatos.setForeground(Color.white);
		lblTotalPlatos.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 20));
		
        // boton crear----------
		JButton btnCrear= new JButton("Crear");
        btnCrear.setBackground(azul);
        btnCrear.setForeground(Color.white);
        btnCrear.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        btnCrear.setFocusPainted(false);
        
        btnCrear.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(null,"Creado");
                actualizarPanel(2);
            }
            
        });


		pAbajo.add(lblTotalPrecio);
		pAbajo.add(lblTotalPlatos);
		pAbajo.add(btnCrear);
		
		crearOrden.add(pAbajo, BorderLayout.SOUTH);

		//-------------------panel elementos---------------
		JPanel panelElementos= new JPanel();
		panelElementos.setLayout(new GridLayout(0,3,10, 10));
		panelElementos.setOpaque(true);
		panelElementos.setBackground(azul);
		
		// añadir elementos al panel 

        // añadir elementos al panel 
        for (int i=0;i<8;i++){
            ElementoPanelOrden elemento =new ElementoPanelOrden("Pasta","pasta.jpg",400);
            
            elemento.getbtnInfo().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    actualizarPanel(8);// redirecciona a info orden platillo
                }
                
            });
            panelElementos.add(elemento.elemento);
            
        }
        /* 
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
	    */
		// ---------scrollpane---------
		JScrollPane scrollPane = new JScrollPane(panelElementos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // ----- panel centro para bordes------------
        JPanel centro= new JPanel();
        centro.setBackground(azul);
        centro.setLayout(new BorderLayout());
        
        JLabel lblBordeRe= new JLabel("  ", JLabel.CENTER);
        centro.add(lblBordeRe,BorderLayout.WEST);
		
		JLabel lblBordeRe2= new JLabel("  ", JLabel.CENTER);
		centro.add(lblBordeRe2,BorderLayout.EAST);
		
		JLabel lblBordeRe3= new JLabel("  ", JLabel.CENTER);
		centro.add(lblBordeRe3,BorderLayout.SOUTH);
		
		JLabel lblBordeRe4= new JLabel("Crear Orden", JLabel.CENTER);
		
		lblBordeRe4.setForeground(Color.white);
		lblBordeRe4.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		centro.add(lblBordeRe4,BorderLayout.NORTH);
        
		centro.add(scrollPane,BorderLayout.CENTER);
		crearOrden.add(centro,BorderLayout.CENTER);

        return crearOrden;
    }

    public JPanel editarOrden(){
        // ------------------panel editar Orden--------------------
	    float totalPrice=0;
	    int totalPlatos=0;
	    String nombreCliente="Agus Tilin";
	    
		JPanel editarOrden= new JPanel();
		editarOrden.setLayout(new BorderLayout());
		editarOrden.setOpaque(true);
		editarOrden.setBackground(naranja);
		menu(editarOrden); 
		JLabel lblConsultar= new JLabel("   ", JLabel.CENTER);
		lblConsultar.setForeground(Color.white);
		lblConsultar.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		editarOrden.add(lblConsultar,BorderLayout.NORTH);
		
		JLabel lblBorde1= new JLabel("  ", JLabel.CENTER);
		editarOrden.add(lblBorde1,BorderLayout.WEST);
		
		JLabel lblBorde2= new JLabel("  ", JLabel.CENTER);
		editarOrden.add(lblBorde2,BorderLayout.EAST);
		
		// ---------panel abajo-------------------
		JPanel pAbajo= new JPanel();
		pAbajo.setBackground(naranja);;
		pAbajo.setLayout(new FlowLayout(0, 80, 10));
		
        //----- boton cancelar editar orden -----
		JButton btnCancelar= new JButton("Cancelar");
		//btnVer.setPreferredSize(new Dimension(75,30));
		btnCancelar.setBackground(azul);
		btnCancelar.setForeground(Color.white);
		btnCancelar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
		btnCancelar.setFocusPainted(false);
		
        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                actualizarPanel(9); // redirecciona a consultar orden
            }
            
        });

		JLabel lblTotalPrecio= new JLabel("Total: $"+totalPrice, JLabel.CENTER);
		lblTotalPrecio.setForeground(Color.white);
		lblTotalPrecio.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 20));
		
		JLabel lblTotalPlatos= new JLabel("Total platillos: "+totalPlatos, JLabel.CENTER);
		lblTotalPlatos.setForeground(Color.white);
		lblTotalPlatos.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 20));
		
        //----- boton actualizar orden----
		JButton btnActualizar= new JButton("Actualizar");
        //btnVer.setPreferredSize(new Dimension(75,30));
        btnActualizar.setBackground(azul);
        btnActualizar.setForeground(Color.white);
        btnActualizar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        btnActualizar.setFocusPainted(false);
		
        btnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(null,"Actualizado");
                actualizarPanel(9);// redirecciona a consultar orden
            }
            
        });


        pAbajo.add(btnCancelar);
		pAbajo.add(lblTotalPrecio);
		pAbajo.add(lblTotalPlatos);
		pAbajo.add(btnActualizar);
		
		editarOrden.add(pAbajo, BorderLayout.SOUTH);
		
		//-------------------panel elementos---------------
		JPanel panelElementos= new JPanel();
		panelElementos.setLayout(new GridLayout(0,3,10, 10));
		panelElementos.setOpaque(true);
		panelElementos.setBackground(azul);
		
		// añadir elementos al panel 
        
        for (int i=0;i<8;i++){
            ElementoPanelOrden elemento =new ElementoPanelOrden("Pasta","pasta.jpg",400);
            
            elemento.getbtnInfo().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    actualizarPanel(8);// redirecciona a info orden platillo
                }
                
            });
            panelElementos.add(elemento.elemento);
            
        }
        /* 
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
	    */
		// ---------scrollpane---------
		JScrollPane scrollPane = new JScrollPane(panelElementos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // ----- panel centro para bordes------------
        JPanel centro= new JPanel();
        centro.setBackground(azul);
        centro.setLayout(new BorderLayout());
        
        JLabel lblBordeRe= new JLabel("  ", JLabel.CENTER);
        centro.add(lblBordeRe,BorderLayout.WEST);
		
		JLabel lblBordeRe2= new JLabel("  ", JLabel.CENTER);
		centro.add(lblBordeRe2,BorderLayout.EAST);
		
		JLabel lblBordeRe3= new JLabel("  ", JLabel.CENTER);
		centro.add(lblBordeRe3,BorderLayout.SOUTH);
		
		JLabel lblBordeRe4= new JLabel("Editando Orden de "+nombreCliente, JLabel.CENTER);
		
		lblBordeRe4.setForeground(Color.white);
		lblBordeRe4.setFont(new Font("Leelawadee UI Semilight", Font.TRUETYPE_FONT, 40));
		centro.add(lblBordeRe4,BorderLayout.NORTH);
        
		centro.add(scrollPane,BorderLayout.CENTER);
		editarOrden.add(centro,BorderLayout.CENTER);

        return editarOrden;
    }


//--------------funcion de actualizar las pantallas-----------------
   public void actualizarPanel(int ventSiguiente) {

        anterior=actual;
        actual=ventSiguiente;

        if(panel!= null) {
            this.remove(panel);
        }

        switch (actual) {

            case 1:
                panel = login();
                this.add(panel);
                break;
            case 2:
                panel = pantallaInicio();
                this.add(panel);
                
                break;
            case 3:
                panel = crearPlatillo();
                this.add(panel);
                break;
            case 4:
                panel=consultarPlatillos();
                this.add(panel);
                break;
               
            case 7:
                panel=crearOrden();
                this.add(panel);
                break;

            case 8:
                panel=infoOrden();
                this.add(panel);
                break;
            case 9:
            panel=consultaOrden();
            this.add(panel);
            break;

            case 10:
            panel=editarOrden();
            this.add(panel);
            break;
        }

        this.repaint();
        this.revalidate();
    }

    //--------------funcion de actualizar las pantallas que necesitan de la referencia de un platillo-----------------
   public void actualizarPanel2(int ventSiguiente,Platillo platillo) {

    anterior=actual;
    actual=ventSiguiente;

    if(panel!= null) {
        this.remove(panel);
    }

    switch (actual) {

        case 5:
            panel=infoPlatillo(platillo);
            this.add(panel);
            break;
        case 6:
            panel=editarPlatillo(platillo);
            this.add(panel);
            break;
        case 8:
            panel=infoOrden();
            this.add(panel);
            break;
       
    }

    this.repaint();
    this.revalidate();
}

}