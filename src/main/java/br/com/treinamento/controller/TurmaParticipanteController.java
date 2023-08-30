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

import br.com.treinamento.model.TurmaParticipante;
import br.com.treinamento.service.TurmaParticipanteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmaParticipante") // Define o mapeamento base para todos os endpoints relacionados os participantes
public class TurmaParticipanteController {

	@Autowired
	private TurmaParticipanteService service;
	
	@GetMapping
	public ResponseEntity<List<TurmaParticipante>> listar(){
		List<TurmaParticipante> turmaParticipante = service.listar();
		return new ResponseEntity<>(turmaParticipante, HttpStatus.OK);
	}
	
	@PostMapping // Endpoint para inclusão de participantes
	public ResponseEntity<TurmaParticipante> adicionar(@Valid @RequestBody TurmaParticipante turmaParticipante){
		TurmaParticipante novaTurmaParticipante = service.incluir(turmaParticipante);
		return new ResponseEntity<>(novaTurmaParticipante, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
        service.excluir(codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
