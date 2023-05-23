package br.com.desafio.back.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.desafio.back.dto.SuspeitoDTO;

@Entity
@Table(name = "suspeito")
public class Suspeito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer suspeitoId;

	@Column(columnDefinition = "CHAR(11)", nullable = false, unique = true)
	@NotNull
	@Length(max = 11, min = 11)
	private String cpf;

	@NotNull
	private String nome;

	@Column(precision = 11, length = 11)
	private String telefone;

	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;

	@Column(name = "antecedentes")
	private String antecedentes;

	@ManyToOne
	@JoinColumn(name = "agenteId", columnDefinition = "int")
	private Agente agenteId;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "cor")
	private String cor;

	@Column(name = "caracteristicas")
	private String caracteristicas;

	public Suspeito() {
		// TODO Auto-generated constructor stub
	}

	public Suspeito(String cpf, String nome, String telefone, Date dataNascimento, String antecedentes, Agente agenteId,
			String endereco, String cor, String caracteristicas) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.antecedentes = antecedentes;
		this.agenteId = agenteId;
		this.endereco = endereco;
		this.cor = cor;
		this.caracteristicas = caracteristicas;
	}
	
	public Suspeito(SuspeitoDTO suspeito) {
		this.suspeitoId = suspeito.getSuspeitoId();
		this.cpf = suspeito.getCpf();
		this.nome = suspeito.getNome();
		this.telefone = suspeito.getTelefone();
		this.dataNascimento = new Date(suspeito.getDataNascimento());
		this.antecedentes = suspeito.getAntecedentes();
		this.agenteId = suspeito.getAgenteId();
		this.endereco = suspeito.getEndereco();
		this.cor = suspeito.getCor();
		this.caracteristicas = suspeito.getCaracteristicas();
	}

	public Integer getSuspeitoId() {
		return suspeitoId;
	}

	public void setSuspeitoId(Integer suspeitoId) {
		this.suspeitoId = suspeitoId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public Agente getAgenteId() {
		return agenteId;
	}

	public void setAgenteId(Agente agenteId) {
		this.agenteId = agenteId;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

}