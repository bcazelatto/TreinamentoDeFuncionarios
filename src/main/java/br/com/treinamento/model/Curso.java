package br.com.treinamento.model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Curso {

	private int codigo;
	
	@NotBlank(message = "É necessário informar o nome do curso!")
	private String nome;
	@NotBlank(message = "É necessário informar a descrição do curso!")
	private String descricao;
	@NotNull(message = "É necessário informar a duração do curso!")
	private int duracao;

	public Curso() {
		super();
	}

	public Curso(int codigo, @NotBlank(message = "É necessário informar o nome do curso!") String nome,
			@NotBlank(message = "É necessário informar a descrição do curso!") String descricao,
			@NotNull(message = "É necessário informar a duração do curso!") int duracao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Curso curso = (Curso) o;
		return duracao == curso.duracao && Objects.equals(codigo, curso.codigo) && Objects.equals(nome, curso.nome)
				&& Objects.equals(descricao, curso.descricao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nome, descricao, duracao);
	}

	@Override
	public String toString() {
		return "Curso{" + "codigo=" + codigo + ", nome='" + nome + '\'' + ", descricao='" + descricao + '\''
				+ ", duracao=" + duracao + '}';
	}
}
