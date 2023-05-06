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
        login.setBackground(Color.decode("#F46B31"));
        login.setLocation(0, 0);
        login.setLayout(null);

        JPanel deco1 = new JPanel();
        deco1.setBackground(Color.decode("#FFF6A9"));
        deco1.setSize(850,600);
        deco1.setLocation(20, 25);
        deco1.setLayout(null);
        deco1.repaint();
        deco1.revalidate();
        login.add(deco1);


        JLabel lblLogin = new JLabel("Login");
            lblLogin.setFont(new Font("Verdana", Font.BOLD, 60));
        lblLogin.setForeground(Color.decode("#923914"));
        lblLogin.setSize(350,100);
        lblLogin.setLocation(50,25);
        deco1.add(lblLogin);

        JLabel lblCorreo = new JLabel("Ingrese Su Correo");
        lblCorreo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblCorreo.setForeground(Color.decode("#C2501F"));
        lblCorreo.setSize(350,50);
        lblCorreo.setLocation(50,170);
        deco1.add(lblCorreo);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("Verdana", Font.BOLD, 15));
        campoCorreo.setSize(350,50);
        campoCorreo.setLocation(50,225);
        deco1.add(campoCorreo);

        JLabel lblPass = new JLabel("Ingrese Su Contraseña");
        lblPass.setFont(new Font("Verdana", Font.BOLD, 20));
        lblPass.setForeground(Color.decode("#C2501F"));
        lblPass.setSize(500,50);
        lblPass.setLocation(50,315);
        deco1.add(lblPass);

        JPasswordField campoPass = new JPasswordField();
        campoPass.setSize(350,50);
        campoPass.setFont(new Font("Verdana", Font.BOLD, 20));
        campoPass.setLocation(50,375);
        deco1.add(campoPass);

        JButton btnLogin = new JButton();
        btnLogin.setFont(new Font("Verdana", Font.BOLD, 20));
        btnLogin.setSize(250, 40);
        btnLogin.setLocation(150, 480);
        btnLogin.setText("Iniciar Sesion");
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(Color.decode("#F36B31"));
        btnLogin.setForeground(Color.decode("#C2501F"));
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
        inicio.setBackground(Color.decode("#F46B31"));
        inicio.setLayout(null);

        JPanel deco4 = new JPanel();
        deco4.setBackground(Color.decode("#FFF6A9"));
        deco4.setSize(850,600);
        deco4.setLocation(20, 25);
        deco4.setLayout(null);
        deco4.repaint();
        deco4.revalidate();
        inicio.add(deco4);

        JLabel in = new JLabel("Inicio");
        in.setFont(new Font("Verdana", Font.BOLD, 60));
        in.setForeground(Color.decode("#923914"));
        in.setSize(350,100);
        in.setLocation(50,25);
        deco4.add(in);

        JLabel platos = new JLabel("Platillos");
        platos.setFont(new Font("Verdana", Font.BOLD, 20));
        platos.setForeground(Color.decode("#C2501F"));
        platos.setSize(350,50);
        platos.setLocation(50,170);
        deco4.add(platos);

        JLabel ordenes = new JLabel("Ordenes");
        ordenes.setFont(new Font("Verdana", Font.BOLD, 20));
        ordenes.setForeground(Color.decode("#C2501F"));
        ordenes.setSize(350,50);
        ordenes.setLocation(450,170);
        deco4.add(ordenes);

        JButton consulta = new JButton();
        consulta.setFont(new Font("Verdana", Font.BOLD, 20));
        consulta.setSize(250, 40);
        consulta.setLocation(50, 250);
        consulta.setText("Consultar");
        consulta.setFocusPainted(false);
        consulta.setBackground(Color.decode("#F36B31"));
        consulta.setForeground(Color.decode("#C2501F"));
        consulta.repaint();
        consulta.revalidate();
        consulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(2);

            }
        });

        JButton crear = new JButton();
        crear.setFont(new Font("Verdana", Font.BOLD, 20));
        crear.setSize(250, 40);
        crear.setLocation(50, 350);
        crear.setText("Crear");
        crear.setFocusPainted(false);
        crear.setBackground(Color.decode("#F36B31"));
        crear.setForeground(Color.decode("#C2501F"));
        crear.repaint();
        crear.revalidate();

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deco4.add(consulta);
        deco4.add(crear);




        JButton consulta1 = new JButton();
        consulta1.setFont(new Font("Verdana", Font.BOLD, 20));
        consulta1.setSize(250, 40);
        consulta1.setLocation(450, 250);
        consulta1.setText("Consultar");
        consulta1.setFocusPainted(false);
        consulta1.setBackground(Color.decode("#F36B31"));
        consulta1.setForeground(Color.decode("#C2501F"));
        consulta1.repaint();
        consulta1.revalidate();
        deco4.add(consulta1);

        JButton crear1 = new JButton();
        crear1.setFont(new Font("Verdana", Font.BOLD, 20));
        crear1.setSize(250, 40);
        crear1.setLocation(450, 350);
        crear1.setText("Crear");
        crear1.setFocusPainted(false);
        crear1.setBackground(Color.decode("#F36B31"));
        crear1.setForeground(Color.decode("#C2501F"));
        crear1.repaint();
        crear1.revalidate();

        crear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deco4.add(crear1);


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
        JPanel creacion = new JPanel();
        creacion.setBackground(Color.decode("#F46B31"));
        creacion.setSize(this.getWidth(),this.getHeight());
        creacion.setLayout(null);

        JPanel deco2 = new JPanel();
        deco2.setBackground(Color.decode("#FFF6A9"));
        deco2.setSize(850,600);
        deco2.setLocation(20, 25);
        deco2.setLayout(null);
        deco2.repaint();
        deco2.revalidate();
        creacion.add(deco2);

        JLabel crear1 = new JLabel("Creacion de Platillo");
        crear1.setSize(150,100);
        crear1.setLocation(250,40);
        deco2.add(crear1);

        JLabel name = new JLabel("Nombre ");
        name.setSize(400,40);
        name.setLocation(70,150);
        name.setFont(new Font("Helvetica", Font.BOLD, 40));
        deco2.add(name);

        return creacion;
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
        }

        this.repaint();
        this.revalidate();
    }

}