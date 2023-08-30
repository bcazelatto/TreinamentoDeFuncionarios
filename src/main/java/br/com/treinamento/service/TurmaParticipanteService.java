package br.com.treinamento.service;

import java.util.List;

import br.com.treinamento.model.TurmaParticipante;

public interface TurmaParticipanteService {

	List<TurmaParticipante> listar();

	TurmaParticipante incluir(TurmaParticipante turmaParticipante);
	
	void excluir(Long codigo);

}
