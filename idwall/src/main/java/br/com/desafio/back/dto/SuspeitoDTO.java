package br.com.desafio.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.desafio.back.entities.Agente;
import br.com.desafio.back.entities.Suspeito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuspeitoDTO {

	@JsonIgnore
	private Integer suspeitoId;
	private String cpf;
	private String nome;
	private String telefone;
	private String dataNascimento;
	private String antecedentes;
	private Agente agenteId;
	private String	endereco;
	private String cor;
	private String caracteristicas;


	public SuspeitoDTO(Suspeito suspeito) {
		this.suspeitoId = suspeito.getSuspeitoId();
		this.cpf = suspeito.getCpf();
		this.nome = suspeito.getNome();
		this.telefone = suspeito.getTelefone();
		this.dataNascimento = suspeito.getDataNascimento().toString();
		this.antecedentes = suspeito.getAntecedentes();
		this.agenteId = suspeito.getAgenteId();
		this.endereco = suspeito.getEndereco();
		this.cor = suspeito.getCor();
		this.caracteristicas = suspeito.getCaracteristicas();
	}

	public SuspeitoDTO build() throws Exception {
		if (validarNome() && validaCpf())
			return this;
		else
			return null;
	}

	protected boolean validarNome() throws Exception {
		if (nome.length() < 3 || nome.matches("[A-z]\\s[A-z]") || nome.matches("[A-z]*"))
			return false;
		else
			return true;
	}

	protected boolean validaCpf() {
		if (cpf.length() != 11 || telefone.length() != 11)
			return false;
		else
			return true;
	}

}
