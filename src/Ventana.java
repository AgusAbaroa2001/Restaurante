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
            lblLogin.setFont(new Font("Helvetica", Font.BOLD, 60));
        lblLogin.setForeground(Color.decode("#923914"));
        lblLogin.setSize(300,100);
        lblLogin.setLocation(50,25);
        deco1.add(lblLogin);

        JLabel lblCorreo = new JLabel("Ingrese Su Correo");
        lblCorreo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblCorreo.setForeground(Color.decode("#C2501F"));
        lblCorreo.setSize(200,50);
        lblCorreo.setLocation(50,170);
        deco1.add(lblCorreo);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("SansSerif", Font.BOLD, 15));
        campoCorreo.setSize(350,50);
        campoCorreo.setLocation(50,225);
        deco1.add(campoCorreo);

        JLabel lblPass = new JLabel("Ingrese Su Contraseña");
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblPass.setForeground(Color.decode("#C2501F"));
        lblPass.setSize(350,50);
        lblPass.setLocation(50,315);
        deco1.add(lblPass);

        JPasswordField campoPass = new JPasswordField();
        campoPass.setSize(350,50);
        campoPass.setFont(new Font("SansSerif", Font.BOLD, 20));
        campoPass.setLocation(50,375);
        deco1.add(campoPass);

        JButton btnLogin = new JButton();
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnLogin.setSize(180, 40);
        btnLogin.setLocation(150, 480);
        btnLogin.setText("Iniciar Sesion");
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

                            JOptionPane.showMessageDialog(null, "Bienvenido "+ nombreUser,"INGRESO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
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
        inicio.setBackground(Color.decode("#D7751E"));
        inicio.setLayout(null);

        JLabel platos = new JLabel("Platillos");
        platos.setSize(200,200);
        platos.setLocation(150,80);
        inicio.add(platos);

        JButton consulta = new JButton("Consultar");
        consulta.setSize(100,30);
        consulta.setLocation(170,180);
        consulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPanel(2);

            }
        });

        JButton crear = new JButton("Crear");
        crear.setSize(100,30);
        crear.setLocation(170,80);


        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        inicio.add(consulta);

        JLabel ordenes = new JLabel("Ordenes");
        ordenes.setSize(200,200);
        ordenes.setLocation(350,80);
        inicio.add(ordenes);

        JButton consulta1 = new JButton("Consultar");
        consulta1.setSize(100,50);
        consulta1.setLocation(150,380);
        inicio.add(consulta1);


        return inicio;

    }

    //---------------aqui es la pantalla de consulta en la seccion platillos-----------------
    public JPanel consultaPlatillo(){

        JPanel consultaScreen = new JPanel();
        consultaScreen.setSize(this.getWidth(),this.getHeight());
        consultaScreen.setLayout(null);

        JLabel comida1 = new JLabel("Tacos");
        comida1.setSize(100,30);
        comida1.setLocation(150,80);
        consultaScreen.add(comida1);

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
        creacion.setSize(this.getWidth(),this.getHeight());
        creacion.setLayout(null);

        JLabel crear1 = new JLabel("Creacion de Platillo");
        crear1.setSize(150,100);
        crear1.setLocation(250,40);
        creacion.add(crear1);

        JLabel name = new JLabel("Nombre ");
        name.setSize(400,40);
        name.setLocation(70,150);
        name.setFont(new Font("Helvetica", Font.BOLD, 40));
        creacion.add(name);

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