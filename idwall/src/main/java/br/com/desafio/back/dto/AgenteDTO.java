package br.com.desafio.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.desafio.back.entities.Agente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgenteDTO {

	@JsonIgnore
	private Integer agenteId;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private Integer setorId;
	private String dataNascimento;

	public AgenteDTO(Agente agente) {
		this.agenteId = agente.getAgenteId();
		this.cpf = agente.getCpf();
		this.nome = agente.getNome();
		this.email = agente.getEmail();
		this.telefone = agente.getTelefone();
		this.setorId = agente.getSetorId().getId();
		this.dataNascimento = agente.getDataNascimento().toString();
	}

	public AgenteDTO build() throws Exception {
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
