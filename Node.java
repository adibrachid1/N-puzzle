
public class Node {
	int n;
	int tab[][];
	Node left = null;
	Node right = null;
	Node up = null;
	int heuristic = 0;// this is used for the greedy search algorithm
	Node down = null;
	Node parent = null;
	int i, j;

	Node(String a, int TAB[][], int I, int J, Node PARENT, int N) {
		n = N;
		tab = new int[n][n];
		for (int q = 0; q < N; q++) {
			for (int w = 0; w < N; w++) {
				tab[q][w] = TAB[q][w];
			}
		}
		parent = PARENT;
		i = I;
		j = J;

		switch (a) {
		case "up":
			tab[i][j] = tab[i - 1][j];
			tab[i - 1][j] = 0;
			i--;
			break;
		case "down":
			tab[i][j] = tab[i + 1][j];
			tab[i + 1][j] = 0;
			i++;
			break;
		case "right":
			tab[i][j] = tab[i][j + 1];
			tab[i][j + 1] = 0;
			j++;
			break;
		case "left":
			tab[i][j] = tab[i][j - 1];
			tab[i][j - 1] = 0;
			j--;
			break;
		default:
			//
			break;
		}

	}

	public void afficher(int N) {
		int count;
		for (int i = 0; i < N; i++) {
			count = 0;
			System.out.println("\n");
			for (int j = 0; j < N; j++) {
				if (count < N)
					System.out.print(tab[i][j] + "  ");
				count++;
			}
		}
		System.out.println("\n=================\n=================");
	}
}