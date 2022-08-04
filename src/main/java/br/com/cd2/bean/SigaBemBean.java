package br.com.cd2.bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;

import br.com.cd2.entidades.SigaBem;
import br.com.cd2.entidades.SigaBemDTO;
import br.com.cd2.repository.DaoSigaBemImpl;

@Named(value = "sigabembean")
@ViewScoped
public class SigaBemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cepOrigemDDD;
	private String cepOrigemUf;
	private String cepDestinoDDD;
	private String cepDestinoUf;

	private SigaBem sigabem = new SigaBem();

	private List<SigaBem> sigabems = new ArrayList<SigaBem>();

	@Inject
	private DaoSigaBemImpl daoSigaBemImpl;

	public String endPoint() {

		sigabem.setVlTotalfrete(new BigDecimal(sigabem.getVlPeso()));

		LocalDateTime data = LocalDateTime.now();
		Timestamp dtTSC = Timestamp.valueOf(data);

		sigabem.setDtConsulta(dtTSC);

		if (cepOrigemDDD.equalsIgnoreCase(cepDestinoDDD) && cepOrigemUf.equalsIgnoreCase(cepDestinoUf)) {
			sigabem.setVlTotalfrete(sigabem.getVlTotalfrete().multiply(new BigDecimal(0.5), MathContext.DECIMAL32));
			data = data.plusDays(1);
		}

		if (!cepOrigemDDD.equalsIgnoreCase(cepDestinoDDD) && cepOrigemUf.equalsIgnoreCase(cepDestinoUf)) {
			sigabem.setVlTotalfrete(sigabem.getVlTotalfrete().multiply(new BigDecimal(0.75), MathContext.DECIMAL32));
			data = data.plusDays(3);
		}

		else if (!cepOrigemUf.equalsIgnoreCase(cepDestinoUf)) {
			data = data.plusDays(10);
		}

		Timestamp dtTSPE = Timestamp.valueOf(data);
		sigabem.setDtPrevistaentrega(dtTSPE);

		return "";
	}

	public String dataFormatada(LocalDateTime data) {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String novo() {
		sigabem = new SigaBem();
		mostraMsg("Realize novo calculo!");
		return "";
	}

	public String salvar() {
		endPoint();
		sigabem = daoSigaBemImpl.merge(sigabem);
		carregarSigaBem();
		mostraMsg("Salvo com sucesso!");
		return "";
	}

	public String delete() {
		daoSigaBemImpl.delete(sigabem);
		sigabem = new SigaBem();
		carregarSigaBem();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage excluir = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluido com sucesso!","");
		context.addMessage(null, excluir);
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

	public void pesquisaCep(AjaxBehaviorEvent event, String cep) {

		try {
			InputStream url = new URL("https://viacep.com.br/ws/" + cep + "/json/").openConnection().getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(url, "UTF-8"));

			String cepAux = "";
			StringBuilder json = new StringBuilder();

			while ((cepAux = br.readLine()) != null) {
				json.append(cepAux);
			}

			SigaBemDTO SigaBemGson = new Gson().fromJson(json.toString(), SigaBemDTO.class);

			sigabem = criarObjetoSigaBem(SigaBemGson);

		} catch (Exception ex) {
			mostraMsg("erro ao consultar cep");
		}
	}

	private SigaBem criarObjetoSigaBem(SigaBemDTO sigaBemGson) {
		SigaBem objSigaBem = new SigaBem();

		objSigaBem.setDDD((sigaBemGson.getDdd() != null) ? sigaBemGson.getDdd() : "61");
		objSigaBem.setUf(sigaBemGson.getUf());

		return objSigaBem;
	}

	public void cepOrigem() {

		pesquisaCep(null, sigabem.getCepOrigem());
		setCepOrigemDDD(sigabem.getDDD());
		setCepOrigemUf(sigabem.getUf());
	}

	public void cepDestino() {

		pesquisaCep(null, sigabem.getCepDestino());
		setCepDestinoDDD(sigabem.getDDD());
		setCepDestinoUf(sigabem.getUf());
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

	public String getCepOrigemDDD() {
		return cepOrigemDDD;
	}

	public void setCepOrigemDDD(String cepOrigemDDD) {
		this.cepOrigemDDD = cepOrigemDDD;
	}

	public String getCepOrigemUf() {
		return cepOrigemUf;
	}

	public void setCepOrigemUf(String cepOrigemUf) {
		this.cepOrigemUf = cepOrigemUf;
	}

	public String getCepDestinoDDD() {
		return cepDestinoDDD;
	}

	public void setCepDestinoDDD(String cepDestinoDDD) {
		this.cepDestinoDDD = cepDestinoDDD;
	}

	public String getCepDestinoUf() {
		return cepDestinoUf;
	}

	public void setCepDestinoUf(String cepDestinoUf) {
		this.cepDestinoUf = cepDestinoUf;
	}

}
