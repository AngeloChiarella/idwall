package br.com.desafio.back.entities;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agente")
public class Agente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer agenteId;

	@Column(columnDefinition = "CHAR(11)", nullable = false, unique = true)
	@NotNull
	@Length(max = 11, min = 11)
	private String cpf;

	@NotNull
	private String nome;

	@NotNull
	private String email;

	@Column(precision = 11, length = 11)
	private String telefone;

	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;

	@ManyToOne
	@JoinColumn(name = "setorId", columnDefinition = "int")
	private Setor setorId;

	public Agente() {
		// TODO Auto-generated constructor stub
	}

	public Agente(Integer agenteId, String cpf, String nome, String email, String telefone, Date dataNascimento,
			Setor setorId) {
		this.agenteId = agenteId;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.setorId = setorId;
	}

	public Integer getAgenteId() {
		return agenteId;
	}

	public void setAgenteId(Integer agenteId) {
		this.agenteId = agenteId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Setor getSetorId() {
		return setorId;
	}

	public void setSetorId(Setor setorId) {
		this.setorId = setorId;
	}

}