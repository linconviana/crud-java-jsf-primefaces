package br.com.medicamentos.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/// :: https://www.youtube.com/watch?v=n1ikOFzbEQI&list=PLnOrFdw5rkTyj3km618OLh5nCatSELRFh&index=46
public class Message {

	public static void info(String msg) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);	
	}
	
	public static void error(String msg) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);	
	}
	
	public static void warning(String msg) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);	
	}
}
