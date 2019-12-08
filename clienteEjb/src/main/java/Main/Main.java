package Main;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import test.OperacionesRemote;



public class Main {

	
	
private OperacionesRemote operaciones;
	
	public void conectar() throws Exception {
		try {  
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "ejb");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            final String lookupName = "ejb:/PruebaEjb/Operaciones!test.OperacionesRemote?stateful"; 
            							
              
            this.operaciones = (OperacionesRemote) context.lookup(lookupName);  
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
	}
	
	public void suma() {
		System.out.println(this.operaciones.suma(1, 4));
	}

	
	
	public static void main(String[] args) {
		Main m = new Main();
		try {
			m.conectar();
			m.suma();
		} catch (Exception e) {
			System.out.println(e.getCause());
		}

	}

}
