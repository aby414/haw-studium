package implementation;

import org.jgrapht.graph.DefaultEdge;


public class LabeledEdge extends DefaultEdge {
	private Vertex s;
	private Vertex t;
	private String label;
	private double weight;
	
	/**
	 * Konstruktor
	 * @param s
	 * @param t
	 * @param label
	 */
	public LabeledEdge(Vertex s, Vertex t, String label) {
		this.s = s;
		this.t = t;
		this.label = label;
	}
	
	/**
	 * Konstruktor
	 * @param s
	 * @param t
	 */
	public LabeledEdge(Vertex s, Vertex t) {
		this.s = s;
		this.t = t;
	
	}
	
	/**
	 * Konstruktor
	 * @param s
	 * @param t
	 * @param label
	 * @param weight
	 */
	public LabeledEdge(Vertex s, Vertex t, String label, double weight) {
		this.s = s;
		this.t = t;
		this.label = label;
		this.weight = weight;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public double getEdgeWeight(){
		return this.weight;
	}
	
	

	public Vertex getS() {
		return s;
	}

	public Vertex getT() {
		return t;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabeledEdge other = (LabeledEdge) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	
	
	

}
