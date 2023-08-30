package br.com.treinamento.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import br.com.treinamento.model.Curso;
import br.com.treinamento.service.CursoService;
import jakarta.validation.ValidationException;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = CursoControllerTest.class)
public class CursoControllerTest {

	@Spy
	@InjectMocks
	private CursoController cursoController;

	@Mock
	private CursoService cursoService;
	
	@Test
	void deveCriarUmCurso() throws Exception{
		
		when(cursoService.cadastrar(any())).thenReturn(any());
		
		ResponseEntity<Curso> response = cursoController.criar(getCurso());
		
		Assertions.assertNotNull(response);
	}
	
	@Test
	void deveRetornarErroEnvioParamentroFaltandoAoCriarUmCurso() throws Exception{
		Curso cursoComParametroNull = new Curso(1, null, "descricao", 90);
		
	    Assertions.assertThrows(ValidationException.class, () -> {
	    	validarCurso(cursoComParametroNull);
	    });
	}
	
	@Test
	void deveAtualizarUmCurso() throws Exception{
		
		when(cursoService.atualizar(anyLong(), any(Curso.class))).thenReturn(getCurso());
		
		ResponseEntity<Curso> response = cursoController.atualizar((long) 1,  getCurso());
		
		Assertions.assertNotNull(response);
	}
	
	@Test
	void deveRetornarNotFoundAoAtualizarUmCurso() throws Exception{
		
		when(cursoService.atualizar(anyLong(), any(Curso.class))).thenReturn(null);
		
		ResponseEntity<Curso> response = cursoController.atualizar((long) 1,  getCurso());
		
		Assertions.assertNotNull(response);
		
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	
	@Test 
	void deveDeletarUmCurso() throws Exception{
		  
		doNothing().when(cursoService).excluir(anyLong());
		
		Assertions.assertNotNull(cursoController.excluir((long) 1));
		
		Assertions.assertEquals(HttpStatus.NO_CONTENT, cursoController.excluir((long) 1).getStatusCode());
	}
	
	@Test
	void deveListarOsCursos() throws Exception{
		
		when(cursoService.listar()).thenReturn(getListCursos());
		
		ResponseEntity<List<Curso>> response = cursoController.listar();
		
		Assertions.assertNotNull(response);
	}
	
	private Curso getCurso() {
		Curso obj = new Curso(1, "Nome", "descricao", 90);
		return obj;
	}
	
	private List<Curso> getListCursos(){
		Curso info1 = new Curso(1, "Nome 1", "descricao 1", 90);
		Curso info2 = new Curso(2, "Nome 2", "descricao 2", 180);
		List<Curso> obj = new ArrayList<>();
		obj.add(info1);
		obj.add(info2);
		
		return obj;
	}
	
	private void validarCurso(Curso curso) {
		if (curso.getNome() == null || curso.getNome().isEmpty()) {
			throw new ValidationException("Curso não pode possuir null informações chaves");
		}
	}
	
}
