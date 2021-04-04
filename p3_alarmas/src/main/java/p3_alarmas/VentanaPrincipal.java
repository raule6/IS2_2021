package p3_alarmas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JTextPane;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VentanaPrincipal {

	private JFrame frmAlarma;
	private JTextField idAlarma;

	private Alarmas alarmasController;

	private JList<String> listActive, listInactive;
	private JLabel lblMsgOffOn, lblMsgAddAlarm;
	private JTextPane txtAlert;
	private JSpinner horaAlarma;
	private JButton btnNuevaAlarma, btnEliminar, btnOff, btnOn;

	private Timer timer = new Timer();
	private MsgClearTask task = new MsgClearTask(this);

	private Clip snd;

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
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private void initialize() {
		
		alarmasController = new Alarmas(this);

		frmAlarma = new JFrame();
		frmAlarma.setResizable(false);
		frmAlarma.setTitle("Alarma - by Pablo & Raúl");
		frmAlarma.setBounds(100, 100, 470, 440);
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

		horaAlarma = new JSpinner();
		Date dActual = new Date();
		Calendar cActual = Calendar.getInstance();
		dActual = cActual.getTime();
		horaAlarma.setModel(new SpinnerDateModel(dActual, null, null, Calendar.HOUR));
		horaAlarma.setFont(new Font("Roboto", Font.PLAIN, 12));
		horaAlarma.setBounds(117, 79, 96, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(horaAlarma, "HH:mm");
		horaAlarma.setEditor(de);
		frmAlarma.getContentPane().add(horaAlarma);

		lblMsgAddAlarm = new JLabel("");
		lblMsgAddAlarm.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsgAddAlarm.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblMsgAddAlarm.setBounds(21, 144, 192, 40);
		frmAlarma.getContentPane().add(lblMsgAddAlarm);

		btnNuevaAlarma = new JButton("Nueva Alarma");
		btnNuevaAlarma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnNuevaAlarma.isEnabled()) {
					if (idAlarma.getText().isBlank() || alarmasController.buscaAlarmaActiva(idAlarma.getText()) != null || alarmasController.buscaAlarmaDesactivada(idAlarma.getText()) != null) {
						setMsgAddAlarm("¡Debes introducir una ID válida!", true);
						return;
					}

					alarmasController.NuevaAlarma(idAlarma.getText(), setTime((Date) horaAlarma.getValue()));
					updateLists();
				}
			}
		});
		btnNuevaAlarma.setFont(new Font("Roboto", Font.BOLD, 15));
		btnNuevaAlarma.setBounds(21, 115, 192, 21);
		frmAlarma.getContentPane().add(btnNuevaAlarma);

		JButton btnApagar = new JButton("\u00A1APAGAR!");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alarmasController.Apagar();				
				updateLists();
			}
		});

		btnApagar.setFont(new Font("Roboto", Font.BOLD, 18));
		btnApagar.setBounds(50, 332, 129, 48);
		frmAlarma.getContentPane().add(btnApagar);

		JLabel lbl_AlarmasActivas = new JLabel("Alarmas activas");
		lbl_AlarmasActivas.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbl_AlarmasActivas.setBounds(283, 22, 110, 13);
		frmAlarma.getContentPane().add(lbl_AlarmasActivas);

		JLabel lbl_AlarmasDesactivadas = new JLabel("Alarmas desactivadas");
		lbl_AlarmasDesactivadas.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbl_AlarmasDesactivadas.setBounds(259, 167, 155, 17);
		frmAlarma.getContentPane().add(lbl_AlarmasDesactivadas);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnEliminar.isEnabled()) {
					//TODO: no funciona bien si en desactivadas hay muchas (cuando aparece el scroll)
					int i = listActive.getSelectedIndex(); // -1 si no hay no hay nada seleccionado
					int o = listInactive.getSelectedIndex();
					if (i != -1) {
						Alarma a = (Alarma) alarmasController.alarmasActivas().toArray()[i];
						alarmasController.BorraAlarma(a.getID());
					}
					if (o == -1 && i == -1) {
						setMsgOffOn("Selecciona una alarma");
					} else if (o != -1) {
						Alarma a = (Alarma) alarmasController.alarmasDesactivadas().get(o);
						alarmasController.BorraAlarma(a.getID());
					}
					updateLists();
				}
			}
		});
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 16));
		btnEliminar.setBounds(283, 346, 111, 34);
		frmAlarma.getContentPane().add(btnEliminar);

		lblMsgOffOn = new JLabel("");
		lblMsgOffOn.setForeground(Color.RED);
		lblMsgOffOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsgOffOn.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblMsgOffOn.setBounds(229, 296, 227, 19);
		frmAlarma.getContentPane().add(lblMsgOffOn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(259, 41, 155, 116);
		frmAlarma.getContentPane().add(scrollPane);

		listActive = new JList();
		listActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listInactive.clearSelection();
			}
		});
		listActive.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listActive);
		listActive.setFont(new Font("Roboto", Font.PLAIN, 13));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(259, 188, 155, 110);
		frmAlarma.getContentPane().add(scrollPane_1);

		listInactive = new JList();
		listInactive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listActive.clearSelection();
			}
		});
		listInactive.setFont(new Font("Roboto", Font.PLAIN, 13));
		listInactive.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_1.setViewportView(listInactive);
		listActive.setFont(new Font("Roboto", Font.PLAIN, 13));

		btnOff = new JButton("OFF");
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnOff.isEnabled()) {
					int i = listActive.getSelectedIndex(); // -1 si no hay no hay nada seleccionado
					if (i == -1) {
						setMsgOffOn("Selecciona una alarma activa");
					} else {
						setMsgOffOn("");
						Alarma a = (Alarma) alarmasController.alarmasActivas().toArray()[i];
						alarmasController.AlarmaOff(a.getID());
					}
					updateLists();
				}
			}
		});
		btnOff.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnOff.setBounds(249, 315, 85, 21);
		frmAlarma.getContentPane().add(btnOff);

		btnOn = new JButton("ON");
		btnOn.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnOn.setBounds(344, 315, 85, 21);
		btnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnOn.isEnabled()) {
					int i = listInactive.getSelectedIndex(); // -1 si no hay no hay nada seleccionado

					Alarma a = (Alarma) alarmasController.alarmasDesactivadas().get(i);
					if (i == -1) {
						setMsgOffOn("Selecciona una alarma desactivada");
					} else {
						setMsgOffOn("");

						Date date = a.getHora();

						a.setHora(setTime(date));

						alarmasController.AlarmaOn(a.getID());
					}
					updateLists();
				}

			}
		});
		frmAlarma.getContentPane().add(btnOn);

		txtAlert = new JTextPane();
		txtAlert.setEditable(false);
		txtAlert.setFont(new Font("Roboto", Font.PLAIN, 20));
		txtAlert.setBounds(21, 227, 192, 71);
		frmAlarma.getContentPane().add(txtAlert);
	}

	/**
	 * Método auxiliar que actualiza las listas de alarmas
	 */
	public void updateLists() {
		// Update activadas
		PriorityQueue<Alarma> activas = alarmasController.alarmasActivas();
		DefaultListModel<String> modeloActivo = new DefaultListModel<String>();
		for (Alarma a: activas) {
			modeloActivo.addElement(a.getID());
		}
		listActive.setModel(modeloActivo);
		listActive.updateUI();

		// Update desactivadas
		DefaultListModel<String> modeloInactivo = new DefaultListModel<String>();
		List<Alarma> desactivadas = alarmasController.alarmasDesactivadas();
		for (Alarma a: desactivadas) {
			modeloInactivo.addElement(a.getID());
		}
		listInactive.setModel(modeloInactivo);
		listInactive.updateUI();
	}

	private Date setTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int h = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);

		// Fecha actual.
		Date d = new Date();
		c = Calendar.getInstance();
		c.setTime(d);
		int ah = c.get(Calendar.HOUR);
		int amin = c.get(Calendar.MINUTE);

		Date when = new Date();
		c = Calendar.getInstance();
		c.setTime(when);
		c.set(Calendar.HOUR, h);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);

		if (60 * ah + amin >= 60 * h + min) {
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
			setMsgAddAlarm("La alarma sonará mañana", false);
		} else {
			setMsgAddAlarm("La alarma sonará hoy", false);
		}

		when = c.getTime();
		return when;
	}

	private void setMsgAddAlarm(String s, boolean error) {
		if (error) {
			lblMsgAddAlarm.setForeground(Color.RED);
		} else {
			lblMsgAddAlarm.setForeground(Color.BLACK);
		}
		lblMsgAddAlarm.setText(s);
		task.cancel();
		task = new MsgClearTask(this);
		timer.schedule(task, 5000);
	}
	
	private void setMsgOffOn(String s) {
		lblMsgOffOn.setText(s);
	}

	public void suenaAlarma() {
		setEnabledMenuOptions(false);
		txtAlert.setText("ESTÁ SONANDO LA ALARMA " + alarmasController.alarmaMasProxima().getID());
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\pablo\\Desktop\\CARRERA\\3º de carrera\\2o cuatrimestre\\Software II\\practica3\\IS2_2021\\workspace-eclipse-IS2_2021\\p3_alarmas\\src\\main\\java\\p3_alarmas\\alarmSnd.wav").getAbsoluteFile());
			snd = AudioSystem.getClip();
			snd.open(audioInputStream);
			snd.loop(Clip.LOOP_CONTINUOUSLY);
			snd.start();
		} catch(Exception ex) {
			System.out.println("Error al reproducir el sonido");
			ex.printStackTrace();
		}
	}

	public void apagaAlarma() {
		setEnabledMenuOptions(true);
		txtAlert.setText("");
		snd.stop();
	}

	private void setEnabledMenuOptions(boolean opt) {
		idAlarma.setEnabled(opt);
		horaAlarma.setEnabled(opt);
		btnNuevaAlarma.setEnabled(opt);
		btnEliminar.setEnabled(opt);
		btnOff.setEnabled(opt);
		btnOn.setEnabled(opt);
	}
	
	public class MsgClearTask extends TimerTask {
		private VentanaPrincipal context;
		public MsgClearTask (VentanaPrincipal c) {
			context = c;
		}
		// Tarea que se ejecuta cuando se alcanza el tiempo
		public void run() {
			context.setMsgAddAlarm("", false);
		}
	}

}
