package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private ExtFlightDelaysDAO dao;
	private Graph <String, DefaultWeightedEdge> grafo;
	
	public Model () {
		this.dao = new ExtFlightDelaysDAO ();
	}
	
	public void creaGrafo() {
		this.grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertex());
		
		for(Adiacenza a : this.dao.getEdges()) {
			if(this.grafo.vertexSet().contains(a.getS1()) && this.grafo.vertexSet().contains(a.getS2())) {
				Graphs.addEdgeWithVertices(this.grafo, a.getS1(), a.getS2(), a.getPeso());
			}
		}
	}

	public List<String> getVertex(){
		List<String> vertici = new ArrayList<>(this.grafo.vertexSet());
		return vertici;
	}
		
	public List<DefaultWeightedEdge> getEdge(){
		List<DefaultWeightedEdge> archi = new ArrayList<>(this.grafo.edgeSet());
		return archi;
	}
	
	public List<VelivoliAdiacenti> getVelivoli (String stato){
		List<String> vicini = Graphs.successorListOf(this.grafo, stato);
		List<VelivoliAdiacenti> result = new ArrayList <>();
		for (String s: vicini) {
			VelivoliAdiacenti pTemp = new VelivoliAdiacenti(s,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(stato, s)));
			result.add(pTemp);
		}
		
		Collections.sort(result);
		return result;

	}
	
	public Map<String, Integer> simula(Integer T, Integer G, String stato){
		Simulator sim = new Simulator(this.grafo,this);
		sim.init(T, G, stato);
		sim.run();
		
		return sim.getTuristi_Stato();
	}

}
