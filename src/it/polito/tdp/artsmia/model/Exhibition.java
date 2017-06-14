package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.Set;

public class Exhibition implements Comparable<Exhibition>{
	
	private int id;
	private String dept;
	private String title;
	private int begin;
	private int end;
	private Set<Integer> idOggetti;
	
	public Exhibition(int id, String dept, String title, int begin, int end) {
		super();
		this.id = id;
		this.dept = dept;
		this.title = title;
		this.begin = begin;
		this.end = end;
		this.idOggetti = new HashSet<Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Exhibition other = (Exhibition) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exhibition [id=" + id + ", dept=" + dept + ", title=" + title + ", begin=" + begin + ", end=" + end
				+ "]";
	}
	

	public Set<Integer> getIdOggetti() {
		return idOggetti;
	}

	public void addOggetto(Integer id) {
		this.idOggetti.add(id);
	}

	@Override
	public int compareTo(Exhibition arg0) {
		return  - (this.idOggetti.size() - arg0.getIdOggetti().size());
	}
	
	

}
