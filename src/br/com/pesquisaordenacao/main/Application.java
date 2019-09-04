package br.com.pesquisaordenacao.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import br.com.pesquisaordenacao.arvore.ArvoreBinaria;
import br.com.pesquisaordenacao.grafo.Aresta;
import br.com.pesquisaordenacao.grafo.Grafo;

public class Application {
	
	private static final String AUTOMATIC = "R";	
	private static final String MANUAL = "M";
	private static Random randomGenerator = new Random();	

	public static void main(String[] args) {
		iniciarArvoreBinaria(AUTOMATIC);
		iniciarGrafo();
	}

	private static void iniciarGrafo() {

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

	private static void iniciarArvoreBinaria(String init) {
		
		ArvoreBinaria tree = new ArvoreBinaria();		
		List<Integer> numeros = Arrays.asList(
				new Integer[]{50,3,81,1,7,85,91,30,10,53});

		if (init.equals(AUTOMATIC))
			inserirRandomizado(tree);
		else if (init.equals(MANUAL))
			inserirManualmente(tree, numeros);

		JFrame jFrameArvore = new JFrame();
		jFrameArvore.add(tree);
		jFrameArvore.setSize(500, 500);
		jFrameArvore.setVisible(true);
	}

	private static void inserirManualmente(ArvoreBinaria tree, List<Integer> numeros) {	
		numeros.forEach(n -> tree.insert(n));
	}

	private static void inserirRandomizado(ArvoreBinaria tree) {			
		for (int idx = 1; idx <= 10; ++idx) {
			int randomInt = randomGenerator.nextInt(100);
			System.out.println("Aleatorios : " + randomInt);
			tree.insert(randomInt);
		}
	}

}
