package br.com.treinamento.model;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Funcionario {

	private int Codigo;
	
	@NotBlank(message = "É necessário informar um nome!")
	private String nome;
	
	@NotBlank(message = "É necessário informar um cpf e deve estar no formato Ex. 000.000.000-00!")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato Ex. 000.000.000-00")
	private String cpf;
	
	@NotNull(message = "É necessário informar uma data de nascimento!")
	private Date nascimento;
	
	@NotBlank(message = "É necessário informar um cargo!")
	private String cargo;
	
	@NotNull(message = "É necessário informar uma data de admissão!")
	private Date admissao;
	
	@NotNull(message = "É necessário informar um status = 1(Ativo) ou 0(Inativo)!")
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
