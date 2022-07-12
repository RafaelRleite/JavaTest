package br.com.cd2.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.cd2.entidades.SigaBem;

@Named(value = "sigabembean")
@ViewScoped
public class SigaBemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	SigaBem sigabem = new SigaBem();

	public String Novo() {
		sigabem = new SigaBem();
		return "";
	}
	
	public String Delete() {
		
		
		return "";
	}

	public String EndPoint() {
		return "";
	}

}
