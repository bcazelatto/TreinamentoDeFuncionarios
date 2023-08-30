package br.com.treinamento.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import br.com.treinamento.model.Curso;
import br.com.treinamento.service.CursoService;


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
	void deveAlterarUmCurso() throws Exception{
		
		when(cursoService.atualizar(anyLong(), any(Curso.class))).thenReturn(getCurso());
		
		ResponseEntity<Curso> response = cursoController.atualizar((long) 1,  getCurso());
		
		Assertions.assertNotNull(response);
	}
	
	/*
	 * @Test void deveDeletarUmCurso() throws Exception{
	 * when(cursoService.excluir(anyLong())).thenReturn(getCurso()); }
	 */
	
	private Curso getCurso() {
		Curso obj = new Curso(1, "Nome", "descricao", 90);
		return obj;
	}
	

}
