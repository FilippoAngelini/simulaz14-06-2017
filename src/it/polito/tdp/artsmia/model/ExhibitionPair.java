package it.polito.tdp.artsmia.model;

public class ExhibitionPair {
	
	Exhibition e1;
	Exhibition e2;
	public ExhibitionPair(Exhibition e1, Exhibition e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	public Exhibition getE1() {
		return e1;
	}
	public void setE1(Exhibition e1) {
		this.e1 = e1;
	}
	public Exhibition getE2() {
		return e2;
	}
	public void setE2(Exhibition e2) {
		this.e2 = e2;
	}
	
	

}
