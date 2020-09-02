package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event> {
	public enum EventType{
		PARTENZA,
		ARRIVO
	}
	
	private EventType type;
	private Integer giorno;
	private String stato;
	private Integer nTuristi;
	/**
	 * @param type
	 * @param giorno
	 * @param stato
	 * @param nTuristi
	 */
	public Event(EventType type, Integer giorno, String stato, Integer nTuristi) {
		super();
		this.type = type;
		this.giorno = giorno;
		this.stato = stato;
		this.nTuristi = nTuristi;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public Integer getGiorno() {
		return giorno;
	}
	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Integer getnTuristi() {
		return nTuristi;
	}
	public void setnTuristi(Integer nTuristi) {
		this.nTuristi = nTuristi;
	}
	@Override
	public String toString() {
		return "Event [type=" + type + ", giorno=" + giorno + ", stato=" + stato + ", nTuristi=" + nTuristi + "]";
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.getGiorno().compareTo(o.getGiorno());
	}
	
	

}
