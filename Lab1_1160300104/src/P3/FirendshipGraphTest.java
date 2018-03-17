package P3;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class FriendshipGraphTest {

	
	private Throwable e;

	void test() throws same {
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
		
		assertEquals(graph.getDistance(rachel, ross), 1);
		assertEquals(graph.getDistance(rachel, ben), 2);
		assertEquals(graph.getDistance(rachel, rachel), 0);
		assertEquals(graph.getDistance(rachel, kramer), -1);
	}

}

