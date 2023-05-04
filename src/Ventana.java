import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana extends JFrame{
    public JPanel panel;
    public Ventana() {
        this.setSize(900,700); // medidas provisionales, se pueden cambiar
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana");
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
        login.setSize(this.getWidth(),this.getHeight());
        login.setBackground(Color.decode("#D7751E"));
        login.setLocation(0,0);
        login.setLayout(null);

        JLabel lblLogin = new JLabel("Iniciar Sesión");
        lblLogin.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 40));
        lblLogin.setForeground(Color.decode("#FFFFFF"));
        lblLogin.setSize(300,50);
        lblLogin.setLocation(330,50);
        login.add(lblLogin);



        JLabel lblCorreo = new JLabel("Ingrese Su Correo");
        lblCorreo.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 20));
        lblCorreo.setForeground(Color.decode("#FFFFFF"));
        lblCorreo.setSize(200,50);
        lblCorreo.setLocation(365,170);
        login.add(lblCorreo);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 15));
        campoCorreo.setSize(350,30);
        campoCorreo.setLocation(285,225);
        login.add(campoCorreo);

        JLabel lblPass = new JLabel("Ingrese Su Contraseña");
        lblPass.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 20));
        lblPass.setForeground(Color.decode("#FFFFFF"));
        lblPass.setSize(350,50);
        lblPass.setLocation(350,315);
        login.add(lblPass);

        JPasswordField campoPass = new JPasswordField();
        campoPass.setSize(350,30);
        campoPass.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 20));
        campoPass.setLocation(285,375);
        login.add(campoPass);

        JButton btnLogin = new JButton();
        btnLogin.setFont(new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 15));
        btnLogin.setSize(150, 40);
        btnLogin.setLocation(300, 550);
        btnLogin.setText("Iniciar Sesion");
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(Color.decode("#2C3333"));
        btnLogin.setForeground(Color.decode("#FFFFFF"));


        login.add(btnLogin);

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