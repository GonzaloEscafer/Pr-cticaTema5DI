package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class ModificacionArticulos extends JFrame {
	public ModificacionArticulos() {
		setTitle("Modificación Articulos");
		getContentPane().setLayout(null);
		setSize(450,268);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    
	    JLabel lblArticulos = new JLabel("Artículo");
		lblArticulos.setBounds(37, 34, 75, 23);
		getContentPane().add(lblArticulos);
	    
	    
	    JComboBox comboBoxArticulos = new JComboBox();
	    comboBoxArticulos.setBounds(164, 34, 244, 23);
		getContentPane().add(comboBoxArticulos);
		
		   // Llenar comboBox con los IDs y descripciones de artículos
        try (Connection con = ConexionDB.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT idArticulo, descripcion FROM Articulos");
            while (rs.next()) {
                comboBoxArticulos.addItem(rs.getInt("idArticulo") + " - " + rs.getString("descripcion"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(75, 188, 89, 23);
        getContentPane().add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el id del artículo seleccionado 
                String seleccionado = comboBoxArticulos.getSelectedItem().toString();
                String id = seleccionado.split(" - ")[0];

                // Abrir la segunda ventana para modificar
                new ModificacionArticulos2(id);
                setVisible(false);
            }
        });
		
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new Articulos().setVisible(true);
			}
		});
		btnAtras.setBounds(268, 188, 89, 23);
		getContentPane().add(btnAtras);
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
