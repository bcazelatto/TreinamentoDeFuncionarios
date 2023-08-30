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
import br.com.treinamento.model.Curso;
import br.com.treinamento.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

	private final ConexaoMySQL conexaoMySQL;
	
	@Autowired
	public CursoServiceImpl(ConexaoMySQL conexaoMySQL) {
		this.conexaoMySQL = conexaoMySQL;
	}

	@Override
	public Curso atualizar(Long codigo, Curso alterar) {
		// Obtém uma conexão do ConexaoMySQL
		Connection conexao = conexaoMySQL.conectar();

		try {
			String sql = "UPDATE Curso SET Nome = ?, Descricao = ?, Duracao = ? WHERE Codigo = ?";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setString(1, alterar.getNome());
			preparedStatement.setString(2, alterar.getDescricao());
			preparedStatement.setInt(3, alterar.getDuracao());
			preparedStatement.setLong(4, codigo);

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows > 0) {
				return alterar;
			} else {
				throw new RuntimeException("Não existe dados para efetuar update.");
			}

		} catch (SQLException | DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar o curso.");
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
			// Query para deletar as turmas associadas ao curso
			String sqlTurmas = "DELETE FROM Turma WHERE Curso IN (SELECT Codigo FROM Curso WHERE Codigo = ?)";
			PreparedStatement preparedStatement = conexao.prepareStatement(sqlTurmas);
			preparedStatement.setLong(1, codigo);

			preparedStatement.executeUpdate();

			 // Query para deletar o curso
	        String sqlCurso = "DELETE FROM Curso WHERE Codigo = ?";
	        PreparedStatement preparedStatementCurso = conexao.prepareStatement(sqlCurso);
	        preparedStatementCurso.setLong(1, codigo);
	        
	        int linhasAfetadasCurso = preparedStatementCurso.executeUpdate();
			
	        if (linhasAfetadasCurso == 0) {
	            throw new RuntimeException("Curso com código " + codigo + " não encontrado.");
	        }
	        
		} catch (SQLException | DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir o curso.");
		} finally {
			// Fecha a conexão após o uso
			conexaoMySQL.desconectar(conexao);
		}

	}

	@Override
	public List<Curso> listar() {
		// Obtém uma conexão do ConexaoMySQL
		Connection conexao = conexaoMySQL.conectar();

		try {
			String sql = "SELECT * FROM Curso";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Curso> cursos = new ArrayList<>();

			while (resultSet.next()) {
				Curso curso = new Curso();
				curso.setCodigo(resultSet.getInt("Codigo"));
				curso.setNome(resultSet.getString("Nome"));
				curso.setDescricao(resultSet.getString("Descricao"));
				curso.setDuracao(resultSet.getInt("Duracao"));

				cursos.add(curso);
			}

			return cursos;

		} catch (SQLException | DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao listar os cursos.");
		} finally {
			// Fecha a conexão após o uso
			conexaoMySQL.desconectar(conexao);
		}
	}

	public Curso cadastrar(Curso curso) {
		// Obtém uma conexão do ConexaoMySQL
		Connection conexao = conexaoMySQL.conectar();

		try {
			String sql = "INSERT INTO Curso (Nome, Descricao, Duracao) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, curso.getNome());
			preparedStatement.setString(2, curso.getDescricao());
			preparedStatement.setInt(3, curso.getDuracao());

			preparedStatement.executeUpdate();

			// Atribui o ID gerado ao objeto Curso, se necessário

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao cadastrar/criar o curso.");
		} finally {
			// Fecha a conexão após o uso
			conexaoMySQL.desconectar(conexao);
		}
		return curso;
	}

}
