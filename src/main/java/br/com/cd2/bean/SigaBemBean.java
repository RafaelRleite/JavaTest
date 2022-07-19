package br.com.cd2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cd2.entidades.SigaBem;
import br.com.cd2.repository.DaoSigaBemImpl;

@Named(value = "sigabembean")
@ViewScoped
public class SigaBemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SigaBem sigabem = new SigaBem();

	private List<SigaBem> sigabems = new ArrayList<SigaBem>();

	@Inject
	private DaoSigaBemImpl daoSigaBemImpl;

	public String endPoint() {

		return "";
	}

	public String novo() {
		sigabem = new SigaBem();
		mostraMsg("Realize novo calculo!");
		return "";
	}

	public String salvar() {
		sigabem = daoSigaBemImpl.merge(sigabem);
		carregarSigaBem();
		mostraMsg("Salvo com sucesso!");
		return "";
	}

	public String delete() {
		daoSigaBemImpl.delete(sigabem);
		sigabem = new SigaBem();
		carregarSigaBem();
		mostraMsg("Excluido com sucesso!");
		return "";
	}

	@PostConstruct
	public void carregarSigaBem() {
		sigabems = daoSigaBemImpl.getList(SigaBem.class);
	}

	private void mostraMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
	}

	public SigaBem getSigabem() {
		return sigabem;
	}

	public void setSigabem(SigaBem sigabem) {
		this.sigabem = sigabem;
	}

	public List<SigaBem> getSigabems() {
		return sigabems;
	}

	public void setSigabems(List<SigaBem> sigabems) {
		this.sigabems = sigabems;
	}

	public DaoSigaBemImpl getDaoSigaBemImpl() {
		return daoSigaBemImpl;
	}

	public void setDaoSigaBemImpl(DaoSigaBemImpl daoSigaBemImpl) {
		this.daoSigaBemImpl = daoSigaBemImpl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
