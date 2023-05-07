import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.Utilities;

public class Ventana extends JFrame{
    public JPanel panel;
    public String nombreUser;
    public String correoUser;
    Color naranja= new Color(233,98,65);
	Color azul= new Color(56,57,82);
    public int anterior;
    public int actual;

    public Ventana() {
        this.setSize(900,700); // medidas provisionales, se pueden cambiar
        this.setLocationRelativeTo(null);
        this.setTitle("Gourmet Eats");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // this.setLayout(null);
        this.setResizable(false);

        panel= login(); // panel  principal
        panel.setSize(this.getWidth(), this.getHeight());;
        panel.setLocation(0,0);
        // panel.setBackground(Color.decode("#0665c0"));
        this.add(panel);

        this.revalidate();
        this.setVisible(true);
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


    //------------------creacion de platillos-----------------------
    public JPanel crearPlatillo(){

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
        txtNombre.setSize(250,30);
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

        String [] opcs ={"Comida Rapida", "Mariscos", "Ensaladas", "Postres","Bebidas","Sushi"};
        JComboBox <String> categorias = new JComboBox<>(opcs);
        categorias.getSelectedItem();
        categorias.setSize(250,30);
        categorias.setLocation(70,340);
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

        JLabel iconoImage = new JLabel(new ImageIcon("iconoImagen.png"));
        iconoImage.setLocation(580,1);
        iconoImage.setSize(200,500);
        subfondo.add(iconoImage);

        JButton fotobtn = new JButton("Ajuntar Foto");
        fotobtn.setSize(150,40);
        fotobtn.setLocation(600,360);
        fotobtn.setFocusPainted(false);
        fotobtn.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 12));
        fotobtn.setBackground(Color.decode("#E96241"));
        fotobtn.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(fotobtn);

        // Boton crear platillo
        JButton crearP = new JButton("CREAR");
        crearP.setSize(150,50);
        crearP.setLocation(600,450);
        crearP.setFocusPainted(false);
        crearP.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        crearP.setBackground(Color.decode("#E96241"));
        crearP.setForeground(Color.decode("#FFFFFF"));
        crearP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Creado correctamente");
                actualizarPanel(2); // redirecciona a inicio
            }
        });
        subfondo.add(crearP);



        return fondo;
    }

    //---------------------------informacion del platillo-----------------------------------------
    public JPanel infoPlatillo(){

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

        JLabel tituloInfo = new JLabel("Informacion General");
        tituloInfo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloInfo.setSize(500,50);
        tituloInfo.setLocation(30,10);
        tituloInfo.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloInfo);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        nombre.setSize(150,40);
        nombre.setLocation(260,90);
        nombre.setForeground(Color.decode("#E96241"));
        subfondo.add(nombre);

        JLabel txtNombre = new JLabel("  ");
        txtNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtNombre.setSize(250,40);
        txtNombre.setLocation(260,130);
        txtNombre.setForeground(Color.decode("#E96241"));
        subfondo.add(txtNombre);

        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        desc.setSize(250,40);
        desc.setLocation(260,190);
        desc.setForeground(Color.decode("#E96241"));
        subfondo.add(desc);

        JLabel txtDesc= new JLabel("  ");
        txtDesc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtDesc.setSize(250,70);
        txtDesc.setLocation(260,240);
        txtDesc.setForeground(Color.decode("#E96241"));
        subfondo.add(txtDesc);

        JLabel imagenTest = new JLabel(new ImageIcon("sushi.png"));
        imagenTest.setSize(230,400);
        imagenTest.setLocation(5,40);
        subfondo.add(imagenTest);

        JLabel categoria = new JLabel("Categoria");
        categoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        categoria.setSize(250,40);
        categoria.setLocation(220,390);
        categoria.setForeground(Color.decode("#E96241"));
        subfondo.add(categoria);

        JLabel txtCategoria= new JLabel("  ");
        txtCategoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtCategoria.setSize(250,30);
        txtCategoria.setLocation(220,440);
        txtCategoria.setForeground(Color.decode("#E96241"));
        subfondo.add(txtCategoria);

        JLabel precio = new JLabel("Precio");
        precio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        precio.setSize(250,40);
        precio.setLocation(590,390);
        precio.setForeground(Color.decode("#E96241"));
        subfondo.add(precio);

        JLabel txtPrecio= new JLabel("  ");
        txtPrecio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtPrecio.setSize(250,30);
        txtPrecio.setLocation(590,440);
        txtPrecio.setForeground(Color.decode("#E96241"));
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
               // actualizarPanel();
            }
        });
        subfondo.add(regresar);

        JButton editar = new JButton("Editar");
        editar.setSize(150,50);
        editar.setLocation(650,510);
        editar.setFocusPainted(false);
        editar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        editar.setBackground(Color.decode("#E96241"));
        editar.setForeground(Color.decode("#FFFFFF"));
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cambiar esta accion de boton
                actualizarPanel(6);
                //JOptionPane.showMessageDialog(null, "Prueba Editar ");
            }
        });
        subfondo.add(editar);


        return fondo;
    }

    //-------------------------------informacion de la orden--------------------------------
    public JPanel infoOrden(){

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

        JLabel tituloInfo = new JLabel("Informacion General");
        tituloInfo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloInfo.setSize(500,50);
        tituloInfo.setLocation(30,10);
        tituloInfo.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloInfo);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        nombre.setSize(150,40);
        nombre.setLocation(260,90);
        nombre.setForeground(Color.decode("#E96241"));
        subfondo.add(nombre);

        JLabel txtNombre = new JLabel("  ");
        txtNombre.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtNombre.setSize(250,40);
        txtNombre.setLocation(260,130);
        txtNombre.setForeground(Color.decode("#E96241"));
        subfondo.add(txtNombre);


        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        desc.setSize(250,40);
        desc.setLocation(260,190);
        desc.setForeground(Color.decode("#E96241"));
        subfondo.add(desc);

        JLabel txtDesc= new JLabel("  ");
        txtDesc.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtDesc.setSize(250,70);
        txtDesc.setLocation(260,240);
        txtDesc.setForeground(Color.decode("#E96241"));
        subfondo.add(txtDesc);

        JLabel imagenTest = new JLabel(new ImageIcon("sushi.png"));
        imagenTest.setSize(230,400);
        imagenTest.setLocation(5,40);
        subfondo.add(imagenTest);

        JLabel categoria = new JLabel("Categoria");
        categoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        categoria.setSize(250,40);
        categoria.setLocation(220,390);
        categoria.setForeground(Color.decode("#E96241"));
        subfondo.add(categoria);

        JLabel txtCategoria= new JLabel("  ");
        txtCategoria.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtCategoria.setSize(250,30);
        txtCategoria.setLocation(220,440);
        txtCategoria.setForeground(Color.decode("#E96241"));
        subfondo.add(txtCategoria);

        JLabel precio = new JLabel("Precio");
        precio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 30));
        precio.setSize(250,40);
        precio.setLocation(590,390);
        precio.setForeground(Color.decode("#E96241"));
        subfondo.add(precio);

        JLabel txtPrecio= new JLabel("  ");
        txtPrecio.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        txtPrecio.setSize(250,30);
        txtPrecio.setLocation(590,440);
        txtPrecio.setForeground(Color.decode("#E96241"));
        subfondo.add(txtPrecio);

        JButton regresar = new JButton("Regresar");
        regresar.setSize(150,50);
        regresar.setLocation(650,510);
        regresar.setFocusPainted(false);
        regresar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 25));
        regresar.setBackground(Color.decode("#E96241"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //actualizarPanel(7); //regresa a crear orden
            }
        });
        subfondo.add(regresar);

        return fondo;
    }

    //--------------------aqui se editan los platillos-------------------------------------ya
    public JPanel editarPlatillo(){

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
        txtNombre.setSize(250,30);
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

        String [] opcs ={"Comida Rapida", "Mariscos", "Ensaladas", "Postres","Bebidas","Sushi"};
        JComboBox <String> categorias = new JComboBox<>(opcs);
        categorias.getSelectedItem();
        categorias.setSize(250,30);
        categorias.setLocation(70,340);
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

        JLabel iconoImage = new JLabel(new ImageIcon("iconoImagen.png"));
        iconoImage.setLocation(580,1);
        iconoImage.setSize(200,500);
        subfondo.add(iconoImage);

        JButton fotobtn = new JButton("Nueva Foto");
        fotobtn.setSize(150,40);
        fotobtn.setLocation(600,360);
        fotobtn.setFocusPainted(false);
        fotobtn.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        fotobtn.setBackground(Color.decode("#E96241"));
        fotobtn.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(fotobtn);

        JButton crearP = new JButton("Actualizar");
        crearP.setSize(150,50);
        crearP.setLocation(600,450);
        crearP.setFocusPainted(false);
        crearP.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
        crearP.setBackground(Color.decode("#E96241"));
        crearP.setForeground(Color.decode("#FFFFFF"));
        crearP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Actualizado");
                actualizarPanel(5); // lo regresamos a info platillo o a consultar platillos?
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
                actualizarPanel(5); // info platillo
            }
        });
        subfondo.add(cancelar);



        return fondo;
    }

    //--------------------aqui se consultan las ordenes--------------------------------
    public JPanel consultaOrden(){

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

        JLabel tituloCrear = new JLabel("Consultar Orden");
        tituloCrear.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 50));
        tituloCrear.setSize(500,50);
        tituloCrear.setLocation(150,10);
        tituloCrear.setForeground(Color.decode("#FFFFFF"));
        subfondo.add(tituloCrear);

        JComboBox nombres = new JComboBox();
        nombres.setSize(300,30);
        nombres.setLocation(150,100);
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
        scroll.setSize(250,300);
        scroll.setLocation(150,150);
        subfondo.add(scroll);

        JButton eliminar = new JButton("Eliminar Orden");
        eliminar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        eliminar.setSize(260,50);
        eliminar.setFocusPainted(false);
        eliminar.setLocation(70,470);
        eliminar.setBackground(Color.decode("#E96241"));
        eliminar.setForeground(Color.white);
        subfondo.add(eliminar);

        JButton editar = new JButton("Editar Orden");
        editar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        editar.setSize(190,50);
        editar.setLocation(590,470);
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
		
		// añadir elementos al panel 
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new ElementoPanelConsultar("Pasta","pasta.jpg",400).elemento);
	
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
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
	
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
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
		panelElementos.add(new  ElementoPanelOrden("Pasta","pasta.jpg",400).elemento);
	
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
                
            case 5:
                panel=infoPlatillo();
                this.add(panel);
                break;
            case 6:
                panel=editarPlatillo();
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

}