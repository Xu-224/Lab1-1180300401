package P3;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendshipGraph {
	
	public ArrayList<Person> person ;  //保存人名  
	public int edge[][];//邻接矩阵
	private HashMap<Person,Integer> relation; 
	boolean X[] ;//最短路径标记
	
	public FriendshipGraph()
	{
		this.person=new ArrayList<Person>();
		this.edge=new int[10000][10000];//邻接矩阵
		for(int i=0; i<10000; i++)
		{
			for(int j=0; j<10000; j++)
			{
				edge[i][j] = 1000;
			}
		}
		this.relation=new HashMap<Person,Integer>(); 
		this.X=new boolean[10000];
	}

    
	
	public void  addVertex(Person name) {//加入顶点
		for(Person P:person)
		{
			if(P.Getname() == name.Getname())//如果名字重复，则输出异常
			{
				System.err.println("There are same names!");
				System.exit(0);
			}
		}
		person.add(name);//没有相同的名字则加入person
		relation.put(name, person.indexOf(name));
	}

	public void addEdge(Person name1, Person name2) {
		int a,b;
		a = relation.get(name1);
		b = relation.get(name2);
		edge[a][b] = 1;
	}

	public int getDistance(Person name1, Person name2) {
	
		if(name1 == name2)//当出现两个相同点时，返回0
		{
			return 0;
		}
		int sum,temp,w;
		int m = relation.get(name1);
		int n = person.size();
		int cost[]=new int[n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(edge[i][j]!=edge[j][i])
					return -1;
			}
		}
		for(int i=0; i<n; i++)
		{
			cost[i] = edge[m][i];
			X[i] = false;
		} 
		X[m] = true;
		for(int i=0; i<n-1; i++)//循环n-1次，每次都能加入一个点，最后每个点都已经加入
		{
			w = 0;
			temp = 100;
			for(int j=0; j<n; j++)
			{
				if(!X[j]&&cost[j]<temp)
				{
					temp = cost[j];
					w = j;//
				}
			}
			X[w] = true;//寻找一个当前最小的cost[j]，体现了贪心算法的思想
			for(int v=0; v<n ;v++)
			{
				if(X[v]!=true)
				{
					sum = cost[w] + edge[w][v];
					if(sum<cost[v])
					{
						cost[v] = sum;
					}
				}
			}
		}
		if(cost[relation.get(name2)]==1000)//意味着两点之间根本没有路径
		{
			return -1;
		}
		else {
			return cost[relation.get(name2)];
		}
		
	}


	public static void main(String[] args) {
		 FriendshipGraph graph = new FriendshipGraph(); 
		 Person rachel = new Person("Rachel"); 
		 Person ross = new Person("ross"); 
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
		 //should print 1 
		 System.out.println(graph.getDistance(rachel, ben));  
		 //should print 2 
		 System.out.println(graph.getDistance(rachel, rachel));  
		 //should print 0 
		 System.out.println(graph.getDistance(rachel, kramer));  
		 //should print -1 
		 
	
	}

}
