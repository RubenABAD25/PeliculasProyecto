package modelo;

public class Geografia {
	private int id;
	private String descripcion;
	private Tipo unTipo;
	private int padre;
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
	public Tipo getUnTipo() {
		return unTipo;
	}
	public void setUnTipo(Tipo unTipo) {
		this.unTipo = unTipo;
	}
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	@Override
	public String toString() {
		return "Geografia [id=" + id + ", descripcion=" + descripcion + ", unTipo=" + unTipo + ", padre=" + padre + "]";
	}
	

}
