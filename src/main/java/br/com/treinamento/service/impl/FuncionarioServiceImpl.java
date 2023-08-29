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
import br.com.treinamento.model.Funcionario;
import br.com.treinamento.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private final ConexaoMySQL conexaoMySQL;
	
	@Autowired
	public FuncionarioServiceImpl(ConexaoMySQL conexaoMySQL) {
		this.conexaoMySQL = conexaoMySQL;
	}
	
	@Override
	public Funcionario cadastrar(Funcionario funcionario) {
		// Obtém uma conexão do ConexaoMySQL
		Connection conn = conexaoMySQL.conectar();
		
		try {
			String sql = "INSERT INTO funcionario (Nome, CPF, Nascimento, Cargo, Admissao, Status) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement std = conn.prepareStatement(sql);
			
			std.setString(1, funcionario.getNome());
			std.setString(2, funcionario.getCpf());
			std.setDate(3, funcionario.getNascimento());
			std.setString(4, funcionario.getCargo());
			std.setDate(5, funcionario.getAdmissao());
			std.setShort(6, funcionario.getStatus());
			
			std.executeUpdate();
			
		} catch (SQLException | DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar um funcionario.");
		}finally {
			conexaoMySQL.desconectar(conn);
		}
		return funcionario;
	}

	@Override
	public List<Funcionario> listar() {
		// Obtém uma conexão do ConexaoMySQL
		Connection conn = conexaoMySQL.conectar();

		try {
			String sql = "SELECT * FROM funcionario";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Funcionario> funcionarios = new  ArrayList<>();
			
			while(resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(resultSet.getString("Nome"));
				funcionario.setCpf(resultSet.getString("CPF"));
				funcionario.setNascimento(resultSet.getDate("Nascimento"));
				funcionario.setCargo(resultSet.getString("Cargo"));
				funcionario.setAdmissao(resultSet.getDate("Admissao"));
				funcionario.setStatus(resultSet.getShort("Status"));
				
				funcionarios.add(funcionario);
			}
			
			return funcionarios;
			
		} catch (SQLException | DataAccessException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao listar todos os funcionario.");
		}finally {
			conexaoMySQL.desconectar(conn);
		}

	}

	@Override
	public Funcionario atualizar(Long codigo, Funcionario alterar) {
		// Obtém uma conexão do ConexaoMySQL
		Connection conn = conexaoMySQL.conectar();
		
		try {
			String sql = "UPDATE funcionario SET Nome = ?, CPF = ?, Nascimento = ?, Cargo = ?, Admissao = ?, Status = ? WHERE Codigo = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, alterar.getNome());
			preparedStatement.setString(2, alterar.getCpf());
			preparedStatement.setDate(3, alterar.getNascimento());
			preparedStatement.setString(4, alterar.getCargo());
			preparedStatement.setDate(5, alterar.getAdmissao());
			preparedStatement.setShort(6, alterar.getStatus());
			preparedStatement.setLong(7, codigo);
			
			int updatedRows = preparedStatement.executeUpdate();
			
			if(updatedRows > 0) {
				return alterar;
			}
			
			throw new RuntimeException("Não existe dados para efetuar update.");
			
		} catch (SQLException | DataAccessException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao atualizar um funcionario.");
		}finally {
			conexaoMySQL.desconectar(conn);
		}		
	}

	@Override
	public void excluir(Long codigo) {
		// Obtém uma conexão do ConexaoMySQL
		Connection conn = conexaoMySQL.conectar();
		
		try {
			String sql = "DELETE FROM funcionario WHERE Codigo = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, codigo);
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			
			if (linhasAfetadas == 0) {
				throw new RuntimeException("Curso com código " + codigo + " não encontrado.");
			}
		
		} catch (SQLException | DataAccessException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao excluir um funcionario.");
		}finally {
			conexaoMySQL.desconectar(conn);
		}		

	}

}
