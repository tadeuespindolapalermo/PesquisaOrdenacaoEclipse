package br.com.pesquisaordenacao.grafo;

import br.com.pesquisaordenacao.grafo.Grafo.AdjArray;

public class GraphDetails {
	
	public void display(Grafo g) {
		
		System.out.println("Graph details");

		for (int i = 0; i < g.numVertices(); i++) {
			System.out.print(i + ": ");
			AdjArray a = g.getAdjList(i);

			for (int j = a.beginning(); !a.end(); j = a.next())
				System.out.print(j + " ");

			System.out.println("");
		}
	}

}
