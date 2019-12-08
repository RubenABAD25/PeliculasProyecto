package ec.edu.ups.boleteriaVehiculos.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;

//import ec.edu.ups.boleteriaVehiculos.datos.TIcketDAO;
//import ec.edu.ups.boleteriaVehiculos.datos.VehiculoDAO;
import ec.edu.ups.boleteriaVehiculos.entidades.Ticket;
import ec.edu.ups.boleteriaVehiculos.entidades.Vehiculo;
//import ec.edu.ups.boleteriaVehiculos.negocio.GestionTickets;
//import testejb.OperacionesRemote;
import parkingejb.*;

import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumeroIngreso;
	private JTextField txtHoraInicioIngreso;
	private JTextField txtPlacaIngreso;
	private JTextField txtMarcaIngreso;
	private JTextField txtNroTicketCobrar;
	private JTextField txtHoraEntradaCobrar;
	private JTextField txtHoraSalidaCobrar;
	private JTextField txtPlacaCobrar;
	private JTextField txtMarcaCobrar;
	private JTextField txtTiempoCobrar;
	private JTextField txtValorCobrar;
	SimpleDateFormat fechaFormato;
	Date fechaActual;
	Timer tiempo;
	TimerTask tareaTiempo;
//	GestionTickets gestionTickets;
//	VehiculoDAO vehiculoDAO;
//	TIcketDAO ticketDAO;

	/**
	 * Launch the application.
	 */
	public OperacionesRemote operaciones;

	
	public void conectar() throws Exception {
		try {  
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "ejb");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            //nombre id de ejb
            final String lookupName = "ejb:/parkingejb/Operaciones!parkingejb.OperacionesRemote";  
              
            this.operaciones = (OperacionesRemote) context.lookup(lookupName);  
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		iniciaComponentes();
//		ticketDAO= new TIcketDAO();
//		vehiculoDAO = new VehiculoDAO();
//		gestionTickets= new GestionTickets();
		try {
			conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timer timer = new Timer();
		TimerTask fecha= new TimerTask() {
			@Override
			public void run() {
				fechaFormato = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
				// TODO Auto-generated method stub
				txtHoraInicioIngreso.setText(""+fechaFormato.format(new Date()));
				txtHoraSalidaCobrar.setText(""+fechaFormato.format(new Date()));
			}
		};
		timer.schedule(fecha, 0, 1000);
		txtNumeroIngreso.setText(""+ this.operaciones.numeroTicket());
//		txtNumeroIngreso.setText(""+gestionTickets.numeroTicket());
	}
	public void iniciaComponentes() {
		try {
			this.conectar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JInternalFrame vtnIngresoTicket = new JInternalFrame("Ingreso Ticket Nuevo");
		panel.add(vtnIngresoTicket);
		vtnIngresoTicket.getContentPane().setLayout(null);
		
		JLabel lblTicketNro = new JLabel("Ticket Nro:");
		lblTicketNro.setBounds(10, 11, 68, 23);
		vtnIngresoTicket.getContentPane().add(lblTicketNro);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 42, 68, 23);
		vtnIngresoTicket.getContentPane().add(lblHora);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 76, 68, 23);
		vtnIngresoTicket.getContentPane().add(lblPlaca);
		
		JLabel lblMarcaIngreso = new JLabel("Marca:");
		lblMarcaIngreso.setBounds(10, 110, 68, 23);
		vtnIngresoTicket.getContentPane().add(lblMarcaIngreso);
		
		JButton btnRegistroIngreso = new JButton("");
		btnRegistroIngreso.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/icons8-guardar-64.png")));
		btnRegistroIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
 				Vehiculo v= new Vehiculo();
				v.setPlaca(txtPlacaIngreso.getText());
				v.setMarca(txtMarcaIngreso.getText());
				operaciones.ingresarVehiculo(v.getPlaca(), v.getMarca());

//				gestionTickets.ingresarVehiculo(v.getPlaca(), v.getMarca());
//				vehiculoDAO.insertar(v);
				
				Ticket t = new Ticket();
//				txtNumeroIngreso.setText(""+gestionTickets.numeroTicket());
				txtNumeroIngreso.setText(""+operaciones.numeroTicket());
				t.setNumero(Integer.parseInt(txtNumeroIngreso.getText()));
				
				txtHoraInicioIngreso.setText(fechaFormato.format(new Date()));
				try {
					fechaActual=fechaFormato.parse(txtHoraInicioIngreso.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}/**/
					
				java.sql.Timestamp fechaSQL= new java.sql.Timestamp(fechaActual.getTime());

				t.setfInicio(fechaSQL);
				t.setUnVehiculo(v);
				t.setValor(1);
				///t.setTiempo(4);
				//gestionTickets.cobrar(t);
				//gestionTickets.cobrar(t);
				//ticketDAO.insertar(t);
//				gestionTickets.ingresarTicket(t);
//				txtNumeroIngreso.setText(""+gestionTickets.numeroTicket());
				operaciones.ingresarTicket(t);
				txtNumeroIngreso.setText(""+ operaciones.numeroTicket());
			}
		});
		btnRegistroIngreso.setBounds(36, 190, 108, 89);
		vtnIngresoTicket.getContentPane().add(btnRegistroIngreso);
		
		JButton btnNuevoTicket = new JButton("");
		btnNuevoTicket.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/actualizarProdcutp.png")));
		btnNuevoTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				txtNumeroIngreso.setText(""+gestionTickets.numeroTicket());
				txtNumeroIngreso.setText(""+operaciones.numeroTicket());
				//txtHoraInicioIngreso.setText("");
				txtPlacaIngreso.setText("");
				txtMarcaIngreso.setText("");
				
			}
		});
		btnNuevoTicket.setBounds(171, 190, 108, 89);
		vtnIngresoTicket.getContentPane().add(btnNuevoTicket);
		
		txtNumeroIngreso = new JTextField();
		txtNumeroIngreso.setColumns(10);
		txtNumeroIngreso.setBounds(100, 11, 179, 24);
		vtnIngresoTicket.getContentPane().add(txtNumeroIngreso);
		
		txtHoraInicioIngreso = new JTextField();
		txtHoraInicioIngreso.setEditable(false);
		txtHoraInicioIngreso.setColumns(10);
		txtHoraInicioIngreso.setBounds(100, 43, 179, 24);
		vtnIngresoTicket.getContentPane().add(txtHoraInicioIngreso);
		
		txtPlacaIngreso = new JTextField();
		txtPlacaIngreso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//				txtMarcaIngreso.setText(gestionTickets.buscarVehiculo(txtPlacaIngreso.getText()).getMarca());
				txtMarcaIngreso.setText(operaciones.buscarVehiculo(txtPlacaIngreso.getText()).getMarca());
			}
		});
		txtPlacaIngreso.setColumns(10);
		txtPlacaIngreso.setBounds(100, 77, 179, 24);
		vtnIngresoTicket.getContentPane().add(txtPlacaIngreso);
		
		txtMarcaIngreso = new JTextField();
		txtMarcaIngreso.setColumns(10);
		txtMarcaIngreso.setBounds(100, 111, 179, 24);
		vtnIngresoTicket.getContentPane().add(txtMarcaIngreso);
		
		JInternalFrame vtnCobrarTicket = new JInternalFrame("Cobrar Ticket Nuevo");
		panel.add(vtnCobrarTicket);
		vtnCobrarTicket.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ticket Nro:");
		label.setBounds(10, 11, 80, 23);
		vtnCobrarTicket.getContentPane().add(label);
		
		JLabel lblHoraDeEntrada = new JLabel("Hora de Entrada: ");
		lblHoraDeEntrada.setBounds(10, 45, 80, 23);
		vtnCobrarTicket.getContentPane().add(lblHoraDeEntrada);
		
		JLabel lblHoraDeSalida = new JLabel("Hora de Salida:");
		lblHoraDeSalida.setBounds(10, 79, 80, 23);
		vtnCobrarTicket.getContentPane().add(lblHoraDeSalida);
		
		JLabel lblPlaca_1 = new JLabel("Placa:");
		lblPlaca_1.setBounds(10, 107, 80, 23);
		vtnCobrarTicket.getContentPane().add(lblPlaca_1);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 141, 80, 23);
		vtnCobrarTicket.getContentPane().add(lblMarca);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setBounds(10, 170, 80, 23);
		vtnCobrarTicket.getContentPane().add(lblTiempo);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 204, 80, 23);
		vtnCobrarTicket.getContentPane().add(lblValor);
		
		JButton btnCobrar = new JButton("");
		btnCobrar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/icons8-guardar-40.png")));
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				GestionTickets gt= new GestionTickets();
				Ticket ticket= new Ticket();
				ticket.setNumero(Integer.parseInt(txtNroTicketCobrar.getText()));
				try {
					fechaActual=fechaFormato.parse(txtHoraSalidaCobrar.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}/**/
					
				java.sql.Timestamp fechaSQL= new java.sql.Timestamp(fechaActual.getTime());

				ticket.setfFin(fechaSQL);
				System.out.println(ticket.getfFin());
				
				operaciones.cobrar(ticket);
				//ticket.setValor(gt.calcularCosto(Integer.parseInt(txtNroTicketCobrar.getText())));
				txtTiempoCobrar.setText(""+operaciones.calcularTiempo(Integer.parseInt(txtNroTicketCobrar.getText())));
				ticket.setTiempo(Integer.parseInt(txtTiempoCobrar.getText()));
				txtValorCobrar.setText(""+operaciones.calcularCosto(Integer.parseInt(txtNroTicketCobrar.getText())));
				ticket.setValor(Double.parseDouble(txtValorCobrar.getText()));
				//ticketDao.editarTicket(ticket);
				operaciones.cobrar(ticket);
			}
		});
		btnCobrar.setBounds(10, 236, 125, 49);
		vtnCobrarTicket.getContentPane().add(btnCobrar);
		
		JButton btnNuevoCobroTicket = new JButton("");
		btnNuevoCobroTicket.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/clean.png")));
		btnNuevoCobroTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNroTicketCobrar.setText("");
				txtHoraEntradaCobrar.setText("");
				txtHoraSalidaCobrar.setText("");
				txtPlacaCobrar.setText("");
				txtMarcaCobrar.setText("");
				txtTiempoCobrar.setText("");
				txtValorCobrar.setText("");

			}
		});
		btnNuevoCobroTicket.setBounds(154, 236, 125, 49);
		vtnCobrarTicket.getContentPane().add(btnNuevoCobroTicket);
		
		txtNroTicketCobrar = new JTextField();
		txtNroTicketCobrar.setColumns(10);
		txtNroTicketCobrar.setBounds(100, 12, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtNroTicketCobrar);
		
		txtHoraEntradaCobrar = new JTextField();
		txtHoraEntradaCobrar.setEditable(false);
		txtHoraEntradaCobrar.setColumns(10);
		txtHoraEntradaCobrar.setBounds(100, 46, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtHoraEntradaCobrar);
		
		txtHoraSalidaCobrar = new JTextField();
		txtHoraSalidaCobrar.setEditable(false);
		txtHoraSalidaCobrar.setColumns(10);
		txtHoraSalidaCobrar.setBounds(100, 80, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtHoraSalidaCobrar);
		
		txtPlacaCobrar = new JTextField();
		txtPlacaCobrar.setColumns(10);
		txtPlacaCobrar.setBounds(100, 108, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtPlacaCobrar);
		
		txtMarcaCobrar = new JTextField();
		txtMarcaCobrar.setColumns(10);
		txtMarcaCobrar.setBounds(100, 142, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtMarcaCobrar);
		
		txtTiempoCobrar = new JTextField();
		txtTiempoCobrar.setColumns(10);
		txtTiempoCobrar.setBounds(100, 171, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtTiempoCobrar);
		
		txtValorCobrar = new JTextField();
		txtValorCobrar.setColumns(10);
		txtValorCobrar.setBounds(100, 205, 179, 24);
		vtnCobrarTicket.getContentPane().add(txtValorCobrar);
		
		JButton btnBuscarTicket = new JButton("New button");
		btnBuscarTicket.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/icons8-b\u00FAsqueda-64.png")));
		btnBuscarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numeroT=Integer.parseInt(txtNroTicketCobrar.getText());
				//System.out.println(ticketDao.read(numeroT));
				
				txtHoraEntradaCobrar.setText(""+operaciones.preCobrarTicket(numeroT).getfInicio());
				txtHoraSalidaCobrar.setText(""+operaciones.preCobrarTicket(numeroT).getfFin());
				txtPlacaCobrar.setText(""+operaciones.preCobrarTicket(numeroT).getUnVehiculo().getPlaca());
				txtMarcaCobrar.setText(""+operaciones.preCobrarTicket(numeroT).getUnVehiculo().getMarca());
				txtTiempoCobrar.setText(""+operaciones.preCobrarTicket(numeroT).getTiempo());
				txtValorCobrar.setText(""+operaciones.preCobrarTicket(numeroT).getValor());
				
				
			}
		});
		btnBuscarTicket.setBounds(289, 11, 59, 57);
		vtnCobrarTicket.getContentPane().add(btnBuscarTicket);
		vtnCobrarTicket.setVisible(true);
		vtnIngresoTicket.setVisible(true);
	}
}
