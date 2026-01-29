package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

public class ConsultaArticulos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table_1;

    public ConsultaArticulos() {
        setTitle("Consulta Artículos");
        getContentPane().setLayout(null);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        
//        JButton btnExportar = new JButton("Exportar a PDF");
//        btnExportar.setBounds(47, 215, 140, 23);
//        getContentPane().add(btnExportar);

        
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(224, 215, 140, 23);
        getContentPane().add(btnAtras);

        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Articulos().setVisible(true);
            }
        });

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 18, 383, 186);
        getContentPane().add(scrollPane);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Id", "Descripción", "Precio", "Cantidad" }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, Double.class, Integer.class };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPane.setViewportView(table_1);

        // Cargar datos desde la base de datos
        cargarDatos();
    }

 
    private void cargarDatos() {
        DefaultTableModel modelo = (DefaultTableModel) table_1.getModel();
        modelo.setRowCount(0); // limpiar tabla antes de llenar

        try (Connection con = ConexionDB.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Articulos");
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("idArticulo");
                fila[1] = rs.getString("descripcion");
                fila[2] = rs.getDouble("precio");
                fila[3] = rs.getInt("cantidad");
                modelo.addRow(fila);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
