package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Event.EventType;

public class Simulator {
	//MODELLO DEL MONDO
	private Graph<String, DefaultWeightedEdge> grafo;
	private Model model;
	private Integer giorno;
	
	public Simulator(Graph<String, DefaultWeightedEdge> grafo, Model model) {
		this.grafo = grafo;
		this.model = model;
	}
	
	//PARAMTERI DI SIMULAZIONE
	private Integer T = 10;
	private Integer G = 10;
	private Double probabilita;

	public void setT(Integer t) {
		T = t;
	}
	public void setG(Integer g) {
		G = g;
	}
	
	//RISULTATI CALCOLATI
	private Map<String, Integer> turisti_Stato;

	public Map<String, Integer> getTuristi_Stato() {
		return turisti_Stato;
	}
	
	//CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	//INIT
	public void init(Integer T, Integer G, String stato) {
		this.T = T;
		this.G = G;
		this.giorno = 0;
		this.probabilita = 0.0;
		this.queue = new PriorityQueue<>();
		this.turisti_Stato = new HashMap<>();
		
		//Metto tutti i turisti nello stato selezionato 
		turisti_Stato.put(stato, T);
		
		for(String s : this.grafo.vertexSet()) {
			turisti_Stato.put(s, 0);
		}
		
		turisti_Stato.remove(stato);
		turisti_Stato.put(stato, T);
		
		//genero la coda
		for(int i = 0; i<= T; i++) {
			Event e = new Event (EventType.PARTENZA, giorno+1, stato, 1);
			queue.add(e);
		}
	}
	
	//RUN
	public void run() {
		while(!this.queue.isEmpty()) {
			//estraggo l'evento
			Event e = this.queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}
	
	private void processEvent(Event e) {
			switch(e.getType()){
			
			case PARTENZA:
				if(e.getGiorno()==G) {
					break;
				}
				
				this.probabilita = Math.random();
				SP calcolato = this.calcoloProb(e.getStato());
				
				if(calcolato.getProbabilita()>probabilita) {
					//Tolgo un turista dal vecchio stato e lo metto nel nuovo
					
					//RIVEDI QUESTA PARTE
					turisti_Stato.remove(e.getStato());
					turisti_Stato.put(e.getStato(), e.getnTuristi()-1);
					Integer vecchio = turisti_Stato.get(calcolato.getStato());
					turisti_Stato.remove(calcolato.getStato());
					turisti_Stato.put(calcolato.getStato(), vecchio+1);
					
					Event e1 = new Event(EventType.ARRIVO, e.getGiorno(), calcolato.getStato(), 1);
					queue.add(e1);	
				} else {
					break;
				}
				
			break;
			
			case ARRIVO:
				//schedulo le partenze per il giorno dopo
				Event e1 = new Event(EventType.PARTENZA, e.getGiorno()+1, e.getStato(), e.getnTuristi());
				queue.add(e1);
				break;

			}
	}
			
	private SP calcoloProb(String stato) {
		List<String> uscenti = Graphs.successorListOf(this.grafo, stato);
		Double best = 0.0;
		Integer sommaArchi = 0;
		SP result= null;
		
		for(String s : uscenti) {
			sommaArchi = (int) (sommaArchi + this.grafo.getEdgeWeight(this.grafo.getEdge(stato, s)));
		}
		
		for(String s : uscenti) {
			Double pTemp = 0.0;
			pTemp = this.grafo.getEdgeWeight(this.grafo.getEdge(stato, s))/sommaArchi;
			if(pTemp>best) {
				best = pTemp;
				result = new SP (s, best);
			}
		}
		
		
		return result;
	}
}

