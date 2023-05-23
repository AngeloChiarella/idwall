package br.com.desafio.back.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.back.dto.AgenteDTO;
import br.com.desafio.back.dto.AgenteGetAllDTO;
import br.com.desafio.back.dto.SuspeitoDTO;
import br.com.desafio.back.entities.Agente;
import br.com.desafio.back.entities.Setor;
import br.com.desafio.back.entities.Suspeito;
import br.com.desafio.back.repositories.AgenteRepository;
import br.com.desafio.back.repositories.SetorRepository;
import br.com.desafio.back.repositories.SuspeitoRepository;
import oracle.net.aso.s;

@Service
public class AgenteService {

	@Autowired
	private AgenteRepository repository;

	@Autowired
	private SuspeitoRepository suspeitoRepository;

	@Autowired
	EnviarEmail email;

	@Autowired
	private SetorRepository setorRepository;

	public AgenteDTO save(AgenteDTO agenteDTO) {

		Optional<Setor> setor = setorRepository.findById(agenteDTO.getSetorId());

		Agente agente = findByCpf(agenteDTO.getCpf());

		if (agente == null) {
			agente = new Agente();
		}

		agente.setCpf(agenteDTO.getCpf());
		agente.setNome(agenteDTO.getNome());
		agente.setEmail(agenteDTO.getEmail());
		agente.setTelefone(agenteDTO.getTelefone());
		agente.setDataNascimento(Date.valueOf(agenteDTO.getDataNascimento()));
		agente.setSetorId(setor.get());

		repository.save(agente);
		email.enviar();
		return agenteDTO;

	}
	
	public SuspeitoDTO saveSuspeito(SuspeitoDTO suspeitoDTO) {

		Suspeito suspeito = suspeitoRepository.findByCpf(suspeitoDTO.getCpf());

		if (suspeito == null) {
			suspeito = new Suspeito(suspeitoDTO);
		}
		
		suspeitoRepository.save(suspeito);
		return suspeitoDTO;
	}

	public List<AgenteGetAllDTO> findAll() {
		List<Agente> agentes = (ArrayList<Agente>) repository.findAll();
		List<AgenteGetAllDTO> agentesGetAllDTO = new ArrayList<>();

		for (Agente agente : agentes) {
			AgenteGetAllDTO agenteDTO = new AgenteGetAllDTO();
			agenteDTO.setCpf(agente.getCpf());
			agenteDTO.setNome(agente.getNome());
			agenteDTO.setEmail(agente.getEmail());
			agenteDTO.setSetor(agente.getSetorId());

			agentesGetAllDTO.add(agenteDTO);
		}
		agentesGetAllDTO.sort(Comparator.comparing(c -> c.getNome()));
		return agentesGetAllDTO;

	}

	public Optional<Agente> findById(Integer agenteId) {
		return repository.findById(agenteId);
	}

	public Agente findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	public AgenteDTO delete(String cpf) {
		AgenteDTO agenteDTO2 = new AgenteDTO(repository.findByCpf(cpf));
		repository.delete((Agente) repository.findByCpf(cpf));
		return agenteDTO2;
	}

}
