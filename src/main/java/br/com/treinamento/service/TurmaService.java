package br.com.treinamento.service;

import java.util.List;

import br.com.treinamento.model.Turma;

public interface TurmaService {

	Turma criarTurma(Turma turma);

	Turma atualizarTurma(Long codigo, Turma turma);

	void excluirTurma(Long codigo);

	List<Turma> listarTurmas();

	List<Turma> buscarTurmasPorCurso(Long curso);

}
