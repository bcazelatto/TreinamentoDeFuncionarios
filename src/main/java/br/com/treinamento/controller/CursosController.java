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

import br.com.treinamento.model.Curso;
import br.com.treinamento.service.CursoService;

@RestController
@RequestMapping("/cursos") // Define o mapeamento base para todos os endpoints relacionados a cursos
public class CursosController {
	
	@Autowired 
	private CursoService cursoService;	 
	
	@PostMapping // Endpoint para inclusão de cursos
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.cadastrar(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }
	
    @PutMapping("/{codigo}") // Endpoint para alteração de cursos
    public ResponseEntity<Curso> atualizar(@PathVariable Long codigo, @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.atualizar(codigo, curso);
        if (cursoAtualizado != null) {
            return new ResponseEntity<>(cursoAtualizado, HttpStatus.OK);
        } 
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @DeleteMapping("/{codigo}") // Endpoint para exclusão de cursos
    public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
        cursoService.excluir(codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping // Endpoint para listagem de todos os cursos
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.listar();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
}
