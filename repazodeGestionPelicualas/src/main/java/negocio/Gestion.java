package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import modelo.Autor;
import modelo.Genero;
import modelo.Pelicula;
@Stateless
public class Gestion implements Remoto,GestionLocal
{
   private List<Pelicula> peliculas = new ArrayList<Pelicula>();
   private List<Autor> autores = new ArrayList<Autor>();
   private List<Genero> generos = new ArrayList<Genero>();
@Override
public void registarAutor(Autor a ) {
	   autores.add(a);
	   System.out.println(autores.size());
}

@Override
public void registrarGenero(Genero g) {
	
	  generos.add(g);
	  System.out.println(generos.size());
	
}

@Override
public void registrarPelicula(Pelicula p) {
	
	peliculas.add(p);
	
}

@Override
public List<Pelicula> getPeliculas() {
	// TODO Auto-generated method stub
	System.out.println(peliculas);
	return peliculas;
}

@Override
public List<Autor> getAutores() {
	// TODO Auto-generated method stub
	System.out.println(autores);
	return autores;
}

@Override
public List<Genero> getGeneros() {
	// TODO Auto-generated method stub
	System.out.println(generos);
	return generos;
}

@Override
public int idIncrementoAutor() {
	System.out.println(autores.size());
	return autores.size()+1;
}

@Override
public int idIncrementoGenero() {
	System.out.println(generos.size());
	return generos.size()+1;
}

@Override
public int idIncrementoPelicula() {
	return peliculas.size()+1;
}
}
