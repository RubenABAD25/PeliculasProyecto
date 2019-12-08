package negocio;

import java.util.List;

import javax.ejb.Local;

import modelo.Geografia;
import modelo.Ruta;
import modelo.Tipo;
@Local
public interface GestionLocal {
	public boolean ingresarTipo(Tipo unTipo);
	public int numeroT();
	public Tipo buscarT(int numero);
	public boolean ingresarG(Geografia g) ;
	public int numeroG();
	public Ruta buscarR(int numero);
	public boolean ingresarR(Ruta r) ;
	public int numeroR();
	public Geografia buscarG(int numero);
	public List<Tipo> getTipos();
	public List<Geografia>getGeos();
	public List<Ruta>getRutas();


}