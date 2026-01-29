package es.studium.tiendecita;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class Articulos extends JFrame{
	public Articulos() {
		setTitle("Articulos");
		getContentPane().setLayout(null);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new AltaArticulos().setVisible(true);
			}
		});
		btnAlta.setBounds(66, 35, 149, 53);
		getContentPane().add(btnAlta);
		
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new BajaArticulos().setVisible(true);
			}
		});
		btnBaja.setBounds(225, 35, 140, 53);
		getContentPane().add(btnBaja);
		
		JButton btnModificacion = new JButton("Modificación");
		btnModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new ModificacionArticulos().setVisible(true);
			}
		});
		btnModificacion.setBounds(66, 99, 149, 53);
		getContentPane().add(btnModificacion);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new ConsultaArticulos().setVisible(true);
			}
		});
		btnConsulta.setBounds(225, 99, 140, 53);
		getContentPane().add(btnConsulta);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setBounds(175, 195, 89, 23);
		getContentPane().add(btnAtras);
		
		JButton btnInforme = new JButton("Informe");
		btnInforme.setBounds(276, 163, 89, 23);
		getContentPane().add(btnInforme);
		
		btnInforme.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // 1. Conexión a la base de datos 
		            String url = "jdbc:mysql://localhost:3306/tiendecitaGEF"; 
		            String user = "root";
		            String password = "Studium2024;"; 
		            Connection con = DriverManager.getConnection(url, user, password);

		            // 2. Ruta del archivo compilado .jasper
		            
		            String reportPath = "src/es/studium/tiendecita/informeArticulos.jasper";

		            // 3. Llenar el reporte
		            
		            JasperPrint print = JasperFillManager.fillReport(reportPath, null, con);

		            // 4. Visualizarlo
		            JasperViewer viewer = new JasperViewer(print, false);
		            viewer.setVisible(true);
		            viewer.setTitle("Listado de Artículos");

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            javax.swing.JOptionPane.showMessageDialog(null, "Error al generar informe: " + ex.getMessage());
		        }
		    }
		});
		
	    btnAtras.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            setVisible(false);
	            new MenuPrincipal().setVisible(true);

	        }
	    });
	    
	    setSize(450,268);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
