package br.com.desafio.back.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.back.dto.SuspeitoDTO;
import br.com.desafio.back.dto.SuspeitoGetAllDTO;
import br.com.desafio.back.entities.Suspeito;
import br.com.desafio.back.repositories.SuspeitoRepository;

@Service
public class SuspeitoService {

	@Autowired
	SuspeitoRepository repository;
	
	public List<SuspeitoGetAllDTO> findAll() {

		List<Suspeito> suspeitos = (ArrayList<Suspeito>) repository.findAll();
		List<SuspeitoGetAllDTO> suspeitosGetAllDTO = new ArrayList<>();

		for (Suspeito suspeito : suspeitos) {
			SuspeitoGetAllDTO suspeitoDTO = new SuspeitoGetAllDTO();
			suspeitoDTO.setCpf(suspeito.getCpf());
			suspeitoDTO.setNome(suspeito.getNome());
			suspeitoDTO.setCaracteristicas(suspeito.getCaracteristicas());
			suspeitoDTO.setCor(suspeito.getCor());
			suspeitoDTO.setAntecedentes(suspeito.getAntecedentes());
			suspeitoDTO.setEndereco(suspeito.getEndereco());
			
			suspeitosGetAllDTO.add(suspeitoDTO);
		}
		suspeitosGetAllDTO.sort(Comparator.comparing(c -> c.getNome()));
		return suspeitosGetAllDTO;
	}
	
	public Suspeito findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
	
	public Suspeito findByNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public Suspeito findByEndereco(String endereco) {
		return repository.findByEndereco(endereco);
	}
	
	public Suspeito findByAntecedentes(String antecedentes) {
		return repository.findByAntecedentes(antecedentes);
	}
	
	public Suspeito findByCor(String cor) {
		return repository.findByCor(cor);
	}
	
	public Suspeito findByCaracteristicas(String caracteristica) {
		return repository.findByCaracteristicas(caracteristica);
	}

	public SuspeitoDTO delete(String cpf) {
		SuspeitoDTO suspeitoDTO2 = new SuspeitoDTO(repository.findByCpf(cpf));
		repository.delete((Suspeito) repository.findByCpf(cpf));
		return suspeitoDTO2;
	}

	public void save(SuspeitoDTO suspeitoDTO) {
		Suspeito suspeito = new Suspeito(suspeitoDTO);
		repository.save(suspeito);
	}

	
}
