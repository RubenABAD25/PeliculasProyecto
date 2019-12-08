package test;

import javax.ejb.Remote;

@Remote
public interface OperacionesRemote {
	public int suma(int a, int b);
}
