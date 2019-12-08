package parkingejb;

import ec.edu.ups.boleteriaVehiculos.entidades.Ticket;
import ec.edu.ups.boleteriaVehiculos.entidades.Vehiculo;

public interface OperacionesRemote {

	public int calcularTiempo(int numeroT);
	public double calcularCosto(int numeroT);
	public Ticket preCobrarTicket(int numero);
	public boolean ingresarTicket(Ticket unTicket);
	public int numeroTicket();
	public Vehiculo buscarVehiculo(String placa);
	public boolean ingresarVehiculo(String placa, String marca);
	public Ticket buscarTicket(int numeroT);
	public boolean cobrar(Ticket unTicket);

	
}