package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultaTickets extends JFrame{
	public ConsultaTickets() {
		setTitle("Consulta Tickets");
		getContentPane().setLayout(null);
		setSize(450,300);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 30, 383, 186);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Id", "Total", "Fecha", "Art\u00EDculo"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, Double.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new Tickets().setVisible(true);
			}
		});
		btnAtras.setBounds(225, 227, 140, 23);
		getContentPane().add(btnAtras);
		
		JButton btnInformeTickets = new JButton("Generar Informe");
		btnInformeTickets.setBounds(58, 227, 127, 23);
		getContentPane().add(btnInformeTickets);
		
		
		btnInformeTickets.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            setVisible(false);
		            new InformeTickets().setVisible(true);

		        }
		    });
//		JButton btnExportar = new JButton("Exportar a PDF");
//		btnExportar.setBounds(48, 227, 140, 23);
//		getContentPane().add(btnExportar);
		
		 cargarDatos();
	}
	
	private void cargarDatos() {
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    modelo.setRowCount(0); 

	    
	    String sqlTickets = 
	    		  "SELECT t.idTicket, " +
	    				    "       t.total, " +                   
	    				    "       t.fecha, " +
	    				    "       GROUP_CONCAT(a.descripcion SEPARATOR ', ') AS articulos " +
	    				    "FROM Tickets t " +
	    				    "LEFT JOIN TicketArticulos ta ON t.idTicket = ta.idTicket " +
	    				    "LEFT JOIN Articulos a ON ta.idArticulo = a.idArticulo " +
	    				    "GROUP BY t.idTicket, t.fecha " +
	    				    "ORDER BY t.idTicket";

	    try (Connection con = ConexionDB.getConnection();
	         var rs = con.createStatement().executeQuery(sqlTickets)) {

	        while (rs.next()) {
	            Object[] fila = new Object[4];
	            fila[0] = rs.getInt("idTicket");
	            fila[1] = rs.getDouble("total");       
	            fila[2] = rs.getDate("fecha").toString();
	            fila[3] = rs.getString("articulos");   
	            modelo.addRow(fila);
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

}
