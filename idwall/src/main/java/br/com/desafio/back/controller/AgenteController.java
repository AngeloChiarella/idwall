package br.com.desafio.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.back.dto.AgenteDTO;
import br.com.desafio.back.dto.AgenteGetAllDTO;
import br.com.desafio.back.entities.Agente;
import br.com.desafio.back.service.AgenteService;

@RestController
@RequestMapping(value = "/agente")
@CrossOrigin(origins = "*")
public class AgenteController {

	@Autowired
	private AgenteService service;

	@GetMapping
	public ResponseEntity<List<AgenteGetAllDTO>> findAll() {
		List<AgenteGetAllDTO> lista = service.findAll();

		if (!lista.isEmpty())
			return new ResponseEntity<List<AgenteGetAllDTO>>(lista, HttpStatus.OK);

		else
			return new ResponseEntity<List<AgenteGetAllDTO>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{cpf}")
	public ResponseEntity<Agente> findByCpf(@PathVariable String cpf) throws NotFoundException {
		if (cpf.length() != 11)
			return new ResponseEntity<Agente>(HttpStatus.BAD_REQUEST);

		if (service.findByCpf(cpf) == null)
			return new ResponseEntity<Agente>(HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<Agente>(service.findByCpf(cpf), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<AgenteDTO> save(@RequestBody AgenteDTO agenteDTO) throws Exception {
		if (agenteDTO.build() == null)
			return new ResponseEntity<AgenteDTO>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<AgenteDTO>(service.save(agenteDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<AgenteDTO> delete(@PathVariable String cpf) throws Exception {
		if (cpf.length() != 11)
			return new ResponseEntity<AgenteDTO>(HttpStatus.BAD_REQUEST);
		
		if (service.findByCpf(cpf) == null)
			return new ResponseEntity<AgenteDTO>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<AgenteDTO>(service.delete(cpf), HttpStatus.OK);
	}

}
