package interfaces;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graph;

import implementation.LabeledEdge;
import implementation.Vertex;

public abstract class GraphBuilder {
	protected Graph<Vertex,LabeledEdge> g;
	protected BufferedReader bReader;
	
	/**
	 * Konstruktor
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public GraphBuilder(String filename) throws FileNotFoundException {
		this.bReader = new BufferedReader(new FileReader(filename));
	}

	/**
	 * Einlesen der Datei und das Konstruieren des Graphen.
	 * @return g
	 */
	public Graph<Vertex,LabeledEdge> buildGraph() {
		String currentLine;
		Pattern pattern = Pattern.compile("(\\w+)(->|;|--)(\\w+)?(\\()?(\\w+)?(\\))?;?");

		try {
			while ((currentLine = this.bReader.readLine()) != null) {
				if (!currentLine.equals("")) {
					currentLine = currentLine.replace(" ", "");
					Matcher matcher = pattern.matcher(currentLine);
					if (matcher.find()) {
						if (!(matcher.group(3) == null) && (matcher.group(4) == null)) { // 1->2;
							Vertex s = new Vertex(matcher.group(1));
							Vertex t = new Vertex(matcher.group(3));
							g.addVertex(s);
							g.addVertex(t);
							g.addEdge(s, t, new LabeledEdge(s, t));
						} else if (!(matcher.group(4) == null)) { // 1->2(a);
							Vertex s = new Vertex(matcher.group(1));
							Vertex t = new Vertex(matcher.group(3));
							g.addVertex(s);
							g.addVertex(t);
							g.addEdge(s, t, new LabeledEdge(s, t, matcher.group(5)));
						} else if ((matcher.group(3) == null)) {
							Vertex s = new Vertex(matcher.group(1));
							g.addVertex(s);
						}
					}
				}
			}
			this.bReader.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}

		return g;
	}
}
