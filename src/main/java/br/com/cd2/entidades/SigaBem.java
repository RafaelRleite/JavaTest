package br.com.cd2.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sigabem")
@NamedQuery(name = "SigaBem.DaoGenerico", query = "select e from SigaBem e")
public class SigaBem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_id")
	private Integer cdId;

	@Column(name = "cep_destino")
	private String cepDestino;

	@Column(name = "cep_origem")
	private String cepOrigem;

	@Column(name = "dt_consulta")
	private Timestamp dtConsulta;

	@Column(name = "dt_previstaentrega")
	private Timestamp dtPrevistaentrega;

	@Column(name = "nm_destinatario")
	private String nmDestinatario;

	@Column(name = "vl_peso")
	private Integer vlPeso;

	@Column(name = "vl_totalfrete")
	private BigDecimal vlTotalfrete;

	@Column(name = "cep_uf")
	private String uf;

	@Column(name = "cep_ddd")
	private String ddd;
	

	public Integer getCdId() {
		return this.cdId;
	}

	public void setCdId(Integer cdId) {
		this.cdId = cdId;
	}

	public String getCepOrigem() {
		return this.cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return this.cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public Timestamp getDtConsulta() {
		return dtConsulta;
	}

	public void setDtConsulta(Timestamp dtConsulta) {
		this.dtConsulta = dtConsulta;
	}

	public Timestamp getDtPrevistaentrega() {
		return dtPrevistaentrega;
	}

	public void setDtPrevistaentrega(Timestamp dtPrevistaentrega) {
		this.dtPrevistaentrega = dtPrevistaentrega;
	}

	public String getNmDestinatario() {
		return this.nmDestinatario;
	}

	public void setNmDestinatario(String nmDestinatario) {
		this.nmDestinatario = nmDestinatario;
	}

	public Integer getVlPeso() {
		return this.vlPeso;
	}

	public void setVlPeso(Integer vlPeso) {
		this.vlPeso = vlPeso;
	}

	public BigDecimal getVlTotalfrete() {
		return this.vlTotalfrete;
	}

	public void setVlTotalfrete(BigDecimal vlVltotalfrete) {
		this.vlTotalfrete = vlVltotalfrete;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getDDD() {
		return ddd;
	}

	public void setDDD(String ddd) {
		this.ddd = ddd;
	}

}