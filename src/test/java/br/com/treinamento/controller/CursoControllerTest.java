package br.com.treinamento.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.treinamento.model.Curso;
import br.com.treinamento.service.CursoService;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CursoService cursoService;
	
	@Test
    public void testListarCursos() throws Exception {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso(1, "Curso 1", "Descrição 1", 10));
        cursos.add(new Curso(2, "Curso 2", "Descrição 2", 20));

        when(cursoService.listar()).thenReturn(cursos);

        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].nome").value("Curso 1"))
                .andExpect(jsonPath("$[0].descricao").value("Descrição 1"))
                .andExpect(jsonPath("$[0].duracao").value(10))
                .andExpect(jsonPath("$[1].codigo").value(2))
                .andExpect(jsonPath("$[1].nome").value("Curso 2"))
                .andExpect(jsonPath("$[1].descricao").value("Descrição 2"))
                .andExpect(jsonPath("$[1].duracao").value(20));

        verify(cursoService, times(1)).listar();
        verifyNoMoreInteractions(cursoService);
    }
	

}
