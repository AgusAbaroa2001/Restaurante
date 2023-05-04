import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{

    public Ventana() {
        this.setSize(700,700); // medidas provisionales, se pueden cambiar
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JPanel panel= new JPanel(); // panel basico
        panel.setSize(this.getWidth(), this.getHeight());;
        panel.setLocation(0,0);
        panel.setBackground(Color.decode("#0665c0"));
        this.add(panel);

        this.revalidate();
        this.setVisible(true);
    }

}