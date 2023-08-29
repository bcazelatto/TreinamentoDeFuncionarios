package br.com.treinamento.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.treinamento.conexao.ConexaoMySQL;
import br.com.treinamento.model.TurmaParticipante;
import br.com.treinamento.service.TurmaParticipanteService;

@Service
public class TurmaParticipanteServiceImpl implements TurmaParticipanteService {

	private final ConexaoMySQL conexaoMySQL;
	
	@Autowired
	public TurmaParticipanteServiceImpl(ConexaoMySQL conexaoMySQL) {
		this.conexaoMySQL = conexaoMySQL;
	}
	
	@Override
	public List<TurmaParticipante> listar() {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
        	String sql = "SELECT * FROM turmaparticipante";
        	PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        	ResultSet resultSet = preparedStatement.executeQuery();
        	
        	List<TurmaParticipante> participantes = new ArrayList<>();
        	
        	while (resultSet.next()) {
        		TurmaParticipante participante = new TurmaParticipante();
				participante.setCodigo(resultSet.getInt("Codigo"));
				participante.setTurmaCodigo(resultSet.getInt("Turma"));
				participante.setFuncionarioCodigo(resultSet.getInt("Funcionario"));

				participantes.add(participante);
			}

			return participantes;
        	
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar os participantes.");
		} finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }    
	}

	@Override
	public TurmaParticipante incluir(TurmaParticipante turmaParticipante) {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
        	String sql = "INSERT INTO turmaparticipante (Turma, Funcionario) VALUES (?, ?)";
        	PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        	
        	preparedStatement.setInt(1, turmaParticipante.getTurmaCodigo());
        	preparedStatement.setInt(2, turmaParticipante.getFuncionarioCodigo());
        	
        	preparedStatement.executeUpdate();
          	
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar uma turma participante.");
		} finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }    
        
		return turmaParticipante;
	}

	@Override
	public TurmaParticipante alterar(Long codigo, TurmaParticipante TurmaParticipante) {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
        	String sql = "UPDATE turmaparticipante SET Turma = ?, Funcionario = ? WHERE Codigo = ?";
        	PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        	preparedStatement.setInt(1, TurmaParticipante.getTurmaCodigo());
        	preparedStatement.setInt(2, TurmaParticipante.getFuncionarioCodigo());
        	preparedStatement.setLong(3, codigo);
        	
        	int updatedRows = preparedStatement.executeUpdate();
        	
        	if (updatedRows > 0) {
				return TurmaParticipante;
			} else {
				throw new RuntimeException("Não existe dados para efetuar update.");
			}
			
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar uma turma participante.");
		} finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }    

	}

	@Override
	public void excluir(Long codigo) {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
    		String sql = "DELETE FROM turmaparticipante WHERE Codigo = ?";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setLong(1, codigo);
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			
			if (linhasAfetadas == 0) {
				throw new RuntimeException("Curso com código " + codigo + " não encontrado.");
			}
			
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir uma turma participante.");
		} finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }    
	}
	

}
