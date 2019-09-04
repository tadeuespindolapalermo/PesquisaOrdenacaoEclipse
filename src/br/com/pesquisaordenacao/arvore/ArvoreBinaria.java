package br.com.pesquisaordenacao.arvore;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ArvoreBinaria extends JPanel {	
	
	private static final long serialVersionUID = -2012742720460574356L;	
	
	private transient Node root;
	private static final int X = 200;
	private static final int Y = 30;
	private static final int X_OFFSET_CENT = 17;
	private static final int Y_OFFSET_CENT = 27;
	private static final int RAIO_NO = 50;	
	
    public ArvoreBinaria() { 
        root = null; 
    }
    
    // This method mainly calls insertRec()
    public void insert(int key) {
       root = insertRec(root, key);
    }
     
    /* A recursive function to insert a new key in BST */
    private Node insertRec(Node root, int key) {
 
        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }
 
        // Otherwise, recur down the tree
        // This method mainly calls insertRec()
        if (key < root.key)
            root.right = insertRec(root.right, key);
        else if (key > root.key)
            root.left = insertRec(root.left, key);        
        
        return root;
    }
	
	@Override
    public void paintComponent(Graphics g) {
		int xs = 10;   //where to start printing on the panel
	    int ys = 20;
	    g.drawString("Minha Árvode Binária:\n", xs, ys);   	    
	    drawTree(root, X, Y, g);        
        revalidate();
	}
	
	private void drawTree(Node root, int xNode, int yNode, Graphics g ) {
		
		if (root != null) {			
			g.drawOval(xNode, yNode, RAIO_NO, RAIO_NO);
			g.drawString(Integer.toString(root.key), (xNode + X_OFFSET_CENT), (yNode + Y_OFFSET_CENT));
			
			drawTree(root.right, xNode - RAIO_NO, yNode + RAIO_NO, g);
			drawTree(root.left, xNode + RAIO_NO, yNode + RAIO_NO, g);
		}				
	}
	
}