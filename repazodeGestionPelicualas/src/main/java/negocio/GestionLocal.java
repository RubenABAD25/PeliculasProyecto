package negocio;

import java.util.List;

import javax.ejb.Local;

import modelo.Autor;
import modelo.Genero;
import modelo.Pelicula;

@Local
public interface GestionLocal 
{
	public void registarAutor(Autor actor);

	public void registrarGenero(Genero genero);

	public void registrarPelicula(Pelicula pelicula);

	public List<Pelicula> getPeliculas();

	public List<Autor> getAutores();
	public List<Genero> getGeneros();
	public int idIncrementoAutor();
	public int idIncrementoGenero();
	public int idIncrementoPelicula();


}
