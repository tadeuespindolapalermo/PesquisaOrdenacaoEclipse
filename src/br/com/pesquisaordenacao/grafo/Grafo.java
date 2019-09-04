package br.com.pesquisaordenacao.grafo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Grafo extends JPanel implements MouseListener {

	private static final long serialVersionUID = -4316231587165133912L;

	private int vertexCount;
	private int edgeCount;
	private boolean digraph;
	private boolean adjacencies[][];
	public GraphDetails graphDetails = null;

	private int xCoordV[];
	private int yCoordV[];
	private Color colorNode[];

	private static final int RAIO_NO = 50;	
	private static final int X_OFF = 22;
	private static final int Y_OFF = 27;
	private static final int MAX_COL = 4;
	private int x0 = 70;
	private int y0 = 70;

	private boolean done;
	private int primeiro;
	private int segundo;

	JButton button;
	JButton button2;

	private boolean isCircular;
	
	public Grafo() { }

	public Grafo(int numVertices, boolean flag) {
		
		vertexCount = numVertices;
		edgeCount = 0;
		digraph = flag;
		adjacencies = new boolean[vertexCount][vertexCount];
		xCoordV = new int[vertexCount];
		yCoordV = new int[vertexCount];
		colorNode = new Color[vertexCount];

		graphDetails = new GraphDetails();
		super.addMouseListener(this);

		primeiro = -1;
		segundo = -1;
		isCircular = false;
		done = false;

		button = new JButton("Circular");		
		button.addActionListener(e -> {
			isCircular = true; 
			done = false;
			repaint();
		});

		button2 = new JButton("Retangular");
		button2.addActionListener(e -> {
			isCircular = false; // Retangular
			done = false;
			repaint();
		});
	}

	int numVertices() {
		return vertexCount;
	}

	int numEdges() {
		return edgeCount;
	}

	boolean directed() {
		return digraph;
	}

	

	public void desenhaAresta() {
	}

	public void clearPanel(Graphics g) {
		int xs = 10; // where to start printing on the panel
		int ys = 20;

		g.drawString("Meu Grafo:\n", xs, ys);

		this.removeAll();
		this.updateUI();
		this.add(button);
		this.add(button2);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 500, 500);
		validate();
	}

	public void paintComponent(Graphics g) {

		clearPanel(g);

		if (!done) {

			if (isCircular)
				drawGrafoCircular(g);
			else
				drawGrafoRetangular(g);

			conectEdges(g);
		} else {
			for (int i = 0; i < this.numVertices(); i++) {
				g.setColor(colorNode[i]);
				g.fillOval(xCoordV[i], yCoordV[i], RAIO_NO, RAIO_NO);

				g.setColor(Color.BLUE);
				g.drawString(Integer.toString(i), xCoordV[i] + X_OFF, yCoordV[i] + Y_OFF);
			}
			conectEdges(g);
		}
	}

	public void drawGrafoCircular(Graphics g) {

		x0 = 250;
		y0 = 250;
		
		final int RADIUS = 150;
		double rad;
		double angulo;
		double anguloStep;

		anguloStep = 360 / numVertices();
		angulo = 0.0;

		for (int i = 0; i < numVertices(); i++) {
			rad = (Math.PI / 180) * angulo;

			xCoordV[i] = x0 + (int) (RADIUS * Math.cos(rad));
			yCoordV[i] = y0 + (int) (RADIUS * Math.sin(rad));
			colorNode[i] = Color.WHITE;
			angulo = angulo + anguloStep;
		}
		conectEdges(g);
	}

	void drawGrafoRetangular(Graphics g) {

		x0 = 70;
		y0 = 70;

		int colCount = 0;
		int xCoord = x0;
		int yCoord = y0;

		for (int i = 0; i < this.numVertices(); i++) {

			if (colCount < MAX_COL) {
				xCoordV[i] = xCoord;
				yCoordV[i] = yCoord;
				colorNode[i] = Color.WHITE;

				xCoord = xCoord + 2 * RAIO_NO;
				colCount++;
			} else {
				colCount = 0;
				yCoord = yCoord + 2 * RAIO_NO;
				xCoord = x0;

				xCoordV[i] = xCoord;
				yCoordV[i] = yCoord;
				colorNode[i] = Color.WHITE;

				xCoord = xCoord + 2 * RAIO_NO;
				colCount++;
			}
		}
	}

	public void conectEdges(Graphics g) {
		
		int x0Inter = 0;
		int y0Inter = 0;
		int x1Inter = 0;
		int y1Inter = 0;

		double scale = 0.0d;

		int x0AuxCoord = 0;
		int y0AuxCoord = 0;

		int x1AuxCoord = 0;
		int y1AuxCoord = 0;

		int dx = 0;
		int dy = 0;

		for (int i = 0; i < this.numVertices(); i++) {
			AdjArray a = this.getAdjList(i);

			for (int j = a.beginning(); !a.end(); j = a.next()) {

				x0AuxCoord = xCoordV[i] + X_OFF;
				y0AuxCoord = yCoordV[i] + Y_OFF;

				x1AuxCoord = xCoordV[j] + X_OFF;
				y1AuxCoord = yCoordV[j] + Y_OFF;

				dx = Math.abs(x1AuxCoord - x0AuxCoord);
				dy = Math.abs(y1AuxCoord - y0AuxCoord);

				scale = RAIO_NO / 2 / ((Math.sqrt(dx * dx + dy * dy)));

				if (x0AuxCoord > x1AuxCoord) {
					x0Inter = x0AuxCoord - (int) (scale * dx);
					x1Inter = x1AuxCoord + (int) (scale * dx);
				} else {
					x0Inter = x0AuxCoord + (int) (scale * dx);
					x1Inter = x1AuxCoord - (int) (scale * dx);
				}

				if (y0AuxCoord > y1AuxCoord) {
					y0Inter = y0AuxCoord - (int) (scale * dy);
					y1Inter = y1AuxCoord + (int) (scale * dy);
				} else {
					y0Inter = y0AuxCoord + (int) (scale * dy);
					y1Inter = y1AuxCoord - (int) (scale * dy);
				}

				g.setColor(Color.BLUE);
				g.drawLine(x0Inter, y0Inter, x1Inter, y1Inter);
			}
		}

		for (int i = 0; i < this.numVertices(); i++) {
			g.setColor(colorNode[i]);
			g.fillOval(xCoordV[i], yCoordV[i], RAIO_NO, RAIO_NO);

			g.setColor(Color.BLUE);
			g.drawString(Integer.toString(i), xCoordV[i] + X_OFF, yCoordV[i] + Y_OFF);
		}
		done = true;
	}

	public void insertEdge(Aresta e) {
		
		int v = e.v;
		int w = e.w;

		if (!adjacencies[v][w]) {
			edgeCount++;
			adjacencies[v][w] = true;
		}

		if (!digraph) {
			adjacencies[w][v] = true;
		}
	}

	public void removeEdge(Aresta e) {
		
		int v = e.v;
		int w = e.w;

		if (adjacencies[v][w]) {
			edgeCount--;
			adjacencies[v][w] = false;
		}

		if (!digraph) {
			adjacencies[w][v] = false;
		}
	}

	boolean edgeTest(int v, int w) {
		return adjacencies[v][w];
	}

	public AdjArray getAdjList(int v) {
		return new AdjArray(v);
	}

	class AdjArray {
		
		private int i;
		private int v;

		AdjArray(int v) {
			this.v = v;
			i = -1;
		}

		public int beginning() {
			i = -1;
			return next();
		}

		public int next() {
			for (i++; i < numVertices(); i++) {
				if (edgeTest(v, i))
					return i;
			}
			return -1;
		}

		public boolean end() {
			return i >= numVertices();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		for (int i = 0; i < this.numVertices(); i++) {
			
			//colorNode[i] = Color.GREEN;

			int dX = 0;
			int dY = 0;

			dX = Math.abs(xCoordV[i] - e.getX());
			dY = Math.abs(yCoordV[i] - e.getY());

			if (dX <= RAIO_NO && dY <= RAIO_NO) {
				if ((primeiro == -1) && (segundo == -1)) {
					colorNode[i] = Color.GREEN;
					primeiro = i;
					System.out.println("Primeiro nó Selecionado");
				} else if (segundo == -1) {
					colorNode[i] = Color.RED;
					segundo = i;
					System.out.println("Segundo nó Selecionado");
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

}
