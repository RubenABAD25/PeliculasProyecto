package ec.edu.ups.boleteriaVehiculos.entidades;

import java.io.Serializable;

public class Vehiculo implements Serializable {
	private String placa;
	private String marca;
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + ", marca=" + marca + "]";
	}
	
	

}
