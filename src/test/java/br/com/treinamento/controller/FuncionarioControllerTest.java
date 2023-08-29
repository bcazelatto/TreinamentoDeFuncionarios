package br.com.treinamento.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.treinamento.model.Funcionario;
import br.com.treinamento.service.FuncionarioService;

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FuncionarioService funcionarioService;
	
	@Test
    public void testListarFuncionario() throws Exception {
		String dataString = "2020-10-10";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = dateFormat.parse(dataString);
		Date sqlDate = new Date(utilDate.getTime());
		
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionario(1, "Nome 1", "000.000.000-00", sqlDate, "Cargo 1", sqlDate, (short) 1));
		funcionarios.add(new Funcionario(2, "Nome 2", "111.000.000-00", sqlDate, "Cargo 2", sqlDate, (short) 1));
		
		when(funcionarioService.listar()).thenReturn(funcionarios);
		
		SimpleDateFormat jsonDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String expectedJsonDate = jsonDateFormat.format(sqlDate);
	    String expectedStatus = String.valueOf((short) 1);
		
		mockMvc.perform(get("/funcionario"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].codigo").value(1))
        .andExpect(jsonPath("$[0].nome").value("Nome 1"))
        .andExpect(jsonPath("$[0].cpf").value("000.000.000-00"))
        .andExpect(jsonPath("$[0].nascimento").value(expectedJsonDate))
        .andExpect(jsonPath("$[0].cargo").value("Cargo 1"))
        .andExpect(jsonPath("$[0].admissao").value(expectedJsonDate))
        .andExpect(jsonPath("$[0].status").value(expectedStatus))
        .andExpect(jsonPath("$[1].codigo").value(2))
        .andExpect(jsonPath("$[1].nome").value("Nome 2"))
        .andExpect(jsonPath("$[1].cpf").value("111.000.000-00"))
        .andExpect(jsonPath("$[1].nascimento").value(expectedJsonDate))
        .andExpect(jsonPath("$[1].cargo").value("Cargo 2"))
        .andExpect(jsonPath("$[1].admissao").value(expectedJsonDate))
        .andExpect(jsonPath("$[1].status").value(expectedStatus));

		verify(funcionarioService, times(1)).listar();
		verifyNoMoreInteractions(funcionarioService);
	}
	
}
