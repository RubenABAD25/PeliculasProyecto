package negocio;

import java.util.List;

import javax.ejb.Stateless;

import dao.GeografiaDAO;
import dao.RutaDAO;
import dao.TipoDAO;
import modelo.Geografia;
import modelo.Ruta;
import modelo.Tipo;
@Stateless
public class Gestion implements GestionLocal{

	@Override
	public boolean ingresarTipo(Tipo unTipo) {
		// TODO Auto-generated method stub
		TipoDAO tdao= new TipoDAO();
		return tdao.insertar(unTipo);
	}

	@Override
	public int numeroT() {
		// TODO Auto-generated method stub
		int val= 0;
		TipoDAO t = new TipoDAO();
		val=t.getNumeroT();
		return val;
	}

	@Override
	public Tipo buscarT(int numero) {
		// TODO Auto-generated method stub
		TipoDAO td = new TipoDAO();
		Tipo t =  new Tipo();
		t=td.read(numero);
		return t;
	}

	@Override
	public boolean ingresarG(Geografia g) {
		// TODO Auto-generated method stub
		GeografiaDAO gd = new GeografiaDAO();
		return gd.insertar(g);
	}

	@Override
	public int numeroG() {
		// TODO Auto-generated method stub
		int val= 0;
		GeografiaDAO gd = new GeografiaDAO();
		val=gd.getNumeroG();
		return val;
	}

	@Override
	public Ruta buscarR(int numero) {
		// TODO Auto-generated method stub
		RutaDAO rd = new RutaDAO();
		Ruta r = new Ruta();
		r = rd.read(numero);
		return r;
	}

	@Override
	public boolean ingresarR(Ruta r) {
		// TODO Auto-generated method stub
		RutaDAO rd = new RutaDAO();
		return rd.insertar(r);
	}

	@Override
	public int numeroR() {
		// TODO Auto-generated method stub
		int val= 0;
		RutaDAO t = new RutaDAO();
		val=t.getNumeroR();
		return val;
	}

	@Override
	public Geografia buscarG(int numero) {
		// TODO Auto-generated method stub
		GeografiaDAO gd = new GeografiaDAO();
		Geografia g = new Geografia();
		g= gd.read(numero);
		return g;
	}

	@Override
	public List<Tipo> getTipos() {
		// TODO Auto-generated method stub
		TipoDAO td = new TipoDAO();
		return td.getTipos();
	}

	@Override
	public List<Geografia> getGeos() {
		// TODO Auto-generated method stub
		GeografiaDAO gd = new GeografiaDAO();
		return gd.getG();
	}

	@Override
	public List<Ruta> getRutas() {
		// TODO Auto-generated method stub
		RutaDAO rd= new RutaDAO();
		return rd.getR();
	}

}
