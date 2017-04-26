package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	private UndirectedGraph <String, DefaultEdge> grafo;

	public List<String> createGraph(int numeroLettere) {
		
		grafo = new SimpleGraph <String, DefaultEdge>(DefaultEdge.class);
		
		WordDAO dao = new WordDAO();
		
		List<String> ris = new ArrayList<String>();
		
		ris = dao.getAllWordsFixedLength(numeroLettere);
		
		if(ris.size() > 0){
			for(String s : ris){
				grafo.addVertex(s);
				for(String v : grafo.vertexSet())
					if(!s.equals(v) && controllaMatch(s,v))
						grafo.addEdge(s, v);
			}
		}
		
		else
			ris.add("Nessuna parola trovata della lunghezza indicata");
		
		System.out.println(grafo);

		return ris;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		if(grafo==null || !grafo.containsVertex(parolaInserita))
			return null;
		
		List<String> ris = new ArrayList<String>(Graphs.neighborListOf(grafo, parolaInserita)); //Implementazione Java
		/*WordDAO dao = new WordDAO();															//Implementazione SQL
		List<String> ris = dao.getAllSimilarWords(parolaInserita, parolaInserita.length());*/

		return ris;
	}

	public String findMaxDegree() {
		
		if(grafo==null || grafo.vertexSet().size()==0)
			return "Attenzione: grafo vuoto.";
		
		if(grafo.edgeSet().size()==0)
			return "Attenzione: nessuna relazione presente.";
		
		int gradoMax = 0;
		String vertex = "";
		
		int grado = 0;
		
		for(String s : grafo.vertexSet()){
			grado = grafo.degreeOf(s);
			if( grado > gradoMax){
				gradoMax = grado;
				vertex = s;
			}
		}
			
		String ris = "";
		
		ris += "Grado massimo = " + gradoMax + "\n";
		ris += "Vertice: " + vertex + "\n";
		
		for(String s : Graphs.neighborListOf(grafo, vertex))
			ris += s + "\n";

		return ris.trim();
	}
	
	private boolean controllaMatch (String parola1, String parola2){
		
		int contDiff = 0;
		
		for(int i = 0; i <parola1.length() ; i++)
			if(parola1.charAt(i) != parola2.charAt(i))
				contDiff++;
		
		if(contDiff == 1)
			return true;
		else
			return false;
	}

	public void reset() {
		grafo = null;
		
	}
}
