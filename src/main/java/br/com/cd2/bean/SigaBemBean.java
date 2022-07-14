package br.com.cd2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

	private List<SigaBem> sigaBems = new ArrayList<SigaBem>();

	@Inject
	private DaoSigaBemImpl daoSigaBemImpl;
	
	public String endPoint() {
		return "";
	}

	public String salvar() {
		sigabem = daoSigaBemImpl.merge(sigabem);
		carregarSigaBem();
		return "";
	}

	public String novo() {
		sigabem = new SigaBem();
		return "";
	}

	public String delete() {
		daoSigaBemImpl.delete(sigabem);
		return "";
	}

	@PostConstruct
	public void carregarSigaBem() {
		sigaBems = daoSigaBemImpl.getList(SigaBem.class);
	}

	public SigaBem getSigabem() {
		return sigabem;
	}

	public void setSigabem(SigaBem sigabem) {
		this.sigabem = sigabem;
	}

	public List<SigaBem> getSigaBems() {
		return sigaBems;
	}

	public void setSigaBems(List<SigaBem> sigaBems) {
		this.sigaBems = sigaBems;
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
