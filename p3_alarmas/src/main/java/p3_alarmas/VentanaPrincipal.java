package p3_alarmas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaPrincipal {

	private JFrame frmAlarma;
	private JTextField idAlarma;
	private JTextField horaAlarma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmAlarma.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlarma = new JFrame();
		frmAlarma.setResizable(false);
		frmAlarma.setTitle("Alarma");
		frmAlarma.setBounds(100, 100, 450, 440);
		frmAlarma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlarma.getContentPane().setLayout(null);
		
		JLabel lbl_idAlarma = new JLabel("ID Alarma");
		lbl_idAlarma.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbl_idAlarma.setBounds(21, 51, 82, 13);
		frmAlarma.getContentPane().add(lbl_idAlarma);
		
		idAlarma = new JTextField();
		idAlarma.setFont(new Font("Roboto", Font.PLAIN, 12));
		idAlarma.setBounds(117, 50, 96, 19);
		frmAlarma.getContentPane().add(idAlarma);
		idAlarma.setColumns(10);
		
		JLabel lbl_horaAlarma = new JLabel("Hora Alarma");
		lbl_horaAlarma.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbl_horaAlarma.setBounds(21, 79, 96, 13);
		frmAlarma.getContentPane().add(lbl_horaAlarma);
		
		horaAlarma = new JTextField();
		horaAlarma.setFont(new Font("Roboto", Font.PLAIN, 12));
		horaAlarma.setColumns(10);
		horaAlarma.setBounds(117, 78, 96, 19);
		frmAlarma.getContentPane().add(horaAlarma);
		
		JButton btnNuevaAlarma = new JButton("Nueva Alarma");
		btnNuevaAlarma.setFont(new Font("Roboto", Font.BOLD, 15));
		btnNuevaAlarma.setBounds(21, 115, 192, 21);
		frmAlarma.getContentPane().add(btnNuevaAlarma);
		
		JButton btnApagar = new JButton("\u00A1APAGAR!");
		btnApagar.setFont(new Font("Roboto", Font.BOLD, 18));
		btnApagar.setBounds(54, 146, 129, 48);
		frmAlarma.getContentPane().add(btnApagar);
		
		JLabel lbl_AlarmasActivas = new JLabel("Alarmas activas");
		lbl_AlarmasActivas.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbl_AlarmasActivas.setBounds(274, 22, 110, 13);
		frmAlarma.getContentPane().add(lbl_AlarmasActivas);
		
		JLabel lbl_AlarmasDesactivadas = new JLabel("Alarmas desactivadas");
		lbl_AlarmasDesactivadas.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbl_AlarmasDesactivadas.setBounds(259, 181, 155, 13);
		frmAlarma.getContentPane().add(lbl_AlarmasDesactivadas);
		
		JButton btnOff = new JButton("OFF");
		btnOff.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnOff.setBounds(249, 315, 85, 21);
		frmAlarma.getContentPane().add(btnOff);
		
		JButton btnOn = new JButton("ON");
		btnOn.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnOn.setBounds(344, 315, 85, 21);
		frmAlarma.getContentPane().add(btnOn);
		
		JButton btnNewButton = new JButton("ELIMINAR");
		btnNewButton.setFont(new Font("Roboto", Font.BOLD, 16));
		btnNewButton.setBounds(283, 346, 111, 34);
		frmAlarma.getContentPane().add(btnNewButton);
	}
}
