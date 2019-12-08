package JsfAppdis.view;

import javax.faces.bean.ManagedBean;

import JsfAppdis.modelo.Mensaje;

@ManagedBean
public class MensajeBean {
	//BeanPropieties
	private Mensaje msj = new Mensaje();
	
		public Mensaje getMsj() {
		return msj;
	}

	public void setMsj(Mensaje msj) {
		this.msj = msj;
	}

		public String enviarMensaje()
	{
		return null;
	}
	
}
