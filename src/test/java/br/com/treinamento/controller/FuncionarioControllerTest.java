package br.com.treinamento.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import br.com.treinamento.model.Funcionario;
import br.com.treinamento.service.FuncionarioService;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = FuncionarioControllerTest.class)
public class FuncionarioControllerTest {

	@Spy
	@InjectMocks
	private FuncionarioController controller;
	
	@Mock
	private FuncionarioService service;
	
	@Test
	void deveCriarUmFuncionario() throws Exception{
		
		when(service.cadastrar(any())).thenReturn(any());
		
		ResponseEntity<Funcionario> response = controller.cadastrar(getFuncionario());
		
		Assertions.assertNotNull(response);
	}
	
	@Test
	void deveAlterarUmFuncionario() throws Exception{
		when(service.atualizar(anyLong(), any())).thenReturn(getFuncionario());
		
		ResponseEntity<Funcionario> response = controller.atualizar((long) 1, getFuncionario());
		
		Assertions.assertNotNull(response);
	}
	
	private Funcionario getFuncionario() {
		Funcionario obj = new Funcionario(1, "Nome", "000.000.000-00", new Date(2023-05-10), "Cargo", new Date(2023-05-10), (short)1);
		return obj;
	}
	
}
