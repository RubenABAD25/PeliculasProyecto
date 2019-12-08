package modelo;

import java.io.Serializable;

public class Ticket implements Serializable {
	private static final long serialVersionUID = -558553967080513790L;
	private int numero;
	private String fecha;
	private String horaInicio;
	private String horaFin;
	private int tiempo;
	private double valor;
	private Vehiculo uveh;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
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
	public Vehiculo getUveh() {
		return uveh;
	}
	public void setUveh(Vehiculo uveh) {
		this.uveh = uveh;
	}
}
