package P3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class FriendshipGraphTest {
	
	@Test
	public void addVertexTest() {//加顶点测试
			FriendshipGraph  graph= new FriendshipGraph();
			Person A = new Person("A");
			Person B = new Person("B");
			Person C = new Person("C");
			Person D = new Person("D");
			graph.addVertex(A);
			graph.addVertex(B);
			graph.addVertex(C);
			graph.addVertex(D);
			assertTrue(graph.person.contains(A));//判断是否加入
			assertTrue(graph.person.contains(B));
			assertTrue(graph.person.contains(C));
			assertTrue(graph.person.contains(D));
	    }
	
	@Test
	public void addEdgeTest() //加边测试
	{
		FriendshipGraph  graph = new FriendshipGraph();
		Person A = new Person("A");
		Person B = new Person("B");
		Person C = new Person("C");
		Person D = new Person("D");
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		graph.addEdge(A, B);
		graph.addEdge(B, A);
		graph.addEdge(B, C);
		graph.addEdge(C, B);
		graph.addEdge(D, B);
		graph.addEdge(B, D);
		graph.addEdge(A, C);
		graph.addEdge(C, A);
		assertEquals(graph.edge[0][1],1);
		assertEquals(graph.edge[1][0],1);
		assertEquals(graph.edge[1][2],1);
		assertEquals(graph.edge[2][1],1);
		assertEquals(graph.edge[1][3],1);
		assertEquals(graph.edge[3][1],1);
		assertEquals(graph.edge[0][2],1);
		assertEquals(graph.edge[2][0],1);
				
	}

	
 @Test 
	public void getDistanceTest() 
	{
		FriendshipGraph  graph = new FriendshipGraph();
		Person A = new Person("A");
		Person B = new Person("B");
		Person C = new Person("C");
		Person D = new Person("D");
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		graph.addEdge(A, B);
		graph.addEdge(B, A);
		graph.addEdge(B, C);
		graph.addEdge(C, B);
		graph.addEdge(D, B);
		graph.addEdge(B, D);
		graph.addEdge(A, C);
		graph.addEdge(C, A);
		assertEquals("except distance",1,graph.getDistance(A, C));
		assertEquals("except distance",1,graph.getDistance(B, D));
		assertEquals("except distance",1,graph.getDistance(B, C));
		assertEquals("except distance",1,graph.getDistance(A, B));
		
	}


}
