package modelo;

import java.io.Serializable;
import java.util.List;

public class Pelicula  implements Serializable{

	
	private int id;
	private String nombre;
	private List<Autor> autores;
	private List<Genero> genero;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public List<Genero> getGenero() {
		return genero;
	}
	public void setGenero(List<Genero> genero) {
		this.genero = genero;
	}
	

}
