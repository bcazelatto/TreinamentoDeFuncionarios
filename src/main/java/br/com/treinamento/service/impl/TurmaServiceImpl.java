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
import br.com.treinamento.model.Turma;
import br.com.treinamento.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

	private final ConexaoMySQL conexaoMySQL;
	
	@Autowired
	public TurmaServiceImpl(ConexaoMySQL conexaoMySQL) {
		this.conexaoMySQL = conexaoMySQL;
	}
	
	@Override
	public Turma criarTurma(Turma turma) {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
            String sql = "INSERT INTO Turma (Inicio, Fim, Local, Curso) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setDate(1, turma.getInicio());
            preparedStatement.setDate(2, turma.getFim());
            preparedStatement.setString(3, turma.getLocal());
            preparedStatement.setLong(4, turma.getCursoCodigo());

            preparedStatement.executeUpdate();
            
            // Atribui o ID gerado ao objeto Curso, se necessário
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar/criar a turma.");
        } finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }    
        return turma;
    }

	@Override
	public Turma atualizarTurma(Long codigo, Turma alterar) {
		// Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
   
        try {
            String sql = "UPDATE Turma SET Inicio = ?, Fim = ?, Local = ?, Curso = ? WHERE Codigo = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, alterar.getInicio());
            preparedStatement.setDate(2, alterar.getFim());
            preparedStatement.setString(3, alterar.getLocal());
            preparedStatement.setInt(4, alterar.getCursoCodigo());
            preparedStatement.setLong(5, codigo);
           
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                return alterar;
            } else {
                return null;
            }
        	
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar a turma.");
        } finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }  
	}

	@Override
	public void excluirTurma(Long codigo) {
		// Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
   
        try {
			String sql = "DELETE FROM Turma WHERE Codigo = ?";
			  PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			  preparedStatement.setLong(1, codigo);

			  int linhasAfetadas = preparedStatement.executeUpdate();

			  if (linhasAfetadas == 0) {
				  throw new RuntimeException("Curso com código " + codigo + " não encontrado.");
			  }
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir a turma.");
        } finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }    


	}

	@Override
	public List<Turma> listarTurmas() {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
			String sql = "SELECT * FROM Turma";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			 
			List<Turma> turmas = new ArrayList<>();
	
	        while (resultSet.next()) {
	            Turma turma = new Turma();
	            turma.setCodigo(resultSet.getLong("Codigo"));;
	            turma.setInicio(resultSet.getDate("Inicio"));
	            turma.setFim(resultSet.getDate("Fim"));
	            turma.setLocal(resultSet.getString("Local"));
	            turma.setCursoCodigo(resultSet.getInt("Curso"));
	            
	            turmas.add(turma);
	        }

	        return turmas;	
       
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar as turmas.");
		} finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }   
	}

	@Override
	public List<Turma> buscarTurmasPorCurso(Long curso) {
        // Obtém uma conexão do ConexaoMySQL
        Connection conexao = conexaoMySQL.conectar();
        
        try {
			String sql = "SELECT * FROM Turma Where Curso = ?";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setLong(1, curso);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			 
			List<Turma> turmas = new ArrayList<>();
	
	        while (resultSet.next()) {
	            Turma turma = new Turma();
	            turma.setCodigo(resultSet.getLong("Codigo"));;
	            turma.setInicio(resultSet.getDate("Inicio"));
	            turma.setFim(resultSet.getDate("Fim"));
	            turma.setLocal(resultSet.getString("Local"));
	            turma.setCursoCodigo(resultSet.getInt("Curso"));
	            
	            turmas.add(turma);
	        }

	        return turmas;	
       
        } catch (SQLException |DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar as turmas.");
		} finally {
            // Fecha a conexão após o uso
            conexaoMySQL.desconectar(conexao);
        }   
	}

}
