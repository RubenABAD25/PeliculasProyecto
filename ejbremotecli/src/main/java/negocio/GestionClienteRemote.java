package negocio;

import java.util.List;

import modelo.Cliente;


public interface GestionClienteRemote {
	
	public void guardarCliente(Cliente cli);
	public List<Cliente> getCliente(String cedula);

}
