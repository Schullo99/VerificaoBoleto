package br.com.unicuritiba.ProjectValidacaoBoleto.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicuritiba.ProjectValidacaoBoleto.models.Boleto;
import br.com.unicuritiba.ProjectValidacaoBoleto.repositories.BoletoRepository;

@RestController
public class BoletoControllers {
	@Autowired
	BoletoRepository repository;
	
	@GetMapping("/boleto")
	public ResponseEntity<Object> getBoleto(){
		return ResponseEntity.ok(repository.findAll());	
	}
	@GetMapping("/boleto/id")
	public ResponseEntity<Boleto> getBoleto(@PathVariable long id) {
		return ResponseEntity.ok(repository.findById(id).get());
	}
	@GetMapping("/boleto/cpf")
	public ResponseEntity<Boleto> getBoletocpf(@PathVariable String cpf) {
		return ResponseEntity.ok(repository.findByCpf(cpf).get());
	}
	@GetMapping("/boleto/codigo_barras")
	public ResponseEntity<Boleto> getBoletobr(@PathVariable String linhaDigitavel) {
		return ResponseEntity.ok(repository.findByLinhaDigitavel(linhaDigitavel).get());
	}
	@GetMapping("/boleto/nome")
	public ResponseEntity<Boleto> getBoletonome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findByNome(nome).get());
			
	}	
	@PostMapping("/boleto")
	public ResponseEntity<Boleto> SaveBoleto(@RequestBody Boleto boleto){
		Boleto savedBoleto = repository.save(boleto);
		return ResponseEntity.ok(savedBoleto);
	}	
	@DeleteMapping("/boleto{id}")
	public void removieBoleto(@PathVariable long id) {
		repository.deleteById(id);
	}
	@PutMapping("/boleto/{id}")
	public ResponseEntity<Boleto> updateBoleto(@PathVariable long id, @RequestBody Boleto boleto) {
		boleto.setId(id);
		Boleto savedBoleto = repository.save(boleto);
		return ResponseEntity.ok(savedBoleto);
	}

}
