package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import modelo.Geografia;
import modelo.Ruta;
import modelo.Tipo;
import negocio.GestionLocal;
@ManagedBean
public class GestionBean {
	@Inject
	private GestionLocal gl;
	//Bean Properties
	private Tipo t;
	private Geografia g;
	private Ruta r;
	private int cod;
	private int codT;
	private int padre;
	private int codo;
	private int codd;
	private List<Tipo> lista1;
	private List<Geografia>lista2;
	private List<Ruta>lista3;
	@PostConstruct
	public void init() 
	{
		t= new Tipo();
		g= new Geografia();
		r= new Ruta();
		this.listarTipos();
		this.listarGeos();
		this.listarRutas();
	
	}
	//setAndGet
	public Tipo getT() {
		return t;
	}
	public void setT(Tipo t) {
		this.t = t;
	}
	public Geografia getG() {
		return g;
	}
	public void setG(Geografia g) {
		this.g = g;
	}
	public Ruta getR() {
		return r;
	}
	public void setR(Ruta r) {
		this.r = r;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public List<Tipo> getLista1() {
		return lista1;
	}
	public void setLista1(List<Tipo> lista1) {
		this.lista1 = lista1;
	}
	public List<Geografia> getLista2() {
		return lista2;
	}
	public void setLista2(List<Geografia> lista2) {
		this.lista2 = lista2;
	}
	public List<Ruta> getLista3() {
		return lista3;
	}
	public void setLista3(List<Ruta> lista3) {
		this.lista3 = lista3;
	}
	
	public int getCodT() {
		return codT;
	}
	public void setCodT(int codT) {
		this.codT = codT;
	}
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	public int getCodo() {
		return codo;
	}
	public void setCodo(int codo) {
		this.codo = codo;
	}
	public int getCodd() {
		return codd;
	}
	public void setCodd(int codd) {
		this.codd = codd;
	}
	//Metodos
	public String ingresarT()
	{
		gl.ingresarTipo(t);
		return null;
	}
	public String ingresarG()
	{
		Tipo t = new Tipo();
		t=gl.buscarT(codT);
		System.out.println(t.getNumero());
		g.setUnTipo(t);
		gl.ingresarG(g);
		return null;
	}
	public String ingresarR()
	{
		Geografia o = new Geografia();
		Geografia d = new Geografia();
		o=gl.buscarG(codo);
		d=gl.buscarG(codd);
		r.setOrigen(o);
		r.setDestino(d);
		gl.ingresarR(r);
		return null;
	}
	public String bT() 
	{
		t=this.gl.buscarT(cod);
		return null;
		
	}
	public String bG() 
	{
		g= this.gl.buscarG(cod);
		return null;
	}
	public String bR() 
	{
		r=this.gl.buscarR(cod);
		return null;
	}
	public String listarTipos() 
	{
		this.lista1=gl.getTipos();
		return null;
	}
	public String listarGeos() 
	{
		this.lista2=gl.getGeos();
		return null;
	}
	public String listarRutas() 
	{
		this.lista3=gl.getRutas();
		return null;
	}
	
}
