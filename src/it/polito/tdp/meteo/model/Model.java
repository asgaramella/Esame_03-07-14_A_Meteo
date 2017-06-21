package it.polito.tdp.meteo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;

import it.polito.tdp.meteo.bean.Situazione;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {
	
	private MeteoDAO dao;
	private Pseudograph<Integer,DefaultEdge> graph;
	private List<Situazione> situazioni;
	
	

	public Model() {
		super();
		dao=new MeteoDAO();
	
	}
	
	public List<Situazione> getAllSituaTo(){
		if(situazioni==null)
			situazioni=dao.getAllSituazioni();
		
		return situazioni;
	}
	
	public List<Stat> getDateTmedia(int tMedia){
		List<Stat> stats=new ArrayList<>();
		List<Situazione> giorni= dao.getSituaToperTempMedia(tMedia);
		for(Situazione stemp:giorni){
			LocalDate precedente=stemp.getData().minusDays(1);
			int tempPrec=dao.getTempGiornoPrima(precedente);
			if(tempPrec!=400){
				stats.add(new Stat(stemp.getData(),stemp.getTMedia(),precedente,tempPrec));
			}else
				stats.add(new Stat(stemp.getData(),stemp.getTMedia()));
		}
		
		Collections.sort(stats);
		
		return stats;
		
	}
	
	public void creaGrafo(){
		graph=new Pseudograph<Integer,DefaultEdge>(DefaultEdge.class);
		 
		Graphs.addAllVertices(graph, dao.getAllMedieTemp());
		
		int counter=1;
		for(Situazione stemp: this.getAllSituaTo()){
			if(counter!=this.getAllSituaTo().size()){
			Situazione succ=this.getAllSituaTo().get(counter);
			//controllo se arco esiste
			DefaultEdge e=graph.getEdge(stemp.getTMedia(),succ.getTMedia());
			if(e==null){
				graph.addEdge(stemp.getTMedia(),succ.getTMedia());
			}
			counter++;
			}
		}
		
		
	}
	
	
	public Set<Integer> getAllTempPos(int temp){
		this.creaGrafo();
		
		Set<Integer> temperature=new HashSet<Integer>();
		temperature.addAll(Graphs.neighborListOf(graph, temp));
		
		for(Integer i:Graphs.neighborListOf(graph, temp)){
			temperature.addAll(Graphs.neighborListOf(graph,i));
		}
		
		
		
		return temperature;
	}

}
