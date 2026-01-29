package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

public class ModificacionArticulos2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textFieldId;   // IdArticulo (solo lectura)
    private JTextField textFieldDescripcion;
    private JTextField textFieldPrecio;
    private JTextField textFieldCantidad;

    public ModificacionArticulos2(String idArticulo) {
        setTitle("Modificación Artículos");
        getContentPane().setLayout(null);
        setSize(450, 268);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Etiquetas
        JLabel lblIdArticulo = new JLabel("IdArtículo");
        lblIdArticulo.setBounds(41, 29, 74, 21);
        getContentPane().add(lblIdArticulo);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(41, 67, 74, 21);
        getContentPane().add(lblDescripcion);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(41, 108, 74, 21);
        getContentPane().add(lblPrecio);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(41, 144, 74, 21);
        getContentPane().add(lblCantidad);

        // Campos de texto
        textFieldId = new JTextField(idArticulo);
        textFieldId.setBounds(140, 26, 262, 27);
        textFieldId.setEditable(false);
        getContentPane().add(textFieldId);

        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setBounds(140, 64, 262, 27);
        getContentPane().add(textFieldDescripcion);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(140, 102, 262, 27);
        getContentPane().add(textFieldPrecio);

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(140, 138, 262, 27);
        getContentPane().add(textFieldCantidad);

        // Cargar datos del artículo desde la base de datos
        try (Connection con = ConexionDB.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery(
                "SELECT descripcion, precio, cantidad FROM Articulos WHERE idArticulo=" + idArticulo
            );
            if (rs.next()) {
                textFieldDescripcion.setText(rs.getString("descripcion"));
                textFieldPrecio.setText(String.valueOf(rs.getDouble("precio")));
                textFieldCantidad.setText(String.valueOf(rs.getInt("cantidad")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Botón Aceptar para modificar
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(94, 195, 89, 23);
        getContentPane().add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descripcion = textFieldDescripcion.getText();
                String precio = textFieldPrecio.getText();
                String cantidad = textFieldCantidad.getText();

                try (Connection con = ConexionDB.getConnection()) {
                    String sql = "UPDATE Articulos SET " +
                                 "descripcion='" + descripcion + "', " +
                                 "precio=" + precio + ", " +
                                 "cantidad=" + cantidad + " " +
                                 "WHERE idArticulo=" + idArticulo;
                    con.createStatement().executeUpdate(sql);
                    javax.swing.JOptionPane.showMessageDialog(null, "Artículo modificado correctamente.");
                    setVisible(false);
                    new Articulos().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al modificar el artículo.");
                }
            }
        });

        // Botón Atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(267, 195, 89, 23);
        getContentPane().add(btnAtras);

        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ModificacionArticulos().setVisible(true);
            }
        });
    }
}
