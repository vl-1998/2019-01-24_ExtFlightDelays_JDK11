package it.polito.tdp.extflightdelays.model;

public class VelivoliAdiacenti implements Comparable<VelivoliAdiacenti>{
	private String s;
	private Integer peso;
	/**
	 * @param s
	 * @param peso
	 */
	public VelivoliAdiacenti(String s, Integer peso) {
		super();
		this.s = s;
		this.peso = peso;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Stato:" + s + " (" + peso + ")";
	}
	@Override
	public int compareTo(VelivoliAdiacenti o) {
		// TODO Auto-generated method stub
		return -this.getPeso().compareTo(o.getPeso());
	}
	
	

}
