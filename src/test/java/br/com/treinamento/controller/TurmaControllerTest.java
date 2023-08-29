package br.com.treinamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.treinamento.service.TurmaService;

@WebMvcTest(TurmaController.class)
public class TurmaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TurmaService turmaService;
	
}
