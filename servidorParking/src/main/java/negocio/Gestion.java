package negocio;

import java.util.List;

import javax.ejb.Stateless;

import dao.TicketDao;
import dao.VehiculoDao;
import modelo.Ticket;
import modelo.Vehiculo;
@Stateless
public class Gestion implements GestionRemota,GestionLocal{
	VehiculoDao vdao= new VehiculoDao();
	TicketDao tdao= new TicketDao();
	@Override
	public int calcularTiempo(int numeroT) {
		System.out.println("GESTION>>>>>>>"+numeroT);
		// TODO Auto-generated method stub
		String horaI= tdao.read(numeroT).getHoraInicio().substring(0,2);
		String horaF=tdao.read(numeroT).getHoraFin().substring(0,2);
		
		int hI = Integer.valueOf(horaI);
		int hF =Integer.valueOf(horaF);
		int valor=  hF-hI;
		return Math.abs(valor);
	}

	@Override
	public double calcularCosto(int numeroT) {
		// TODO Auto-generated method stub
		double retorno= (double) (this.calcularTiempo(numeroT)*0.50);
		System.out.println(retorno);
		return Math.abs(retorno);
	}

	@Override
	public Ticket preCobrarTicket(int numero) {
		// TODO Auto-generated method stub
		TicketDao t = new TicketDao();
		return t.read(numero);
	}

	@Override
	public boolean ingresarTicket(Ticket unTicket) {
		// TODO Auto-generated method stub
		boolean bandera = true;
		TicketDao t = new TicketDao();
		bandera = t.insertar(unTicket);
		return bandera;
	}

	@Override
	public int numeroTicket() {
		// TODO Auto-generated method stub
		int val =0;
		TicketDao t = new TicketDao();
		val= t.getNumeroTicket();
		return val;
	}

	@Override
	public Vehiculo buscarVehiculo(String placa) {
		// TODO Auto-generated method stub
		VehiculoDao vd = new VehiculoDao();
		Vehiculo v = new Vehiculo ();
		v = vd.obtenerVehiculo(placa);		
		return v;
	}

	@Override
	public boolean ingresarVehiculo(Vehiculo v) {
		/*Vehiculo veh= new Vehiculo();
		veh.setPlaca(v.getPlaca());
		veh.setMarca(v.getMarca());*/
		VehiculoDao vd = new VehiculoDao();
		return vd.insertar(v);
	}

	@Override
	public Ticket buscarTicket(int numeroT) {
		// TODO Auto-generated method stub
		TicketDao tik = new TicketDao();
		return tik.read(numeroT);
	}

	@Override
	public boolean cobrar(Ticket unTicket) {
		// TODO Auto-generated method stub
		TicketDao tik = new TicketDao();
		return tik.editarTicket(unTicket);
	}

	@Override
	public List<Vehiculo> getVehiculos(String placa) {
		// TODO Auto-generated method stub
		List<Vehiculo>lista = vdao.obtenerVehiculos(placa);
		return lista;
	}

	@Override
	public List<Ticket> getTickets(String fecha) {
		// TODO Auto-generated method stub
		TicketDao tik = new TicketDao();
		List<Ticket>lista= tik.obtenerTicket(fecha);
		return lista;
	}

	@Override
	public List<Vehiculo> getVehiculostodos() {
		// TODO Auto-generated method stub
		VehiculoDao vehiculoDao= new VehiculoDao();
		return vehiculoDao.getVehiculos();
	}
	

}
