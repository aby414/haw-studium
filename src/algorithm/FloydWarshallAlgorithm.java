package algorithm;

import java.util.Arrays;
import java.util.Set;

import org.jgrapht.Graph;

import implementation.LabeledEdge;
import implementation.Vertex;

public class FloydWarshallAlgorithm {

	private Graph<Vertex, LabeledEdge> g;
	private Set<Vertex> allVertices;
	private Vertex[] vertexArray;
	private Set<LabeledEdge> allEdges;
	double[][] distanceMatrix ;
	double[][] transitMatrix ;
	private double minLength = 0.0;

	public FloydWarshallAlgorithm(Graph<Vertex, LabeledEdge> g) {
		this.g = g;
		this.allVertices = g.vertexSet();
		this.allEdges = g.edgeSet();
		this.distanceMatrix = new double[allVertices.size()][allVertices.size()];
		this.transitMatrix = new double[allVertices.size()][allVertices.size()];
		initDistanceMatrix();
		intiTransitMatrix();
	}


	public void findShortestPath(Vertex startVertex, Vertex endVertex) throws Exception {
		// Vorbedingung
		if (!this.g.containsVertex(startVertex) || !(this.g.containsVertex(endVertex))) {
			throw new Exception("Bitte geben Sie gültige Werte an!");
		}

		double currentValue = 0.0;
		for(int j = 0 ; j < this.allVertices.size(); j++){
			for(int i = 0; i< this.allVertices.size(); i++){
				for(int k= 0; k < this.allVertices.size(); k++){
					if(i != j && k != j){
						currentValue = this.distanceMatrix[i][k];
						this.distanceMatrix[i][k] = Math.min(this.distanceMatrix[i][k], this.distanceMatrix[i][j] + this.distanceMatrix[j][k]);
						if(Double.compare(currentValue, this.distanceMatrix[i][k]) != 1){
							this.transitMatrix[i][k] = j;
						}
					}			
				}
				if(this.distanceMatrix[i][i] < 0){
					break;
				}
			}
			
		}
		
		this.minLength = this.distanceMatrix[Arrays.asList(this.vertexArray).indexOf(startVertex)][Arrays.asList(this.vertexArray).indexOf(endVertex)];
		
		
		
//		for(int i = 0; i < this.distanceMatrix.length; i++){
//			System.out.print(this.vertexArray[i].getName() + "   ");
//			for(int j = 0; j < this.distanceMatrix.length; j++){
//				System.out.printf("%3.5f    ", this.distanceMatrix[i][j]);
//			}
//			System.out.println();
//		}
		
	}
	
	public void initDistanceMatrix(){

		this.vertexArray = this.allVertices.toArray(new Vertex[this.allVertices.size()]);
		
		for(int i = 0; i < this.distanceMatrix.length; i++){
			for(int j = 0; j < this.distanceMatrix.length; j++){
				if(i == j){
					this.distanceMatrix[i][j] = 0.0;
				}else if(containsEdge(vertexArray[i], vertexArray[j]) != null){
					this.distanceMatrix[i][j] = containsEdge(vertexArray[i], vertexArray[j]).getEdgeWeight();
				}else{
					this.distanceMatrix[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		System.out.print("        ");
		for(Vertex v : vertexArray){
			
			System.out.print(v.getName() + "         ");
		}
		System.out.println();
	}
	
	private void intiTransitMatrix() {
		for(int i = 0; i < this.transitMatrix.length; i++){
			for(int j = 0; j < this.transitMatrix.length; j++){
				if(i == j){
					 this.transitMatrix[i][j] = 0.0;
				}else{
					 this.transitMatrix[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
	}
	
	public LabeledEdge containsEdge(Vertex s, Vertex t){
		for(LabeledEdge e: this.allEdges){
			if((e.getS().equals(s) && e.getT().equals(t)) || (e.getS().equals(t) && e.getT().equals(s)) ){
				return e;
			}
		}
		return null;
	}

	public double getMinLength(){
		return this.minLength;
	}
	
}











