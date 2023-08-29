package br.com.treinamento.service;

import java.util.List;

import br.com.treinamento.model.Curso;

public interface CursoService {
	
	Curso atualizar(Long codigo, Curso alterar);
	
	void excluir(Long codigo);
	
	List<Curso> listar();

	Curso cadastrar(Curso curso);

}
