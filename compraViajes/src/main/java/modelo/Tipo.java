package modelo;

public class Tipo {
	private int numero;
	private String descripcion;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Tipo [numero=" + numero + ", descripcion=" + descripcion + "]";
	}
	

}
