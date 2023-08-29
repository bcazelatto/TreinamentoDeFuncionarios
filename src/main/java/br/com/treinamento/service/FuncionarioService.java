package br.com.treinamento.service;

import java.util.List;

import br.com.treinamento.model.Funcionario;

public interface FuncionarioService {
	
	Funcionario cadastrar(Funcionario funcionario);

	List<Funcionario> listar();
	
	Funcionario atualizar(Long codigo, Funcionario alterar);
	
	void excluir (Long codigo);

}
