package br.com.treinamento.controller;

import static org.mockito.ArgumentMatchers.any;
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

import br.com.treinamento.model.TurmaParticipante;
import br.com.treinamento.service.TurmaParticipanteService;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = TurmaParticipanteControllerTest.class)
public class TurmaParticipanteControllerTest {

	@Spy
	@InjectMocks
	private TurmaParticipanteController controller;
	
	@Mock
	private TurmaParticipanteService service;
	
	@Test
	void deveCriarUmaTurmaParticipante() throws Exception{
		when(service.incluir(any())).thenReturn(any());
		
		ResponseEntity<TurmaParticipante> response = controller.adicionar(getTurmaParticipante());
		
		Assertions.assertNotNull(response);
		
	}
	
	private TurmaParticipante getTurmaParticipante() {
		TurmaParticipante obj = new TurmaParticipante(1, 1, 1);
		return obj;
	}

	
}
