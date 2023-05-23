package br.com.desafio.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuspeitoGetAllDTO {
	
	private String cpf;
	private String nome;
	private String endereco;
	private String antecedentes;
	private String cor;
	private String caracteristicas;

}
