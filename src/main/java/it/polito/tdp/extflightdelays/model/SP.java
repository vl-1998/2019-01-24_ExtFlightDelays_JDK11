package it.polito.tdp.extflightdelays.model;

public class SP {
	private String stato;
	private Double probabilita;
	/**
	 * @param stato
	 * @param probabilita
	 */
	public SP(String stato, Double probabilita) {
		super();
		this.stato = stato;
		this.probabilita = probabilita;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Double getProbabilita() {
		return probabilita;
	}
	public void setProbabilita(Double probabilita) {
		this.probabilita = probabilita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SP other = (SP) obj;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		return true;
	}
	
	

}
