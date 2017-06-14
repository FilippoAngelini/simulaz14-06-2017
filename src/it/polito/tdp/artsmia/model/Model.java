package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	ArtsmiaDAO dao;
	List<Integer> anni;
	
	List<Exhibition> mostre;
	
	DirectedGraph<Exhibition, DefaultEdge> graph;
	
	public Model(){
		dao = new ArtsmiaDAO();
	}
	
	public List<Integer> getAllAnni(){
		if(this.anni == null)
			anni = dao.listAnni();
		return anni;
	}
	
	private List<Exhibition> getMostre(Integer annoInizio){
		mostre = dao.getMostreDaAnno(annoInizio);
		return mostre;
	}
	
	public void creaGrafo(Integer annoInizio){
		this.graph = new SimpleDirectedGraph<Exhibition, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(graph, this.getMostre(annoInizio));
		
		for(ExhibitionPair ep : dao.listAllCoppieExhibition(this.mostre, annoInizio))
			graph.addEdge(ep.getE1(), ep.getE2());		
		
	}
	
	public String getStats(){
		
		String ris="";
		
		ConnectivityInspector<Exhibition,DefaultEdge> ci = new ConnectivityInspector<>(graph);
		
		boolean connesso = ci.isGraphConnected();
		
		if(connesso)
			ris+="Il grafo è connesso\n";
		else
			ris+="Il grafo non è connesso\n";
		
		dao.setOggetti(this.mostre);
		
		List<Exhibition> temp = new ArrayList<Exhibition>(graph.vertexSet());
		
		Collections.sort(temp);
		
		ris += "La mostra con più oggetti esposti è: " + temp.get(0).toString() + " con " + temp.get(0).getIdOggetti().size() + " oggetti.";
		
		return ris;
		
	}
	
	public String getClassificaOpere(int anno){
		
		Simulator sim = new Simulator(this.graph);
		
		sim.load(anno, 5);
		
		sim.run();
		
		return sim.getResults();
		
	}

}
