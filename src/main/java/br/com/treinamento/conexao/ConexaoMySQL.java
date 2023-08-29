package br.com.treinamento.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConexaoMySQL {
	
	//@Value("${database.url}")
	private String url = "jdbc:mysql://localhost:3306/treinamento";
	//private String url;

	//@Value("${database.user}")
	private String user = "root";
	//private String user;

	//@Value("${database.password}")
	private String password = "root";
	//private String password;

	//@Value("${database.driver}")
	private String driver = "com.mysql.cj.jdbc.Driver";
	//private String driver;

    public Connection conectar() {
        Connection conexao = null;
        try {
            // Registre o driver JDBC (isso é necessário apenas uma vez)
            Class.forName(driver);
            
            // Crie a conexão com o banco de dados
            conexao = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return conexao;
    }

    public void desconectar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados.");
                e.printStackTrace();
            }
        }
    }
}
