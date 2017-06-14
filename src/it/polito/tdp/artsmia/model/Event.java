package it.polito.tdp.artsmia.model;

public class Event implements Comparable<Event>{
	
	public enum EventType {VISITA};
	
	private EventType type;
	private Exhibition e;
	private Studente s;	

	public Event(EventType type, Exhibition e, Studente s) {
		super();
		this.type = type;
		this.e = e;
		this.s = s;
	}

	


	public EventType getType() {
		return type;
	}




	public void setType(EventType type) {
		this.type = type;
	}




	public Exhibition getE() {
		return e;
	}




	public void setE(Exhibition e) {
		this.e = e;
	}




	public Studente getS() {
		return s;
	}




	public void setS(Studente s) {
		this.s = s;
	}




	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return e.getBegin() - o.getE().getBegin();
	}
	
	

}
