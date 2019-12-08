package test;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
public class Operaciones implements OperacionesRemote{
	int r = 0;
	public int suma(int a, int b) {
		r = (a+b);
		System.out.println("llegando datos: "+r);
		return r;
	}
	
}

