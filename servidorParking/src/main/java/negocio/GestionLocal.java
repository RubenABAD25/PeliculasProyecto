package negocio;

import java.util.List;

import javax.ejb.Local;

import modelo.Ticket;
import modelo.Vehiculo;

@Local
public interface GestionLocal {
	public int calcularTiempo(int numeroT) ;
	public double calcularCosto(int numeroT) ;
	public Ticket preCobrarTicket(int numero);
	public boolean ingresarTicket(Ticket unTicket);
	public int numeroTicket();
	public Vehiculo buscarVehiculo(String placa) ;
	public boolean ingresarVehiculo(Vehiculo v) ;
	public Ticket buscarTicket(int numeroT) ;
	public boolean cobrar(Ticket unTicket);
	public List<Vehiculo> getVehiculos(String placa);
	public List<Vehiculo> getVehiculostodos();
	public List<Ticket> getTickets(String fecha);
}
