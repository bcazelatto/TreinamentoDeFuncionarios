package br.com.treinamento.model;

import java.sql.Date;

public class Funcionario {

	private int Codigo;
	private String nome;
	private String cpf;
	private Date nascimento;
	private String cargo;
	private Date admissao;
	private short status;

	public Funcionario(int codigo, String nome, String cpf, Date nascimento, String cargo, Date admissao,
			short status) {
		super();
		Codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.cargo = cargo;
		this.admissao = admissao;
		this.status = status;
	}
	
	public Funcionario() {
		super();
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}
