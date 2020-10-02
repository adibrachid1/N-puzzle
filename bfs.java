import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	static Queue<Node> bfs = new LinkedList<Node>();

	private static boolean displayInOrder(Node root, int[][] initial, Node node) {
		if (root != null) {
			if (Arrays.deepEquals(root.tab, initial) && root != node) {
				return false;
			}
			return (displayInOrder(root.up, initial, node) && displayInOrder(root.down, initial, node)
					&& displayInOrder(root.left, initial, node) && displayInOrder(root.right, initial, node));
		}
		return true;
	}

	public static void run(Node node, int i, int j, Arbre a) {
		node.afficher(a.N);
		if (!correct(node.tab, a)) {
			if (i != 0) {
				node.up = new Node("up", node.tab, node.i, node.j, null, a.N);
				if (displayInOrder(a.racine, node.up.tab, node.up)) {
					bfs.add(node.up);
				} else {
					node.up = null;
				}
			}
			if (j != 0) {
				node.left = new Node("left", node.tab, node.i, node.j, null, a.N);
				if (displayInOrder(a.racine, node.left.tab, node.left)) {
					bfs.add(node.left);
				} else {
					node.left = null;
				}
			}

			if (j != a.N - 1) {
				node.right = new Node("right", node.tab, node.i, node.j, null, a.N);
				if (displayInOrder(a.racine, node.right.tab, node.right)) {
					bfs.add(node.right);
				} else {
					node.right = null;
				}
			}
			if (i != a.N - 1) {
				node.down = new Node("down", node.tab, node.i, node.j, null, a.N);
				if (displayInOrder(a.racine, node.down.tab, node.down)) {
					bfs.add(node.down);
				} else {
					node.down = null;
				}
			}

		} else {
			node.afficher(a.N);
			System.out.println("I solved your 8 puzzle game, I am a genius!! :) Computer Engineer #TEAMGIN ");
			System.exit(5);
			;
		}
		Node cur = bfs.poll();
		run(cur, cur.i, cur.j, a);

	}

	public static boolean correct(int[][] tab, Arbre a) {
		
		int solution[][] = a.solution;
		if (Arrays.deepEquals(tab, solution)) {
			return true;
		}
		return false;

	}

	public static void main(String[] args) {
		// Remark: not all cases are solvable
				int tab[][] = { { 4, 0, 2, 3 }, { 5, 1, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };
				int N = 4;
				int coord[] = Arbre.getCoordinates(tab, N);
				int i = coord[0];
				int j = coord[1];
				Arbre a = new Arbre(new Node("hayalla", tab, i, j, null, N), N);
				a.racine.afficher(N);
				run(a.racine, i, j, a);
	}

}
