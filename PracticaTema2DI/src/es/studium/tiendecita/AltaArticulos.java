package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AltaArticulos extends JFrame{
	public AltaArticulos() {
		setTitle("Alta Artículos");
		getContentPane().setLayout(null);
		setSize(450,268);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
		
		//textFieldIdArticulo = new JTextField();
	    //textFieldIdArticulo.setBounds(125, 11, 262, 27);
	    //getContentPane().add(textFieldIdArticulo);
	    //textFieldIdArticulo.setColumns(10);    Al ser auto increment es inutil añadir el id ya que se adjudica el siguiente numero automáticamente
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(125, 49, 262, 27);
		getContentPane().add(textFieldDescripcion);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(125, 87, 262, 27);
		getContentPane().add(textFieldPrecio);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setColumns(10);
		textFieldCantidad.setBounds(125, 123, 262, 27);
		getContentPane().add(textFieldCantidad);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(90, 195, 89, 23);
		getContentPane().add(btnAceptar);
		
		 btnAceptar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String descripcion = textFieldDescripcion.getText();
	                String precio = textFieldPrecio.getText();
	                String cantidad = textFieldCantidad.getText();

	                try (Connection con = ConexionDB.getConnection()) {
	                    String sql = "INSERT INTO Articulos (descripcion, precio, cantidad) " +
	                                 "VALUES ('" + descripcion + "', " + precio + ", " + cantidad + ")";
	                    con.createStatement().executeUpdate(sql);

	                    javax.swing.JOptionPane.showMessageDialog(null, "Artículo agregado correctamente.");
	                    setVisible(false);
	                    new Articulos().setVisible(true);

	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                    javax.swing.JOptionPane.showMessageDialog(null, "Error al agregar artículo.");
	                }
	            }
	        });
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new Articulos().setVisible(true);
			}
		});
		btnAtras.setBounds(263, 195, 89, 23);
		getContentPane().add(btnAtras);
		
		//JLabel lblIdArticulo = new JLabel("IdArtículo");
		//lblIdArticulo.setBounds(26, 14, 74, 21);
		//getContentPane().add(lblIdArticulo);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(26, 52, 74, 21);
		getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(26, 93, 74, 21);
		getContentPane().add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(26, 129, 74, 21);
		getContentPane().add(lblCantidad);
	}
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JTextField textFieldIdArticulo;
	private JTextField textFieldDescripcion;
	private JTextField textFieldPrecio;
	private JTextField textFieldCantidad;
}

