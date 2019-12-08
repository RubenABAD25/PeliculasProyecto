package ec.edu.ups.boleteriaVehiculos.entidades;
import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
	private int numero;
	private Date fInicio;
	private Date fFin;
	private int tiempo;
	private double valor;
	private Vehiculo unVehiculo;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getfInicio() {
		return fInicio;
	}
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}
	public Date getfFin() {
		return fFin;
	}
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Vehiculo getUnVehiculo() {
		return unVehiculo;
	}
	public void setUnVehiculo(Vehiculo unVehiculo) {
		this.unVehiculo = unVehiculo;
	}
	@Override
	public String toString() {
		return "Ticket [numero=" + numero + ", fInicio=" + fInicio + ", fFin=" + fFin + ", tiempo=" + tiempo
				+ ", valor=" + valor + ", unVehiculo=" + unVehiculo + "]";
	}
	

}
