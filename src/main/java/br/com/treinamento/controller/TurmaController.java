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

import br.com.treinamento.model.Turma;
import br.com.treinamento.service.TurmaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/turma") // Define o mapeamento base para todos os endpoints relacionados as turmas
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@PostMapping // Endpoint para inclusão das turmas
	public ResponseEntity<Turma> cadastrarTurma(@Valid @RequestBody Turma turma) {
		Turma novaTurma = turmaService.criarTurma(turma);
		return new ResponseEntity<>(novaTurma, HttpStatus.CREATED);
	}

	@PutMapping("/{codigo}") // Endpoint para alteracao das turmas
	public ResponseEntity<Turma> alterarTurma(@PathVariable Long codigo,@Valid @RequestBody Turma turma) {
		Turma atualizarTurma = turmaService.atualizarTurma(codigo, turma);
		if (atualizarTurma != null) {
			return new ResponseEntity<>(atualizarTurma, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{codigo}") // Endpoint para exclusão de cursos
	public ResponseEntity<Void> excluirTurma(@PathVariable Long codigo) {
		turmaService.excluirTurma(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
    @GetMapping // Endpoint para listagem de todos os cursos
    public ResponseEntity<List<Turma>> listarTurmas() {
        List<Turma> turmas = turmaService.listarTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
    
    @GetMapping("/{curso}") // Endpoint para listagem de todos os cursos
    public ResponseEntity<List<Turma>> listarTurmasPorGrupo(@PathVariable Long curso) {
        List<Turma> turmas = turmaService.buscarTurmasPorCurso(curso);
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
}
