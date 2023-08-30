package br.com.treinamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.model.Funcionario;
import br.com.treinamento.service.FuncionarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario") // Define o mapeamento base para todos os endpoints relacionados a funcionario
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@PostMapping
	public ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario ){
		Funcionario novoFuncionario = service.cadastrar(funcionario);
		return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar(){
		List<Funcionario> todosFuncionarios = service.listar();
		return new ResponseEntity<>(todosFuncionarios, HttpStatus.OK);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long codigo,@Valid @RequestBody Funcionario funcionario){
		Funcionario updateFuncionario = service.atualizar(codigo, funcionario);
		if(updateFuncionario != null) {
			 return new ResponseEntity<>(updateFuncionario, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo){
		service.excluir(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
