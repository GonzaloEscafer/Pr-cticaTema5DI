package es.studium.tiendecita;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class InformeTickets extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField textFieldFechaDesde;
    private JTextField textFieldFechaHasta;

    public InformeTickets() {
        setTitle("Informe Tickets");
        getContentPane().setLayout(null);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(100, 137, 89, 23);
        // IMPORTANTE: Registrar el listener
        btnAceptar.addActionListener(this);
        getContentPane().add(btnAceptar);

        textFieldFechaDesde = new JTextField();
        textFieldFechaDesde.setBounds(143, 32, 128, 20);
        getContentPane().add(textFieldFechaDesde);

        textFieldFechaHasta = new JTextField();
        textFieldFechaHasta.setBounds(143, 93, 128, 20);
        getContentPane().add(textFieldFechaHasta);

        JLabel lblFechaDesde = new JLabel("Fecha desde:");
        lblFechaDesde.setBounds(44, 35, 89, 14);
        getContentPane().add(lblFechaDesde);

        JLabel lblFechaHasta = new JLabel("Fecha hasta:");
        lblFechaHasta.setBounds(44, 96, 89, 14);
        getContentPane().add(lblFechaHasta);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tiendecitaGEF"; 
            String user = "root";
            String password = "Studium2024;"; 
            Connection con = DriverManager.getConnection(url, user, password);

            // 2. Parámetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("pFechaDesde", textFieldFechaDesde.getText());
            parametros.put("pFechaHasta", textFieldFechaHasta.getText());

            // 3. Ruta 
            String reportPath = "src/es/studium/tiendecita/InformeTickets.jasper";

            // 4. Llenar y 5. Mostrar
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametros, con);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
            
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar informe: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}