package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;

import domain.Client;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemEvent;

public class VentanaLogin {

	private JFrame frame;
	private JPanel panel;
	private JLabel lblEstado;
	private JLabel lblLogo;
	private JLabel lblUsuario;
	private JTextField tfUsuario;
	private JLabel lblPassword;
	private JLabel lblAviso;
	private JPasswordField pwdfPassword;
	private JButton btnEntrar;
	private final String password = "ipo1";
	private JCheckBox cbMostrarLogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/org/Interface/111.png")));
		frame.setBounds(100, 100, 441, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		{
			panel = new JPanel();
			panel.addMouseListener(new PanelMouseListener());
			panel.setBounds(10, 11, 405, 175);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			{
				lblLogo = new JLabel("");
				lblLogo.addMouseListener(new LblLogoMouseListener());
				lblLogo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/org/Interface/012.png")));
				lblLogo.setBounds(10, 11, 100, 112);
				panel.add(lblLogo);
			}
			{
				cbMostrarLogo = new JCheckBox("Mostrar Logo");
				cbMostrarLogo.setSelected(true);
				cbMostrarLogo.addItemListener(new CbMostrarLogoItemListener());
				cbMostrarLogo.setBounds(6, 130, 238, 23);
				panel.add(cbMostrarLogo);
			}
			{
				lblUsuario = new JLabel("Usuario");
				lblUsuario.setBounds(175, 53, 46, 14);
				panel.add(lblUsuario);
			}
			{
				tfUsuario = new JTextField();
				tfUsuario.addActionListener(new TfUsuarioActionListener());
				tfUsuario.setBounds(258, 50, 86, 20);
				panel.add(tfUsuario);
				tfUsuario.setColumns(10);
			}
			{
				lblPassword = new JLabel("Contrase\u00F1a");
				lblPassword.setEnabled(false);
				lblPassword.setBounds(173, 84, 71, 14);
				panel.add(lblPassword);
			}
			{
				lblAviso = new JLabel("");
				lblAviso.setOpaque(true);
				lblAviso.setBounds(120, 109, 275, 14);
				panel.add(lblAviso);
			}
			{
				pwdfPassword = new JPasswordField();
				pwdfPassword.addKeyListener(new PwdfPasswordKeyListener());
				pwdfPassword.addActionListener(new PwdfPasswordActionListener());
				pwdfPassword.setEnabled(false);
				pwdfPassword.setBounds(258, 81, 86, 20);
				panel.add(pwdfPassword);
			}
			{
				btnEntrar = new JButton("Entrar");
				btnEntrar.setEnabled(false);
				btnEntrar.setBounds(258, 130, 89, 23);
				panel.add(btnEntrar);
			}
		}
		{
			lblEstado = new JLabel("Aqui se mostraran todas las cosas");
			lblEstado.setBounds(10, 184, 405, 41);
			frame.getContentPane().add(lblEstado);
			lblEstado.setBackground(Color.ORANGE);
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstado.setOpaque(true);
		}
	}

	private class LblLogoMouseListener extends MouseAdapter {
		ImageIcon imagenOriginal = new ImageIcon(VentanaLogin.class.getResource("/org/Interface/012.png"));
		ImageIcon imagenCambio = new ImageIcon(VentanaLogin.class.getResource("/org/Interface/112.png"));

		public void mouseEntered(MouseEvent e) {
			lblEstado.setText("Evento de Ratón: mouseEntered" + e.getSource().getClass());
			lblLogo.setIcon(imagenCambio);
		}

		public void mouseExited(MouseEvent e) {
			lblEstado.setText("Evento de Ratón: mouseExited" + e.getSource().getClass());
			lblLogo.setIcon(imagenOriginal);
		}

	}

	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			lblEstado.setText("Evento de ratón: MouseClicked(x: " + e.getX() + ", y:" + e.getY() + ")");
		}
	}

	private class TfUsuarioActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			lblEstado.setText("Evento de acción: ActionPerformed " + arg0.getActionCommand());
			lblPassword.setEnabled(true);
			pwdfPassword.setEnabled(true);
			pwdfPassword.requestFocus();
		}
	}
//Meter aquí la mierda de la comprobación
	private class PwdfPasswordActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean log = false;
			try {
				GestorLogin gestorLogin = new GestorLogin();
				log = gestorLogin.login(tfUsuario.getText().toString(), pwdfPassword.getText().toString());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			lblEstado.setText("Evento de Acción: ActionPerformed" + e.getActionCommand());
			if (log) {
				lblAviso.setBackground(Color.GREEN);
				lblAviso.setText("Contraseña correcta. Puede entrar");
				lblAviso.setVisible(true);
				btnEntrar.setEnabled(true);
				lblPassword.setEnabled(false);
				pwdfPassword.setEnabled(false);
			} else {
				lblAviso.setBackground(Color.RED);
				lblAviso.setText("Contraseña incorrecta. Vuelva a intentarlo");
				lblAviso.setVisible(true);
				btnEntrar.setEnabled(false);
			}
		}
	}

	private class PwdfPasswordKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent arg0) {
			lblEstado.setText("Evento de Teclado: KeyTyped. Tecla: " + arg0.getKeyChar());
		}
	}

	private class CbMostrarLogoItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			lblEstado.setText("Evento de Item: ItemStateChanged. Checkbox" + e.getStateChange());
			lblLogo.setVisible(cbMostrarLogo.isSelected());
		}
	}

}
