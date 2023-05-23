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

import br.com.desafio.back.dto.SuspeitoDTO;
import br.com.desafio.back.dto.SuspeitoGetAllDTO;
import br.com.desafio.back.entities.Suspeito;
import br.com.desafio.back.service.SuspeitoService;

@RestController
@RequestMapping(value = "/suspeito")
@CrossOrigin(origins = "*")
public class SuspeitoController {

	@Autowired
	private SuspeitoService service;

	@GetMapping
	public ResponseEntity<List<SuspeitoGetAllDTO>> findAll() {
		List<SuspeitoGetAllDTO> lista = service.findAll();

		if (!lista.isEmpty())
			return new ResponseEntity<List<SuspeitoGetAllDTO>>(lista, HttpStatus.OK);

		else
			return new ResponseEntity<List<SuspeitoGetAllDTO>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "cpf/{cpf}")
	public ResponseEntity<Suspeito> findByCpf(@PathVariable String cpf) throws NotFoundException {
		if (cpf.length() != 11)
			return new ResponseEntity<Suspeito>(HttpStatus.BAD_REQUEST);

		if (service.findByCpf(cpf) == null)
			return new ResponseEntity<Suspeito>(HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<Suspeito>(service.findByCpf(cpf), HttpStatus.OK);

	}
	
	@GetMapping(value = "cor/{cor}")
	public ResponseEntity<Suspeito> findByCor(@PathVariable String cor) throws NotFoundException {
		if (service.findByCor(cor) == null)
			return new ResponseEntity<Suspeito>(HttpStatus.NOT_FOUND);
		
		else
			return new ResponseEntity<Suspeito>(service.findByCor(cor), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "antecedentes/{antecedentes}")
	public ResponseEntity<Suspeito> findByAntecedentes(@PathVariable String antecedentes) throws NotFoundException {
		if (service.findByAntecedentes(antecedentes) == null)
			return new ResponseEntity<Suspeito>(HttpStatus.NOT_FOUND);
		
		else
			return new ResponseEntity<Suspeito>(service.findByAntecedentes(antecedentes), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "endereco/{endereco}")
	public ResponseEntity<Suspeito> findByEndereco(@PathVariable String endereco) throws NotFoundException {
		if (service.findByEndereco(endereco) == null)
			return new ResponseEntity<Suspeito>(HttpStatus.NOT_FOUND);
		
		else
			return new ResponseEntity<Suspeito>(service.findByEndereco(endereco), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "caracteristicas/{caracteristicas}")
	public ResponseEntity<Suspeito> findByCaracteristicas(@PathVariable String caracteristicas) throws NotFoundException {
		if (service.findByCaracteristicas(caracteristicas) == null)
			return new ResponseEntity<Suspeito>(HttpStatus.NOT_FOUND);
		
		else
			return new ResponseEntity<Suspeito>(service.findByCaracteristicas(caracteristicas), HttpStatus.OK);
		
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody SuspeitoDTO suspeitoDTO) throws Exception {
		if (suspeitoDTO.build() == null)
			new ResponseEntity<SuspeitoDTO>(HttpStatus.BAD_REQUEST);
		else
			service.save(suspeitoDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<SuspeitoDTO> delete(@PathVariable String cpf) throws Exception {
		if (cpf.length() != 11)
			return new ResponseEntity<SuspeitoDTO>(HttpStatus.BAD_REQUEST);
		
		if (service.findByCpf(cpf) == null)
			return new ResponseEntity<SuspeitoDTO>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<SuspeitoDTO>(service.delete(cpf), HttpStatus.OK);
	}

}
