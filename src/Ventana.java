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

    public JPanel login() {

        JPanel login = new JPanel();
        login.setSize(this.getWidth(), this.getHeight());
        login.setBackground(Color.decode("#F46B31"));
        login.setLocation(0, 0);
        login.setLayout(null);

        JPanel deco1 = new JPanel();
        deco1.setSize(this.getWidth(), this.getHeight());
        deco1.setBackground(Color.decode("#FFF6A9"));
        deco1.setSize(850,600);
        deco1.setLocation(20, 25);
        login.add(deco1);


//        JLabel lblFechaHora = new JLabel();
//        lblFechaHora.setFont(new Font("SansSerif", Font.BOLD, 20));
//        lblFechaHora.setForeground(Color.decode("#C2501"));
//        lblFechaHora.setSize(350,50);
//        lblFechaHora.setLocation(365,270);
//        deco1.add(lblFechaHora);
//
//// intento fecha y hora
//        Timer timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Date now = new Date();
//                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                lblFechaHora.setText(format.format(now));
//            }
//        });
//        timer.start();

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("SansSerif", Font.BOLD, 40));
        lblLogin.setForeground(Color.decode("#923914"));
        lblLogin.setSize(300,50);
        lblLogin.setLocation(50,25);
        deco1.add(lblLogin);



        JLabel lblCorreo = new JLabel("Ingrese Su Correo");
        lblCorreo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblCorreo.setForeground(Color.decode("#C2501"));
        lblCorreo.setSize(200,50);
        lblCorreo.setLocation(365,170);
        deco1.add(lblCorreo);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("SansSerif", Font.BOLD, 15));
        campoCorreo.setSize(350,30);
        campoCorreo.setLocation(285,225);
        deco1.add(campoCorreo);

        JLabel lblPass = new JLabel("Ingrese Su Contraseña");
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblPass.setForeground(Color.decode("#C2501"));
        lblPass.setSize(350,50);
        lblPass.setLocation(1,315);
        deco1.add(lblPass);

        JPasswordField campoPass = new JPasswordField();
        campoPass.setSize(350,30);
        campoPass.setFont(new Font("SansSerif", Font.BOLD, 20));
        campoPass.setLocation(285,375);
        deco1.add(campoPass);

        JButton btnLogin = new JButton();
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnLogin.setSize(150, 40);
        btnLogin.setLocation(300, 550);
        btnLogin.setText("Iniciar Sesion");
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(Color.decode("#2C3333"));
        btnLogin.setForeground(Color.decode("#FFFFFF"));


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
                            //actualizarPanel(2);

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

        JButton btnCancelar = new JButton();
        btnCancelar.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 15));
        btnCancelar.setSize(150, 40);
        btnCancelar.setLocation(490, 550);
        btnCancelar.setText("Cancelar");
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBackground(Color.decode("#2C3333"));
        btnCancelar.setForeground(Color.decode("#FFFFFF"));

        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,"Adios");
                campoPass.setText("");
                campoCorreo.setText("");

            }

        });

        login.add(btnCancelar);
        // this.add(login);
        return login;
    }

}