package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Ticket;
import modelo.Vehiculo;
import negocio.GestionRemota;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField txtPlaca;
	private JTextField txtMarca;
	private JTextField txtNumeroT;
	private JTextField txtfecha;
	private JTextField txtHoraI;
	private JTextField txtHoraf;
	private JTextField txtTiempo;
	private JTextField txtValor;
	private JTextField txtPlacaV;
	private GestionRemota operaciones;
	Vehiculo val;
	Ticket ti;
	private JTextArea textArea_1;
	private JTextArea textArea2;

	private JTextField txtFecha;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel tbDatos;
	Vehiculo vB = new Vehiculo();
	Calendar fecha = new GregorianCalendar();
	int mes = fecha.get(Calendar.MONTH) + 1;
	String fe = fecha.get(Calendar.YEAR) + "/" + mes + "/" + fecha.get(Calendar.DAY_OF_MONTH) + "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void conectar() throws Exception {
		System.out.println("in");
		try {
			System.out.println("ou");
			final Hashtable<String, Comparable> jndiProperties = new Hashtable<String, Comparable>();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put("jboss.naming.client.ejb.context", true);

			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "ejb");

			final Context context = new InitialContext(jndiProperties);

			final String lookupName = "ejb:/servidorParking/Gestion!negocio.GestionRemota";

			this.operaciones = (GestionRemota) context.lookup(lookupName);

		} catch (Exception ex) {
			System.out.println("ERRORES " + ex.getLocalizedMessage());
			ex.printStackTrace();
			throw ex;
		}
	}

	public Ventana() {
		try {
			conectar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1167, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 386, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 297, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		tbDatos = new DefaultTableModel(new Object[][] {},
				new Object[] { "ID", "Fecha", "HoraInicio", "HoraFin", "Placa", "Tiempo", "Valor" });
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
				JLabel lblPlaca = new JLabel("Placa:");
				GridBagConstraints gbc_lblPlaca = new GridBagConstraints();
				gbc_lblPlaca.insets = new Insets(0, 0, 5, 0);
				gbc_lblPlaca.gridx = 0;
				gbc_lblPlaca.gridy = 0;
				panel.add(lblPlaca, gbc_lblPlaca);
		
				txtPlaca = new JTextField();
				txtPlaca.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {

						val = operaciones.buscarVehiculo(txtPlaca.getText());
						// txtMarca.setEditable(false);
						txtMarca.setText(val.getMarca());
						// txtPlacaV.setEditable(false);
						txtPlacaV.setText(val.getPlaca());
						

					}

				});
				GridBagConstraints gbc_txtPlaca = new GridBagConstraints();
				gbc_txtPlaca.insets = new Insets(0, 0, 5, 0);
				gbc_txtPlaca.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtPlaca.gridx = 0;
				gbc_txtPlaca.gridy = 1;
				panel.add(txtPlaca, gbc_txtPlaca);
				txtPlaca.setColumns(10);
		
				JLabel lblMarca = new JLabel("Marca:");
				GridBagConstraints gbc_lblMarca = new GridBagConstraints();
				gbc_lblMarca.insets = new Insets(0, 0, 5, 0);
				gbc_lblMarca.gridx = 0;
				gbc_lblMarca.gridy = 2;
				panel.add(lblMarca, gbc_lblMarca);
		
				txtMarca = new JTextField();
				GridBagConstraints gbc_txtMarca = new GridBagConstraints();
				gbc_txtMarca.insets = new Insets(0, 0, 5, 0);
				gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMarca.gridx = 0;
				gbc_txtMarca.gridy = 3;
				panel.add(txtMarca, gbc_txtMarca);
				txtMarca.setColumns(10);
			
				JButton btnListar_1 = new JButton("listar");
				btnListar_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int j = 0; j < operaciones.getVehiculostodos().size(); j++) {

							textArea_1.append(operaciones.getVehiculostodos().get(j).getPlaca() + "  "
									+ operaciones.getVehiculostodos().get(j).getMarca() + "\n");
						}

					}
				});
				
						JButton btnGuardar = new JButton("guardar");
						btnGuardar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Vehiculo v = new Vehiculo();
								v.setPlaca(txtPlaca.getText());
								v.setMarca(txtMarca.getText());
								operaciones.ingresarVehiculo(v);

							}
						});
						GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
						gbc_btnGuardar.insets = new Insets(0, 0, 5, 0);
						gbc_btnGuardar.gridx = 0;
						gbc_btnGuardar.gridy = 5;
						panel.add(btnGuardar, gbc_btnGuardar);
				GridBagConstraints gbc_btnListar_1 = new GridBagConstraints();
				gbc_btnListar_1.insets = new Insets(0, 0, 5, 0);
				gbc_btnListar_1.gridx = 0;
				gbc_btnListar_1.gridy = 6;
				panel.add(btnListar_1, gbc_btnListar_1);

		textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridheight = 2;
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 8;
		panel.add(textArea_1, gbc_textArea_1);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(57, 4, 50, 16);
		panel_1.add(lblNumero);

		txtNumeroT = new JTextField();
		txtNumeroT.setBounds(171, 1, 219, 22);
		panel_1.add(txtNumeroT);
		txtNumeroT.setColumns(10);

		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.setBounds(402, 0, 79, 25);
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(vB.getMarca());
				System.out.println(vB.getPlaca());
				Ticket ti = new Ticket();
				ti.setNumero(Integer.valueOf(txtNumeroT.getText()));
				ti.setFecha(txtfecha.getText());
				ti.setHoraInicio(txtHoraI.getText());
				ti.setHoraFin(txtHoraf.getText());
				ti.setUveh(vB);
				operaciones.ingresarTicket(ti);

				// operaciones.calcularTiempo(numeroT)

				// operaciones.ingresarTicket(ti);
			}
		});
		panel_1.add(btnGuardar_1);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(127, 34, 39, 16);
		panel_1.add(lblFecha);

		txtfecha = new JTextField();
		txtfecha.setBounds(171, 31, 219, 22);
		txtfecha.setEditable(false);
		panel_1.add(txtfecha);
		txtfecha.setColumns(10);

		JButton btnBuscar = new JButton("BuscarTik");
		btnBuscar.setBounds(402, 30, 71, 25);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod = Integer.valueOf(txtNumeroT.getText());
				Ticket ti = new Ticket();
				ti = operaciones.buscarTicket(cod);
				txtfecha.setText(ti.getFecha());
				txtHoraI.setText(ti.getHoraInicio());
				txtHoraf.setText(ti.getHoraFin());
				txtTiempo.setText(operaciones.calcularTiempo(cod) + "");
				txtValor.setText(operaciones.calcularCosto(cod) + "");
				txtPlacaV.setText(ti.getUveh().getPlaca());
				JOptionPane.showMessageDialog(null, "Carga de datos");
				txtValor.setText(operaciones.calcularCosto(ti.getNumero()) + "");
				txtTiempo.setText("" + operaciones.calcularTiempo(ti.getNumero()));
				ti.setTiempo(Integer.valueOf(txtTiempo.getText()));
				ti.setValor(Double.valueOf(txtValor.getText()));
				operaciones.cobrar(ti);

			}
		});
		panel_1.add(btnBuscar);

		JLabel lblHorainicio = new JLabel("HoraInicio:");
		lblHorainicio.setBounds(104, 64, 62, 16);
		panel_1.add(lblHorainicio);

		txtHoraI = new JTextField();
		txtHoraI.setBounds(171, 60, 219, 22);
		panel_1.add(txtHoraI);
		txtHoraI.setColumns(10);

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(402, 60, 63, 25);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Ticket> lista = operaciones.getTickets(txtFecha.getText());
//				for (int i = 0; i < lista.size(); i++) {
//					textArea2.append(lista.get(i).getFecha());
//					
//				}
				final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				// tcr.setHorizontalAlignment(SwingConstants.CENTER);
				table = new JTable(tbDatos);
				scrollPane.setViewportView(table);
				for (Ticket t : lista) {
					Object[] datos = new Object[10];
					datos[0] = t.getNumero();
					datos[1] = t.getFecha();
					datos[2] = t.getHoraInicio();
					datos[3] = t.getHoraFin();
					datos[4] = t.getUveh().getPlaca();
					datos[5] = t.getTiempo();
					datos[6] = t.getValor();
					tbDatos.addRow(datos);
				}
				table.setModel(tbDatos);
			}
		});
		panel_1.add(btnListar);

		JLabel lblHorafin = new JLabel("HoraFin:");
		lblHorafin.setBounds(117, 93, 49, 16);
		panel_1.add(lblHorafin);

		txtHoraf = new JTextField();
		txtHoraf.setBounds(171, 90, 219, 22);
		panel_1.add(txtHoraf);
		txtHoraf.setColumns(10);

		JLabel lblVehiculo = new JLabel("Vehiculo");
		lblVehiculo.setBounds(402, 109, 48, 16);
		panel_1.add(lblVehiculo);

		JLabel lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setBounds(118, 120, 48, 16);
		panel_1.add(lblTiempo);

		txtTiempo = new JTextField();
		txtTiempo.setBounds(171, 117, 219, 22);
		txtTiempo.setEditable(false);
		panel_1.add(txtTiempo);
		txtTiempo.setColumns(10);

		txtPlacaV = new JTextField();
		txtPlacaV.setBounds(451, 106, 219, 22);
		panel_1.add(txtPlacaV);
		txtPlacaV.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(131, 147, 35, 16);
		panel_1.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(171, 144, 219, 22);
		txtValor.setEnabled(false);
		panel_1.add(txtValor);
		txtValor.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setBounds(451, 144, 219, 22);
		txtfecha.setText(fe);
		panel_1.add(txtFecha);
		txtFecha.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 231, 571, 269);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setModel(tbDatos);
		scrollPane.setViewportView(table);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tbDatos = (DefaultTableModel) table.getModel();
				for (int i = table.getRowCount() - 1; i >= 0; i--) {
					tbDatos.removeRow(i);
				}
			}
		});
		btnLimpiar.setBounds(248, 193, 172, 25);
		panel_1.add(btnLimpiar);
		
		JButton btnBuscarveh = new JButton("BuscarVeh");
		btnBuscarveh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vB = operaciones.buscarVehiculo(txtPlacaV.getText());
				System.out.println(vB.getPlaca()+" "+ vB.getMarca());
			}
		});
		btnBuscarveh.setBounds(485, 30, 97, 25);
		panel_1.add(btnBuscarveh);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(409, 147, 56, 16);
		panel_1.add(lblBuscar);
	}
}
