package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import modelo.Autor;
import modelo.Genero;
import modelo.Pelicula;
import negocio.GestionLocal;

@ManagedBean
@RequestScoped
public class GestionBean 
{
	@Inject
	private GestionLocal gl;
	
	
	private Autor a = new Autor();
	private Genero g = new Genero();
	private Pelicula p;
	private List<Autor> lista;
	private List<Genero> lista2;
	private List<Pelicula> lista3;
	
	@PostConstruct
	public void init() {
		this.a.setId(gl.idIncrementoAutor());
		this.lista=gl.getAutores();
		this.lista2=gl.getGeneros();
		this.lista3=gl.getPeliculas();
	}
	
	
	public Autor getA() {
		return a;
	}
	public void setA(Autor a) {
		this.a = a;
	}
	public Genero getG() {
		return g;
	}
	public void setG(Genero g) {
		this.g = g;
	}
	public Pelicula getP() {
		return p;
	}
	public void setP(Pelicula p) {
		this.p = p;
	}
	
	public String crearAutor() {
		this.a.setId(gl.idIncrementoAutor());
		gl.registarAutor(a);
		return "listado";
	}
	public String crearGenero() {
		gl.registrarGenero(g);
		return null;
	}


	public List<Autor> getLista() {
		return lista;
	}


	public void setLista(List<Autor> lista) {
		this.lista = lista;
	}


	public List<Genero> getLista2() {
		return lista2;
	}


	public void setLista2(List<Genero> lista2) {
		this.lista2 = lista2;
	}


	public List<Pelicula> getLista3() {
		return lista3;
	}


	public void setLista3(List<Pelicula> lista3) {
		this.lista3 = lista3;
	}
	
}
