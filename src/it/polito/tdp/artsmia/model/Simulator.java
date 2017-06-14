package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.artsmia.model.Event.EventType;

public class Simulator {
	
	private PriorityQueue<Event> queue;
	private DirectedGraph<Exhibition, DefaultEdge> graph;
	private List<Studente> studenti;
	
	public Simulator(DirectedGraph<Exhibition, DefaultEdge> graph){
		this.queue = new PriorityQueue<Event>();
		this.graph = graph;
		studenti = new ArrayList<Studente>();
	}
	
	public void load(int anno, int N){
		int rand = (int)Math.random() * graph.vertexSet().size();
		
		Exhibition prima = null;
		
		int cont = 0;
		
		for(Exhibition e : graph.vertexSet())
			if(cont == rand){
				prima = e;
				break;
			}
			else
				cont++;
		for(int i = 0; i < N; i++){
			Studente s = new Studente();
			studenti.add(s);
			queue.add(new Event(EventType.VISITA,prima,s));
		}
				
	}
	
	public void run(){
		
		while(!queue.isEmpty()){
			Event e = queue.poll();
			
			e.getS().addOpereViste(e.getE().getIdOggetti());
			
			//int rand = (int)Math.random() * graph.vertexSet().size();
			
			Random r = new Random();
			
			int rand = r.nextInt(graph.vertexSet().size());
			
			Exhibition prossima = null;
			
			int cont = 0;
			
			for(DefaultEdge de : graph.outgoingEdgesOf(e.getE()))
				if(cont == rand){
					prossima = graph.getEdgeTarget(de);
					break;
				}
				else
					cont++;
			
			if(prossima!=null)
				queue.add(new Event(EventType.VISITA,prossima,e.getS()));
				
		}
		
	}
	
	public String getResults(){
		
		String ris="";
		
		Collections.sort(studenti);
		
		for(Studente s : studenti)
			ris += s.getOpereViste().size() + "\n";
		
		return ris;
	}

}
