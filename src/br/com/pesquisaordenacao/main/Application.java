package br.com.pesquisaordenacao.main;

import java.util.Random;

import javax.swing.JFrame;

import br.com.pesquisaordenacao.arvore.ArvoreBinaria;
import br.com.pesquisaordenacao.grafo.Aresta;
import br.com.pesquisaordenacao.grafo.Grafo;

public class Application {	
	
	public static void main(String[] args) 	{		
		mainArvoreBinaria();
		//mainGrafo();
	}
	
	private static void mainGrafo() {
		
		Grafo myGraph = new Grafo(8, false);
		myGraph.insertEdge(new Aresta(0, 5));
		myGraph.insertEdge(new Aresta(5, 2));
		myGraph.insertEdge(new Aresta(2, 6));
		myGraph.insertEdge(new Aresta(6, 3));
		myGraph.insertEdge(new Aresta(6, 7));
		myGraph.insertEdge(new Aresta(0, 7));	
				
		myGraph.graphDetails.display(myGraph);
		
		JFrame jFrameGrafo = new JFrame();
		jFrameGrafo.add(myGraph);
		jFrameGrafo.setSize(500, 500);
		jFrameGrafo.setVisible(true);

		myGraph.removeEdge(new Aresta(0, 1));

		myGraph.graphDetails.display(myGraph);
	}
	
	private static void mainArvoreBinaria() {
		
		ArvoreBinaria tree = new ArvoreBinaria();
		
		tree.insert(50);
		tree.insert(3);
		tree.insert(81);
		tree.insert(1);
		tree.insert(7);
		tree.insert(85);
		tree.insert(91);
		tree.insert(30);
		tree.insert(10);
		tree.insert(53);
		
		/*Random randomGenerator = new Random();
		
        for (int idx = 1; idx <= 10; ++idx) {
          int randomInt = randomGenerator.nextInt(100);
          System.out.println("Aleatorios : " + randomInt);
          tree.insert(randomInt);          
        }	*/	
		        
		JFrame jFrameArvore = new JFrame();
		jFrameArvore.add(tree);
		jFrameArvore.setSize(500, 500);
		jFrameArvore.setVisible(true);
	}

}
