package br.com.treinamento.model;

import java.sql.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Turma {

	private Long codigo;
	
	@NotNull(message = "É necessário informar uma data de início!")
	private Date inicio;
	
	@NotNull(message = "É necessário informar uma data de fim!")
	private Date fim;
	
	@NotBlank(message = "É necessário informar um local!")
	private String local;
	
	@NotNull(message = "É necessário informar uma código de curso!")
	private int cursoCodigo;

	public Turma() {
	}

	public Turma(Date inicio, Date fim, String local, int cursoCodigo) {
		this.inicio = inicio;
		this.fim = fim;
		this.local = local;
		this.cursoCodigo = cursoCodigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getCursoCodigo() {
		return cursoCodigo;
	}

	public void setCursoCodigo(int cursoCodigo) {
		this.cursoCodigo = cursoCodigo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Turma turma = (Turma) o;
		return Objects.equals(codigo, turma.codigo) && Objects.equals(inicio, turma.inicio)
				&& Objects.equals(fim, turma.fim) && Objects.equals(local, turma.local)
				&& Objects.equals(cursoCodigo, turma.cursoCodigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, inicio, fim, local, cursoCodigo);
	}

	@Override
	public String toString() {
		return "Turma{" + "codigo=" + codigo + ", inicio=" + inicio + ", fim=" + fim + ", local='" + local + '\''
				+ ", cursoCodigo=" + cursoCodigo + '}';
	}
}
