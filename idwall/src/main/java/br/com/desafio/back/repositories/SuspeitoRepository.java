package br.com.desafio.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.back.entities.Suspeito;

public interface SuspeitoRepository extends JpaRepository<Suspeito, Integer>{
	
	Suspeito findByCpf(String cpf);
	Suspeito findByNome(String nome);
	Suspeito findByEndereco(String endereco);
	Suspeito findByAntecedentes(String antecedentes);
	Suspeito findByCor(String cor);
	Suspeito findByCaracteristicas(String caracteristicas);

}
