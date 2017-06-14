package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.Set;

public class Studente implements Comparable<Studente>{
	
	private Set<Integer> opereViste;

	public Studente() {
		this.opereViste = new HashSet<Integer>();
	}

	public Set<Integer> getOpereViste() {
		return opereViste;
	}

	public void addOpereViste(Set<Integer> opereViste) {
		this.opereViste.addAll(opereViste);
	}

	@Override
	public int compareTo(Studente o) {
		
		return - (this.opereViste.size() - o.opereViste.size());
	}
	
	

}
