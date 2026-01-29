package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class BajaArticulos extends JFrame{
	public BajaArticulos() {
		setTitle("Baja Artículos");
		getContentPane().setLayout(null);
		setSize(450,268);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
		
		JComboBox comboBoxArticulos = new JComboBox();
		comboBoxArticulos.setBounds(141, 29, 241, 22);
		getContentPane().add(comboBoxArticulos);
		
		// Llenar comboBox con los IDs de Articulos
        try (Connection con = ConexionDB.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT idArticulo, descripcion FROM Articulos");
            while (rs.next()) {
            	int id = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");

                comboBoxArticulos.addItem(id + " - " + descripcion);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(79, 179, 89, 23);
        getContentPane().add(btnBorrar);

        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                final ConfirmacionBaja confirmacion = new ConfirmacionBaja();
                
                // Botón Aceptar de la ventana de confirmación
                confirmacion.getBtnAceptar().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        String item = comboBoxArticulos.getSelectedItem().toString();
                        String id = item.split(" - ")[0]; //si no añado esto pilla el id + descripcion , de esta manera solo usa el id
                        try (Connection con = ConexionDB.getConnection()) {
                            String sql = "DELETE FROM Articulos WHERE idArticulo=" + id;
                            con.createStatement().executeUpdate(sql);
                            javax.swing.JOptionPane.showMessageDialog(null, "Artículo eliminado correctamente.");
                            setVisible(false);
                            new Articulos().setVisible(true);
                            confirmacion.dispose(); // cerrar ventana de confirmación
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar artículo.");
                        }
                    }
                });
            }
        });
		
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new Articulos().setVisible(true);
			}
		});
		btnAtras.setBounds(264, 179, 89, 23);
		getContentPane().add(btnAtras);
		
		JLabel lblArticulos = new JLabel("Articulo");
		lblArticulos.setBounds(32, 29, 74, 22);
		getContentPane().add(lblArticulos);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
