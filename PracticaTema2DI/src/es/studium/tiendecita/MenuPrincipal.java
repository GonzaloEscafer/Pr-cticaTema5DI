package es.studium.tiendecita;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame{
	public MenuPrincipal() {
		setTitle("Tiendecita");
		setSize(450,200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JButton btnArticulos = new JButton("Articulos");
		btnArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Articulos();

			}
		});
		btnArticulos.setBounds(50, 45, 147, 61);
		getContentPane().add(btnArticulos);

		JButton btnTickets = new JButton("Tickets");
		btnTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Tickets();
			}
		});
		
		btnTickets.setBounds(235, 45, 147, 61);
		getContentPane().add(btnTickets);
	}
	public static void main(String[] args)
	{
		MenuPrincipal ventana = new MenuPrincipal();

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true); 

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
