package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import negocio.GestionClienteRemote;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFecha;
	private JButton btnRegistrar;
	private GestionClienteRemote operaciones;

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
	public Ventana() {
		try {
			this.conectar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.anchor = GridBagConstraints.EAST;
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		contentPane.add(lblCodigo, gbc_lblCodigo);
		
		txtCodigo = new JTextField();
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.insets = new Insets(0, 0, 5, 0);
		gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigo.gridx = 1;
		gbc_txtCodigo.gridy = 0;
		contentPane.add(txtCodigo, gbc_txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula:");
		GridBagConstraints gbc_lblCedula = new GridBagConstraints();
		gbc_lblCedula.anchor = GridBagConstraints.EAST;
		gbc_lblCedula.insets = new Insets(0, 0, 5, 5);
		gbc_lblCedula.gridx = 0;
		gbc_lblCedula.gridy = 2;
		contentPane.add(lblCedula, gbc_lblCedula);
		
		txtCedula = new JTextField();
		GridBagConstraints gbc_txtCedula = new GridBagConstraints();
		gbc_txtCedula.insets = new Insets(0, 0, 5, 0);
		gbc_txtCedula.anchor = GridBagConstraints.NORTH;
		gbc_txtCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCedula.gridx = 1;
		gbc_txtCedula.gridy = 2;
		contentPane.add(txtCedula, gbc_txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres:");
		GridBagConstraints gbc_lblNombres = new GridBagConstraints();
		gbc_lblNombres.anchor = GridBagConstraints.EAST;
		gbc_lblNombres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombres.gridx = 0;
		gbc_lblNombres.gridy = 4;
		contentPane.add(lblNombres, gbc_lblNombres);
		
		txtNombres = new JTextField();
		GridBagConstraints gbc_txtNombres = new GridBagConstraints();
		gbc_txtNombres.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombres.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombres.gridx = 1;
		gbc_txtNombres.gridy = 4;
		contentPane.add(txtNombres, gbc_txtNombres);
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 6;
		contentPane.add(lblApellidos, gbc_lblApellidos);
		
		txtApellidos = new JTextField();
		GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
		gbc_txtApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellidos.gridx = 1;
		gbc_txtApellidos.gridy = 6;
		contentPane.add(txtApellidos, gbc_txtApellidos);
		txtApellidos.setColumns(10);
		
		JLabel lblFechan = new JLabel("FechaN:");
		GridBagConstraints gbc_lblFechan = new GridBagConstraints();
		gbc_lblFechan.anchor = GridBagConstraints.EAST;
		gbc_lblFechan.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechan.gridx = 0;
		gbc_lblFechan.gridy = 8;
		contentPane.add(lblFechan, gbc_lblFechan);
		
		txtFecha = new JTextField();
		GridBagConstraints gbc_txtFecha = new GridBagConstraints();
		gbc_txtFecha.insets = new Insets(0, 0, 5, 0);
		gbc_txtFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFecha.gridx = 1;
		gbc_txtFecha.gridy = 8;
		contentPane.add(txtFecha, gbc_txtFecha);
		txtFecha.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente fmo = new  Cliente();
				fmo.setCodigo(Integer.valueOf(txtCodigo.getText()));
				fmo.setCedula(txtCedula.getText());
				fmo.setNombre(txtNombres.getText());
				fmo.setApellido(txtApellidos.getText());
				fmo.setFechaN(txtFecha.getText());
				operaciones.guardarCliente(fmo);			
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 9;
		contentPane.add(btnRegistrar, gbc_btnRegistrar);
	}
	public void conectar() throws Exception {
		System.out.println("in");
		try {  
			System.out.println("ou");
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "ejb");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            final String lookupName = "ejb:/pruebaAbadRuben/GestionCliente!negocio.GestionClienteRemote";  
              
            this.operaciones = (GestionClienteRemote) context.lookup(lookupName);  
              
        } catch (Exception ex) {  
        	System.out.println("ERRORES " +ex.getLocalizedMessage());
            ex.printStackTrace();  
            throw ex;  
        }  
	}

}
