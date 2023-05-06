import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class Ventana extends JFrame{
    public JPanel panel;
    public String nombreUser;
    public String correoUser;

    public int anterior;
    public int actual;

    public Ventana() {
        this.setSize(900,700); // medidas provisionales, se pueden cambiar
        this.setLocationRelativeTo(null);
        this.setTitle("Restaurante menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        panel= login(); // panel  principal
        panel.setSize(this.getWidth(), this.getHeight());;
        panel.setLocation(0,0);
        // panel.setBackground(Color.decode("#0665c0"));
        this.add(panel);

        this.revalidate();
        this.setVisible(true);
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
                            actualizarPanel(1);

                            validacion = true;

                        }
                    }
                    if (validacion == false){
                        JOptionPane.showMessageDialog(null, "El usuario y contraseña no coindicen","Error!", JOptionPane.ERROR_MESSAGE);
                    }

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

        JMenuBar menuA = new JMenuBar();
        menuA.setLocation(0,0);
        menuA.setSize(900,20);

        JMenu menu1 = new JMenu("Menu");
        JMenuItem item1 = new JMenuItem("Inicio");
        JMenuItem item2 = new JMenuItem("Cerrar Sesion");

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(4);
            }
        });

        menu1.add(item1);
        menu1.add(item2);
        menuA.add(menu1);

        inicio.add(menuA);


        //--------panel de platillos----------
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



        //--------panel de ordenes--------------
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

//-------------boton consulta de platillo----------------------
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
                actualizarPanel(2);

            }
        });

        //-----------------boton crear de platillo-------------------------
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


        //----------------boton consulta ordenes------------------------
        JButton consultaOrden = new JButton("Consultar");
        consultaOrden.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        consultaOrden.setSize(250, 40);
        consultaOrden.setLocation(50, 280);
        consultaOrden.setFocusPainted(false);
        consultaOrden.setBackground(Color.decode("#E96241"));
        consultaOrden.setForeground(Color.decode("#FFFFFF"));
        consultaOrden.repaint();
        consultaOrden.revalidate();

        //-------------------boton crear ordenes--------------------
        JButton crearOrden = new JButton("Crear");
        crearOrden.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        crearOrden.setSize(250, 40);
        crearOrden.setLocation(50, 350);
        crearOrden.setFocusPainted(false);
        crearOrden.setBackground(Color.decode("#E96241"));
        crearOrden.setForeground(Color.decode("#FFFFFF"));
        crearOrden.repaint();
        crearOrden.revalidate();


//------------agregar botones a paneles---------------------
        plato.add(consulta);
        plato.add(crear);
        orden.add(consultaOrden);
        orden.add(crearOrden);


        return inicio;

    }

    //---------------aqui es la pantalla de consulta en la seccion platillos-----------------
    public JPanel consultaPlatillo(){

        JPanel consultaScreen = new JPanel();
        consultaScreen.setBackground(Color.decode("#F46B31"));
        consultaScreen.setSize(this.getWidth(),this.getHeight());
        consultaScreen.setLayout(null);

        JPanel deco3 = new JPanel();
        deco3.setBackground(Color.decode("#FFF6A9"));
        deco3.setSize(850,600);
        deco3.setLocation(20, 25);
        deco3.setLayout(null);
        deco3.repaint();
        deco3.revalidate();
        consultaScreen.add(deco3);

        JLabel comida1 = new JLabel("Tacos");
        comida1.setSize(100,30);
        comida1.setLocation(150,80);
        deco3.add(comida1);

        JButton crear = new JButton("Crear Pedido");
        crear.setSize(150,30);
        crear.setLocation(250,200);
        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(3);

            }
        });

        JButton atras = new JButton("Atras");
        atras.setSize(150,30);
        atras.setLocation(350,200);

        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(1);
            }
        });

        consultaScreen.add(atras);
        consultaScreen.add(crear);


        return consultaScreen;
    }

    //------------------creacion de platillos-----------------------
    public JPanel CrearPlatillo(){

        JPanel fondo = new JPanel();
        fondo.setBackground(Color.decode("#E96241"));
        fondo.setSize(this.getWidth(),this.getHeight());
        fondo.setLayout(null);

        //menu
        JMenuBar menuA = new JMenuBar();
        menuA.setLocation(0,0);
        menuA.setSize(900,20);

        JMenu menu1 = new JMenu("Menu");
        JMenuItem item1 = new JMenuItem("Inicio");
        JMenuItem item2 = new JMenuItem("Cerrar Sesion");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(4);
            }
        });
        menu1.add(item1);
        menu1.add(item2);
        menuA.add(menu1);
        fondo.add(menuA);

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



        return fondo;
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
                panel = pantallaInicio();
                this.add(panel);
                break;
            case 2:
                panel = consultaPlatillo();
                this.add(panel);
                break;
            case 3:
                panel = CrearPlatillo();
                this.add(panel);
                break;
            case 4:
                panel=login();
                this.add(panel);
                break;
        }

        this.repaint();
        this.revalidate();
    }

}