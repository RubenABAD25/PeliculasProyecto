package modelo;

public class Ruta {
	private int id;
	private String descripcion;
	private double valor;
	private Geografia origen;
	private Geografia destino;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Geografia getOrigen() {
		return origen;
	}
	public void setOrigen(Geografia origen) {
		this.origen = origen;
	}
	public Geografia getDestino() {
		return destino;
	}
	public void setDestino(Geografia destino) {
		this.destino = destino;
	}
	@Override
	public String toString() {
		return "Ruta [id=" + id + ", descripcion=" + descripcion + ", valor=" + valor + ", origen=" + origen
				+ ", destino=" + destino + "]";
	}
	

}
