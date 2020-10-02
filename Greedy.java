import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Greedy {

	static List<int[][]> explored = new ArrayList<int[][]>();//list of the already explored nodes 
	static PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {
		//priority queue from the smallest heuristic to the biggest so we can poll the smallest one.
		public int compare(Node i, Node j) {
			if ((i.heuristic > j.heuristic)) {
				return 1;
			}

			else if (i.heuristic < j.heuristic) {
				return -1;
			}

			else {
				return 0;
			}
		}
	}

	);

	public static void main(String[] args) {
		// Remark: not all cases are solvable
		int tab[][] = { { 1, 2, 5 }, { 3, 4, 8 }, { 0, 6, 7 } };
		//int tab[][] = { { 2, 0, 1 }, { 3, 4, 5 }, { 6, 7, 8 } };//unsolvable and no space in queue!
		//int tab[][] = { { 1, 2}, { 3,0} };
		//this one is unsolvable with N=2 
		int N = 3;
		int coord[] = Arbre.getCoordinates(tab, N);
		int i = coord[0];
		int j = coord[1];
		Arbre a = new Arbre(new Node("does_not_matter", tab, i, j, null, N), N);
		queue.add(a.racine);
		greedy(a.racine, i, j, a);//queue.poll is the root of the tree
	}

	public static boolean correct(int[][] tab, Arbre a) {
		int solution[][] = a.solution;
		if (Arrays.deepEquals(tab, solution)) {
			return true;
		}
		return false;

	}
	//function that checks if the explored list contains a table with same values as the current node's table values
	public static boolean contain(int[][]tab,List<int[][]> list,Arbre a){
		 
		for(int [][]tmp:list){
			if(Arrays.deepEquals(tmp,tab))return true;
		}
		return false;
	}
	public static void greedy(Node source, int i, int j, Arbre a) {
		Node node = source;//the current node
		if(!contain(node.tab,explored,a)){//if true we should pass by it
			node.afficher(a.N);
			if (correct(node.tab, a)) {//if true ,we reached the goal
				System.out.println("I solved your 8 puzzle game");
				System.exit(5);
			}
			explored.add(node.tab);//current added to the explored list and we will generate its successors.
			if (i != 0) {
				node.up = new Node("up", node.tab, node.i, node.j, node.parent, a.N);
				//add heuristic to the successor node (node.up)
				node.up.heuristic = Arbre.functionG(node.up.tab, a.N, a.solution);
				if(!contain(node.up.tab,explored,a)){
					//if not explored add it to the queue
					queue.add(node.up);
					System.out.println("if up heuristic=" + node.up.heuristic);
				} else
					//if its explored, we don't need to pass by it so we delete it
					node.up = null;
			}
			if (j != 0) {
				node.left = new Node("left", node.tab, node.i, node.j, null, a.N);
				node.left.heuristic = Arbre.functionG(node.left.tab, a.N, a.solution);
				if(!contain(node.left.tab,explored,a)){
					queue.add(node.left);
					System.out.println("if left heuristic=" + node.left.heuristic);
				} else
					node.left = null;

			}
			if (j != a.N - 1) {
				node.right = new Node("right", node.tab, node.i, node.j, null, a.N);
				node.right.heuristic = Arbre.functionG(node.right.tab, a.N, a.solution);
				if(!contain(node.right.tab,explored,a)){
					queue.add(node.right);
					System.out.println("if right heuristic=" + node.right.heuristic);
				} else
					node.right = null;
			}

			if (i != a.N - 1) {
				node.down = new Node("down", node.tab, node.i, node.j, node, a.N);
				node.down.heuristic = Arbre.functionG(node.down.tab, a.N, a.solution);
				if(!contain(node.down.tab,explored,a)){
					queue.add(node.down);
					System.out.println("if down heuristic=" + node.down.heuristic);
				} else
					node.down = null;
			}
		}
		if(queue.isEmpty()){System.out.println("Unsolvable");System.exit(5);}
		Node n = queue.poll();
		//we poll the unexplored node with the smallest heuristic and we do the recursive function
		greedy(n, n.i, n.j, a);
	}

}
