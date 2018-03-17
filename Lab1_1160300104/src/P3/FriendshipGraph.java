package P3;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class FriendshipGraph{
	private int n = 100;
	private Map<String, Integer> map;
	private boolean is = false;
	private int nVerts = 0;
	private Vertex dfs[];
	private Vertex bfs[];
	@SuppressWarnings("rawtypes")
	public Queue queue;
	private Vertex vertexList[];
	public FriendshipGraph(){
        map = new TreeMap<String, Integer>();
        dfs = new Vertex[n];
        bfs = new Vertex[n];
    }
    public  FriendshipGraph(int n){
        map = new TreeMap<String, Integer>();
        dfs = new Vertex[n];
        bfs = new Vertex[n];
        
    }
    public FriendshipGraph(int n, boolean is){
        this.is = is;
        map = new TreeMap<String, Integer>();
        dfs = new Vertex[n];
        bfs = new Vertex[n];
    }
    public boolean isIs() {
        return is;
    }
    public void setIs(boolean is) {
        this.is = is;
    }
    public Map<String, Integer> getVertexList() {
        return map;
    }
    public Vertex[] getDfs() {
        return dfs;
    }
    public Vertex[] getBfs() {
        return bfs;
    }
	@SuppressWarnings("static-access")
	public void addVertex(Person rachel){
        rachel.setIndex(nVerts);
        map.put((String) rachel.name,n);
        nVerts++;
    }
    @SuppressWarnings("static-access")
	public void addEdge(Person rachel, Person ross){
		int Rachel = map.get(rachel.name);
		int Ross = map.get(ross.name);
        vertexList[Rachel].addAdj(vertexList[Ross]);
        if (!is) {vertexList[Ross].addAdj(vertexList[Rachel]);}
    }
	public int getDistance(Person rachel, Person kramer) {
		return vertexList.length;
	}
	@SuppressWarnings("unchecked")
	public void bfs() {
        vertexList[0].isVisted = true;
        bfs[0] = vertexList[0];
        queue.offer(vertexList[0]);
        int bfsIndex = 0;
        Vertex vertex;
        while(!queue.isEmpty()){
            Vertex vertex2 = (Vertex)queue.remove();
            while((vertex = getAdjVertex(vertex2))!=null){
                vertex.isVisted = true;
                bfs[++bfsIndex] = vertex;
                queue.offer(vertex);
            }
        }
        for (int i = 0; i < getVertsCount(); i++) {
            vertexList[i].isVisted = false;
        }
    }
	private int getVertsCount() {
		return 0;
	}
	public Vertex getAdjVertex(Vertex vertex){    
        ArrayList<Vertex> adjVertexs = vertex.getAdj();
        for (int i = 0; i < adjVertexs.size(); i++) {
            if(!adjVertexs.get(i).isVisted){
                return adjVertexs.get(i);
            }
        }
        return null;
    }
	public static void main(String[] args) throws same {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		// should print 1
		System.out.println(graph.getDistance(rachel, ben));
		// should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		// should print -1
	}
}
