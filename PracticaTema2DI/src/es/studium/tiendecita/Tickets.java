package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tickets extends JFrame{
	public Tickets() {
		setTitle("Tickets");
		getContentPane().setLayout(null);
		setSize(450,300);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AltaTickets();
			}
		});
		btnAlta.setBounds(63, 38, 149, 53);
		getContentPane().add(btnAlta);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ConsultaTickets();
			}
		});
		btnConsulta.setBounds(222, 38, 140, 53);
		getContentPane().add(btnConsulta);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new MenuPrincipal().setVisible(true);
			}
		});
		btnAtras.setBounds(172, 198, 89, 23);
		getContentPane().add(btnAtras);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
