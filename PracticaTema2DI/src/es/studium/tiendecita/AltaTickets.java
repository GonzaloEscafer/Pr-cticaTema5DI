package es.studium.tiendecita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AltaTickets extends JFrame{
	public AltaTickets() {
		setTitle("Alta Tickets");
		getContentPane().setLayout(null);
		setSize(450,300);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
		
	    JLabel lblFecha = new JLabel("Fecha");
	    lblFecha.setBounds(38, 30, 74, 21);
	    getContentPane().add(lblFecha);
	    setSize(450,300);
	     setLocationRelativeTo(null);
	     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
	     setVisible(true);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(137, 27, 262, 27);
		getContentPane().add(textFieldFecha);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(102, 211, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertarTicket();
            }
        });

        setVisible(true);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new Tickets().setVisible(true);
			}
		});
		btnAtras.setBounds(259, 211, 89, 23);
		getContentPane().add(btnAtras);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 91, 262, 94);
		getContentPane().add(scrollPane);

		// Inicializar el campo de la clase
		listArticulos = new JList<>();
		scrollPane.setViewportView(listArticulos);
		
		
		
		JLabel lblArticulos = new JLabel("Artículos");
		lblArticulos.setBounds(38, 91, 74, 21);
		getContentPane().add(lblArticulos);
		
		JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
		lblYyyymmdd.setBounds(235, 57, 74, 21);
		getContentPane().add(lblYyyymmdd);
		
		cargarArticulos();
		
	}
	
	  private void cargarArticulos() {
	        modeloArticulos = new DefaultListModel<>();
	        listArticulos.setModel(modeloArticulos);

	        try (Connection cn = ConexionDB.getConnection();
	             Statement st = cn.createStatement();
	             ResultSet rs = st.executeQuery("SELECT * FROM Articulos")) {

	            while (rs.next()) {
	                modeloArticulos.addElement(
	                    new ArticuloModelo(
	                        rs.getInt("idArticulo"),
	                        rs.getString("descripcion"),
	                        rs.getDouble("precio"),
	                        rs.getInt("cantidad")
	                    )
	                );
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Error cargando artículos.");
	            e.printStackTrace();
	        }
	    }
	  
	  private void insertarTicket() {

		    String fecha = textFieldFecha.getText();
		    var seleccionados = listArticulos.getSelectedValuesList();

		    if (fecha.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Introduce una fecha.");
		        return;
		    }

		    if (seleccionados.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Selecciona al menos un artículo.");
		        return;
		    }

		    double total = 0;
		    for (ArticuloModelo art : seleccionados) {
		        total += art.precio * art.cantidad;
		    }

		    try (Connection cn = ConexionDB.getConnection();
		         Statement st = cn.createStatement()) {

		        // Insertar Ticket
		        String sqlTicket = "INSERT INTO Tickets (total, fecha) " +
		                           "VALUES (" + total + ", '" + fecha + "')";
		        st.executeUpdate(sqlTicket, Statement.RETURN_GENERATED_KEYS);

		        // Obtener idTicket generado
		        ResultSet rs = st.getGeneratedKeys();
		        int idTicket = 0;
		        if (rs.next()) {
		            idTicket = rs.getInt(1);
		        }

		        // Insertar artículos relacionados
		        for (ArticuloModelo art : seleccionados) {
		            String sqlArt = "INSERT INTO TicketArticulos (idTicket, idArticulo, cantidad) " +
		                            "VALUES (" + idTicket + ", " + art.id + ", " + art.cantidad +" )";
		            st.executeUpdate(sqlArt);
		        }

		        JOptionPane.showMessageDialog(this, "Ticket insertado correctamente.");
		        setVisible(false);
		        new Tickets().setVisible(true);

		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(this, "Error insertando ticket.");
		        e.printStackTrace();
		    }
		}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldFecha;
	  private JList<ArticuloModelo> listArticulos;
	    private DefaultListModel<ArticuloModelo> modeloArticulos;
}
