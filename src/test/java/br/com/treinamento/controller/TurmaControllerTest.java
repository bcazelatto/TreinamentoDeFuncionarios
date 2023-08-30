package br.com.treinamento.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import br.com.treinamento.model.Turma;
import br.com.treinamento.service.TurmaService;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = TurmaControllerTest.class)
public class TurmaControllerTest {

	@Spy
	@InjectMocks
	private TurmaController controller;
	
	@Mock
	private TurmaService service;
	
	@Test
	void deveCriarUmCurso() throws Exception{
		when(service.criarTurma(any())).thenReturn(any());
		
		ResponseEntity<Turma> response = controller.cadastrarTurma(getTurma());
		
		Assertions.assertNotNull(response);
				
	}
	
	private Turma getTurma() {
		Turma obj = new Turma(new Date(2020-10-10), new Date(2020-10-10), "Local", 1);
		return obj;
	}

	
}
