package br.com.desafio.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.back.entities.Agente;

public interface AgenteRepository extends JpaRepository<Agente, Integer> {

	Agente findByCpf(String cpf);

}
