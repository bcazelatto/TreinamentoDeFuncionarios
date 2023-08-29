package br.com.treinamento.model;

import java.util.Objects;

public class TurmaParticipante {

	private int codigo;
	private int turmaCodigo;
	private int funcionarioCodigo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getTurmaCodigo() {
		return turmaCodigo;
	}

	public void setTurmaCodigo(int turmaCodigo) {
		this.turmaCodigo = turmaCodigo;
	}

	public int getFuncionarioCodigo() {
		return funcionarioCodigo;
	}

	public void setFuncionarioCodigo(int funcionarioCodigo) {
		this.funcionarioCodigo = funcionarioCodigo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TurmaParticipante that = (TurmaParticipante) o;
		return Objects.equals(codigo, that.codigo) && Objects.equals(turmaCodigo, that.turmaCodigo)
				&& Objects.equals(funcionarioCodigo, that.funcionarioCodigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, turmaCodigo, funcionarioCodigo);
	}

	@Override
	public String toString() {
		return "TurmaParticipante{" + "codigo=" + codigo + ", turmaCodigo=" + turmaCodigo + ", funcionarioCodigo="
				+ funcionarioCodigo + '}';
	}

}
