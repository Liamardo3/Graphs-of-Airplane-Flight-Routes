//Graph.java

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Graph
{
    private HashMap<Vertex, ArrayList<Edge>> fromEdges;
    private HashMap<Vertex, ArrayList<Edge>> toEdges;

    //Default constructor
    public Graph()
    {
        fromEdges = new HashMap<Vertex, ArrayList<Edge>>();
        toEdges = new HashMap<Vertex, ArrayList<Edge>>();
    }

    //=====Methods to add vertices and directed or undirected edges
    //Add vertex with a given label
    public Vertex addVertex(String newVertexLabel)
    {
        //Create the new Vertex object
        Vertex newVertex = new Vertex(newVertexLabel);

        //Every vertex must exist as a key in both maps
        fromEdges.put(newVertex, new ArrayList<Edge>());
        toEdges.put(newVertex, new ArrayList<Edge>());

        return newVertex;
    }

    //Add edge directed from one vertex to another,
    //with specified weight
    public Edge addDirectedEdge(Vertex fromVertex, Vertex toVertex,
                                double weight)
    {
        //Don't add the same edge twice
        if (hasEdge(fromVertex, toVertex))
        {
            return null;
        }

        //Create the Edge object
        Edge newEdge = new Edge(fromVertex, toVertex, weight);

        //Add the edge to the appropriate list in both maps
        fromEdges.get(fromVertex).add(newEdge);
        toEdges.get(toVertex).add(newEdge);

        return newEdge;
    }

    //Add undirected edge between two vertices,
    //with specified weight
    public Edge[] addUndirectedEdge(Vertex vertexA, Vertex vertexB,
                                    double weight)
    {
        Edge edge1 = addDirectedEdge(vertexA, vertexB, weight);
        Edge edge2 = addDirectedEdge(vertexB, vertexA, weight);
        Edge[] result = { edge1, edge2 };
        return result;
    }

    //Get all edges in the graph leaving a given vertex
    public Collection<Edge> getEdgesFrom(Vertex fromVertex)
    {
        return fromEdges.get(fromVertex);
    }

    //Get all edges in the graph entering a given vertex
    public Collection<Edge> getEdgesTo(Vertex toVertex)
    {
        return toEdges.get(toVertex);
    }

    //Returns a vertex with a matching label,
    //or null if no such vertex exists
    public Vertex getVertex(String vertexLabel)
    {
        //Search the collection of vertices
        //for a vertex with a matching label
        for (Vertex vertex : getVertices())
        {
            if (vertex.label.equals(vertexLabel))
            {
                return vertex;
            }
        }
        return null;
    }

    //=====Methods to return collections of vertices
    //Get all of this graph's vertices
    public Collection<Vertex> getVertices()
    {
        return fromEdges.keySet();
    }

    //Check if this graph has an edge from
    //one particular vertex to another
    public boolean hasEdge(Vertex fromVertex, Vertex toVertex)
    {
        if (!fromEdges.containsKey(fromVertex))
        {
            //fromVertex is not in this graph
            return false;
        }

        //Search the list of edges for an edge that goes to toVertex
        ArrayList<Edge> edges = fromEdges.get(fromVertex);
        for (Edge edge : edges)
        {
            if (edge.toVertex == toVertex)
            {
                return true;
            }
        }

        return false;
    }
}

