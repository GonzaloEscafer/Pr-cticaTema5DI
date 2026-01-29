package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmacionBaja extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton btnAceptar; 

    public ConfirmacionBaja() {
        getContentPane().setLayout(null);
        setSize(369,140);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        JLabel lblNewLabel = new JLabel("¿Estás seguro de la baja?");
        lblNewLabel.setBounds(113, 11, 150, 37);
        getContentPane().add(lblNewLabel);

        btnAceptar = new JButton("Aceptar"); 
        btnAceptar.setBounds(42, 59, 89, 23);
        getContentPane().add(btnAceptar);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnAtras.setBounds(215, 59, 89, 23);
        getContentPane().add(btnAtras);
    }

    
    public JButton getBtnAceptar() {
        return btnAceptar;
    }
}